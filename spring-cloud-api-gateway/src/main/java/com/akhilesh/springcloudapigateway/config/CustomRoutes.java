package com.akhilesh.springcloudapigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class CustomRoutes {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes().route("user-service", route -> route
                        .path("/**")
                        /*
                         * Replaces the url matched with the regex with the replacement string
                         */
                        .filters(gatewayFilterSpec ->
                                gatewayFilterSpec.rewritePath("/regex","/something"))
                        .uri("lb://user-app"))
                .build();
    }
}
