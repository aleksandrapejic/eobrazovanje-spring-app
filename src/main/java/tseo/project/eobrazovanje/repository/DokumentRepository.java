package tseo.project.eobrazovanje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tseo.project.eobrazovanje.entity.Dokument;
import tseo.project.eobrazovanje.entity.Student;

@Repository
public interface DokumentRepository extends JpaRepository<Dokument, Long> {

	Page<Dokument> findAllByNazivIgnoreCaseContains(String naziv, Pageable pageable);

	Page<Dokument> findByStudent(Student student, Pageable pageable);
}
