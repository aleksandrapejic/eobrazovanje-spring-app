package tseo.project.eobrazovanje.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ChatBotIdentitet {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long ChatId;
	protected Long UserId;
	protected String FirstName;
	protected String LastName;
	
	public ChatBotIdentitet(){
		
		
	}
	
	
	public ChatBotIdentitet(Long chatId, Long userId, String firstName, String lastName) {
		super();
		ChatId = chatId;
		UserId = userId;
		FirstName = firstName;
		LastName = lastName;
	}
	public Long getChatId() {
		return ChatId;
	}
	public void setChatId(Long chatId) {
		ChatId = chatId;
	}
	public Long getUserId() {
		return UserId;
	}
	public void setUserId(Long userId) {
		UserId = userId;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	   
	
}
