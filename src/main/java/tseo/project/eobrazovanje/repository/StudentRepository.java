package tseo.project.eobrazovanje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tseo.project.eobrazovanje.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	Student findByTekuciRacun(String tekuciRacun);

	Page<Student> findAllByImeIgnoreCaseContainsAndPrezimeIgnoreCaseContains(String ime, String prezime,
			Pageable pageable);
}
