package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ContractRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Auditor;
import domain.Consumer;
import domain.Contract;
import domain.Supplier;

@Service
@Transactional
public class ContractService {

	// Managed repository ---------------------------------------
	@Autowired
	private ContractRepository contractRepository;

	// Supporting services --------------------------------------

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private AuditorService auditorService;

	// Constructors ---------------------------------------------

	public ContractService() {
		super();
	}

	// Simple CRUD methods --------------------------------------

	public Contract create(){
		Contract c = new Contract();
		return c;

	}

	public Contract save(Contract c){
		Date d = new Date();
		Assert.isTrue(c.getStartDate().before(d));
		Assert.isTrue(c.getEndDate().after(d));
		UserAccount uA= LoginService.getPrincipal();
		Authority auth = uA.getAuthorities().iterator().next();
		Authority admin = new Authority();
		admin.setAuthority(Authority.ADMIN);
		Assert.isTrue(auth.equals(admin));
		Assert.isTrue(!c.getDescription().isEmpty());
		Assert.isTrue(c.getStartDate().before(c.getEndDate()));
		if(c.getDateContractHolderSign()==null || c.getDateContractorSign()==null){
			Assert.isTrue(c.getInvoices().isEmpty());
		}
		Assert.isTrue(c.getRequest()!=null);
		Assert.isTrue(c.getContractor()!=null);
		
		if(c.getId()==0){

			c.setCreationMoment(d);
			
		}else{

			Assert.isTrue(c.getCreationMoment().before(d));
			
		}

		return contractRepository.save(c);
	}
	
	//

	public Contract findOneToEdit(int id) {
		Contract contract = contractRepository.findOne(id);
		return contract;
	}


	public Collection<Contract> contractsByConsumerUAId() {

		Consumer userConnect = consumerService.findByPrincipal();

		Collection<Contract> contracts = contractRepository
				.contractsByConsumerUAId(userConnect.getId());

		return contracts;
	}

	public Collection<Contract> contractsNotSignedByConsumerUAId() {

		Consumer userConnect = consumerService.findByPrincipal();

		Collection<Contract> contracts = contractRepository
				.contractsNotSignedByConsumerUAId(userConnect.getId());

		return contracts;
	}

	public Collection<Contract> contractsBySupplierUAId() {

		Supplier userConnect = supplierService.findByPrincipal();

		Collection<Contract> contracts = contractRepository
				.contractsBySupplierUAId(userConnect.getId());

		return contracts;
	}

	public Collection<Contract> contractsNotSignedBySupplierUAId() {

		Supplier userConnect = supplierService.findByPrincipal();

		Collection<Contract> contracts = contractRepository
				.contractsNotSignedBySupplierUAId(userConnect.getId());

		return contracts;
	}

	public Collection<Contract> contractsCancelled() {

		Collection<Contract> contracts = contractRepository
				.contractsCancelled();

		return contracts;
	}

	public Collection<Contract> contractsByAuditorUAId() {

		Auditor userConnect = auditorService.findByPrincipal();

		Collection<Contract> contracts = contractRepository
				.contractsByAuditorUAId(userConnect.getId());

		return contracts;
	}

	public Collection<Contract> contractsNotAudited() {

		Collection<Contract> contracts = contractRepository
				.contractsNotAudited();

		return contracts;
	}

}
