package tseo.project.eobrazovanje.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tseo.project.eobrazovanje.entity.Ispit;
import tseo.project.eobrazovanje.entity.Predmet;
import tseo.project.eobrazovanje.entity.Student;

@Repository
public interface IspitRepository extends JpaRepository<Ispit, Long> {

	@Query("Select i from Ispit i where i.rokZaPrijavu > :datum " + "and :student member of i.predmet.studenti "
			+ "and not exists (select p from Prijava p where p.ispit = i and p.student = :student) "
			+ "and not exists(select p from Prijava p where p.student = :student and p.ispit.predmet = i.predmet and p.polozio = true)")
	List<Ispit> findByStudentAndDatum(@Param("student") Student student, @Param("datum") Date date);

	Page<Ispit> findByPredmet(Predmet predmet, Pageable pageable);

	Page<Ispit> findByPredmetAndNastavnikId(Predmet predmet, Long nastavnikId, Pageable pageable);
}
