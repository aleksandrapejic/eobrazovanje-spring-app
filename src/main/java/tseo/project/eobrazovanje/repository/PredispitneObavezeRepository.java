package tseo.project.eobrazovanje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tseo.project.eobrazovanje.entity.PredispitneObaveze;

@Repository
public interface PredispitneObavezeRepository extends JpaRepository<PredispitneObaveze, Long> {

}
