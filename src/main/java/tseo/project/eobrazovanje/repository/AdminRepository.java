package tseo.project.eobrazovanje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tseo.project.eobrazovanje.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
