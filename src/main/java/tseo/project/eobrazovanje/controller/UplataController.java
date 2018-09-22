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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tseo.project.eobrazovanje.dto.UplataDto;
import tseo.project.eobrazovanje.entity.Uplata;
import tseo.project.eobrazovanje.service.StudentServiceInterface;
import tseo.project.eobrazovanje.service.UplataServiceInterface;

@RestController
@RequestMapping("/api/uplate")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UplataController {

	@Autowired
	UplataServiceInterface uplataService;

	@Autowired
	StudentServiceInterface studentService;

	@GetMapping
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity getAll(Pageable pageable) {

		Page<Uplata> uplate = uplataService.findAll(pageable);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(uplate.getTotalPages()));

		return ResponseEntity.ok().headers(headers).body(uplate.getContent());

	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'STUDENT')")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Uplata uplata = uplataService.findOne(id);
		if (uplata != null) {
			return new ResponseEntity(uplata, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity postOne(@Validated @RequestBody UplataDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		Uplata uplata = uplataService.save(dto);
		return new ResponseEntity(uplata, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		Uplata uplata = uplataService.findOne(id);
		if (uplata != null) {
			uplataService.delete(uplata.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

}
