package tseo.project.eobrazovanje.service;

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
import tseo.project.eobrazovanje.util.BeanUtil;

@Service
public class BotCommandsService  {

	public static final String POLOZENI_ISPITI = "Polozeni ispiti";
	public static final String PRIJAVLJENI_ISPITI = "Prijavljeni ispiti";
	public static final String ISPITI_ZA_PRIJAVU = "Ispiti za prijavu";
	public static final String STANJE_RACUNA = "Stanje na racunu";
	public static final String START = "/start";
	public static final String BROJ_TELEFONA = "Posalji svoj broj telefona";
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
	

	
	// standardna tastatura za korisnike sa menijem
	public ReplyKeyboardMarkup getKeyboardMenuOptions(){
	
	    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
	    List<KeyboardRow> keyboard = new ArrayList<>();
	    KeyboardRow row = new KeyboardRow();
	    KeyboardRow row2 = new KeyboardRow();
	    row.add(ISPITI_ZA_PRIJAVU);
	    row.add(POLOZENI_ISPITI);
	    keyboard.add(row);
	    row2.add(STANJE_RACUNA);
	    row2.add(PRIJAVLJENI_ISPITI);
	    keyboard.add(row2);
	    keyboardMarkup.setKeyboard(keyboard);
	    // Add it to the message
	   return keyboardMarkup;
		
	}
	
	// tastatura za korisnike koji treba da ostave broj i potvrde identitet
	public ReplyKeyboardMarkup getKeyboardForPhoneNumber(){
		
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
	    replyKeyboardMarkup.setSelective(true);
	    replyKeyboardMarkup.setResizeKeyboard(true);
	    replyKeyboardMarkup.setOneTimeKeyboard(true);

	    // new list
	    List<KeyboardRow> keyboard = new ArrayList<>();

	    // first keyboard line
	    KeyboardRow keyboardFirstRow = new KeyboardRow();
	    KeyboardButton keyboardButton = new KeyboardButton();
	    keyboardButton.setText(BROJ_TELEFONA).setRequestContact(true);
	    keyboardFirstRow.add(keyboardButton);

	    // add array to list
	    keyboard.add(keyboardFirstRow);

	    // add list to our keyboard
	    replyKeyboardMarkup.setKeyboard(keyboard);

	    
	    return replyKeyboardMarkup;
	}
	
	
	public ChatBotIdentitet saveChatIdentitet(Update update, Student student){
		return getChatIdentitetService().save(update, student);
	}
	

	// identifikacija i slanje menija 
	public SendMessage sendMessagePotvrdaIMeni(Update update){
		
		  Contact contact = update.getMessage().getContact();
		  System.out.println(contact + "dosao sam do studenta POTVRDA I MENI");
		  Student student = getStudentService().findOneByBrojTelefona(contact.getPhoneNumber());
		  System.out.println(student  +"evo studenta");
		  // cuvane kontakta u bazi kao chatbotidentitet
		 
		 
		  if(student != null){
			  if(getChatIdentitetService().findOneByUser(student) != null){
			
					Long chatId = update.getMessage().getChatId();                             
				    String firstName = update.getMessage().getContact().getFirstName();		 
				    SendMessage message = new SendMessage() // Create a message object object
		                       .setChatId(chatId)
				               .setText("Dobrodosao/la nazad  " + firstName +  ", ovo je tvoj meni.");			  
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
				               .setText("Dobrodosao/la na chat bot E-Obrazovanja " + firstName +  ", ovo je tvoj meni.");			  
				    message.setReplyMarkup(getKeyboardMenuOptions());  
				    return message;	
			  }			  	   			    
		  }else{
			  System.out.println("usao sam u else nesto ne valja");
			  return sendMessageNonExistentNumber(update);				
		  }
		
	  }

	
	// kod za pravljenje nove poruke kao sablon ??
	public SendMessage sendMessagePolozeniIspiti(Update update, Long chatId){
		
		System.out.println(  "evo ga student, ulazim u polozene ispite i  " + chatId);
		ChatBotIdentitet chatBotIdentitet = getChatIdentitetService().findOneByChatId(chatId);
		
		if(chatBotIdentitet != null){
			System.out.println(  "evo ga student, ulazim u polozene ispite i  " + chatBotIdentitet.getFirstName());
			
			System.out.println(chatBotIdentitet.getFirstName() + "evo ga student, ulazim u polozene ispite ");
			Student student = getStudentService().findOne((chatBotIdentitet.getUser().getId()));
			Set<Predmet> polozeniPredmeti = getPredmetService().getPolozeniPredmeti(student);
			String predmeti = "";
		
			for(Predmet predmet: polozeniPredmeti){
				
				predmeti += "| " + predmet.toString() + " | " + "\n";
			}			
		    SendMessage message = new SendMessage() 
		                    .setChatId(update.getMessage().getChatId())
		                    .setText("POLOZENI ISPITI: " + predmeti);
		    message.setReplyMarkup(getKeyboardMenuOptions());    
		    
		    return message; 		
			
		}
		else{
			System.out.println("nema identitet, botcommand polozeni ispiti");
			 return sendMessageNonExistentNumber(update);				
		}
		
	}
	
