package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuditionRecordRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

import domain.AuditionRecord;

@Service
@Transactional
public class AuditionRecordService {
	
	@Autowired
	private AuditionRecordRepository auditionRecordService;
	
	@Autowired
	private AuditorService auditorService;
	
	public AuditionRecordService(){
		super();
	}
	
	public AuditionRecord create(){
		AuditionRecord audRecord = new AuditionRecord();
		return audRecord;
	}
	
	public AuditionRecord save(AuditionRecord a){
		UserAccount uA= LoginService.getPrincipal();
		Authority auth = uA.getAuthorities().iterator().next();
		Authority audito = new Authority();
		audito.setAuthority(Authority.AUDITOR);
		Assert.isTrue(auth.equals(audito));
		Assert.isTrue(a.getStatement()!=null);
		Assert.isTrue(a.getNumberIncidences()>=0);	
		if(a.getId()==0){
			a.setAuditor(auditorService.findByPrincipal());
			Date d = new Date();
			a.setCreationMoment(d);		
		}else{
			Assert.isTrue(a.getContract()==null);
			Assert.isTrue(a.getAuditor().equals(auditorService.findByPrincipal()));
			Date f = new Date();
			Assert.isTrue(a.getCreationMoment().before(f));	
		}
		return auditionRecordService.save(a);
		
	}
	
	public void delete(AuditionRecord a){
		UserAccount uA= LoginService.getPrincipal();
		Authority auth = uA.getAuthorities().iterator().next();
		Authority audito = new Authority();
		audito.setAuthority(Authority.AUDITOR);
		Assert.isTrue(auth.equals(audito));
		Assert.isTrue(a.getAuditor().getUserAccount().equals(uA));
		Assert.isTrue(a.getContract()==null);
	}
	
	public AuditionRecord findOne(int id){
		return auditionRecordService.findOne(id);
	}
	public Collection<AuditionRecord> findAll(){
		return auditionRecordService.findAll();
	}

}
