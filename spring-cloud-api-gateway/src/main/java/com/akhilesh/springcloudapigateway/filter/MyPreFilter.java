package com.akhilesh.springcloudapigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Pre-filter - Runs before the request is sent to the microservice
 */
//@Component
public class MyPreFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("\n\nThis is a filter\n" + exchange.getRequest().getPath().toString() + "\n");
        return chain.filter(exchange);
    }
}
