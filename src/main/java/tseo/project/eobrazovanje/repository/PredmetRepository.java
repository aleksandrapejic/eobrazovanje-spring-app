package tseo.project.eobrazovanje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tseo.project.eobrazovanje.entity.Predmet;

@Repository
public interface PredmetRepository extends JpaRepository<Predmet, Long> {

	Page<Predmet> findAllByNazivIgnoreCaseContains(String naziv, Pageable pageable);

	Predmet findByOznaka(String oznaka);

}
