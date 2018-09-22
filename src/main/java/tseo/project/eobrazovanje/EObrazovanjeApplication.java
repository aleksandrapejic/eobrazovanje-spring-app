package tseo.project.eobrazovanje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.telegram.telegrambots.ApiContextInitializer;

import tseo.project.eobrazovanje.util.BeanUtil;

@SpringBootApplication
@EnableAutoConfiguration
@EnableResourceServer
public class EObrazovanjeApplication {


	
	public static void main(String[] args) {
		
		ApiContextInitializer.init();
		
		SpringApplication.run(EObrazovanjeApplication.class, args);
		
		BeanUtil.makeABot();
		
		
	}
	
	   
  
	
}
