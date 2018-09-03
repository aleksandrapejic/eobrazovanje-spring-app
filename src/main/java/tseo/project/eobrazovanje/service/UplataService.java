package tseo.project.eobrazovanje.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tseo.project.eobrazovanje.dto.UplataDto;
import tseo.project.eobrazovanje.entity.Student;
import tseo.project.eobrazovanje.entity.Uplata;
import tseo.project.eobrazovanje.repository.UplataRepository;
import tseo.project.eobrazovanje.service.interfaces.UplataServiceInterface;

@Service
public class UplataService implements UplataServiceInterface {

	@Autowired
	UplataRepository uplataRepository;

	@Autowired
	StudentService studentService;

	@Override
	public Page<Uplata> findAll(Pageable pageable) {
		return uplataRepository.findAll(pageable);
	}

	@Override
	public Uplata findOne(Long id) {
		return uplataRepository.findOne(id);
	}

	@Override
	public Uplata save(Uplata uplata) {

		return uplataRepository.save(uplata);
	}

	@Override
	public Boolean delete(Long id) {
		uplataRepository.delete(id);
		return true;
	}

	@Override
	public Uplata save(UplataDto dto) {
		Uplata uplata = new Uplata();
		uplata.setDatumUplate(new Date());
		uplata.setIznosUplate(dto.getIznosUplate());
		uplata.setPozivNaBroj(dto.getPozivNaBroj());
		uplata.setRacunPrimaoca(dto.getRacunPrimaoca());
		uplata.setSvrhaUplate(dto.getSvrhaUplate());

		Student student = studentService.findByTekuciRacun(dto.getRacunPrimaoca());
		uplata.setStudent(student);

		if (student != null) {
			if (student.getStanje() == null) {
				student.setStanje(0d);
			}
			student.setStanje(student.getStanje() + uplata.getIznosUplate());
			student.getUplate().add(uplata);
		}

		return save(uplata);
	}

	@Override
	public Page<Uplata> findByStudent(Student student, Pageable pageable) {
		return uplataRepository.findByStudent(student, pageable);
	}

}
