package tseo.project.eobrazovanje.service.interfaces;

import java.util.List;

import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;

import tseo.project.eobrazovanje.entity.ChatBotIdentitet;
import tseo.project.eobrazovanje.entity.Student;

public interface ChatBotIdentitetServiceInterface {
	
	List<ChatBotIdentitet> findAll();

	ChatBotIdentitet findOne(Long id);

	Boolean delete(Long id);

	ChatBotIdentitet findOneByChatId(Long chatId);

	ChatBotIdentitet updateChatBotIdentitetPretplata(ChatBotIdentitet chatIdentitet, boolean subscribedTelegram);

	ChatBotIdentitet save(Update update, Student student);

	
}
