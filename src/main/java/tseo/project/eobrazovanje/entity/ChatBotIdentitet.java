package tseo.project.eobrazovanje.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class ChatBotIdentitet {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected Long chatId; 
	@OneToOne
	protected Student user; 
	protected String firstName;
	protected String lastName;
	protected boolean subscribedTelegram;
	
	
	
	
	public ChatBotIdentitet(Long id, Long chatId, Student user, String firstName, String lastName, String phoneNumber, boolean subscribedTelegram) {
		super();
		this.id = id;
		this.chatId = chatId;
		this.user = user;
		this.firstName = firstName;
		this.lastName = lastName;
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



	public Student getUser() {
		return user;
	}



	public void setUserId(Student user) {
		this.user = user;
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







	
}
