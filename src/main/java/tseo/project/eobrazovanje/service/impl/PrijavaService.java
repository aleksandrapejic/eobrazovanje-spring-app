package tseo.project.eobrazovanje.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tseo.project.eobrazovanje.dto.PrijavaDto;
import tseo.project.eobrazovanje.entity.Ispit;
import tseo.project.eobrazovanje.entity.PredispitneObaveze;
import tseo.project.eobrazovanje.entity.Prijava;
import tseo.project.eobrazovanje.entity.Student;
import tseo.project.eobrazovanje.repository.PrijavaRepository;
import tseo.project.eobrazovanje.service.PrijavaServiceInterface;

@Service
public class PrijavaService implements PrijavaServiceInterface {

	@Autowired
	PrijavaRepository prijavaRepository;

	@Autowired
	IspitService ispitService;

	@Autowired
	NastavnikService nastavnikService;

	@Autowired
	StudentService studentService;

	@Override
	public Page<Prijava> findAll(Pageable pageable) {
		return prijavaRepository.findAll(pageable);
	}

	@Override
	public Prijava findOne(Long id) {
		return prijavaRepository.findOne(id);
	}

	@Override
	public Prijava save(Prijava prijava) {
		return prijavaRepository.save(prijava);
	}

	@Override
	public Boolean delete(Long id) {
		Prijava prijava = prijavaRepository.findOne(id);
		Student student = prijava.getStudent();
		System.out.println(student);
		if (student == null || prijava == null) {
			return null;
		}
		if (student.getStanje() == null) {
			student.setStanje(0d);
		}
		System.out.println("stigao sam do ste stanje studentu");
		student.setStanje(student.getStanje() + 200);
		studentService.save(student);
		System.out.println("sacuvaj sam stanje studenta");
		System.out.println(id );
		
		prijavaRepository.delete(id);
		
		return true;
	}

	@Override
	public Prijava save(PrijavaDto dto) {
		System.out.println("stigao sam do cuvanja prijave" + dto);
		Ispit ispit = ispitService.findOne(dto.getIspit());
		Student student = studentService.findOne(dto.getStudent());

		if (ispit == null || student == null || student.getStanje() == null || student.getStanje() < 200
				|| ispit.getPredmet().getPredispitneObavezeSabloni().size() != studentService
						.getLatestPredispitneObaveze(student, ispit.getPredmet().getId(), new Date()).size()) {
			return null;

		}

		Prijava prijava = new Prijava();
		System.out.println("stigao sam do pravljenja prijave" + prijava);
		prijava.setIspit(ispit);
		prijava.setDatumPrijave(new Date());
		prijava.setStudent(student);
		student.getPrijave().add(prijava);
		if (student.getStanje() == null || student.getStanje() < 200) {
			student.setStanje(0d);
			return null;
		}
		if (prijava.getPredispitniBodovi() == null) {
			prijava.setPredispitniBodovi(0F);
		}
		student.setStanje(student.getStanje() - 200);
		for (PredispitneObaveze p : studentService.getLatestPredispitneObaveze(student, ispit.getPredmet().getId(),
				new Date())) {
			if (!p.isPolozio()) {
				return null;
			}
			prijava.setPredispitniBodovi(prijava.getPredispitniBodovi() + p.getOsvojeniBodovi());
		}
		prijava.setOsvojeniBodoviUsmeni(0F);
		prijava.setOsvojeniBodoviIspit(prijava.getPredispitniBodovi());
		System.out.println("stigao sam do cuvanja prijave" + prijava);
		return save(prijava);
	}

	@Override
	public Prijava update(PrijavaDto dto) {
		Ispit ispit = ispitService.findOne(dto.getIspit());
		Student student = studentService.findOne(dto.getStudent());
		if (ispit == null || student == null) {
			return null;
		} else {
			Prijava prijava = findOne(dto.getId());
			prijava.setOsvojeniBodoviUsmeni(dto.getOsvojeniBodoviUsmeni());
			prijava.setOcenjeno(true);
			prijava.setPredispitniBodovi(0F);
			for (PredispitneObaveze p : studentService.getLatestPredispitneObaveze(student, ispit.getPredmet().getId(),
					prijava.getDatumPrijave())) {
				prijava.setPredispitniBodovi(prijava.getPredispitniBodovi() + p.getOsvojeniBodovi());
			}
			prijava.setOsvojeniBodoviIspit(prijava.getOsvojeniBodoviUsmeni() + prijava.getPredispitniBodovi());
			if (prijava.getOsvojeniBodoviIspit() >= 51) {
				if (prijava.getOsvojeniBodoviIspit() > 100) {
					prijava.setOsvojeniBodoviIspit(100f);
				}
				prijava.setPolozio(true);
			}
			if (prijava.getOsvojeniBodoviUsmeni() < prijava.getIspit().getUsmeniMinimumBodova()) {
				prijava.setPolozio(false);
			}
			return save(prijava);
		}
	}

	@Override
	public Set<Prijava> getPolozeniPredmeti(Student student) {
		return student.getPrijave().stream().filter(p -> p.isPolozio()).collect(Collectors.toSet());
	}

	@Override
	public Page<Prijava> getPrijavaByStudent(Student student, Pageable pageable) {
		return prijavaRepository.findByStudent(student, pageable);
	}
	

	public Page<Prijava> getByIspit(Ispit ispit, Pageable pageable) {
		return prijavaRepository.findByIspit(ispit, pageable);
	}
	
	@Override
	public List<Prijava> getPrijavljeniIspiti(Student student) {
		return prijavaRepository.findByStudent(student);
	}

	public List<Prijava> getPrijavaByStudent(Student student) {
		return prijavaRepository.findByStudent(student);
	}
	

}
