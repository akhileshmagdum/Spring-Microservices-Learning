package com.akhilesh.springcloudapigateway.config;

import com.akhilesh.springcloudapigateway.filter.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRoutes {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service",
                        route -> route
                                .path("/something")
                                .and()
                                /*
                                 *Checking bearer token in header
                                 */
                                .header("Authorization","Bearer (.*)")
                                .filters(f-> f.filter(authorizationFilter.apply(new AuthorizationFilter.MyConfig())))
                                /*
                                 * Replaces the url matched with the regex with the replacement string
                                 */
//                                .filters(gatewayFilterSpec ->
//                                        gatewayFilterSpec.rewritePath("/regex", "/something"))
                                .uri("lb://user-app"))
                .build();
    }
    @Autowired
    private AuthorizationFilter authorizationFilter;
}
