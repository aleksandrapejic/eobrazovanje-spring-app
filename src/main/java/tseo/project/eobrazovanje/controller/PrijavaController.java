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

import tseo.project.eobrazovanje.dto.PrijavaDto;
import tseo.project.eobrazovanje.entity.NotificationBot;
import tseo.project.eobrazovanje.entity.Prijava;
import tseo.project.eobrazovanje.entity.Student;
import tseo.project.eobrazovanje.entity.User;
import tseo.project.eobrazovanje.service.IspitServiceInterface;
import tseo.project.eobrazovanje.service.NastavnikServiceInterface;
import tseo.project.eobrazovanje.service.PrijavaServiceInterface;
import tseo.project.eobrazovanje.service.StudentServiceInterface;
import tseo.project.eobrazovanje.util.BeanUtil;

@RestController
@RequestMapping("/api/prijave")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PrijavaController {

	@Autowired
	PrijavaServiceInterface prijavaService;

	@Autowired
	IspitServiceInterface ispitService;

	@Autowired
	NastavnikServiceInterface nastavnikService;

	@Autowired
	StudentServiceInterface studentService;
	
	
	/**
	 * Returns a NotificationBot from context 
	 *
	 */
	private NotificationBot getBot() {
		
		return BeanUtil.getBot();
	}
	
	/**
	 * Returns a list of Prijave objects. 
	 *
	 * 
	 * @return      		ResponseEntity with the body containing the
	 * 						list of Prijave objects
	 * @see         		Prijava
	 */
	@GetMapping
	public ResponseEntity getAll(Pageable pageable) {

		Page<Prijava> prijave = prijavaService.findAll(pageable);
		HttpHeaders headers = new HttpHeaders();
		headers.set("total", String.valueOf(prijave.getTotalPages()));

		return ResponseEntity.ok().headers(headers).body(prijave.getContent());

	}
	
	/**
	 * Returns a Prijava object based on id. 
	 *
	 * 
	 * @param	      		id
	 * @return				ResponseEntity with the body containing Prijava object
	 * 						based on the id.
	 * @see         		Prijava
	 */
	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Prijava prijava = prijavaService.findOne(id);
		if (prijava != null) {
			return new ResponseEntity(prijava, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Returns a new Prijava object. 
	 *
	 * 
	 * @param	      		dto
	 * @return				ResponseEntity with the body containing new Prijava object
	 * 						made from dto
	 * @see         		Prijava
	 */
	@PostMapping
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity postOne(@Validated @RequestBody PrijavaDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		System.out.println(dto);
		Prijava prijava = prijavaService.save(dto);

		if (prijava == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(prijava, HttpStatus.CREATED);
	}
	/**
	 * Returns a NO_CONTENT status after deleting a Prijava object. 
	 *
	 * 
	 * @param	      		id
	 * @return				ResponseEntity with the NO_CONTENT status, after deleting a Prijava object
	 * 						based on the id.
	 * @see         		Prijava
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('STUDENT')")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		if (prijavaService.delete(id)) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Returns a Prijava object based on id and sends a notification using NotificationBot. 
	 *
	 * 
	 * @param	      		dto
	 * @return				ResponseEntity with the body containing Prijava object
	 * 						based on the dto.
	 * @see         		Prijava
	 */
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('NASTAVNIK')")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody PrijavaDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			Prijava prijava = prijavaService.update(dto);
			if (prijava == null) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			} else {
				if(prijava.isOcenjeno()){
					Student student = studentService.findOne(dto.getStudent());					
					getBot().posaljiObavestenje(prijava, student );
				}
				return new ResponseEntity(prijava, HttpStatus.CREATED);
			}
		}
	}



}
