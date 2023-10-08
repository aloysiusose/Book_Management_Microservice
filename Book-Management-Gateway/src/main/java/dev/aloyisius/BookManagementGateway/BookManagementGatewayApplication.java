package dev.aloyisius.BookManagementGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookManagementGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookManagementGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder rlb){
		return rlb.routes()
				.route("book-details", r -> r.path("/book-details/{isbn}")
						.filters(GatewayFilterSpec::tokenRelay)
						.uri("http://localhost:8082"))
				.build();
	}

}
