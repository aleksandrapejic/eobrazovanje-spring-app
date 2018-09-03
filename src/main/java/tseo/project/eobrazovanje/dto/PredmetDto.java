package tseo.project.eobrazovanje.dto;

import javax.validation.constraints.NotNull;

public class PredmetDto {
	private Long id;
	@NotNull
	private String naziv;
	@NotNull
	private String oznaka;
	@NotNull
	private int espb;

	public PredmetDto() {

	}

	public PredmetDto(Long id, String naziv, String oznaka, int espb) {
		this.id = id;
		this.naziv = naziv;
		this.oznaka = oznaka;
		this.espb = espb;
	}

	public long getId() {
		return id;
	}

	public String getNaziv() {
		return naziv;
	}

	public String getOznaka() {
		return oznaka;
	}

	public int getEspb() {
		return espb;
	}

	public PredmetDto setId(long id) {
		this.id = id;
		return this;
	}

	public PredmetDto setNaziv(String naziv) {
		this.naziv = naziv;
		return this;
	}

	public PredmetDto setOznaka(String oznaka) {
		this.oznaka = oznaka;
		return this;
	}

	public PredmetDto setEspb(int espb) {
		this.espb = espb;
		return this;
	}
}
