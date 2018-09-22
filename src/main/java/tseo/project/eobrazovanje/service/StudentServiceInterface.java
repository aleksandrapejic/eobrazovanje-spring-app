package tseo.project.eobrazovanje.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tseo.project.eobrazovanje.dto.StudentDto;
import tseo.project.eobrazovanje.entity.PredispitneObaveze;
import tseo.project.eobrazovanje.entity.Student;
import tseo.project.eobrazovanje.entity.User;

public interface StudentServiceInterface {

	Student findOne(Long id);

	Student save(Student student);

	Boolean delete(Long id);

	Student findByTekuciRacun(String tekuciRacun);

	Student update(StudentDto dto);
	
	List<Student> findAllList();

	List<PredispitneObaveze> getLatestPredispitneObaveze(Student student, Long predmetId, Date datum);

	Page<Student> findAll(String ime, String prezime, Pageable pageable);

	Student changePassword(Student student);

	Student create(Student student);
	
	Student findOneByBrojTelefona(String brojTelefona);


	StudentDto studentDtoMaker(Student student);

	void updateChatBotIdentitet(Student student, boolean subscribedTelegram);


}
