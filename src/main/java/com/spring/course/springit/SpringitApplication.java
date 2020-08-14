package com.spring.course.springit;

import com.spring.course.springit.config.SpringitProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(SpringitProperties.class)
@EnableJpaAuditing
public class SpringitApplication {

//	@Autowired
//	private SpringitProperties springitProperties;

	public static void main(String[] args) {
		SpringApplication.run(SpringitApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner() {
//		return args -> {
//			System.out.println("Welcome Message: " + springitProperties.getWelcomeMsg());
//		};
//	}

}
