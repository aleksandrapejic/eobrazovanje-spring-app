package tseo.project.eobrazovanje.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tseo.project.eobrazovanje.dto.AdminDto;
import tseo.project.eobrazovanje.entity.Admin;
import tseo.project.eobrazovanje.repository.AdminRepository;
import tseo.project.eobrazovanje.service.AdminServiceInterface;

@Service
public class AdminService implements AdminServiceInterface {

	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

	@Override
	public List<Admin> findAll() {
		return adminRepository.findAll();
	}

	@Override
	public Admin findOne(Long id) {
		return adminRepository.findOne(id);
	}

	@Override
	public Admin save(Admin admin) {
		adminRepository.save(admin);
		return admin;
	}

	@Override
	public Admin changePassword(Admin admin) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return save(admin);
	}

	@Override
	public Boolean delete(Long id) {
		adminRepository.delete(id);
		return true;
	}

	@Override
	public Admin save(AdminDto adminDto) {
		Admin admin = new Admin();
		admin.setIme(adminDto.getIme());
		admin.setJmbg(adminDto.getJmbg());
		admin.setPrezime(adminDto.getPrezime());
		admin.setUsername(adminDto.getUsername());
		admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
		return save(admin);
	}

	@Override
	public Admin update(AdminDto adminDto) {
		Admin admin = findOne(adminDto.getId());
		if (admin == null) {
			return null;
		} else {
			admin.setIme(adminDto.getIme());
			admin.setJmbg(adminDto.getJmbg());
			admin.setPrezime(adminDto.getPrezime());
			admin.setUsername(adminDto.getUsername());
			admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
			return admin;
		}
	}
}
