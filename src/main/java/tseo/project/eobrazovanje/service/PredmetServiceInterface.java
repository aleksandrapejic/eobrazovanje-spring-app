package tseo.project.eobrazovanje.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tseo.project.eobrazovanje.entity.Nastavnik;
import tseo.project.eobrazovanje.entity.Predmet;
import tseo.project.eobrazovanje.entity.Student;

public interface PredmetServiceInterface {

	Page<Predmet> findAll(String naziv, Pageable pageable);

	Predmet findOne(Long id);

	Predmet save(Predmet predmet);

	Boolean delete(Long id);

	Set<Nastavnik> getNastavnici(List<Long> nastavnici);

	Set<Student> getStudenti(List<Long> studenti);

	Predmet addNastavnici(Predmet predmet, List<Long> nastavnici);

	Predmet addStudenti(Predmet predmet, List<Long> studenti);

	Boolean removeStudent(Predmet predmet, Student student);

	Boolean removeNastavnik(Predmet predmet, Nastavnik nastavnik);

	Set<Predmet> getPolozeniPredmeti(Student student);

	Set<Predmet> getNepolozeniPredmeti(Student student);

	Predmet findByOznaka(String oznaka);

}
