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
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.generics.BotSession;

import tseo.project.eobrazovanje.dto.IspitDto;
import tseo.project.eobrazovanje.entity.Ispit;
import tseo.project.eobrazovanje.entity.NotificationBot;
import tseo.project.eobrazovanje.entity.Prijava;
import tseo.project.eobrazovanje.service.impl.BotCommandsService;
import tseo.project.eobrazovanje.service.impl.IspitService;
import tseo.project.eobrazovanje.service.impl.NastavnikService;
import tseo.project.eobrazovanje.service.impl.PredmetService;
import tseo.project.eobrazovanje.service.impl.PrijavaService;
import tseo.project.eobrazovanje.service.impl.StudentService;
import tseo.project.eobrazovanje.util.BeanUtil;

@RestController
@RequestMapping("/api/ispit")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IspitController {

	@Autowired
	IspitService ispitService;

	@Autowired
	NastavnikService nastavnikService;

	@Autowired
	StudentService studentService;

	@Autowired
	PredmetService predmetService;

	@Autowired
	PrijavaService prijavaService;

	@GetMapping
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity getAll() {

		return new ResponseEntity(ispitService.findAll(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Ispit ispit = ispitService.findOne(id);
		if (ispit != null) {
			return new ResponseEntity(ispit, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/prijave")
	@PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'NASTAVNIK')")
	public ResponseEntity getPrijave(@PathVariable("id") long id, Pageable pageable) {
		Ispit ispit = ispitService.findOne(id);
		if (ispit != null) {
			Page<Prijava> prijave = prijavaService.getByIspit(ispit, pageable);
			HttpHeaders headers = new HttpHeaders();
			headers.set("total", String.valueOf(prijave.getTotalPages()));
			
			return ResponseEntity.ok().headers(headers).body(prijave.getContent());
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	
	private NotificationBot getBot() {
        return BeanUtil.getBot();
    }
		

	@PostMapping
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity postOne(@Validated @RequestBody IspitDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto != null) {
			
			Ispit ispit = ispitService.save(dto);
			
			return new ResponseEntity(ispit, HttpStatus.CREATED);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		Ispit ispit = ispitService.findOne(id);
		if (ispit != null) {
			ispitService.delete(ispit.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody IspitDto dto, Errors errors) {

		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			Ispit ispit = ispitService.update(dto);
			if (ispit == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity(ispit, HttpStatus.OK);
			}
		}
	}
}
