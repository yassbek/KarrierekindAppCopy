package com.karrierekind.karrierekindapp;

import com.karrierekind.karrierekindapp.config.AppCommands;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.command.annotation.EnableCommand;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableCommand(AppCommands.class)
public class KarrierekindappApplication {

	public static void main(String[] args) {
		SpringApplication.run(KarrierekindappApplication.class, args);
	}


	@Configuration
	public class WebConfig implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
					.allowedOrigins("http://localhost:3000")
					.allowedMethods("*")
					.allowedHeaders("*")
					.allowCredentials(true);
		}
	}
}


