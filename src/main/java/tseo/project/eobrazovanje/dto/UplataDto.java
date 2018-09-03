package tseo.project.eobrazovanje.dto;

import java.util.Date;

public class UplataDto {
	private Long id;
	private Date datumUplate;
	private double iznosUplate;
	private String racunPrimaoca;
	private String pozivNaBroj;
	private String svrhaUplate;
	private Long student;

	public UplataDto() {

	}

	public UplataDto(Date datumUplate, double iznosUplate, String racunPrimaoca, String pozivNaBroj, String svrhaUplate,
			Long student) {
		this.datumUplate = datumUplate;
		this.iznosUplate = iznosUplate;
		this.racunPrimaoca = racunPrimaoca;
		this.pozivNaBroj = pozivNaBroj;
		this.svrhaUplate = svrhaUplate;
		this.student = student;
	}

	public UplataDto(Date datumUplate, double iznosUplate, String racunPrimaoca, String pozivNaBroj, Long student) {
		this.datumUplate = datumUplate;
		this.iznosUplate = iznosUplate;
		this.racunPrimaoca = racunPrimaoca;
		this.pozivNaBroj = pozivNaBroj;
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public Date getDatumUplate() {
		return datumUplate;
	}

	public double getIznosUplate() {
		return iznosUplate;
	}

	public String getRacunPrimaoca() {
		return racunPrimaoca;
	}

	public String getPozivNaBroj() {
		return pozivNaBroj;
	}

	public String getSvrhaUplate() {
		return svrhaUplate;
	}

	public Long getStudent() {
		return student;
	}

	public UplataDto setId(Long id) {
		this.id = id;
		return this;
	}

	public UplataDto setDatumUplate(Date datumUplate) {
		this.datumUplate = datumUplate;
		return this;
	}

	public UplataDto setIznosUplate(double iznosUplate) {
		this.iznosUplate = iznosUplate;
		return this;
	}

	public UplataDto setRacunPrimaoca(String racunPrimaoca) {
		this.racunPrimaoca = racunPrimaoca;
		return this;
	}

	public UplataDto setPozivNaBroj(String pozivNaBroj) {
		this.pozivNaBroj = pozivNaBroj;
		return this;
	}

	public UplataDto setSvrhaUplate(String svrhaUplate) {
		this.svrhaUplate = svrhaUplate;
		return this;
	}

	public UplataDto setStudent(Long student) {
		this.student = student;
		return this;
	}
}
