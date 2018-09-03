package tseo.project.eobrazovanje.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class PrijavaDto {

	private Long id;
	private Date datumPrijave;
	@NotNull
	private Long ispit;
	@NotNull
	private Long student;
	private Float osvojeniBodoviIspit;
	@NotNull
	private Float osvojeniBodoviUsmeni;
	private Float predispitniBodovi;
	private boolean polozio;
	private boolean ocenjeno;

	public PrijavaDto() {

	}

	public Long getId() {
		return id;
	}

	public Date getDatumPrijave() {
		return datumPrijave;
	}

	public Long getIspit() {
		return ispit;
	}

	public Long getStudent() {
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

	public void setIspit(Long ispit) {
		this.ispit = ispit;
	}

	public void setStudent(Long student) {
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
