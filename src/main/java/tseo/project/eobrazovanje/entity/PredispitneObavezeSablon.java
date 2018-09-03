package tseo.project.eobrazovanje.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PredispitneObavezeSablon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private Predmet predmet;
	@NotNull
	@Min(1)
	private int ukupnoBodova;
	@NotNull
	@Min(1)
	private int minimumBodova;
	private String naziv;
	@OneToMany(mappedBy = "sablon", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<PredispitneObaveze> polaganja;

	public PredispitneObavezeSablon() {
	}

	public PredispitneObavezeSablon(Long id, Predmet predmet, int ukupnoBodova, int minimumBodova, String naziv,
			Set<PredispitneObaveze> polaganja) {
		super();
		this.id = id;
		this.predmet = predmet;
		this.ukupnoBodova = ukupnoBodova;
		this.minimumBodova = minimumBodova;
		this.naziv = naziv;
		this.polaganja = polaganja;
	}

	public Long getId() {
		return id;
	}

	public Predmet getPredmet() {
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

	public Set<PredispitneObaveze> getPolaganja() {
		return polaganja;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPredmet(Predmet predmet) {
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

	public void setPolaganja(Set<PredispitneObaveze> polaganja) {
		this.polaganja = polaganja;
	}

}
