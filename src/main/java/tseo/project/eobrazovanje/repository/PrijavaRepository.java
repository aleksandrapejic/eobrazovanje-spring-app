package tseo.project.eobrazovanje.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tseo.project.eobrazovanje.entity.Ispit;
import tseo.project.eobrazovanje.entity.Prijava;
import tseo.project.eobrazovanje.entity.Student;

@Repository
public interface PrijavaRepository extends JpaRepository<Prijava, Long> {

	Page<Prijava> findAll(Pageable pageable);

	Page<Prijava> findByStudent(Student student, Pageable pageable);

	Page<Prijava> findByIspit(Ispit ispit, Pageable pageable);


	List<Prijava> findByStudent(Student student);

}
