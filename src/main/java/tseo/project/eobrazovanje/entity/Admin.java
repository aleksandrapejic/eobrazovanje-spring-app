package tseo.project.eobrazovanje.entity;

import javax.persistence.Entity;

import tseo.project.eobrazovanje.enumeration.Role;

@Entity
public class Admin extends User {

	public Admin() {

	}

	public Admin(Long id, String username, String password, String ime, String prezime, String jmbg, String adresa) {
		super(id, username, password, ime, prezime, jmbg, adresa, Role.ADMINISTRATOR);
	}

}
