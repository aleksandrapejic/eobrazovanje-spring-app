package tseo.project.eobrazovanje.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import tseo.project.eobrazovanje.dto.StudentDto;
import tseo.project.eobrazovanje.entity.ChatBotIdentitet;
import tseo.project.eobrazovanje.entity.PredispitneObaveze;
import tseo.project.eobrazovanje.entity.Student;
import tseo.project.eobrazovanje.entity.User;
import tseo.project.eobrazovanje.enumeration.Role;
import tseo.project.eobrazovanje.repository.StudentRepository;
import tseo.project.eobrazovanje.service.StudentServiceInterface;
import tseo.project.eobrazovanje.util.BeanUtil;

@Service
public class StudentService implements StudentServiceInterface {

	@Autowired
	StudentRepository studentRepository;
	
	
	@Override
	public Page<Student> findAll(String ime, String prezime, Pageable pageable) {
		return studentRepository.findAllByImeIgnoreCaseContainsAndPrezimeIgnoreCaseContains(ime, prezime, pageable);
	}
	
	@Override
	public List<Student> findAllList() {
		System.out.println("dosao sam dovde findalllist");
		return studentRepository.findAll();
	}

	@Override
	public Student findOne(Long id) {
		return studentRepository.findOne(id);
	}

	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student changePassword(Student student) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		return save(student);
	}

	@Override
	public Student update(StudentDto dto) {
		Student student = findOne(dto.getId());
		if (student == null) {
			return null;
		} else {
			if (!dto.getIme().equals(""))
				student.setIme(dto.getIme());
			if (!dto.getPrezime().equals(""))
				student.setPrezime(dto.getPrezime());
			if (!dto.getUsername().equals(""))
				student.setUsername(dto.getUsername());
			if (!dto.getAdresa().equals(""))
				student.setAdresa(dto.getAdresa());
			if (!dto.getBrojIndexa().equals(""))
				student.setBrojIndexa(dto.getBrojIndexa());
			if (!dto.getBrojTelefona().equals("") && !dto.getBrojTelefona().equals(student.getBrojTelefona())){
				
				student.setChatbotIdentitet(null);
				student.setBrojTelefona(dto.getBrojTelefona());
			}
			
			
			return save(student);
		}
	}

	@Override
	public Boolean delete(Long id) {
		studentRepository.delete(id);
		return true;
	}

	@Override
	public Student findByTekuciRacun(String tekuciRacun) {
		return studentRepository.findByTekuciRacun(tekuciRacun);
	}

	@Override
	public List<PredispitneObaveze> getLatestPredispitneObaveze(Student student, Long predmetId, Date datum) {
		List<PredispitneObaveze> result = student.getPredispitneObaveze().stream()
				.filter(p -> p.getSablon().getPredmet().getId() == predmetId && p.getDatum().before(datum))
				.collect(Collectors.toList());
		ArrayList<PredispitneObaveze> ret = new ArrayList<>();
		result.forEach(po -> {
			boolean isMatch = false;
			for (int i = 0; i < ret.size(); i++)
				if (po.getSablon().getId() == ret.get(i).getSablon().getId()) {
					isMatch = true;
					if ((po.getDatum().after(ret.get(i).getDatum()))
							|| (po.getDatum().equals(ret.get(i).getDatum()) && po.getId() > ret.get(i).getId())) {
						ret.remove(ret.get(i));
						ret.add(po);
					}
				}
			if (!isMatch) {
				ret.add(po);
			}
		});

		// VRACA I NEPOLOZENE, NAKON REFINEMENTA MORAMO PROMENITI LINIJU 71!
		return ret;
	}

	@Override
	public Student create(Student student) {
		return changePassword(student);
	}
	
	@Override
	public Student findOneByBrojTelefona(String broj) {
		
		
		System.out.println("RADI REPO" + broj);
		List<Student> studenti = studentRepository.findAll();
		for(Student student: studenti){
			System.out.println( student.getIme() + "STUDENT");
		}
		
		
		Student student = studentRepository.findOneByBrojTelefona(broj);
		System.out.println(student + broj );
		return student;
	}

	@Override
	public void updateChatBotIdentitet(Student student, boolean subscribedTelegram) {
		
		System.out.println("usao sam u update chat bot identiteta u student servisu");
		ChatBotIdentitet chatBotIdentitet = BeanUtil.getChatIdentitetService().findOneByUser(student);
		if(chatBotIdentitet != null){

			BeanUtil.getChatIdentitetService().updateChatBotIdentitetSubscribe(chatBotIdentitet, subscribedTelegram);
			
		}
		else{
			System.out.println("usao sam u else u apdejtu, nesto ne valja");
			
		}
		
			
		}

	@Override
	public StudentDto studentDtoMaker(Student student) {
		
		
		ChatBotIdentitet identitet = BeanUtil.getChatIdentitetService().findOneByUser(student);
		
		if(student.getChatbotIdentitet() != null){

			StudentDto dto = dtoBasic(student);
			dto.setSubscribedTelegram(identitet.isSubscribedTelegram());		
			dto.setChatId(identitet.getChatId());
			
			return dto;
		}
		else{
			
			return dtoBasic(student);
		}
	}
		
	
	public StudentDto dtoBasic(Student student) {
		
		StudentDto dto = new StudentDto();
		dto.setId(student.getId());
		dto.setAdresa(student.getAdresa());
		dto.setIme(student.getIme());
		dto.setPrezime(student.getPrezime());
		dto.setBrojTelefona(student.getBrojTelefona());
		dto.setJmbg(student.getJmbg());
		dto.setUsername(student.getUsername());
		dto.setBrojIndexa(student.getBrojIndexa());
		dto.setRole(Role.STUDENT);
		dto.setStanje(student.getStanje());
		
		return dto;
	}
	
}
