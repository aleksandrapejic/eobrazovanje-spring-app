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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tseo.project.eobrazovanje.dto.DokumentDto;
import tseo.project.eobrazovanje.entity.Dokument;
import tseo.project.eobrazovanje.service.DokumentService;
import tseo.project.eobrazovanje.service.StudentService;

@RestController
@RequestMapping("/api/dokument")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DokumentController {

	@Autowired
	DokumentService dokumentService;

	@Autowired
	StudentService studentService;

	@GetMapping
	public ResponseEntity getAll(@RequestParam(value = "naziv", defaultValue = "") String naziv, Pageable pageable) {

		Page<Dokument> dokumenti = dokumentService.findAll(naziv, pageable);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(dokumenti.getTotalPages()));

		return ResponseEntity.ok().headers(headers).body(dokumenti.getContent());

	}

	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Dokument dokument = dokumentService.findOne(id);
		if (dokument != null) {
			return new ResponseEntity(dokument, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@DeleteMapping("/{id}")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		Dokument dokument = dokumentService.findOne(id);
		if (dokument != null) {
			dokumentService.delete(dokument.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody DokumentDto dto, Errors errors) {

		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {

			Dokument dokument = dokumentService.update(dto);
			if (dokument == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity(dokument, HttpStatus.OK);
			}
		}
	}
}
