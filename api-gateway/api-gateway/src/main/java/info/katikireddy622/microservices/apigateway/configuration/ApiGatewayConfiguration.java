package info.katikireddy622.microservices.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    //Custom Filters
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){

        return builder.routes().route(p->p.path("/get")
                .filters(f->f.addRequestHeader("myHeader","myURI").addRequestParameter("Parm","MyValue"))
                .uri("http://httpbin.org:80"))
                .route(p->p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange-service"))
                .route(p->p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion-service"))
                .route(p->p.path("/currency-conversion-feign/**")
                        .filters(f->f.rewritePath("/currency-conversion-feign/(?<segment>.*)","/currency-conversion/feign/${segment}"))
                        .uri("lb://currency-conversion-service"))
                .build();
    }

}
