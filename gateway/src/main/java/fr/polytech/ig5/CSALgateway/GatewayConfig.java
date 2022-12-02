package fr.polytech.ig5.CSALgateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("offer-service", r -> r.path("/offer/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://offers:8000"))
                .route("offer-service", r -> r.path("/offers/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://offers:8000"))
                .route("offer-service", r -> r.path("/advantages/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://offers:8000"))
                .route("offer-service", r -> r.path("/keywords/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://offers:8000"))


                .route("affect-service", r -> r.path("/affect/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://affect:7575"))
                .route("affect-service", r -> r.path("/nbaffected/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://affect:7575"))
                .route("affect-service", r -> r.path("/nbjobs/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://affect:7575"))


                .route("user-service", r -> r.path("/user/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://users:8081"))
                .route("user-service", r -> r.path("/resume/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://users:8081"))




                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://auth:8082"))
                .build();
    }

}