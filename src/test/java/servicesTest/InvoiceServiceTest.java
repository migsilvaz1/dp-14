package servicesTest;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import services.ContractService;
import services.InvoiceService;
import utilities.AbstractTest;
import domain.Invoice;

public class InvoiceServiceTest extends AbstractTest{
	@Autowired
	private InvoiceService invS;
	@Autowired
	private ContractService contractService;
	@Test
	public void testCreate(){
		Invoice i = invS.create();
		Assert.isTrue(i.getId()==0);
	}
	
	@Test
	public void testSave(){
		
		authenticate("supplier1");
		Invoice inv = invS.create();
		inv.setDescription("This is invoice 5");
		inv.setTotalCost(1.0);
		inv.setContract(contractService.findOneToEdit(43));
		Invoice res= invS.save(inv);
	}
	@Test
	public void testDelete(){
		authenticate("supplier1");
		Invoice inv = invS.create();
		inv.setDescription("This is invoice 5");
		inv.setTotalCost(1.0);
		inv.setContract(contractService.findOneToEdit(43));
		Invoice res= invS.save(inv);
		invS.delete(inv);
	}
	@Test
	public void testFindAll(){
		Collection<Invoice> colInv = invS.findAll();
		Assert.isTrue(colInv.size()>0);
	}
	@Test
	public void testInvoicesByConsumerUAId(){
		Collection<Invoice> colInv = invS.invoicesByConsumerUAId(6);
		Assert.isTrue(colInv.size()>0);
	}
	@Test
	public void testInvoicesNotPayedByConsumerUAId(){
		Collection<Invoice> colInv = invS.invoicesNotPayedByConsumerUAId(6);
		Assert.isTrue(colInv.size()>0);
	}
	@Test
	public void testInvoicesBySupplierUAId(){
		Collection<Invoice> colInv = invS.invoicesBySupplierUAId(8);
		Assert.isTrue(colInv.size()>0);
	}
	@Test
	public void testInvoicesNotPayedBySupplierUAId(){
		Collection<Invoice> colInv = invS.invoicesNotPayedBySupplierUAId(9);
		Assert.isTrue(colInv.size()>0);
	}
}
