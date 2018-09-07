package tseo.project.eobrazovanje.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import tseo.project.eobrazovanje.enumeration.Role;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String username;
	protected String password;
	protected String ime;
	protected String prezime;
	protected String jmbg;
	protected String adresa;
	protected String brojtelefona;
	@Enumerated(EnumType.STRING)
	protected Role role;

	public User() {

	}

	public User(Long id, String username, String password, String ime, String prezime, String jmbg, String adresa,
			Role role, String brojtelefona) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.role = role;
		this.username = username;
		this.password = password;
		this.adresa = adresa;
		this.brojtelefona = brojtelefona;
	}

	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@JsonProperty(value = "password")
	public String getPassword() {
		return password;
	}

	
	public String getBrojTelefona() {
		return brojtelefona;
	}

	public void setBrojTelefona(String brojtelefona) {
		this.brojtelefona = brojtelefona;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public Role getRole() {
		return role;
	}

	public User setId(Long id) {
		this.id = id;
		return this;
	}

	public User setIme(String ime) {
		this.ime = ime;
		return this;
	}

	public User setPrezime(String prezime) {
		this.prezime = prezime;
		return this;
	}

	public User setJmbg(String jmbg) {
		this.jmbg = jmbg;
		return this;
	}

	public User setRole(Role role) {
		this.role = role;
		return this;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

}
