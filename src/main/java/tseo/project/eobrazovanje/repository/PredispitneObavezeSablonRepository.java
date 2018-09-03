package tseo.project.eobrazovanje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tseo.project.eobrazovanje.entity.PredispitneObavezeSablon;

@Repository
public interface PredispitneObavezeSablonRepository extends JpaRepository<PredispitneObavezeSablon, Long> {

}
