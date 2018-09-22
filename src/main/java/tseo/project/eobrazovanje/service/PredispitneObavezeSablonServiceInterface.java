package tseo.project.eobrazovanje.service;

import java.util.List;

import tseo.project.eobrazovanje.dto.PredispitneObavezeSablonDto;
import tseo.project.eobrazovanje.entity.PredispitneObavezeSablon;

public interface PredispitneObavezeSablonServiceInterface {
	List<PredispitneObavezeSablon> findAll();

	PredispitneObavezeSablon findOne(Long id);

	PredispitneObavezeSablon save(PredispitneObavezeSablon predispitneObavezeSablon);

	Boolean delete(Long id);

	PredispitneObavezeSablon save(PredispitneObavezeSablonDto dto);
}
