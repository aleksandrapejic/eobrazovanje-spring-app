package tseo.project.eobrazovanje.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tseo.project.eobrazovanje.dto.PredispitneObavezeDto;
import tseo.project.eobrazovanje.entity.PredispitneObaveze;
import tseo.project.eobrazovanje.repository.PredispitneObavezeRepository;
import tseo.project.eobrazovanje.service.PredispitneObavezeServiceInterface;

@Service
public class PredispitneObavezeService implements PredispitneObavezeServiceInterface {

	@Autowired
	PredispitneObavezeRepository predispitneObavezeRepository;

	@Autowired
	PredispitneObavezeSablonService sablonService;

	@Autowired
	StudentService studentService;

	@Override
	public List<PredispitneObaveze> findAll() {
		return predispitneObavezeRepository.findAll();
	}

	@Override
	public PredispitneObaveze findOne(Long id) {
		return predispitneObavezeRepository.findOne(id);
	}

	@Override
	public PredispitneObaveze save(PredispitneObaveze predispitneObaveze) {
		predispitneObavezeRepository.save(predispitneObaveze);
		return predispitneObaveze;
	}

	@Override
	public Boolean delete(Long id) {
		predispitneObavezeRepository.delete(id);
		return true;
	}

	@Override
	public PredispitneObaveze save(PredispitneObavezeDto dto) {
		PredispitneObaveze pObaveze = new PredispitneObaveze();
		pObaveze.setDatum(new Date());
		pObaveze.setSablon(sablonService.findOne(dto.getSablon()));
		pObaveze.setOsvojeniBodovi(dto.getOsvojeniBodovi());
		pObaveze.setStudent(studentService.findOne(dto.getStudent()));
		if (pObaveze.getOsvojeniBodovi() >= pObaveze.getSablon().getMinimumBodova()) {
			pObaveze.setPolozio(true);
		} else {
			pObaveze.setPolozio(false);
		}
		return save(pObaveze);
	}

	@Override
	public PredispitneObaveze update(PredispitneObavezeDto dto) {
		PredispitneObaveze pObaveze = findOne(dto.getId());
		if (pObaveze == null) {
			return null;
		} else {
			pObaveze.setId(dto.getId());
			pObaveze.setDatum(dto.getDatum());
			pObaveze.setSablon(sablonService.findOne(dto.getSablon()));
			pObaveze.setOsvojeniBodovi(dto.getOsvojeniBodovi());
			pObaveze.setStudent(studentService.findOne(dto.getStudent()));
			if (pObaveze.getOsvojeniBodovi() >= pObaveze.getSablon().getMinimumBodova()) {
				pObaveze.setPolozio(true);
			} else {
				pObaveze.setPolozio(false);
			}
			return save(pObaveze);
		}
	}
}