	// kod za pravljenje nove poruke kao sablon ??
	public SendMessage sendMessagePrijavljeniIspiti(Update update, Long chatId){
		
		System.out.println(  "evo ga student, ulazim u prijaveljne ispite i  " + chatId);
		ChatBotIdentitet chatBotIdentitet = getChatIdentitetService().findOneByChatId(chatId);
		
		if(chatBotIdentitet != null){
			
			
			System.out.println(chatBotIdentitet.getFirstName() + "BotCommand: sendMessagePrijavljeniIspiti metoda, pronašao sam chatidentitet studenta");
			Student student = getStudentService().findOne((chatBotIdentitet.getUser().getId()));
			List<Prijava> prijavljeniIspiti = getPrijavaService().getPrijavljeniIspiti(student);
			String prijave = "";
		
			for(Prijava p: prijavljeniIspiti){
				
				prijave += "| " + p.getIspit().toString() + " | " + "\n";
			}			
		    SendMessage message = new SendMessage() 
		                    .setChatId(update.getMessage().getChatId())
		                    .setText("PRIJAVLJENI ISPITI: " + prijave);
		    message.setReplyMarkup(getKeyboardMenuOptions());    
		    
		    return message; 		
			
		}
		else{
			
			 return sendMessageNonExistentNumber(update);				
		}
		
	}
	
	

	// kod za pravljenje nove poruke kao sablon 
	public SendMessage sendMessageIspitiZaPrijavu(Update update, Long chatId){
		
		
		System.out.println(  "evo ga student, ulazim u ispite za prijavu i  " + chatId);
		ChatBotIdentitet chatBotIdentitet = getChatIdentitetService().findOneByChatId(chatId);
		
		if(chatBotIdentitet != null){
			
		
			System.out.println(chatBotIdentitet.getFirstName() + "BotCommand: sendMessageIspitiZaPrijavu metoda, pronašao sam chatidentitet studenta");
			Student student = getStudentService().findOne((chatBotIdentitet.getUser().getId()));
			List<Ispit> ispitiZaPrijavu = getIspitService().ispitiZaPrijavu(student);
			String ispiti = "";
		
			for(Ispit ispit: ispitiZaPrijavu){
				
				ispiti += "| " + ispit.toString() + " | " + "\n";
			}			
		    SendMessage message = new SendMessage() 
		                    .setChatId(update.getMessage().getChatId())
		                    .setText("ISPITI ZA PRIJAVU: " + ispiti);
		    message.setReplyMarkup(getKeyboardMenuOptions());    
		    
		    return message; 		
			
		}
		else{
			
			 return sendMessageNonExistentNumber(update);				
		}
		
	
		
	
	}
	
	// kod za pravljenje nove poruke kao sablon 
	public SendMessage sendMessageStanjeRacuna(Update update, Long chatId){
		ChatBotIdentitet chatBotIdentitet = getChatIdentitetService().findOneByChatId(chatId);
		if(chatBotIdentitet != null){
			
			
			Student student = getStudentService().findOne((chatBotIdentitet.getUser().getId()));
			
		    SendMessage message = new SendMessage() 
		                    .setChatId(update.getMessage().getChatId())
		                    .setText("STANJE VASEG RACUNA JE: " + student.getStanje() + " RSD");
		    message.setReplyMarkup(getKeyboardMenuOptions());    
		    
		    return message; 
	    
		}else{
			 return sendMessageNonExistentNumber(update);		
		}
	}
	
	public SendMessage sendMessageStart(Update update){
		SendMessage message = new SendMessage() 
						.setChatId(update.getMessage().getChatId())
						.setText("Dobrodosao/la na chat bot E-Obrazovanja. Potrebno je da podelite vas broj telefona kako bismo vas identifikovali.");
		message.setReplyMarkup(getKeyboardForPhoneNumber()); 
		
		return message;
	}
	
	
	public SendMessage sendMessageNonExistentNumber(Update update){
		SendMessage message = new SendMessage() 
						.setChatId(update.getMessage().getChatId())
						.setText("Identifikacija neuspesna. Molimo vas da proverite da li je vas kontakt na web stranici E-Obrazovanja ispravan.");
		message.setReplyMarkup(getKeyboardForPhoneNumber()); 
		
		return message;
	}
	
}
