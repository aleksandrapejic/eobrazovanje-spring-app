package tseo.project.eobrazovanje.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tseo.project.eobrazovanje.enumeration.Role;

@Entity
public class Nastavnik extends User {
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable
	@JsonIgnore
	private Set<Predmet> predmeti;
	@OneToMany(mappedBy = "nastavnik", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Ispit> ispiti;

	public Nastavnik() {

	}

	public Nastavnik(Long id, String username, String password, String ime, String prezime, String jmbg,
			String adresa) {
		super(id, username, password, ime, prezime, jmbg, adresa, Role.NASTAVNIK);
	}

	public Set<Predmet> getPredmeti() {
		return predmeti;
	}

	public Set<Ispit> getIspiti() {
		return ispiti;
	}

	public void setPredmeti(Set<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	public void setIspiti(Set<Ispit> ispiti) {
		this.ispiti = ispiti;
	}

}
