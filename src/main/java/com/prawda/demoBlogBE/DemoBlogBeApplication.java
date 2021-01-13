package com.prawda.demoBlogBE;

import com.prawda.demoBlogBE.domain.User;
import com.prawda.demoBlogBE.domain.UserDBO;
import com.prawda.demoBlogBE.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class DemoBlogBeApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoBlogBeApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository) {

		User admin = new User(
				null,
				"AdminF",
				"AdminS",
				"Admin",
				"Admin",
				"Admin@this.com",
				true);

		UserDBO adminDBO = admin.toDBO();

		return (args) -> {
			userRepository.save(adminDBO).subscribe();
		};
	}
}
