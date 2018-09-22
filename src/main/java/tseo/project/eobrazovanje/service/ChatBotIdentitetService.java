package tseo.project.eobrazovanje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;

import tseo.project.eobrazovanje.entity.ChatBotIdentitet;
import tseo.project.eobrazovanje.entity.Student;
import tseo.project.eobrazovanje.repository.ChatBotIdentitetRepository;
import tseo.project.eobrazovanje.service.interfaces.ChatBotIdentitetServiceInterface;
import tseo.project.eobrazovanje.util.BeanUtil;

@Service
public class ChatBotIdentitetService implements ChatBotIdentitetServiceInterface {

	@Autowired
	ChatBotIdentitetRepository repo;
	
	
	@Override
	public List<ChatBotIdentitet> findAll() {
		
		return repo.findAll();
	}

	@Override
	public ChatBotIdentitet findOne(Long id) {
		
		return repo.findOne(id);
	}

	@Override
	public ChatBotIdentitet save(Update update, Student student) {
		
		
		ChatBotIdentitet chatIdentitet = new ChatBotIdentitet();
		
		chatIdentitet.setChatId(update.getMessage().getChatId());
		chatIdentitet.setUserId(student);

		
		return repo.save(chatIdentitet);
	}

	
	public Boolean delete(Long id) {
		
		repo.delete(id);
		return true;
	}


	@Override
	public ChatBotIdentitet findOneByChatId(Long chatId) {
		return repo.findOneByChatId(chatId);
	}
	
	@Override
	public ChatBotIdentitet updateChatBotIdentitetPretplata(ChatBotIdentitet chatIdentitet, boolean subscribedTelegram) {

		 System.out.println("cuvam pretplacenost upravo u chatbotidentitet");
		chatIdentitet.setSubscribedTelegram(subscribedTelegram);
		
		return repo.save(chatIdentitet);
	}

	public ChatBotIdentitet findOneByUser(Student student) {
		 return repo.findOneByUser(student);
	}



}
