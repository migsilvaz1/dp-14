package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private AuditorService auditorService;
	
	
	public MessageService(){
		super();
	}
	
	public Message create(){
		Message message = new Message();
		return message;
		
	}
	
	public Message save(Message m){
		UserAccount uA= LoginService.getPrincipal();
		
		if(m.getId()==0){
			Assert.isTrue(m.getBody()!=null);
			Assert.isTrue(m.getSubject()!=null);
			m.setMoment(new Date());
			Authority auth = uA.getAuthorities().iterator().next();
			Authority supplier = new Authority();
			supplier.setAuthority(Authority.SUPPLIER);
			Authority consumer = new Authority();
			consumer.setAuthority(Authority.CONSUMER);
			Authority admin = new Authority();
			admin.setAuthority(Authority.ADMIN);
			Authority auditor = new Authority();
			auditor.setAuthority(Authority.AUDITOR);;
			if(auth.equals(supplier)){
				m.setSender(supplierService.findByPrincipal());	
			}else if(auth.equals(consumer)){
				m.setSender(consumerService.findByPrincipal());
			}else if(auth.equals(admin)){
				m.setSender(administratorService.findByPrincipal());
			}else if(auth.equals(auditor)){
				m.setSender(auditorService.findByPrincipal());
			}
			
			Assert.isTrue(m.getRecipient().getId()!=uA.getId());
			for(Folder f: m.getSender().getFolders()){
				if(f.getName()=="outbox"){
					m.getFolders().add(f);
					break;
				}
			}
			for(Folder f: m.getRecipient().getFolders()){
				if(f.getName()=="inbox"){
					m.getFolders().add(f);
					break;
				}
			}
			
		}else{
			Assert.isTrue(false);
		}
		
		return messageRepository.save(m);
	}
	
	public void delete(Message m){
		Assert.isTrue(false, "No se puede");
		
	}
	
	//Other
	
		public Collection<Message> messagesByUAId(int id){
			return messageRepository.messagesByUAId(id);
		}
		
		public Collection<Message> findAll(){
			return messageRepository.findAll();
		}
		public Message findOne(int id){
			return messageRepository.findOne(id);
		}
		
		public void moveMessage(Folder from, Folder to, Message m){
			UserAccount uA= LoginService.getPrincipal();
			Assert.isTrue(m.getRecipient().getUserAccount().equals(uA)||m.getSender().getUserAccount().equals(uA));
			Assert.isTrue(from.getActor().getUserAccount().equals(uA));
			Assert.isTrue(to.getActor().getUserAccount().equals(uA));
			m.getFolders().remove(from);
			m.getFolders().add(to);
			messageRepository.save(m);
		}

}
