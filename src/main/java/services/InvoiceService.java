package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Invoice;

import repositories.InvoiceRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public InvoiceService(){
		super();
	}
	
	public Invoice create(){
		Invoice invoice = new Invoice();
		return invoice;
	}
	
	public Invoice save(Invoice invoice){
		Assert.isTrue(invoice.getContract()!=null);
		Assert.isTrue(invoice.getContract().getDateContractHolderSign()!=null && invoice.getContract().getDateContractorSign()!=null  );
		Assert.isTrue(invoice.getTotalCost()>=0);
		Date d = new Date();
		UserAccount uA= LoginService.getPrincipal();
		Assert.isTrue(invoice.getContract().getContractor().getUserAccount().equals(uA));
		if(invoice.getId()==0){
			invoice.setCreationMoment(d);
		}else{
			Assert.isTrue(invoice.getCreationMoment().before(d));
		}
		return invoiceRepository.save(invoice);
	}
	
	public void delete(Invoice invoice){
		Assert.isTrue(invoice.getPaymentMoment()==null);
		invoiceRepository.delete(invoice);
	}
	
	public Collection<Invoice> findAll(){
		return invoiceRepository.findAll();
	}
	
	public Invoice findOne(int id){
		return invoiceRepository.findOne(id);
	}
	
	public Collection<Invoice> invoicesByConsumerUAId(int id){
		return invoiceRepository.invoicesByConsumerUAId(id);
	}
	
	public Collection<Invoice> invoicesNotPayedByConsumerUAId(int id){
		return invoiceRepository.invoicesNotPayedByConsumerUAId(id);
	}

	public Collection<Invoice> invoicesBySupplierUAId(int id){
		return invoiceRepository.invoicesBySupplierUAId(id);
	}
	
	public Collection<Invoice> invoicesNotPayedBySupplierUAId(int id){
		return invoiceRepository.invoicesNotPayedBySupplierUAId(id);
	}
}
