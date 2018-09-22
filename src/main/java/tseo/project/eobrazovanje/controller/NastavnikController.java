package tseo.project.eobrazovanje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tseo.project.eobrazovanje.dto.NastavnikDto;
import tseo.project.eobrazovanje.entity.Nastavnik;
import tseo.project.eobrazovanje.service.impl.NastavnikService;

@RestController
@RequestMapping("/api/nastavnik")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class NastavnikController {

	@Autowired
	NastavnikService nastavnikService;

	@GetMapping
	public ResponseEntity getAll(@RequestParam(value = "ime", defaultValue = "") String ime,
			@RequestParam(value = "prezime", defaultValue = "") String prezime, Pageable pageable) {

		Page<Nastavnik> nastavnici = nastavnikService.findAll(ime, prezime, pageable);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(nastavnici.getTotalPages()));

		return ResponseEntity.ok().headers(headers).body(nastavnici.getContent());

	}

	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Nastavnik nastavnik = nastavnikService.findOne(id);
		if (nastavnik != null) {
			return new ResponseEntity(nastavnik, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity postOne(@Validated @RequestBody Nastavnik nastavnik, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (nastavnik != null) {
			return new ResponseEntity(nastavnikService.save(nastavnik), HttpStatus.CREATED);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		Nastavnik nastavnik = nastavnikService.findOne(id);
		if (nastavnik != null) {
			nastavnikService.delete(nastavnik.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody NastavnikDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			Nastavnik nastavnik = nastavnikService.update(dto);
			if (nastavnik == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity(nastavnik, HttpStatus.OK);
			}
		}
	}

	@GetMapping("/{id}/predmeti")
	public ResponseEntity getPredmeti(@PathVariable("id") long id) {
		Nastavnik nastavnik = nastavnikService.findOne(id);
		if (nastavnik != null) {
			return new ResponseEntity(nastavnik.getPredmeti(), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}
