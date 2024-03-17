package com.main.dbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DbDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbDemoApplication.class, args);
	}

	@Bean
	public LoggingInterceptor loggingInterceptor() {
		return new LoggingInterceptor();
	}

	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(loggingInterceptor());
			}
		};
	}

}
