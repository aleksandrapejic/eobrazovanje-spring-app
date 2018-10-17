package tseo.project.eobrazovanje.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;


import tseo.project.eobrazovanje.entity.ChatBotIdentitet;
import tseo.project.eobrazovanje.entity.Ispit;
import tseo.project.eobrazovanje.entity.Predmet;
import tseo.project.eobrazovanje.entity.Prijava;
import tseo.project.eobrazovanje.entity.Student;
import tseo.project.eobrazovanje.service.BotCommandsServiceInterface;
import tseo.project.eobrazovanje.util.BeanUtil;

@Service
public class BotCommandsService  implements BotCommandsServiceInterface{

	public static final String POLOŽENI_ISPITI = "Položeni ispiti";
	public static final String PRIJAVLJENI_ISPITI = "Prijavljeni ispiti";
	public static final String ISPITI_ZA_PRIJAVU = "Ispiti za prijavu";
	public static final String STANJE_RAČUNA = "Stanje na računu";
	public static final String START = "/start";
	public static final String BROJ_TELEFONA = "Pošalji svoj broj telefona";
	public static final String MENI = "meni";
	
	
	StudentService getStudentService() {
        return BeanUtil.getStudentService();
    }
	
	public PrijavaService getPrijavaService() {
        return BeanUtil.getPrijavaService();
    }
	
	public IspitService getIspitService() {
        return BeanUtil.getIspitService();
    }
	
	public PredmetService getPredmetService() {
        return BeanUtil.getPredmetService();
    }
	
	
	public ChatBotIdentitetService getChatIdentitetService() {
        return BeanUtil.getChatIdentitetService();
    }
	
	
	/**
	 * Returns an ReplyKeyboardMarkup object with menu options.
	 *
	 */
	@Override
	public ReplyKeyboardMarkup getKeyboardMenuOptions(){
	
	    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
	    List<KeyboardRow> keyboard = new ArrayList<>();
	    KeyboardRow row = new KeyboardRow();
	    KeyboardRow row2 = new KeyboardRow();
	    row.add(ISPITI_ZA_PRIJAVU);
	    row.add(POLOŽENI_ISPITI);
	    keyboard.add(row);
	    row2.add(STANJE_RAČUNA);
	    row2.add(PRIJAVLJENI_ISPITI);
	    keyboard.add(row2);
	    keyboardMarkup.setKeyboard(keyboard);
	   return keyboardMarkup;
		
	}
	
	/**
	 * Returns a ReplyKeyboardMarkup object with menu options.
	 *
	 */
	@Override
	public ReplyKeyboardMarkup getKeyboardForPhoneNumber(){
		
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
	    replyKeyboardMarkup.setSelective(true);
	    replyKeyboardMarkup.setResizeKeyboard(true);
	    replyKeyboardMarkup.setOneTimeKeyboard(true);
	    List<KeyboardRow> keyboard = new ArrayList<>();
	    KeyboardRow keyboardFirstRow = new KeyboardRow();
	    KeyboardButton keyboardButton = new KeyboardButton();
	    keyboardButton.setText(BROJ_TELEFONA).setRequestContact(true);
	    keyboardFirstRow.add(keyboardButton);
	    keyboard.add(keyboardFirstRow);
	    replyKeyboardMarkup.setKeyboard(keyboard);

	    
	    return replyKeyboardMarkup;
	}
	
	/**
	 * Save and return a ChatBotIdentitet object.
	 *
	 */
	@Override
	public ChatBotIdentitet saveChatIdentitet(Update update, Student student){
		return getChatIdentitetService().save(update, student);
	}
	


