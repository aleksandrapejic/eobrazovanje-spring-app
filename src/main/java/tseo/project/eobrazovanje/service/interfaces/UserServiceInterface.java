package tseo.project.eobrazovanje.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import tseo.project.eobrazovanje.dto.PasswordDto;
import tseo.project.eobrazovanje.entity.User;

public interface UserServiceInterface {

	List<User> findAll();

	User findOne(Long id);

	User save(User user);

	Boolean delete(Long id);

	User findByUserName(String username);

	ResponseEntity<Void> updatePassword(User user, PasswordDto passwordDto);

}
