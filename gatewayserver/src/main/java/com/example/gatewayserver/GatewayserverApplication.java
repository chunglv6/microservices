package com.example.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(
                        p -> p.path("/wps/mypoc")
                                .filters(f -> f.rewritePath("wps/mypoc", "/order/order-management/14859014")
                                        .addResponseHeader("X-Tesponse-Time", LocalDateTime.now().toString())
                                )
                                .uri("https://xentry-dev3.mercedes-benz.com")
                )
//                .route(
//                        p -> p
//                                .path("/chunglv6/loans/**")
//                                .filters(
//                                        f -> f
//                                                .rewritePath("/chunglv6/loans/(?<segment>.*)", "/${segment}")
//                                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
//                                )
//                                .uri("lb://LOANS")
//                )
//                .route(
//                        p -> p
//                                .path("/chunglv6/cards/**")
//                                .filters(
//
//                                        f -> f.rewritePath("/chunglv6/cards/(?<segment>.*)", "/${segment}")
//                                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
//                                )
//                                .uri("lb://CARDS")
//                )
                .build();
    }
}
