package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Curriculum;

import repositories.CurriculumRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class CurriculumService {
	
	@Autowired
	private CurriculumRepository curriculumRepository;
	
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private SupplierService supplierService;
	
	public CurriculumService(){
		super();
	}
	
	//CRUD
	
	public Curriculum create(){
		Curriculum c = new Curriculum();
		return c;
	}
	
	public Curriculum save(Curriculum c){
		UserAccount uA= LoginService.getPrincipal();
		Authority auth = uA.getAuthorities().iterator().next();
		Authority supplier = new Authority();
		supplier.setAuthority(Authority.SUPPLIER);
		Authority consumer = new Authority();
		consumer.setAuthority(Authority.CONSUMER);
		if(auth.equals(supplier)){
			supplierService.findByPrincipal().setCurriculum(c);
		}else if(auth.equals(consumer)){
			consumerService.findByPrincipal().setCurriculum(c);
		}
		Assert.isTrue(!c.getWebAddress().isEmpty());
		Assert.isTrue(!c.getMission().isEmpty());
		Assert.isTrue(!c.getVision().isEmpty());
		Assert.isTrue(!c.getValues().isEmpty());
		Assert.isTrue(!c.getStatement().isEmpty());
		return curriculumRepository.save(c);
	}

	public Collection<Curriculum> consumerCurricula() {
		 
        Collection<Curriculum> curricula = curriculumRepository
                .consumerCurricula();
 
        return curricula;
    }
 
    public Collection<Curriculum> supplierCurricula() {
 
        Collection<Curriculum> curricula = curriculumRepository
                .supplierCurricula();
 
        return curricula;
    }
    
    public Curriculum findOne(int id){
    	return curriculumRepository.findOne(id);
    }
	
    public Collection<Curriculum> findAll(){
    	return curriculumRepository.findAll();
    }
}
