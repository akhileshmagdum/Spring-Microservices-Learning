package com.akhilesh.springcloudapigateway.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalFiltersConfiguration {

    @Bean
    public GlobalFilter myPrePostFilterViaMethod() {
        return ((exchange, chain) -> {
            System.out.println("\n\nThis is a pre filter from method\n\n");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("\n\nThis is a post filter in a method\n\n");
            }));
        });
    }
}
