package tseo.project.eobrazovanje.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Prijava {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Date datumPrijave;
	@NotNull
	@ManyToOne
	@JoinColumn
	private Ispit ispit;
	@NotNull
	@ManyToOne
	@JoinColumn
	private Student student;
	@NotNull
	private Float osvojeniBodoviIspit;
	@NotNull
	private Float osvojeniBodoviUsmeni;
	@NotNull
	private Float predispitniBodovi;
	private boolean polozio;
	private boolean ocenjeno;

	public Prijava() {

	}

	public Prijava(Long id, Date datumPrijave, Ispit ispit, Student student, Float osvojeniBodoviIspit,
			Float osvojeniBodoviUsmeni, Float predispitniBodovi, boolean polozio, boolean ocenjeno) {
		super();
		this.id = id;
		this.datumPrijave = datumPrijave;
		this.ispit = ispit;
		this.student = student;
		this.osvojeniBodoviIspit = osvojeniBodoviIspit;
		this.osvojeniBodoviUsmeni = osvojeniBodoviUsmeni;
		this.predispitniBodovi = predispitniBodovi;
		this.polozio = polozio;
		this.ocenjeno = ocenjeno;
	}

	public Long getId() {
		return id;
	}

	public Date getDatumPrijave() {
		return datumPrijave;
	}

	public Ispit getIspit() {
		return ispit;
	}

	public Student getStudent() {
		return student;
	}

	public Float getOsvojeniBodoviIspit() {
		return osvojeniBodoviIspit;
	}

	public Float getOsvojeniBodoviUsmeni() {
		return osvojeniBodoviUsmeni;
	}

	public Float getPredispitniBodovi() {
		return predispitniBodovi;
	}

	public boolean isPolozio() {
		return polozio;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDatumPrijave(Date datumPrijave) {
		this.datumPrijave = datumPrijave;
	}

	public void setIspit(Ispit ispit) {
		this.ispit = ispit;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setOsvojeniBodoviIspit(Float osvojeniBodoviIspit) {
		this.osvojeniBodoviIspit = osvojeniBodoviIspit;
	}

	public void setOsvojeniBodoviUsmeni(Float osvojeniBodoviUsmeni) {
		this.osvojeniBodoviUsmeni = osvojeniBodoviUsmeni;
	}

	public void setPredispitniBodovi(Float predispitniBodovi) {
		this.predispitniBodovi = predispitniBodovi;
	}

	public void setPolozio(boolean polozio) {
		this.polozio = polozio;
	}

	public boolean isOcenjeno() {
		return ocenjeno;
	}

	public void setOcenjeno(boolean ocenjeno) {
		this.ocenjeno = ocenjeno;
	}

}
