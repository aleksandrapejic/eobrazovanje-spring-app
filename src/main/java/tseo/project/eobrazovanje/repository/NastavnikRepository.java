package tseo.project.eobrazovanje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tseo.project.eobrazovanje.entity.Nastavnik;

@Repository
public interface NastavnikRepository extends JpaRepository<Nastavnik, Long> {

	Page<Nastavnik> findAllByImeIgnoreCaseContainsAndPrezimeIgnoreCaseContains(String ime, String prezime,
			Pageable pageable);

}
