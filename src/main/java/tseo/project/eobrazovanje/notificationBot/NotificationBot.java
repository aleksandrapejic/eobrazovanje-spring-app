package tseo.project.eobrazovanje.notificationBot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import tseo.project.eobrazovanje.entity.ChatBotIdentitet;
import tseo.project.eobrazovanje.entity.Prijava;
import tseo.project.eobrazovanje.entity.Student;

@Component
public class NotificationBot extends TelegramLongPollingBot  {
	
	public static final String POLOZENI_ISPITI = "Polozeni ispiti";
	public static final String PRIJAVLJENI_ISPITI = "Prijavljeni ispiti";
	public static final String ISPITI_ZA_PRIJAVU = "Ispiti za prijavu";
	public static final String STANJE_RACUNA = "Stanje na racunu";
	public static final String START = "/start";
	public static final String BROJ_TELEFONA = "Posalji svoj broj telefona";
	public static final String MENI = "meni";
	
	
	
	private BotCommandsService getBotCommandsService() {
        return BeanUtil.getBotCommandsService();
    }
		

	 
	@Override
	public String getBotUsername() {
		
		return "eobrazovanje_bot";
	}

	@Override
	public void onUpdateReceived(Update update) {
		
		
		 //check if the update has a message
        if(update.hasMessage()){
        				Long chatid = update.getMessage().getChatId();
        				System.out.println(chatid);
                       	String messageText = update.getMessage().getText();
                       	if(messageText == null && update.getMessage().getContact() != null ){
                       			messageText = BROJ_TELEFONA;
                       			System.out.println(messageText);
                       		}
                       	
                   
        				if (messageText.equals(START)) {
        					 	  
        						
        						SendMessage sendMessage = getBotCommandsService().sendMessageStart(update);        					    

        					     try {
        					         execute(sendMessage);
        					     } catch (TelegramApiException e) {
        					         e.printStackTrace();
        					     }


        				}else if(messageText.equals(BROJ_TELEFONA)){
        					 
        						SendMessage sendMessage = getBotCommandsService().sendMessagePotvrdaIMeni(update);        					    
        					
			        			  try {
			        			      execute(sendMessage); // Sending our message object to user
			        			        
			        			  } catch (TelegramApiException e) {
			        			        e.printStackTrace();
			        			  }
		        			    
        					 
		        			    
        				}else if(messageText.equals(ISPITI_ZA_PRIJAVU)){
        					 System.out.println(chatid);
        					 SendMessage sendMessage = getBotCommandsService().sendMessageIspitiZaPrijavu(update, chatid);        					    
        					
		        			 try {
		        			      execute(sendMessage); // Sending our message object to user
		        			        
		        			  } catch (TelegramApiException e) {
		        			        e.printStackTrace();
		        			  }
	        			    
        				}else if(messageText.equals(POLOZENI_ISPITI)){
        					System.out.println(chatid);
        					SendMessage sendMessage = getBotCommandsService().sendMessagePolozeniIspiti(update, chatid);        					    
         					
	 		        		try {
	 		        			      execute(sendMessage); // Sending our message object to user
	 		        			        
	 		        			  } catch (TelegramApiException e) {
	 		        			        e.printStackTrace();
	 		        			  }
        				 
	 		        		
	 		        		
	 		        		
	 		        		
        				}else if(messageText.equals(PRIJAVLJENI_ISPITI)){
        					System.out.println(chatid);
        					SendMessage sendMessage = getBotCommandsService().sendMessagePrijavljeniIspiti(update, chatid);        					    
         					
	 		        		try {
	 		        			      execute(sendMessage); // Sending our message object to user
	 		        			        
	 		        			  } catch (TelegramApiException e) {
	 		        			        e.printStackTrace();
	 		        			  }
        				 
        				}
        				else if(messageText.equals(STANJE_RACUNA)){
        					System.out.println(chatid);
        					SendMessage sendMessage = getBotCommandsService().sendMessageStanjeRacuna(update, chatid);        					    
         					
	 		        		try {
	 		        			      execute(sendMessage); // Sending our message object to user
	 		        			        
	 		        			  } catch (TelegramApiException e) {
	 		        			        e.printStackTrace();
	 		        			  }
        				 }
        				 
                
        				 
        		}
        				 
        }
        
	
	@Override
	public String getBotToken() {
		
		return "647577471:AAHvJjfRQRH3xhM8k2tAr_Z6Xm_LijMZP6c";
	}
	

	
	public void posaljiObavestenje(Prijava prijava, String student){
		
		int ocena = Math.round(prijava.getOsvojeniBodoviIspit()/10);
		ChatBotIdentitet chatBotIdentitet = getBotCommandsService().getChatIdentitetService().findOneByPhoneNumber(student);
		if(chatBotIdentitet.isSubscribedTelegram()){			
			
			 SendMessage message = new SendMessage() 
	                    .setChatId(chatBotIdentitet.getChatId())
	                    .setText("OBAVESTENJE O POLOZENOM ISPITU:" + prijava.getIspit().getPredmet().getNaziv() + ", bodovi: " + prijava.getOsvojeniBodoviIspit() + ", ocena: " + ocena);
	    
	    
	    try {
		      execute(message); // Sending our message object to user
		        
		  } catch (TelegramApiException e) {
		        e.printStackTrace();
		  }
	    
		}else{
			
			System.out.println("student nije pretplacen na obavestenja o polozenim ispitima");
		}
	   
	}






}
