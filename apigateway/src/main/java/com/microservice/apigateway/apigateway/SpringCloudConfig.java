package com.microservice.apigateway.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {
	@Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/command/**")
                        .uri("lb://COMMAND-SERVICE"))

                .route(r -> r.path("/query/**")
                        .uri("lb://QUERY-SERVICE"))
                .build();
    }
}
