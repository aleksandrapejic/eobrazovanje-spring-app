package tseo.project.eobrazovanje.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import tseo.project.eobrazovanje.dto.DokumentDto;
import tseo.project.eobrazovanje.entity.Dokument;
import tseo.project.eobrazovanje.entity.Student;

public interface DokumentServiceInterface {

	Dokument findOne(Long id);

	Dokument save(Dokument dokument);

	Boolean delete(Long id);

	Dokument save(MultipartFile file, String naziv, Student student);

	Dokument update(DokumentDto dto);

	Page<Dokument> findAll(String naziv, Pageable pageable);
}
