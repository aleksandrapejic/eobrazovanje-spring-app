package tseo.project.eobrazovanje.notificationBot;

import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import tseo.project.eobrazovanje.entity.PredispitneObaveze;
import tseo.project.eobrazovanje.repository.IspitRepository;
import tseo.project.eobrazovanje.repository.PredispitneObavezeRepository;
import tseo.project.eobrazovanje.repository.PrijavaRepository;
import tseo.project.eobrazovanje.repository.StudentRepository;
import tseo.project.eobrazovanje.service.IspitService;
import tseo.project.eobrazovanje.service.PrijavaService;
import tseo.project.eobrazovanje.service.StudentService;

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


     
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
         
        // store ApplicationContext reference to access required beans later on
        BeanUtil.context = context;
    }
    
    
    @PreDestroy
    public void resetStatics() {
                  context=null;
}
}