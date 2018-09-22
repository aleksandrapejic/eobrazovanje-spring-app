package tseo.project.eobrazovanje.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tseo.project.eobrazovanje.dto.NastavnikDto;
import tseo.project.eobrazovanje.entity.Nastavnik;
import tseo.project.eobrazovanje.repository.NastavnikRepository;
import tseo.project.eobrazovanje.service.NastavnikServiceInterface;

@Service
public class NastavnikService implements NastavnikServiceInterface {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	NastavnikRepository nastavnikRepository;

	@Override
	public Page<Nastavnik> findAll(String ime, String prezime, Pageable pageable) {
		return nastavnikRepository.findAllByImeIgnoreCaseContainsAndPrezimeIgnoreCaseContains(ime, prezime, pageable);
	}

	@Override
	public Nastavnik changePassword(Nastavnik nastavnik) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		nastavnik.setPassword(passwordEncoder.encode(nastavnik.getPassword()));
		return save(nastavnik);
	}

	@Override
	public Nastavnik findOne(Long id) {
		return nastavnikRepository.findOne(id);
	}

	@Override
	public Nastavnik save(Nastavnik nastavnik) {
		nastavnikRepository.save(nastavnik);
		return nastavnik;
	}

	@Override
	public Boolean delete(Long id) {
		nastavnikRepository.delete(id);
		return true;
	}

	@Override
	public Nastavnik save(NastavnikDto dto) {
		Nastavnik nastavnik = new Nastavnik();
		nastavnik.setIme(dto.getIme());
		nastavnik.setJmbg(dto.getJmbg());
		nastavnik.setPrezime(dto.getPrezime());
		nastavnik.setUsername(dto.getUsername());
		nastavnik.setPassword(passwordEncoder.encode(dto.getPassword()));
		return save(nastavnik);
	}

	@Override
	public Nastavnik update(NastavnikDto dto) {
		Nastavnik nastavnik = findOne(dto.getId());
		if (nastavnik == null) {
			return null;
		} else {
			if (!dto.getIme().equals(""))
				nastavnik.setIme(dto.getIme());
			if (!dto.getPrezime().equals(""))
				nastavnik.setPrezime(dto.getPrezime());
			if (!dto.getUsername().equals(""))
				nastavnik.setUsername(dto.getUsername());
			if (!dto.getAdresa().equals(""))
				nastavnik.setAdresa(dto.getAdresa());
			return save(nastavnik);
		}
	}

}
