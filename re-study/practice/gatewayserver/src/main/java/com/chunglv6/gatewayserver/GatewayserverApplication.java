package com.chunglv6.gatewayserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class GatewayserverApplication {
    @Autowired
    private TokenRelayGatewayFilterFactory filterFactory;

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator routing(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/chunglv6/accounts/**")
                        .filters(f -> f.filter(filterFactory.apply())
                                .rewritePath("/chunglv6/accounts/(?<segment>.*)", "/${segment}")
                                .removeRequestHeader("Cookie")
                        )
                        .uri("lb://accounts")

                )
                .route(p -> p.path("/chunglv6/loans/**")
                        .filters(f -> f.filter(filterFactory.apply())
                                .rewritePath("/chunglv6/loans/(?<segment>.*)", "/${segment}")
                                .removeRequestHeader("Cookie")

                        )
                        .uri("lb://loans")
                )
                .route(p -> p.path("/chunglv6/cards/**")
                        .filters(f -> f.filter(filterFactory.apply())
                                .rewritePath("/chunglv6/cards/(?<segment>.*)", "/${segment}")
                                .removeRequestHeader("Cookie")
                        )
                        .uri("lb://cards")

                )

                .build();
    }
}
