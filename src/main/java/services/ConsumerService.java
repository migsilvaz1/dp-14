package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConsumerRepository;
import security.Authority;
import security.LoginService;
import domain.Assessment;
import domain.Consumer;
import domain.Folder;

@Service
@Transactional
public class ConsumerService {
	
	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Autowired
	public FolderService folderService;
	
	public ConsumerService(){
		super();
	}
	
	public Consumer Create(){
		Consumer consumer = new Consumer();
		return consumer;
	}
	
	public void save(Consumer s){
		if(s.getId()==0){
			Authority a = new Authority();
			a.setAuthority(Authority.CONSUMER);
			List<Authority> as = new ArrayList<Authority>();
			s.getUserAccount().setAuthorities(as);
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			String pass = s.getUserAccount().getPassword();
			s.getUserAccount().setPassword(encoder.encodePassword(pass, null));
		}else{
			Consumer saux = consumerRepository.findOne(s.getId());
			Assert.isTrue(saux.getUserAccount().equals(s.getUserAccount()));
			
		}
		Consumer a = consumerRepository.save(s);
		Folder inbox = new Folder();
		inbox.setActor(a);
		inbox.setErasable(false);
		inbox.setName("inbox");
		Folder outbox = new Folder();
		outbox.setActor(a);
		outbox.setErasable(false);
		outbox.setName("outbox");
		folderService.saveAux(inbox);
		folderService.saveAux(outbox);
	}
	
	public Collection<Consumer> findAll(){
		return consumerRepository.findAll();
	}
	

	public Consumer findConsumerByUAId(int id){
		return consumerRepository.findConsumerByUAId(id);
	}
	
	public Collection<Assessment> assessmentByConsumerUAId(int id){
		return consumerRepository.assessmentByConsumerUAId(id);
	}
	
	public Consumer findByPrincipal(){
		return consumerRepository.findConsumerByUAId(LoginService.getPrincipal().getId());
	}
}
