package tseo.project.eobrazovanje.dto;

public class NastavnikDto {

	private long id;
	private String ime;
	private String prezime;
	private String jmbg;
	private String username;
	private String password;
	private String adresa;

	public NastavnikDto() {
	}

	public NastavnikDto(String ime, String password, String username, String prezime, String jmbg, String adresa) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.username = username;
		this.password = password;
		this.adresa = adresa;
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

	public NastavnikDto setId(long id) {
		this.id = id;
		return this;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public NastavnikDto setPrezime(String prezime) {
		this.prezime = prezime;
		return this;
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

	public void setIme(String ime) {
		this.ime = ime;
	}

}
