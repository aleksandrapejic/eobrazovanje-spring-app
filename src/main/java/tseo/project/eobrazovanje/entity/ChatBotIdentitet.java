package tseo.project.eobrazovanje.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ChatBotIdentitet {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected Long chatId;
	protected Integer userId;
	protected String firstName;
	protected String lastName;
	protected String phoneNumber;
	protected boolean subscribedTelegram;
	
	
	
	
	public ChatBotIdentitet(Long id, Long chatId, Integer userId, String firstName, String lastName, String phoneNumber, boolean subscribedTelegram) {
		super();
		this.id = id;
		this.chatId = chatId;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.subscribedTelegram = subscribedTelegram;
	}



	public boolean isSubscribedTelegram() {
		return subscribedTelegram;
	}



	public void setSubscribedTelegram(boolean subscribedTelegram) {
		this.subscribedTelegram = subscribedTelegram;
	}



	public ChatBotIdentitet(){
		
		
	}
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getChatId() {
		return chatId;
	}



	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}




	
}
