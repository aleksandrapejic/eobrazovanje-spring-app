package tseo.project.eobrazovanje.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import tseo.project.eobrazovanje.entity.ChatBotIdentitet;
import tseo.project.eobrazovanje.entity.Student;

public interface BotCommandsServiceInterface {
		
	public ReplyKeyboardMarkup getKeyboardMenuOptions();
	
	public ReplyKeyboardMarkup getKeyboardForPhoneNumber();
	
	public ChatBotIdentitet saveChatIdentitet(Update update, Student student);
	
	public SendMessage sendMessagePotvrdaIMeni(Update update);
	
	public SendMessage sendMessagePolozeniIspiti(Update update, Long chatId);
	
	public SendMessage sendMessagePrijavljeniIspiti(Update update, Long chatId);
		
	public SendMessage sendMessageIspitiZaPrijavu(Update update, Long chatId);
	
	public SendMessage sendMessageStanjeRacuna(Update update, Long chatId);
	
	public SendMessage sendMessageStart(Update update);
	
	public SendMessage sendMessageNonExistentNumber(Update update);
		
		
		
}
