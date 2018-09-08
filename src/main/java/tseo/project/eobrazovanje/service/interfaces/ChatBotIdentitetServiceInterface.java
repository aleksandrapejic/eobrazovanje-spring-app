package tseo.project.eobrazovanje.service.interfaces;

import java.util.List;

import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;

import tseo.project.eobrazovanje.entity.ChatBotIdentitet;

public interface ChatBotIdentitetServiceInterface {
	
	List<ChatBotIdentitet> findAll();

	ChatBotIdentitet findOne(Long id);

	ChatBotIdentitet save(Update update, Contact contact);

	Boolean delete(Long id);

	ChatBotIdentitet findOneByPhoneNumber(String broj);

	ChatBotIdentitet findOneByChatId(Long chatId);

	
}
