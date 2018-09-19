package tseo.project.eobrazovanje.dto;

import tseo.project.eobrazovanje.enumeration.Role;

public class StudentDto {

	private long id;
	private String ime;
	private String prezime;
	private String jmbg;
	private String brojIndexa;
	private String username;
	private String password;
	private String adresa;
	private String brojTelefona;
	private boolean subscribedTelegram;
	private Role role;

	public StudentDto() {

	}

	public StudentDto(String username, String password, String ime, String prezime, String jmbg, String brojIndexa,
			String adresa, String brojTelefona, boolean subscribedTelegram, Role role) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.brojIndexa = brojIndexa;
		this.username = username;
		this.password = password;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.subscribedTelegram = subscribedTelegram;
		this.role = role;
	}

	
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isSubscribedTelegram() {
		return subscribedTelegram;
	}

	public void setSubscribedTelegram(boolean subscribedTelegram) {
		this.subscribedTelegram = subscribedTelegram;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public StudentDto setId(long id) {
		this.id = id;
		return this;
	}

	public String getIme() {
		return ime;
	}

	public StudentDto setIme(String ime) {
		this.ime = ime;
		return this;
	}

	public String getPrezime() {
		return prezime;
	}

	public StudentDto setPrezime(String prezime) {
		this.prezime = prezime;
		return this;
	}

	public String getBrojIndexa() {
		return brojIndexa;
	}

	public void setBrojIndexa(String brojIndexa) {
		this.brojIndexa = brojIndexa;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

}
