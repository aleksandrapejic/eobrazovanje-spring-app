package tseo.project.eobrazovanje.dto;

import javax.validation.constraints.NotNull;

public class AdminDto {

	private long id;
	@NotNull
	private String ime;
	@NotNull
	private String prezime;
	@NotNull
	private String jmbg;
	@NotNull
	private String username;
	@NotNull
	private String password;

	public AdminDto() {

	}

	public AdminDto(String username, String password, String ime, String prezime, String jmbg) {
		super();
		this.username = username;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
