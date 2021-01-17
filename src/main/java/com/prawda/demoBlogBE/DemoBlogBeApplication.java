package com.prawda.demoBlogBE;

import com.prawda.demoBlogBE.converters.CSVConverter;
import com.prawda.demoBlogBE.domain.post.PostDBO;
import com.prawda.demoBlogBE.helpers.CSVData;
import com.prawda.demoBlogBE.repositories.PostRepository;
import com.prawda.demoBlogBE.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Import(DBConfig.class)
@SpringBootApplication
public class DemoBlogBeApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoBlogBeApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository, PostRepository postRepository) throws IOException {

		CSVConverter csvConverter = new CSVConverter();
		CSVData csvData = csvConverter.parse();

		return (args) -> {
			csvData.getUserList().forEach(user -> {
				userRepository.save(user.toDBO()).subscribe();
			});

			Flux.fromStream(csvData.getPostList().stream())
					.flatMap(post ->
							userRepository
									.findByName(post.getUser().getName())
									.map(user -> new PostDBO(null, post.getContents(), user.getId()))
					)
					.flatMap(postRepository::save)
					.subscribe();

		};
	}
}
