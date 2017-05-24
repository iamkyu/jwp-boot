package io.iamkyu;

import io.iamkyu.common.BasicAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloBootApplication.class, args);
	}

	@Bean
	public BasicAuthInterceptor basicAuthInterceptor() {
		return new BasicAuthInterceptor();
	}
}
