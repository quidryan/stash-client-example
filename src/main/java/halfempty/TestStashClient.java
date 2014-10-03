package halfempty;

import com.atlassian.stash.rest.client.api.StashClient;
import com.atlassian.stash.rest.client.api.entity.Page;
import com.atlassian.stash.rest.client.api.entity.Project;
import com.atlassian.stash.rest.client.httpclient.HttpClientConfig;
import com.atlassian.stash.rest.client.httpclient.HttpClientStashClientFactory;
import com.atlassian.stash.rest.client.httpclient.HttpClientStashClientFactoryImpl;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class TestStashClient {
    public static void main(String[] args) throws Exception {
        URL baseurl = new URL("https://stash.corp.netflix.com");
        HttpClientConfig clientConfig = new HttpClientConfig(baseurl, "bobthebuilder", "AllYourBuilds");

        HttpClientStashClientFactory factory = new HttpClientStashClientFactoryImpl();
        StashClient client = factory.getStashClient(clientConfig);

        Map<String, String> props = client.getStashApplicationProperties();

        for(Map.Entry<String, String> entry: props.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        Page<Project> projectPage = client.getAccessibleProjects(0, 10);
        do {
            // Print it out
            List<Project> projects = projectPage.getValues();
            for(Project project: projects) {
                System.out.println(project.getKey() + ":" + project.getName() + " - " + project.getDescription());
            }

            // Get next page
            if (projectPage != null) {
                projectPage = client.getAccessibleProjects(projectPage.getNextPageStart().longValue(), 10);
            }
        } while(projectPage != null && !projectPage.isLastPage());
    }
}
