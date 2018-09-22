package tseo.project.eobrazovanje.service;

import java.util.List;

import tseo.project.eobrazovanje.dto.PredispitneObavezeDto;
import tseo.project.eobrazovanje.entity.PredispitneObaveze;

public interface PredispitneObavezeServiceInterface {

	List<PredispitneObaveze> findAll();

	PredispitneObaveze findOne(Long id);

	PredispitneObaveze save(PredispitneObaveze predispitneObaveze);

	Boolean delete(Long id);

	PredispitneObaveze save(PredispitneObavezeDto dto);

	PredispitneObaveze update(PredispitneObavezeDto dto);
}
