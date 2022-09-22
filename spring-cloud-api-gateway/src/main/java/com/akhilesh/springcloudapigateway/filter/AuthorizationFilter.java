package com.akhilesh.springcloudapigateway.filter;

import io.jsonwebtoken.Jwts;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.MyConfig> {

    /**
     * All the configurable properties go here
     */
    public static class MyConfig {

    }

    @Override
    public GatewayFilter apply(MyConfig config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return throwError(exchange, HttpStatus.BAD_REQUEST);
            }
            ;

            if (!checkJwtValid(request.getHeaders()
                    .get(HttpHeaders.AUTHORIZATION)
                    .get(0).replace("Bearer ", ""))) {
                return throwError(exchange, HttpStatus.BAD_GATEWAY);
            }
            return chain.filter(exchange);
        };
    }

    private Mono<Void> throwError(ServerWebExchange webExchange, HttpStatus status) {
        ServerHttpResponse response = webExchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }

    private boolean checkJwtValid(String jwt) {
        String subject = Jwts.parser()
                .setSigningKey("somethingforjwttokentosignthistokenwith")
                .parseClaimsJws(jwt)
                .getBody().getSubject();
        if (subject.isEmpty() || subject == null) {
            return false;
        }
        return true;
    }
}
