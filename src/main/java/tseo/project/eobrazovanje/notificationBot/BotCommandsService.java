package tseo.project.eobrazovanje.notificationBot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotOptions;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import tseo.project.eobrazovanje.entity.Ispit;
import tseo.project.eobrazovanje.entity.Student;
import tseo.project.eobrazovanje.service.IspitService;
import tseo.project.eobrazovanje.service.StudentService;

@Service
public class BotCommandsService  {

	public static final String POLOZENI_ISPITI = "Polozeni ispiti";
	public static final String PRIJAVLJENI_ISPITI = "Prijavljeni ispiti";
	public static final String ISPITI_ZA_PRIJAVU = "Ispiti za prijavu";
	public static final String STANJE_RACUNA = "Stanje na racunu";
	public static final String START = "/start";
	public static final String BROJ_TELEFONA = "Posalji svoj broj telefona";
	public static final String MENI = "meni";
	
	
	private StudentService getStudentService() {
        return BeanUtil.getStudentService();
    }
	
	private IspitService getIspitService() {
        return BeanUtil.getIspitService();
    }
	
	
	
	// standardna tastatura za korisnike sa menijem
	public ReplyKeyboardMarkup getKeyboardMenuOptions(){
		
		
		//Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("Polozeni ispiti");
        row.add("Ispiti za prijavu");
        row.add("Stanje racuna");
        // Add the first row to the keyboard
        keyboard.add(row);
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

	// identifikacija i slanje menija 
	public SendMessage sendMessagePotvrdaIMeni(Update update){
		
	  Contact contact = update.getMessage().getContact();
	  System.out.println(contact + "dosao sam do studenta ");
	  Student student = getStudentService().findOneByBrojTelefona(contact.getPhoneNumber());
	  System.out.println(student);
	  if(contact != null && contact.getPhoneNumber().equals(student.getBrojTelefona())){
			Long chatId = update.getMessage().getChatId();                    
           	String messageText = update.getMessage().getText();
		    System.out.println("NASAO SAM BROJ USPEO SAM, PROSLO JE IZ SERVISA");
		    System.out.println(update.getMessage().getContact());
		    System.out.println("#############");
		    String firstName = update.getMessage().getContact().getFirstName();
		 
		    SendMessage message = new SendMessage() // Create a message object object
                       .setChatId(chatId)
		                        .setText("Broj je identifikovan. Zdravo " + firstName +  ", ovo je tvoj meni.");
		    // Create ReplyKeyboardMarkup object
		    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
		    // Create the keyboard (list of keyboard rows)
		    List<KeyboardRow> keyboard = new ArrayList<>();
		    // Create a keyboard row
		    KeyboardRow row = new KeyboardRow();
		    KeyboardRow row2 = new KeyboardRow();
		    // Set each button, you can also use KeyboardButton objects if you need something else than text
		    row.add(ISPITI_ZA_PRIJAVU);
		    row.add(POLOZENI_ISPITI);
		    // Add the first row to the keyboard
		    keyboard.add(row);
		    row2.add(STANJE_RACUNA);
		    row2.add(PRIJAVLJENI_ISPITI);
		    keyboard.add(row2);
		    keyboardMarkup.setKeyboard(keyboard);
		    // Add it to the message
		    message.setReplyMarkup(keyboardMarkup);
		    
		    return message;
	  }else{
		  
		  return sendMessageNonExistentNumber(update);
			
	  }

	
	  }

	
	// kod za pravljenje nove poruke kao sablon ??
	public SendMessage sendMessagePolozeniIspiti(Update update){
		
	    SendMessage message = new SendMessage() 
	                    .setChatId(update.getMessage().getChatId())
	                    .setText(update.getMessage().getText());
	    message.setReplyMarkup(getKeyboardMenuOptions());    
	    
	    return message; 
	}
	
	// kod za pravljenje nove poruke kao sablon 
	public SendMessage sendMessageIspitiZaPrijavu(Update update){
		
		Contact contact = update.getMessage().getContact();
		System.out.println(contact + "evo ga student, ulazim u ispite ");
		Student student = getStudentService().findOneByBrojTelefona(contact.getPhoneNumber());
		List<Ispit> ispitiZaPrijavu = getIspitService().ispitiZaPrijavu(student);
		String ispiti = "";
		for(Ispit ispit: ispitiZaPrijavu){
			
			ispiti += ispit.toString() + " /";
		}
		
	    SendMessage message = new SendMessage() 
	                    .setChatId(update.getMessage().getChatId())
	                    .setText(ispiti);
	    message.setReplyMarkup(getKeyboardMenuOptions());    
	    
	    return message; 
	}
	
	// kod za pravljenje nove poruke kao sablon 
	public SendMessage sendMessageStanjeRacuna(Update update){
		
	    SendMessage message = new SendMessage() 
	                    .setChatId(update.getMessage().getChatId())
	                    .setText(update.getMessage().getText());
	    message.setReplyMarkup(getKeyboardMenuOptions());    
	    
	    return message; 
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
