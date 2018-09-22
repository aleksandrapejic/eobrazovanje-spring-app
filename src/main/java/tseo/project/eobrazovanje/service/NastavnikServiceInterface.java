package tseo.project.eobrazovanje.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tseo.project.eobrazovanje.dto.NastavnikDto;
import tseo.project.eobrazovanje.entity.Nastavnik;

public interface NastavnikServiceInterface {

	Nastavnik findOne(Long id);

	Nastavnik save(Nastavnik nastavnik);

	Boolean delete(Long id);

	Nastavnik save(NastavnikDto dto);

	Nastavnik update(NastavnikDto dto);

	Page<Nastavnik> findAll(String ime, String prezime, Pageable pageable);

	Nastavnik changePassword(Nastavnik nastavnik);

}
