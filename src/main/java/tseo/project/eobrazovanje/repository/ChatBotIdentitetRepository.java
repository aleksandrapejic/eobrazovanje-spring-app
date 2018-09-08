package tseo.project.eobrazovanje.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tseo.project.eobrazovanje.entity.ChatBotIdentitet;

public interface ChatBotIdentitetRepository extends JpaRepository<ChatBotIdentitet, Long> {

	ChatBotIdentitet findByPhoneNumber(String phone_number);

	ChatBotIdentitet findOneByChatId(Long chatId);

}
