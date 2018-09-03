package tseo.project.eobrazovanje.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tseo.project.eobrazovanje.dto.IspitDto;
import tseo.project.eobrazovanje.entity.Ispit;
import tseo.project.eobrazovanje.entity.Predmet;
import tseo.project.eobrazovanje.entity.Student;

public interface IspitServiceInterface {

	List<Ispit> findAll();

	Ispit findOne(Long id);

	Ispit save(Ispit ispit);

	Boolean delete(Long id);

	Ispit save(IspitDto dto);

	Ispit update(IspitDto dto);

	List<Ispit> ispitiZaPrijavu(Student student);

	List<Ispit> nepolozeniIspiti(Student student);

	Page<Ispit> findByPredmet(Predmet predmet, Pageable pageable);

	Page<Ispit> findByPredmetAndNastavnikId(Predmet predmet, Long nastavnikId, Pageable pageable);
}
