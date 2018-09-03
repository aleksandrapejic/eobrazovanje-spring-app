package tseo.project.eobrazovanje.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class PredispitneObavezeDto {

	private Long id;
	private Date datum;
	@NotNull
	private float osvojeniBodovi;
	@NotNull
	private Long student;
	@NotNull
	private Long sablon;
	private boolean polozio;

	public PredispitneObavezeDto() {

	}

	public PredispitneObavezeDto(Long id, Date datum, float osvojeniBodovi, Long student, Long sablon,
			boolean polozio) {
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

	public float getOsvojeniBodovi() {
		return osvojeniBodovi;
	}

	public Long getStudent() {
		return student;
	}

	public Long getSablon() {
		return sablon;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public void setOsvojeniBodovi(float osvojeniBodovi) {
		this.osvojeniBodovi = osvojeniBodovi;
	}

	public void setStudent(Long student) {
		this.student = student;
	}

	public void setSablon(Long sablon) {
		this.sablon = sablon;
	}

	public boolean isPolozio() {
		return polozio;
	}

	public void setPolozio(boolean polozio) {
		this.polozio = polozio;
	}

}