	/**
	 * Checks if there is a student with shared phone number from Update object, if the number is correct but
	 * chatbotidentitet doesn't exist, makes a new chatbotIdentitet.
	 * Returns a menu message or a failed identification message.
	 *
	 */
	@Override
	public SendMessage sendMessagePotvrdaIMeni(Update update){
		
		  Contact contact = update.getMessage().getContact();
		  Student student = getStudentService().findOneByBrojTelefona(contact.getPhoneNumber());
		 
		  if(student != null){
			  if(getChatIdentitetService().findOneByUser(student) != null){
			
					Long chatId = update.getMessage().getChatId();                             
				    String firstName = update.getMessage().getContact().getFirstName();		 
				    SendMessage message = new SendMessage() 
		                       .setChatId(chatId)
				               .setText("Dobrodošao/la nazad  " + firstName +  ", ovo je tvoj meni.");			  
				    message.setReplyMarkup(getKeyboardMenuOptions());  
				    return message;			  
			  }
			  else{
				  ChatBotIdentitet chatBotIdentitet = saveChatIdentitet(update, student);
				  student.setChatbotIdentitet(chatBotIdentitet);
				  BeanUtil.getStudentService().save(student);
				  Long chatId = update.getMessage().getChatId(); 
				  System.out.println(chatBotIdentitet );
				    String firstName = update.getMessage().getContact().getFirstName();		 
				    SendMessage message = new SendMessage() 
		                       .setChatId(chatId)
				               .setText("Dobrodošao/la na chat bot E-Obrazovanja " + firstName +  ", ovo je tvoj meni.");			  
				    message.setReplyMarkup(getKeyboardMenuOptions());  
				    return message;	
			  }			  	   			    
		  }else{
			  return sendMessageNonExistentNumber(update);				
		  }
		
	  }

	

	/**
	 * Checks if chatbotIdentitet exists in the database by chatId.
	 * Returns a message with polozeni ispiti or a failed identification message.
	 *
	 */
	@Override
	public SendMessage sendMessagePolozeniIspiti(Update update, Long chatId){
		
		ChatBotIdentitet chatBotIdentitet = getChatIdentitetService().findOneByChatId(chatId);
		
		if(chatBotIdentitet != null){
			
			Student student = getStudentService().findOne((chatBotIdentitet.getUser().getId()));
			Set<Predmet> polozeniPredmeti = getPredmetService().getPolozeniPredmeti(student);
			String predmeti = "";
		
			for(Predmet predmet: polozeniPredmeti){
				
				predmeti += "| " + predmet.toString() + " | " + "\n";
			}			
			if(predmeti.equals("")){
			    SendMessage message = new SendMessage() 
			                    .setChatId(update.getMessage().getChatId())
			                    .setText("Trenutno nemate položenih ispita.");
			    message.setReplyMarkup(getKeyboardMenuOptions());    
			    return message; 
			}else{
				SendMessage message = new SendMessage() 
	                    .setChatId(update.getMessage().getChatId())
	                    .setText("POLOŽENI ISPITI: " + predmeti);
				message.setReplyMarkup(getKeyboardMenuOptions());    
				return message; 
				
			} 		
			
		}
		else{
			 return sendMessageNonExistentNumber(update);				
		}
		
	}
	
