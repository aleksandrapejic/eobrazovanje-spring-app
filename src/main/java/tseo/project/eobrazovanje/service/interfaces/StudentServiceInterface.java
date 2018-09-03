package tseo.project.eobrazovanje.service.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tseo.project.eobrazovanje.dto.StudentDto;
import tseo.project.eobrazovanje.entity.PredispitneObaveze;
import tseo.project.eobrazovanje.entity.Student;

public interface StudentServiceInterface {

	Student findOne(Long id);

	Student save(Student student);

	Boolean delete(Long id);

	Student findByTekuciRacun(String tekuciRacun);

	Student update(StudentDto dto);

	List<PredispitneObaveze> getLatestPredispitneObaveze(Student student, Long predmetId, Date datum);

	Page<Student> findAll(String ime, String prezime, Pageable pageable);

	Student changePassword(Student student);

	Student create(Student student);
}
