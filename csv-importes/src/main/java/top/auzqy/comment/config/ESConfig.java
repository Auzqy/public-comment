package top.auzqy.comment.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * description:
 * createTime: 2019-12-30 16:37
 * @author au
 */
@Configuration
public class ESConfig {


    /**
     * TransportClient 已经不推荐了，这里使用只是看一下
     * 推荐和 kibana 一样，使用 http 的方式进行访问
     */
    @Bean
    public TransportClient getClient(){
        TransportClient transportClient = null;
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name","dianping").build();
            transportClient = new PreBuiltTransportClient(settings);
            TransportAddress firstAddress = new TransportAddress(InetAddress.getByName("127.0.0.1"),Integer.parseInt("9300"));
            TransportAddress secondAddress = new TransportAddress(InetAddress.getByName("127.0.0.1"),Integer.parseInt("9301"));
            TransportAddress thirdAddress = new TransportAddress(InetAddress.getByName("127.0.0.1"),Integer.parseInt("9302"));
            transportClient.addTransportAddress(firstAddress);
            transportClient.addTransportAddress(secondAddress);
            transportClient.addTransportAddress(thirdAddress);

        }catch (Exception e){
            e.printStackTrace();

        }
        return transportClient;
    }
}
