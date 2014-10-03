package halfempty;

import com.atlassian.stash.rest.client.api.StashClient;
import com.atlassian.stash.rest.client.httpclient.HttpClientConfig;
import com.atlassian.stash.rest.client.httpclient.HttpClientStashClientFactory;
import com.atlassian.stash.rest.client.httpclient.HttpClientStashClientFactoryImpl;
import java.net.URL;
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

        client
    }
}
