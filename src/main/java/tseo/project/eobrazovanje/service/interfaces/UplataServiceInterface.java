package tseo.project.eobrazovanje.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tseo.project.eobrazovanje.dto.UplataDto;
import tseo.project.eobrazovanje.entity.Student;
import tseo.project.eobrazovanje.entity.Uplata;

public interface UplataServiceInterface {

	Uplata findOne(Long id);

	Uplata save(Uplata uplata);

	Boolean delete(Long id);

	Uplata save(UplataDto dto);

	Page<Uplata> findAll(Pageable pageable);

	Page<Uplata> findByStudent(Student student, Pageable pageable);
}
