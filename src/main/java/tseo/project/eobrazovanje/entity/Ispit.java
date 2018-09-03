package tseo.project.eobrazovanje.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ispit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Date datum;
	@NotNull
	@ManyToOne
	@JoinColumn
	private Predmet predmet;
	@NotNull
	@ManyToOne
	@JoinColumn
	private Nastavnik nastavnik;
	@NotNull
	private Float usmeniMinimumBodova;
	@NotNull
	private Float usmeniUkupnoBodova;
	@NotNull
	private Date rokZaPrijavu;
	@OneToMany(mappedBy = "ispit")
	@JsonIgnore
	private Set<Prijava> prijave;

	public Ispit() {

	}

	public Ispit(Long id, Date datum, Predmet predmet, Nastavnik nastavnik, Date rokZaPrijavu,
			Float usmeniMinimumBodova, Float usmeniUkupnoBodova) {
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

	public Date getDatum() {
		return datum;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public Nastavnik getNastavnik() {
		return nastavnik;
	}

	public Date getRokZaPrijavu() {
		return rokZaPrijavu;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public void setNastavnik(Nastavnik nastavnik) {
		this.nastavnik = nastavnik;
	}

	public void setRokZaPrijavu(Date rokZaPrijavu) {
		this.rokZaPrijavu = rokZaPrijavu;
	}

	public Set<Prijava> getPrijave() {
		return prijave;
	}

	public void setPrijave(Set<Prijava> prijave) {
		this.prijave = prijave;
	}

	public Float getUsmeniUkupnoBodova() {
		return usmeniUkupnoBodova;
	}

	public void setUsmeniUkupnoBodova(Float usmeniUkupnoBodova) {
		this.usmeniUkupnoBodova = usmeniUkupnoBodova;
	}

	public Float getUsmeniMinimumBodova() {
		return usmeniMinimumBodova;
	}

	public void setUsmeniMinimumBodova(Float usmeniMinimumBodova) {
		this.usmeniMinimumBodova = usmeniMinimumBodova;
	}

}
