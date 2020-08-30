package com.spring.course.springit;

import com.spring.course.springit.config.SpringitProperties;
import com.spring.course.springit.domain.Comment;
import com.spring.course.springit.domain.Link;
import com.spring.course.springit.repository.CommentRepository;
import com.spring.course.springit.repository.LinkRepository;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication (scanBasePackages = {"com.spring", "com.github.timpeeters.boot.shutdown"})
@EnableConfigurationProperties(SpringitProperties.class)
public class SpringitApplication {

//	@Autowired
//	private SpringitProperties springitProperties;

	public static void main(String[] args) {
		SpringApplication.run(SpringitApplication.class, args);
	}

//	@Bean
	CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository) {
		return args -> {
//			System.out.println("Welcome Message: " + springitProperties.getWelcomeMsg());
			Link link = new Link("Getting Started with Spring Boot 2","https://therealdanvega.com/spring-boot-2");
			linkRepository.save(link);

			Comment comment = new Comment("This Spring Boot 2 link is awesome!", link);
			commentRepository.save(comment);
			link.addComment(comment);
		};
	}

	@Bean
	PrettyTime prettyTime() {
		return new PrettyTime();
	}

}
