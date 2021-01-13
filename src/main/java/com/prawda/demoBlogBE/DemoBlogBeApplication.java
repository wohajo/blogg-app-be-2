package com.prawda.demoBlogBE;

import com.prawda.demoBlogBE.converters.CSVConverter;
import com.prawda.demoBlogBE.domain.User;
import com.prawda.demoBlogBE.domain.UserDBO;
import com.prawda.demoBlogBE.helpers.CSVData;
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
	public CommandLineRunner demo(UserRepository userRepository) throws IOException {

		CSVConverter csvConverter = new CSVConverter();
		CSVData csvData = csvConverter.parse();

		return (args) -> {
			csvData.getUserList().forEach(user -> {
				userRepository.save(user.toDBO()).subscribe();
			});
		};
	}
}
