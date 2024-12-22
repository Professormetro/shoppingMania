package org.chernov.apigateway.config;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter implements GatewayFilterFactory<GatewayFilter> {

    @Override
    public GatewayFilter apply(GatewayFilterChain chain) {

    }


}
