package tseo.project.eobrazovanje.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class PredispitneObaveze {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Date datum;
	@NotNull
	private Float osvojeniBodovi;
	@NotNull
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private Student student;
	@NotNull
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private PredispitneObavezeSablon sablon;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn
	private Predmet predmet;
	private boolean polozio;

	public PredispitneObaveze() {

	}

	public PredispitneObaveze(Long id, Date datum, Float osvojeniBodovi, Student student,
			PredispitneObavezeSablon sablon, boolean polozio) {
		super();
		this.id = id;
		this.datum = datum;
		this.osvojeniBodovi = osvojeniBodovi;
		this.student = student;
		this.sablon = sablon;
		this.polozio = polozio;
	}

	public Long getId() {
		return id;
	}

	public Date getDatum() {
		return datum;
	}

	public Float getOsvojeniBodovi() {
		return osvojeniBodovi;
	}

	public Student getStudent() {
		return student;
	}

	public PredispitneObavezeSablon getSablon() {
		return sablon;
	}

	public boolean isPolozio() {
		return polozio;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public void setOsvojeniBodovi(Float osvojeniBodovi) {
		this.osvojeniBodovi = osvojeniBodovi;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setSablon(PredispitneObavezeSablon sablon) {
		this.sablon = sablon;
	}

	public void setPolozio(boolean polozio) {
		this.polozio = polozio;
	}

}
