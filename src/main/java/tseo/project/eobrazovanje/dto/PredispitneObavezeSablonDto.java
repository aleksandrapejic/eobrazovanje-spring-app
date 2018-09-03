package tseo.project.eobrazovanje.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PredispitneObavezeSablonDto {

	private Long id;
	@NotNull
	private Long predmet;
	@NotNull
	@Min(1)
	private int ukupnoBodova;
	@NotNull
	@Min(1)
	private int minimumBodova;
	private String naziv;

	public PredispitneObavezeSablonDto() {

	}

	public PredispitneObavezeSablonDto(Long id, Long predmet, int ukupnoBodova, int minimumBodova, String naziv) {
		this.id = id;
		this.predmet = predmet;
		this.ukupnoBodova = ukupnoBodova;
		this.minimumBodova = minimumBodova;
		this.naziv = naziv;
	}

	public Long getId() {
		return id;
	}

	public Long getPredmet() {
		return predmet;
	}

	public int getUkupnoBodova() {
		return ukupnoBodova;
	}

	public int getMinimumBodova() {
		return minimumBodova;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPredmet(Long predmet) {
		this.predmet = predmet;
	}

	public void setUkupnoBodova(int ukupnoBodova) {
		this.ukupnoBodova = ukupnoBodova;
	}

	public void setMinimumBodova(int minimumBodova) {
		this.minimumBodova = minimumBodova;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

}
