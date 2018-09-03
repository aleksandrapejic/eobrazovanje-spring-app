package tseo.project.eobrazovanje.service.interfaces;

import java.util.List;

import tseo.project.eobrazovanje.dto.AdminDto;
import tseo.project.eobrazovanje.entity.Admin;

public interface AdminServiceInterface {

	List<Admin> findAll();

	Admin findOne(Long id);

	Admin save(Admin admin);

	Boolean delete(Long id);

	Admin save(AdminDto adminDto);

	Admin update(AdminDto adminDto);

	Admin changePassword(Admin admin);
}
