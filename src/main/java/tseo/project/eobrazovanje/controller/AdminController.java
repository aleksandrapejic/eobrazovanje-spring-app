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

import tseo.project.eobrazovanje.dto.AdminDto;
import tseo.project.eobrazovanje.entity.Admin;
import tseo.project.eobrazovanje.service.impl.AdminService;

@RestController
@RequestMapping("/api/admin")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AdminController {

	@Autowired
	AdminService adminService;

	
	/** ej sta radis__* nemoj nist askidati sa githaba itd to je prastaro tamo
	 * Hteo sam da vidim promene
	 * aha okej cekiraj 
	 * Returns a ResponseEntity with the body containing a 
	 * list of all Admin objects. 
	 *
	 * @return      ResponseEntity with the body containing
	 * 				all Admin objects.
	 * @see         Admin
	 */
	@GetMapping
	public ResponseEntity getAll() {

		return new ResponseEntity(adminService.findAll(), HttpStatus.OK);

	}

	/**
	 * Returns an Admin object based on the given id. 
	 *
	 * @param  id	identifier of the Admin
	 * @return      ResponseEntity with the body containing the
	 * 				Admin with the given id, or no body.
	 * @see         Admin
	 */
	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		Admin admin = adminService.findOne(id);
		if (admin != null) {
			return new ResponseEntity(admin, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Creates an Admin object. 
	 *
	 * @param  dto	Admin to be created.
	 * @return      ResponseEntity with the body containing the created
	 * 				Admin or an Error object, or no body.
	 * @see         Admin, Error
	 */
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@PostMapping
	public ResponseEntity postOne(@Validated @RequestBody AdminDto dto, Errors errors) {
		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto != null) {
			Admin admin = adminService.save(dto);
			return new ResponseEntity(admin, HttpStatus.CREATED);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Deletes an Admin with the given id. 
	 *
	 * @param  id	identifier of the Admin to be deleted
	 * @return      ResponseEntity with no body.
	 */
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@DeleteMapping("/{id}")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		Admin admin = adminService.findOne(id);
		if (admin != null) {
			adminService.delete(admin.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * Updates and returns an Admin object. 
	 *
	 * @param  id	identifier of the Admin to be updated.
	 * @param dto	AdminDto containing the update information.
	 * @return      ResponseEntity with the body containing the
	 * 				updated Admin object or an Error, or no body.
	 * @see         Administrator, Error
	 */
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@PutMapping("/{id}")
	public ResponseEntity putOne(@PathVariable("id") long id, @Validated @RequestBody AdminDto dto, Errors errors) {

		if (errors.hasErrors()) {
			return new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		if (dto.getId() != id) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			Admin admin = adminService.update(dto);
			if (admin == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity(admin, HttpStatus.OK);
			}

		}
	}
}
