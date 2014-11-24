package servicesTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Contract;
import domain.Item;
import domain.Request;
import domain.Supplier;

import services.ConsumerService;
import services.ContractService;
import services.ItemService;
import services.RequestService;
import services.SupplierService;
import utilities.AbstractTest;
@Transactional(noRollbackFor = Exception.class)
public class ContractServiceTest extends AbstractTest{
	@Autowired
	private ContractService contractS;
	@Autowired
	private SupplierService supS;
	@Autowired
	private ConsumerService conS;
	@Autowired
	private ItemService iteS;
	@Autowired
	private RequestService requestService;
	
	@Test
	public void testCreate(){
		Contract c = contractS.create();
		Assert.isTrue(c.getId()==0);
	}
	/*@Test
	public void testSave(){
		authenticate("admin1");
		Contract cont = contractS.create();
		cont.setDescription("This is contract 5");
		cont.setContractor(supS.findSupplierByUAId(9));
		cont.setRequest(requestService.findOne(42));
		
		contractS.save(cont);
		
	}*/
}
