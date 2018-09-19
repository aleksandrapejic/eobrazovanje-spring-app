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
	public ChatBotIdentitet save(Update update, Contact contact) {
		
		ChatBotIdentitet chatIdentitet = new ChatBotIdentitet();
		
		chatIdentitet.setChatId(update.getMessage().getChatId());
		chatIdentitet.setUserId(contact.getUserID());
		chatIdentitet.setFirstName(contact.getFirstName());
		chatIdentitet.setLastName(contact.getLastName());
		chatIdentitet.setPhoneNumber(contact.getPhoneNumber());
		
		return repo.save(chatIdentitet);
	}

	
	public Boolean delete(Long id) {
		
		repo.delete(id);
		return true;
	}


	@Override
	public ChatBotIdentitet findOneByPhoneNumber(String broj) {
		
		return repo.findByPhoneNumber(broj);
	}
	
	
	@Override
	public ChatBotIdentitet findOneByChatId(Long chatId) {
		return repo.findOneByChatId(chatId);
	}
	

	public ChatBotIdentitet updateChatBotIdentitetBroj(ChatBotIdentitet chatIdentitet, Student student) {

		 System.out.println("cuvam novi broj upravo u chatbotidentitet");
		chatIdentitet.setPhoneNumber(student.getBrojTelefona());
		 System.out.println("broj: " + student.getBrojTelefona());
		return repo.save(chatIdentitet);
	}

	public ChatBotIdentitet updateChatBotIdentitetPretplata(ChatBotIdentitet chatIdentitet, boolean subscribedTelegram) {

		 System.out.println("cuvam pretplacenost upravo u chatbotidentitet");
		chatIdentitet.setSubscribedTelegram(subscribedTelegram);
		
		return repo.save(chatIdentitet);
	}

}
