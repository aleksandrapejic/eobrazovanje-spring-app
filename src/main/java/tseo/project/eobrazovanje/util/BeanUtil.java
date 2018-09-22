package tseo.project.eobrazovanje.util;

import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;

import tseo.project.eobrazovanje.entity.NotificationBot;
import tseo.project.eobrazovanje.entity.PredispitneObaveze;
import tseo.project.eobrazovanje.repository.ChatBotIdentitetRepository;
import tseo.project.eobrazovanje.repository.IspitRepository;
import tseo.project.eobrazovanje.repository.PredispitneObavezeRepository;
import tseo.project.eobrazovanje.repository.PredispitneObavezeSablonRepository;
import tseo.project.eobrazovanje.repository.PrijavaRepository;
import tseo.project.eobrazovanje.repository.StudentRepository;
import tseo.project.eobrazovanje.service.impl.BotCommandsService;
import tseo.project.eobrazovanje.service.impl.ChatBotIdentitetService;
import tseo.project.eobrazovanje.service.impl.IspitService;
import tseo.project.eobrazovanje.service.impl.PredispitneObavezeSablonService;
import tseo.project.eobrazovanje.service.impl.PredmetService;
import tseo.project.eobrazovanje.service.impl.PrijavaService;
import tseo.project.eobrazovanje.service.impl.StudentService;

@Component
public class BeanUtil implements ApplicationContextAware {
	
	
    private static ApplicationContext context;
    
    public static StudentService getStudentService() {
        return (StudentService)context.getBean("studentService");
    }
    
    public static IspitService getIspitService() {
        return (IspitService)context.getBean("ispitService");
    }
    
    
    public static BotCommandsService getBotCommandsService() {
        return (BotCommandsService)context.getBean("botCommandsService");
    }
    
    
    public static StudentRepository getStudentRepo() {
        return (StudentRepository)context.getBean("studentRepository");
    }
    
    
    public static IspitRepository getIspitRepo() {
        return (IspitRepository)context.getBean("ispitRepository");
    }
    
    public static PredispitneObaveze getPredispitneService() {
        return (PredispitneObaveze)context.getBean("predispitneService");
    }
    
    
    public static PredispitneObavezeRepository getPredispitneRepo() {
        return (PredispitneObavezeRepository)context.getBean("predispitneRepository");
    }
    
    
    public static PrijavaService getPrijavaService() {
        return (PrijavaService)context.getBean("prijavaService");
    }
    
    
    public static PrijavaRepository getPrijavaRepo() {
        return (PrijavaRepository)context.getBean("prijavaRepository");
    }

    
    public static ChatBotIdentitetService getChatIdentitetService() {
        return (ChatBotIdentitetService)context.getBean("chatBotIdentitetService");
    }
    
    
    public static ChatBotIdentitetRepository getChatIdentitetRepo() {
        return (ChatBotIdentitetRepository)context.getBean("chatBotIdentitetRepo");
    }
    

    public static PredispitneObavezeSablonService getSablonService() {
        return (PredispitneObavezeSablonService)context.getBean("sablonService");
    }
    
    
    public static PredispitneObavezeSablonRepository getSabonRepo() {
        return (PredispitneObavezeSablonRepository)context.getBean("sablonRepo");
    }
    
    public static BotSession makeABot(){
    	 TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
    	    try {
    	    	System.out.println("pravim bota iz utila");
    	    	NotificationBot bot = new NotificationBot();
    	    	return  telegramBotsApi.registerBot(bot);       	    	
    	    } catch (TelegramApiException e) {
    	    	System.out.println("nesto je pukloo bato");
    	       return null;
    	    }
    }
    
    
    public static NotificationBot getBot() {
        return (NotificationBot)context.getBean("notificationBot");
    }
    

     
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
         
        // store ApplicationContext reference to access required beans later on
        BeanUtil.context = context;
    }
    
    
    @PreDestroy
    public void resetStatics() {
                  context=null;
}

	public static PredmetService getPredmetService() {
		 return (PredmetService)context.getBean("predmetService");
	}
}