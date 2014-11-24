package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Auditor;
import domain.Folder;

import repositories.AuditorRepository;
import security.Authority;
import security.LoginService;

@Service
@Transactional
public class AuditorService {
	
	@Autowired
	private AuditorRepository auditorRepository;
	
	@Autowired
	public FolderService folderService;
	
	public AuditorService(){
		super();
	}

	public Auditor create(){
		Auditor a= new Auditor();
		return a;
	}
	
	public void save(Auditor s){
		if(s.getId()==0){
			Authority a = new Authority();
			a.setAuthority(Authority.AUDITOR);
			List<Authority> as = new ArrayList<Authority>();
			s.getUserAccount().setAuthorities(as);
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			String pass = s.getUserAccount().getPassword();
			s.getUserAccount().setPassword(encoder.encodePassword(pass, null));
		}else{
			Auditor saux = auditorRepository.findOne(s.getId());
			Assert.isTrue(saux.getUserAccount().equals(s.getUserAccount()));
			
		}
		auditorRepository.save(s);
	}
	
	public Auditor findByPrincipal(){
		return auditorRepository.findAuditorByUAId(LoginService.getPrincipal().getId());
	}
}
