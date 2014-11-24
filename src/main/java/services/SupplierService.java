package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SupplierRepository;
import security.Authority;
import security.LoginService;
import domain.Assessment;
import domain.Folder;
import domain.Supplier;

@Service
@Transactional
public class SupplierService {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	public FolderService folderService;
	
	public SupplierService(){
		super();
	}
	
	public Supplier Create(){
		Supplier supplier = new Supplier();
		return supplier;
	}
	
	public void save(Supplier s){
		if(s.getId()==0){
			Authority a = new Authority();
			a.setAuthority(Authority.SUPPLIER);
			List<Authority> as = new ArrayList<Authority>();
			s.getUserAccount().setAuthorities(as);
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			String pass = s.getUserAccount().getPassword();
			s.getUserAccount().setPassword(encoder.encodePassword(pass, null));
		}else{
			Supplier saux = supplierRepository.findOne(s.getId());
			Assert.isTrue(saux.getUserAccount().equals(s.getUserAccount()));
			
		}
		Supplier a = supplierRepository.save(s);
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
	
	public Collection<Supplier> findAll(){
		return supplierRepository.findAll();
	}
	
	public Supplier findOne(int id){
		return supplierRepository.findOne(id);
	}

	public Supplier findSupplierByUAId(int id){
		return supplierRepository.findSupplierByUAId(id);
	}
	
	public Collection<Assessment> assessmentBySupplierUAId(int id){
		return supplierRepository.assessmentBySupplierUAId(id);
	}
	
	public Supplier findByPrincipal(){
		return supplierRepository.findSupplierByUAId(LoginService.getPrincipal().getId());
	}
}
