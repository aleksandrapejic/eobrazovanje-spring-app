package tseo.project.eobrazovanje.repository;

import org.springframework.stereotype.Repository;
import tseo.project.eobrazovanje.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
