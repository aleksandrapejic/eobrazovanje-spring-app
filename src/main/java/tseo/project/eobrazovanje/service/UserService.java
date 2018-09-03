package tseo.project.eobrazovanje.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tseo.project.eobrazovanje.dto.PasswordDto;
import tseo.project.eobrazovanje.entity.User;
import tseo.project.eobrazovanje.repository.UserRepository;
import tseo.project.eobrazovanje.service.interfaces.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface, UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public Boolean delete(Long id) {
		userRepository.delete(id);
		return true;
	}

	@Override
	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
		return authorities;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
				getGrantedAuthorities(user));

	}

	@Override
	public ResponseEntity<Void> updatePassword(User user, PasswordDto passwordDto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (!(passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
		save(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
