package tseo.project.eobrazovanje.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tseo.project.eobrazovanje.entity.Nastavnik;
import tseo.project.eobrazovanje.entity.Predmet;
import tseo.project.eobrazovanje.entity.Prijava;
import tseo.project.eobrazovanje.entity.Student;
import tseo.project.eobrazovanje.repository.PredmetRepository;
import tseo.project.eobrazovanje.service.PredmetServiceInterface;

@Service
public class PredmetService implements PredmetServiceInterface {

	@Autowired
	NastavnikService nastavnikService;

	@Autowired
	StudentService studentService;

	@Autowired
	PredmetRepository predmetRepository;

	@Autowired
	IspitService ispitiService;

	@Override
	public Page<Predmet> findAll(String naziv, Pageable pageable) {
		return predmetRepository.findAllByNazivIgnoreCaseContains(naziv, pageable);
	}

	@Override
	public Predmet findOne(Long id) {
		return predmetRepository.findOne(id);
	}

	@Override
	public Predmet save(Predmet predmet) {
		return predmetRepository.save(predmet);
	}

	@Override
	public Boolean delete(Long id) {
		predmetRepository.delete(id);
		return true;
	}

	@Override
	public Set<Nastavnik> getNastavnici(List<Long> nastavnici) {
		Set<Nastavnik> nastavniciObj = new HashSet<>();
		for (Long l : nastavnici) {
			Nastavnik n = nastavnikService.findOne(l);
			if (n != null) {
				nastavniciObj.add(n);
			}
		}
		return nastavniciObj;
	}

	@Override
	public Set<Student> getStudenti(List<Long> studenti) {
		Set<Student> studentiObj = new HashSet<>();
		for (Long l : studenti) {
			Student s = studentService.findOne(l);
			if (s != null) {
				studentiObj.add(s);
			}
		}
		return studentiObj;
	}

	@Override
	public Predmet addNastavnici(Predmet predmet, List<Long> nastavnici) {
		predmet.getNastavnici().forEach(n -> n.getPredmeti().remove(predmet));
		predmet.setNastavnici(getNastavnici(nastavnici));
		predmet.getNastavnici().forEach(n -> n.getPredmeti().add(predmet));
		return save(predmet);
	}

	@Override
	public Predmet addStudenti(Predmet predmet, List<Long> studenti) {
		predmet.getStudenti().forEach(n -> n.getPredmeti().remove(predmet));
		predmet.setStudenti(getStudenti(studenti));
		predmet.getStudenti().forEach(n -> n.getPredmeti().add(predmet));
		return save(predmet);
	}

	@Override
	public Boolean removeStudent(Predmet predmet, Student student) {
		if (predmet.getStudenti().contains(student)) {
			predmet.getStudenti().remove(student);
			student.getPredmeti().remove(predmet);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean removeNastavnik(Predmet predmet, Nastavnik nastavnik) {
		if (predmet.getNastavnici().contains(nastavnik)) {
			predmet.getNastavnici().remove(nastavnik);
			nastavnik.getPredmeti().remove(predmet);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Set<Predmet> getPolozeniPredmeti(Student student) {
		Set<Prijava> prijave = student.getPrijave();
		Set<Predmet> polozeniPredmeti = new HashSet<>();
		for (Prijava prijava : prijave) {
			if (prijava.isPolozio()) {
				polozeniPredmeti.add(prijava.getIspit().getPredmet());
			}
		}
		return polozeniPredmeti;
	}

	@Override
	public Set<Predmet> getNepolozeniPredmeti(Student student) {
		Set<Prijava> prijave = student.getPrijave();
		Set<Predmet> predmeti = student.getPredmeti();
		Set<Predmet> nepolozeni = new HashSet<>();
		for (Predmet predmet : predmeti) {
			boolean p = false;
			for (Prijava prijava : prijave) {
				if (prijava.getIspit().getPredmet() == predmet) {
					p = true;
					if (!prijava.isPolozio()) {
						nepolozeni.add(predmet);
					}
				}
			}
			if (!p) {
				nepolozeni.add(predmet);
			}
		}
		return nepolozeni;
	}

	@Override
	public Predmet findByOznaka(String oznaka) {
		return predmetRepository.findByOznaka(oznaka);
	}

}
