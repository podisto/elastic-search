package com.simba.elasticsearch.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:ElHadjiOmar.DIONE@orange-sonatel.com">podisto</a>
 * @since 2019-09-15
 */
@Configuration
@Slf4j
public class ElasticSearchConfiguration {

    private final ApplicationProperties applicationProperties;

    public ElasticSearchConfiguration(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient
                        .builder(new HttpHost(applicationProperties.getEsHost(), applicationProperties.getEsPort(), applicationProperties.getEsProtocol()))
                        .setRequestConfigCallback(config -> config
                                .setConnectTimeout(applicationProperties.getEsConnectTimeout())
                                .setConnectionRequestTimeout(applicationProperties.getEsConnectTimeout())
                                .setSocketTimeout(applicationProperties.getEsConnectTimeout())
                        ));
        log.info("--- connection ES success ---");
        return client;
    }
}
