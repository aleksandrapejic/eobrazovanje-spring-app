package tseo.project.eobrazovanje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import tseo.project.eobrazovanje.notificationBot.BeanUtil;
import tseo.project.eobrazovanje.notificationBot.NotificationBot;
import tseo.project.eobrazovanje.service.StudentService;

@SpringBootApplication
@EnableAutoConfiguration
@EnableResourceServer
public class EObrazovanjeApplication {


	
	public static void main(String[] args) {
		
		ApiContextInitializer.init();
		
		SpringApplication.run(EObrazovanjeApplication.class, args);
		
		
		
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
	        try {
	            telegramBotsApi.registerBot(new NotificationBot());
	            
	        } catch (TelegramApiException e) {
	           
	        }
	}
	
	   
  
	
}
