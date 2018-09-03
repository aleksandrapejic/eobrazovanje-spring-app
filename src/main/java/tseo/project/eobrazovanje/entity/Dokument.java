package tseo.project.eobrazovanje.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Dokument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Date datumDokumenta;
	@NotNull
	private String naziv;
	@NotNull
	private String sadrzaj;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	public Dokument() {
	}

	public Dokument(Long id, Date datumDokumenta, String naziv, String sadrzaj, Student student) {
		this.id = id;
		this.datumDokumenta = datumDokumenta;
		this.naziv = naziv;
		this.sadrzaj = sadrzaj;
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatumDokumenta() {
		return datumDokumenta;
	}

	public void setDatumDokumenta(Date datumDokumenta) {
		this.datumDokumenta = datumDokumenta;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
