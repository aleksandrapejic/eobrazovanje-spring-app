package tseo.project.eobrazovanje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tseo.project.eobrazovanje.dto.PredispitneObavezeSablonDto;
import tseo.project.eobrazovanje.entity.PredispitneObaveze;
import tseo.project.eobrazovanje.entity.PredispitneObavezeSablon;
import tseo.project.eobrazovanje.repository.PredispitneObavezeSablonRepository;
import tseo.project.eobrazovanje.service.interfaces.PredispitneObavezeSablonServiceInterface;

@Service
public class PredispitneObavezeSablonService implements PredispitneObavezeSablonServiceInterface {

	@Autowired
	PredispitneObavezeSablonRepository sablonRepository;

	@Autowired
	NastavnikService nastavnikService;

	@Autowired
	PredispitneObavezeService predispitneObavezeService;

	@Autowired
	PredmetService predmetService;

	@Override
	public List<PredispitneObavezeSablon> findAll() {
		return sablonRepository.findAll();
	}

	@Override
	public PredispitneObavezeSablon findOne(Long id) {
		return sablonRepository.findOne(id);
	}

	@Override
	public PredispitneObavezeSablon save(PredispitneObavezeSablon predispitneObaveze) {
		sablonRepository.save(predispitneObaveze);
		return predispitneObaveze;
	}

	@Override
	public Boolean delete(Long id) {
		PredispitneObavezeSablon sablon = findOne(id);
		for (PredispitneObaveze p : sablon.getPolaganja()) {
			predispitneObavezeService.delete(p.getId());
		}
		sablonRepository.delete(id);
		return true;
	}

	@Override
	public PredispitneObavezeSablon save(PredispitneObavezeSablonDto dto) {
		PredispitneObavezeSablon predispitneObavezeSablon = new PredispitneObavezeSablon();
		if (dto.getId() != null)
			predispitneObavezeSablon.setId(dto.getId());
		predispitneObavezeSablon.setMinimumBodova(dto.getMinimumBodova());
		predispitneObavezeSablon.setNaziv(dto.getNaziv());
		predispitneObavezeSablon.setUkupnoBodova(dto.getUkupnoBodova());
		predispitneObavezeSablon.setPredmet(predmetService.findOne(dto.getPredmet()));
		return save(predispitneObavezeSablon);
	}

}
