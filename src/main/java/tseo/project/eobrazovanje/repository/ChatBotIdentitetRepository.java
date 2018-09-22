package tseo.project.eobrazovanje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tseo.project.eobrazovanje.entity.ChatBotIdentitet;
import tseo.project.eobrazovanje.entity.Student;

@Repository
public interface ChatBotIdentitetRepository extends JpaRepository<ChatBotIdentitet, Long> {

	ChatBotIdentitet findOneByUser(Student student);

	ChatBotIdentitet findOneByChatId(Long chatId);

	

}
