package tseo.project.eobrazovanje.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import tseo.project.eobrazovanje.dto.PredispitneObavezeDto;
import tseo.project.eobrazovanje.entity.PredispitneObaveze;
import tseo.project.eobrazovanje.service.impl.NastavnikService;
import tseo.project.eobrazovanje.service.impl.PredispitneObavezeSablonService;
import tseo.project.eobrazovanje.service.impl.PredispitneObavezeService;
import tseo.project.eobrazovanje.service.impl.PredmetService;
import tseo.project.eobrazovanje.service.impl.PrijavaService;
import tseo.project.eobrazovanje.service.impl.StudentService;

@RestController
@RequestMapping("/api/predispitne-obaveze")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PredispitneObavezeController {

	@Autowired
	PredispitneObavezeService obavezeService;

	@Autowired
	PredispitneObavezeSablonService sablonService;

	@Autowired
	NastavnikService nastavnikService;

	@Autowired
	PredmetService predmetService;

	@Autowired
	PrijavaService prijavaService;

	@Autowired
	StudentService studentService;

	@GetMapping
	public ResponseEntity getAll() {

		return new ResponseEntity(obavezeService.findAll(), HttpStatus.OK);

	}

	@PreAuthorize("hasAuthority('NASTAVNIK')")
	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		PredispitneObaveze pObaveze = obavezeService.findOne(id);
		if (pObaveze != null) {
			return new ResponseEntity(pObaveze, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAuthority('NASTAVNIK')")
	@PostMapping
	public ResponseEntity postOne(@Validated @RequestBody PredispitneObavezeDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto != null) {
			PredispitneObaveze predispitneObaveze = obavezeService.save(dto);
			return new ResponseEntity(predispitneObaveze, HttpStatus.CREATED);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAuthority('NASTAVNIK')")
	@DeleteMapping("/{id}")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		PredispitneObaveze pObaveze = obavezeService.findOne(id);
		if (pObaveze != null) {
			obavezeService.delete(pObaveze.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	@PreAuthorize("hasAuthority('NASTAVNIK')")
	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody PredispitneObavezeDto dto,
			Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			PredispitneObaveze pObaveze = obavezeService.update(dto);
			if (pObaveze == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity(pObaveze, HttpStatus.OK);
			}
		}
	}

}
