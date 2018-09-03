package tseo.project.eobrazovanje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableAutoConfiguration
@EnableResourceServer
public class EObrazovanjeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EObrazovanjeApplication.class, args);
	}
}
