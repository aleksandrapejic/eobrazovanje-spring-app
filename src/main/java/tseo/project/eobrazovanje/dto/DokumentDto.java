package tseo.project.eobrazovanje.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class DokumentDto {

	private Long id;
	private Date datumDokumenta;
	@NotNull
	private String naziv;

	public DokumentDto() {

	}

	public DokumentDto(Date datumDokumenta, String naziv) {
		super();
		this.datumDokumenta = datumDokumenta;
		this.naziv = naziv;
	}

	public Long getId() {
		return id;
	}

	public DokumentDto setId(Long id) {
		this.id = id;
		return this;
	}

	public Date getDatumDokumenta() {
		return datumDokumenta;
	}

	public DokumentDto setDatumDokumenta(Date datumDokumenta) {
		this.datumDokumenta = datumDokumenta;
		return this;
	}

	public String getNaziv() {
		return naziv;
	}

	public DokumentDto setNaziv(String naziv) {
		this.naziv = naziv;
		return this;
	}
}
