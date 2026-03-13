package oi.github.api.aplataformaEnsino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AplataformaEnsinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplataformaEnsinoApplication.class, args);
	}

}
