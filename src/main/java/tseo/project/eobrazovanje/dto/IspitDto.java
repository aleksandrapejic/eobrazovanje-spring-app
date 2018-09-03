package tseo.project.eobrazovanje.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class IspitDto {

	private Long id;
	@NotNull
	private Date datum;
	@NotNull
	private Long predmet;
	@NotNull
	private Long nastavnik;
	@NotNull
	private Date rokZaPrijavu;
	@NotNull
	private float usmeniMinimumBodova;
	@NotNull
	private float usmeniUkupnoBodova;

	public IspitDto() {
	}

	public IspitDto(Long id, Date datum, Long predmet, Long nastavnik, Date rokZaPrijavu, float usmeniMinimumBodova,
			float usmeniUkupnoBodova) {
		super();
		this.id = id;
		this.datum = datum;
		this.predmet = predmet;
		this.nastavnik = nastavnik;
		this.rokZaPrijavu = rokZaPrijavu;
		this.usmeniMinimumBodova = usmeniMinimumBodova;
		this.usmeniUkupnoBodova = usmeniUkupnoBodova;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Long getPredmet() {
		return predmet;
	}

	public void setPredmet(Long predmet) {
		this.predmet = predmet;
	}

	public Long getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(Long nastavnik) {
		this.nastavnik = nastavnik;
	}

	public Date getRokZaPrijavu() {
		return rokZaPrijavu;
	}

	public void setRokZaPrijavu(Date rokZaPrijavu) {
		this.rokZaPrijavu = rokZaPrijavu;
	}

	public float getUsmeniMinimumBodova() {
		return usmeniMinimumBodova;
	}

	public void setUsmeniMinimumBodova(float usmeniMinimumBodova) {
		this.usmeniMinimumBodova = usmeniMinimumBodova;
	}

	public float getUsmeniUkupnoBodova() {
		return usmeniUkupnoBodova;
	}

	public void setUsmeniUkupnoBodova(float usmeniUkupnoBodova) {
		this.usmeniUkupnoBodova = usmeniUkupnoBodova;
	}

}