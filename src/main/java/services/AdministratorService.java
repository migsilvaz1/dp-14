package services;
 
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
 
import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Auditor;
import domain.Consumer;
import domain.Contract;
import domain.CreditCard;
import domain.Customer;
import domain.Folder;
import domain.Supplier;
 
@Service
@Transactional
public class AdministratorService {
 
    // Managed repository ---------------------------------------
    @Autowired
    private AdministratorRepository administratorRepository;
 
    // Supporting services --------------------------------------
    
    @Autowired
    public FolderService folderService;
 
    // Constructors ---------------------------------------------
 
    public AdministratorService() {
        super();
    }
 
    // Simple CRUD methods --------------------------------------
    
    public Administrator create(){
    	Administrator a = new Administrator();
    	return a;
    }
    
    public Administrator save(Administrator s){
    	
    	if(s.getId()==0){
			Authority a = new Authority();
			a.setAuthority(Authority.ADMIN);
			List<Authority> as = new ArrayList<Authority>();
			s.getUserAccount().setAuthorities(as);
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			String pass = s.getUserAccount().getPassword();
			s.getUserAccount().setPassword(encoder.encodePassword(pass, null));
		}else{
			Administrator saux = administratorRepository.findOne(s.getId());
			Assert.isTrue(saux.getUserAccount().equals(s.getUserAccount()));
		}
    	
    	return administratorRepository.save(s);
    }
    
    //
 
    public Administrator findByPrincipal() {
        UserAccount userAccount = LoginService.getPrincipal();
        return administratorRepository.findAdminByUAId(userAccount.getId());
    }
 
    public Collection<CreditCard> sharedCreditCards() {
        Collection<CreditCard> creditCards;
        creditCards = administratorRepository.sharedCreditCards();
        Assert.notNull(creditCards);
        return creditCards;
    }
 
    public String nameConsumerMoreRequests() {
        return administratorRepository.nameConsumerMoreRequests();
    }
 
    public Collection<Double> itemStatistics() {
        Collection<Double> itemStats = administratorRepository.itemStatistics();
        return itemStats;
    }
 
    public Collection<String> nameSuppliersMostLeastItems() {
        Collection<String> nameSuppliers = administratorRepository.nameSuppliersMostLeastItems();
        return nameSuppliers;
    }
 
    public Collection<Contract> contractNotSigned() {
        Collection<Contract> contractsNotSigned = administratorRepository
                .contractNotSigned();
        return contractsNotSigned;
    }
 
    public Collection<Consumer> consumerWhitMostSignedContracts() {
        Collection<Consumer> consumers = administratorRepository
                .consumerWhitMostSignedContracts();
        return consumers;
    }
 
    Collection<Supplier> supplierWhitMostSignedContracts() {
        Collection<Supplier> suppliers = administratorRepository
                .supplierWhitMostSignedContracts();
        return suppliers;
    }
 
    public Collection<Consumer> consumerWhitMostUnpayedInvoices() {
        Collection<Consumer> defaulting = administratorRepository
                .consumerWhitMostUnpayedInvoices();
        return defaulting;
    }
 
    public Collection<Supplier> supplierWhitMostUnpayedInvoices() {
        Collection<Supplier> defaulting = administratorRepository
                .supplierWhitMostUnpayedInvoices();
        return defaulting;
    }
 
    public Collection<Consumer> consumerMostPayed() {
        Collection<Consumer> consumers = administratorRepository
                .consumerMostPayed();
        return consumers;
    }
 
    public Collection<Supplier> supplierMostEarned() {
        return administratorRepository.supplierMostEarned();
    }
 
    public Collection<Double> auditorStatistics() {
        Collection<Double> stats = administratorRepository.auditorStatistics();
        return stats;
    }
 
    public Collection<Double> incidencesStatistics() {
        Collection<Double> stats = administratorRepository
                .incidencesStatistics();
        return stats;
    }
 
    public Collection<Auditor> auditorMostAudited() {
        Collection<Auditor> auditors = administratorRepository
                .auditorMostAudited();
        return auditors;
    }
 
    public Collection<Auditor> auditorLeastAudited() {
        Collection<Auditor> auditors = administratorRepository
                .auditorLeastAudited();
        return auditors;
    }
 
    public Collection<Integer> curriculaStatistics() {
        Collection<Integer> stats = administratorRepository
                .curriculaStatistics();
        return stats;
    }
 
    public Integer numberItemsNotTagged() {
        Integer i = administratorRepository.numberItemsNotTagged();
        return i;
    }
 
    public Collection<Customer> noCurriculumRegistered() {
        Collection<Customer> customers = administratorRepository
                .noCurriculumRegistered();
        return customers;
    }
 
    public Collection<Customer> noCurriculumUpdated() {
        Collection<Customer> customers = administratorRepository
                .noCurriculumUpdated();
        return customers;
    }
 
    public Map<Consumer, Long> consumerRequests() {
        Map<Consumer, Long> map = new HashMap<Consumer, Long>();
 
        for (Object[] elem : administratorRepository.consumerRequests()) {
            Consumer c = (Consumer) elem[0];
            Assert.isTrue(c.getClass().equals(Consumer.class));
            Long i = (Long) elem[1];
            Assert.isTrue(i.getClass().equals(Long.class));
            map.put(c, i);
        }
        return map;
    }
}