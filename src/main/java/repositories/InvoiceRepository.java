package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
	
	@Query("select i from Request r inner join r.contracts c inner join c.invoices i where r.consumer.userAccount.id = ?1")
	Collection<Invoice> invoicesByConsumerUAId(int id);
	
	@Query("select i from Request r inner join r.contracts c inner join c.invoices i where r.consumer.userAccount.id = ?1 and paymentMoment = null")
	Collection<Invoice> invoicesNotPayedByConsumerUAId(int id);
	
	@Query("select c.invoices from Contract c where c.contractor.userAccount.id = ?1")
	Collection<Invoice> invoicesBySupplierUAId(int id);
	
	@Query("select c.invoices from Contract c where c.contractor.userAccount.id = ?1 and paymentMoment = null")
	Collection<Invoice> invoicesNotPayedBySupplierUAId(int id);

}
