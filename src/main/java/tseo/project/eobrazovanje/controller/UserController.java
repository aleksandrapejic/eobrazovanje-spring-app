package tseo.project.eobrazovanje.controller;

import java.util.Optional;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tseo.project.eobrazovanje.enumeration.Role;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import tseo.project.eobrazovanje.dto.PasswordDto;
import tseo.project.eobrazovanje.dto.StudentDto;
import tseo.project.eobrazovanje.entity.Admin;
import tseo.project.eobrazovanje.entity.ChatBotIdentitet;
import tseo.project.eobrazovanje.entity.Student;
import tseo.project.eobrazovanje.entity.User;
import tseo.project.eobrazovanje.service.StudentServiceInterface;
import tseo.project.eobrazovanje.service.UserServiceInterface;
import tseo.project.eobrazovanje.service.impl.ChatBotIdentitetService;
import tseo.project.eobrazovanje.service.impl.StudentService;

@RestController
@RequestMapping("/api/users")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UserController {
	
	
	

	@Autowired
	UserServiceInterface userService;
	
	@Autowired
	StudentServiceInterface studentService;

	/**
	 * Returns a ResponseEntity with the body containing a 
	 * list of all User objects. 
	 *
	 * @return      ResponseEntity with the body containing
	 * 				all User objects.
	 * @see         User
	 */
	@GetMapping()
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity getAll() {
		return new ResponseEntity(userService.findAll(), HttpStatus.OK);
	}

	/**
	 * Returns a User object based on the given id. 
	 *
	 * @param  id	identifier of the User
	 * @return      ResponseEntity with the body containing the
	 * 				User with the given id, or no body.
	 * @see         User
	 */
	@GetMapping("/{id}")
	public ResponseEntity getOne(@PathVariable("id") long id) {
		User user = userService.findOne(id);
		if (user != null) {
			return new ResponseEntity(user, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Deletes a User with the given id. 
	 *
	 * @param  id	identifier of the User to be deleted
	 * @return      ResponseEntity with no body.
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity deleteOne(@PathVariable("id") long id) {
		User user = userService.findOne(id);
		if (user != null) {
			userService.delete(user.getId());
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Updates the password of the authenticated user. 
	 *
	 * @param  passwordDto	PasswordDto object containing 
	 * 						the old and new passwords.
	 * @return      		ResponseEntity with no body.
	 * @see         		PasswordDto
	 */
	@PutMapping("/password")
	public ResponseEntity<Void> updatePassword(@RequestBody PasswordDto passwordDto, Errors errors) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUserName(auth.getPrincipal().toString());
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return userService.updatePassword(user, passwordDto);
	}

	/**
	 * Checks if a User with the given username exists.
	 *
	 * @param  username		sername to be checked.
	 *
	 * @return		     	ResponseEntity with HttpStatus FOUND or NOT_FOUND.
	 * @see		    		User
	 */
	@GetMapping("/username-check")
	public ResponseEntity<Void> checkUsername(@RequestParam(value = "username", defaultValue = "") String username) {
		User user = userService.findByUserName(username);
		if (user != null) {
			return new ResponseEntity<>(HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Returns a User object based on the given username. 
	 *
	 * @param  username		username of the User
	 * @return      		ResponseEntity with the body containing the
	 * 						User with the given username, or no body.
	 * @see         		User
	 */
	@GetMapping("/user/{username}")
	public ResponseEntity getUserByUsername(@PathVariable("username") String username) {
		User user = userService.findByUserName(username);
		
		if (user != null) {
			if(user.getRole().equals(Role.STUDENT)){
				Student student = studentService.findOne(user.getId());
				StudentDto studentdto = studentService.studentDtoMaker(student);
				
				return ResponseEntity.ok(studentdto);
			}else{
				return ResponseEntity.ok(user);
				}
			
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

}
