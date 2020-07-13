package com.smalik.resilience.frontend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Profile("frontend")
@Configuration
public class BackendConfig
{
    @Bean
    public RestTemplate restTemplate(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${backend.baseUrl}") String baseUrl,
            @Value("${backend.connectionTimeoutMillis}") int connectionTimeoutMillis,
            @Value("${backend.readTimeoutMillis:1000}") int readTimeoutMillis)
    {
        return restTemplateBuilder
           .rootUri(baseUrl)
           .setConnectTimeout(connectionTimeoutMillis)
           .setReadTimeout(readTimeoutMillis)
           .build();
    }
}