	/**
	 * Checks if chatbotIdentitet exists in the database by chatId.
	 * Returns a message with polozeni ispiti or a failed identification message.
	 *
	 */
	@Override
	public SendMessage sendMessagePrijavljeniIspiti(Update update, Long chatId){
		
		ChatBotIdentitet chatBotIdentitet = getChatIdentitetService().findOneByChatId(chatId);
		
		if(chatBotIdentitet != null){
			
			Student student = getStudentService().findOne((chatBotIdentitet.getUser().getId()));
			List<Prijava> prijavljeniIspiti = getPrijavaService().getPrijavljeniIspiti(student);
			String prijave = "";
		
			for(Prijava p: prijavljeniIspiti){
				
				prijave += "| " + p.getIspit().toString() + " | " + "\n";
			}			
			if(prijave.equals("")){
			    SendMessage message = new SendMessage() 
			                    .setChatId(update.getMessage().getChatId())
			                    .setText("Trenutno nemate prijavljenih ispita.");
			    message.setReplyMarkup(getKeyboardMenuOptions());    
			    return message; 
			}else{
				SendMessage message = new SendMessage() 
	                    .setChatId(update.getMessage().getChatId())
	                    .setText("PRIJAVLJENI ISPITI: " + prijave);
				message.setReplyMarkup(getKeyboardMenuOptions());    
				return message; 
				
			}
			
		}
		else{
				
			 return sendMessageNonExistentNumber(update);				
			}
			
	}
	
	
	/**
	 * Checks if chatbotIdentitet exists in the database by chatId.
	 * Returns a message with ispiti za prijavu or a failed identification message.
	 *
	 */
	@Override
	public SendMessage sendMessageIspitiZaPrijavu(Update update, Long chatId){
		
		ChatBotIdentitet chatBotIdentitet = getChatIdentitetService().findOneByChatId(chatId);
		
		if(chatBotIdentitet != null){
			
			Student student = getStudentService().findOne((chatBotIdentitet.getUser().getId()));
			List<Ispit> ispitiZaPrijavu = getIspitService().ispitiZaPrijavu(student);
			String ispiti = "";
		
			for(Ispit ispit: ispitiZaPrijavu){
				
				ispiti += "| " + ispit.toString() + " | " + "\n";
			}	
			
			if(ispiti.equals("")){
			    SendMessage message = new SendMessage() 
			                    .setChatId(update.getMessage().getChatId())
			                    .setText("Trenutno nemate ispite za prijavu.");
			    message.setReplyMarkup(getKeyboardMenuOptions());    
			    
			    return message; 
			}else{
				
				SendMessage message = new SendMessage() 
	                    .setChatId(update.getMessage().getChatId())
	                    .setText("ISPITI ZA PRIJAVU: " + ispiti);
				message.setReplyMarkup(getKeyboardMenuOptions());    
	    
				return message; 
				
			}
		}
		else{
			
			 return sendMessageNonExistentNumber(update);				
		}
		
	
		
	
	}
	
	
	/**
	 * Checks if chatbotIdentitet exists in the database by chatId.
	 * Returns a message with stanje racuna or a failed identification message.
	 *
	 */
	@Override
	public SendMessage sendMessageStanjeRacuna(Update update, Long chatId){
		ChatBotIdentitet chatBotIdentitet = getChatIdentitetService().findOneByChatId(chatId);
		if(chatBotIdentitet != null){
		
			Student student = getStudentService().findOne((chatBotIdentitet.getUser().getId()));
		    SendMessage message = new SendMessage() 
		                    .setChatId(update.getMessage().getChatId())
		                    .setText("STANJE VAŠEG RAČUNA JE: " + student.getStanje() + " RSD");
		    message.setReplyMarkup(getKeyboardMenuOptions());    
		    
		    return message; 
	    
		}else{
			 return sendMessageNonExistentNumber(update);		
		}
	}
	
	
	/**
	 *
	 * Returns a start message.
	 *
	 */
	@Override
	public SendMessage sendMessageStart(Update update){
		SendMessage message = new SendMessage() 
						.setChatId(update.getMessage().getChatId())
						.setText("Dobrodošao/la na chat bot E-Obrazovanja. Potrebno je da podelite vaš broj telefona kako bismo vas identifikovali.");
		message.setReplyMarkup(getKeyboardForPhoneNumber()); 
		
		return message;
	}
	/**
	 *
	 * Returns a failed identification message.
	 *
	 */
	@Override
	public SendMessage sendMessageNonExistentNumber(Update update){
		SendMessage message = new SendMessage() 
						.setChatId(update.getMessage().getChatId())
						.setText("Identifikacija neuspešna. Molimo vas da proverite da li je vaš kontakt na web stranici E-Obrazovanja ispravan, zatim pokušajte ponovo.");
		message.setReplyMarkup(getKeyboardForPhoneNumber()); 
		
		return message;
	}
	
}
