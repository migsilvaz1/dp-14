package servicesTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.ContractService;
import services.RequestService;
import services.SupplierService;
import utilities.AbstractTest;
import domain.Contract;
@Transactional(noRollbackFor = Exception.class)
public class ContractServiceTest extends AbstractTest{
	@Autowired
	private ContractService contractS;
	@Autowired
	private SupplierService supS;
	@Autowired
	private RequestService requestService;
	
	@Test
	public void testCreate(){
		Contract c = contractS.create();
		Assert.isTrue(c.getId()==0);
	}
	
	@Test
	public void testSave() throws ParseException{
		authenticate("admin1");
		Contract cont = contractS.create();
		cont.setDescription("This is contract 5");
		cont.setContractor(supS.findSupplierByUAId(9));
		cont.setRequest(requestService.findOne(42));
		cont.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/04/2013"));
		cont.setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse("25/04/2015"));
		contractS.save(cont);
		
	}
	@Test
	public void testFindOne() {
		Assert.isTrue(contractS.findOne(45)!=null);
	}
	@Test
	public void testContractsByConsumerUAId() {
		authenticate("consumer1");
		contractS.contractsByConsumerUAId();
	}

	@Test
	public void testContractsNotSignedByConsumerUAId() {
		authenticate("consumer1");
		contractS.contractsNotSignedByConsumerUAId();
	}

	/*public void contractsBySupplierUAId() {
		
	}

	public void contractsNotSignedBySupplierUAId() {
		
	}

	public void contractsCancelled() {
		
	}

	public void contractsByAuditorUAId() {
		
	}

	public void contractsNotAudited() {
		
	}*/
}
