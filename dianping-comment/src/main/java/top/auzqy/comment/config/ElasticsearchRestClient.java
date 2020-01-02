package top.auzqy.comment.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description:  es 客户端的配置类
 * createTime: 2020-01-02 21:37
 * @author au
 */
@Configuration
public class ElasticsearchRestClient {

    @Value("${elasticsearch.ip}")
    String esIp;

    @Value("${elasticsearch.port}")
    Integer esPort;

    @Bean(name="highLevelClient")
    public RestHighLevelClient highLevelClient(){
        HttpHost httpHost = new HttpHost(esIp,esPort,"http");
        // 这里我们可以定义数组，集群中多个es地址都添加进去，以提高可用性，这里只填写了一个
//        return new RestHighLevelClient(RestClient.builder(new HttpHost[]{httpHost}))
        return new RestHighLevelClient(RestClient.builder(httpHost));
    }

}
