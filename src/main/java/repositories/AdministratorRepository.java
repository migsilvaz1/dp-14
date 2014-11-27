package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Auditor;
import domain.Consumer;
import domain.Contract;
import domain.CreditCard;
import domain.Customer;
import domain.Supplier;

@Repository
public interface AdministratorRepository extends
		JpaRepository<Administrator, Integer> {
	@Query("select a from Administrator a where a.userAccount.id=?1")
	Administrator findAdminByUAId(int id);

	// 1 The list of credit cards that are used by more than one customer.
	@Query("select c.creditCard from Customer c group by c.creditCard having count(c)>1")
	Collection<CreditCard> sharedCreditCards();

	// 2 The name of the consumer who has made more requests.
	@Query("select c.name from Consumer c where c.requests.size=(select max(c.requests.size) from Consumer c)")
	String nameConsumerMoreRequests();

	// 3 The minimum, the maximum, and the average number of items offered by
	// the suppliers.
	@Query("select min(s.items.size), max(s.items.size), avg(s.items.size) from Supplier s")
	Collection<Double> itemStatistics();

	// 4 The name of the supplier who offers more items and the name of the
	// supplier who offers less items.
	@Query("select s.name from Supplier s where s.items.size=(select max(s.items.size) from Supplier s) or s.items.size=(select min(s1.items.size) from Supplier s1)")
	Collection<String> nameSuppliersMostLeastItems();

	// 5 A list of consumers, together with the number of requests that he or
	// she's created. The list must be ordered in descending order of number of
	// requests.
	// Map<Consumer, Integer> suppliersRequests();
	@Query("select c,count(r) from Request r,Consumer c where r member of c.requests group by c order by count(r) desc")
	Collection<Object[]> consumerRequests();

	// 6 The list of contracts that have not been signed, yet. The list must be
	// ordered in ascending order regarding the moment when the contract was
	// created.
	@Query("select c from Contract c where c.dateContractHolderSign is null or c.dateContractorSign is null group by c.creationMoment order by c.creationMoment asc")
	Collection<Contract> contractNotSigned();

	// 7 The consumer/s who has/have signed more contracts.
	@Query("select c.name from Consumer c inner join c.requests r inner join r.contracts cont where cont.size=(select max(cont.size) from Consumer c inner join c.requests r inner join r.contracts cont group by c) group by c")
	Collection<Consumer> consumerWhitMostSignedContracts();

	// 8 The suppliers/s who has/have signed more contracts.
	@Query("select s.name from Supplier s where s.contracts.size=(select max(s.contracts.size) from Supplier s)")
	Collection<Supplier> supplierWhitMostSignedContracts();

	// 9 The consumer/s who has/have more unpaid invoices.
	@Query("select c from Consumer c inner join c.requests r inner join r.contracts cont inner join cont.invoices i where i.paymentMoment is null and i.size=(select max(i.size) from Consumer c inner join c.requests r inner join r.contracts cont inner join cont.invoices i where i.paymentMoment is null group by c) group by c")
	Collection<Consumer> consumerWhitMostUnpayedInvoices();

	// 10 The supplier/s who has/have more unpaid invoices.
	@Query("select s from Supplier s inner join s.contracts cont inner join cont.invoices i where i.paymentMoment is null and i.size=(select max(i.size) from Supplier s inner join s.contracts cont inner join cont.invoices i where i.paymentMoment is null group by s) group by s")
	Collection<Supplier> supplierWhitMostUnpayedInvoices();

	// 11 The consumer/s who has/have paid more money.
	@Query("select c from Consumer c inner join c.requests r inner join r.contracts cont inner join cont.invoices i group by c order by sum(i.totalCost) desc")
	Collection<Consumer> consumerMostPayed();

	// 12 The supplier/s who has/have earned more money.
	@Query("select s from Supplier s inner join s.contracts cont inner join cont.invoices i group by s order by sum(i.totalCost) desc")
	Collection<Supplier> supplierMostEarned();

	// 13 The minimum, the maximum, and the average number of contracts audited
	// by every auditor.
	@Query("select a.name, min(a.auditionRecords.size),max(a.auditionRecords.size),avg(a.auditionRecords.size) from Auditor a")
	Collection<Object[]> auditorStatistics();

	// 14 The minimum, the maximum, and the average number of incidences per
	// audit record.
	@Query("select ar.name, min(ar.numberIncidences),max(ar.numberIncidences),avg(ar.numberIncidences) from AuditionRecord ar")
	Collection<Object[]> incidencesStatistics();

	// 15 The auditor/s who has/have audited more contracts.
	@Query("select a from Auditor a where a.auditionRecords.size=(select max(a.auditionRecords.size) from Auditor a)")
	Collection<Auditor> auditorMostAudited();

	// 16 The auditor/s who has/have audited less contracts.
	@Query("select a from Auditor a where a.auditionRecords.size=(select min(a.auditionRecords.size) from Auditor a)")
	Collection<Auditor> auditorLeastAudited();

	// 17 The number of customers who have registered a curriculum and the
	// number of consumers who have not registered any curricula.
	@Query("select (select count(c1) from Customer c1 where c1.curriculum != null) , count(c) from Consumer c where c.curriculum = null")
	Collection<Integer> curriculaStatistics();

	// 18 The number of items that have not been tagged.
	@Query("select count(i) from Item i where i.tags is empty")
	Integer numberItemsNotTagged();

	// 19 The list of customers who have not registered their curricula
	@Query("select c.name from Customer c where c.curriculum is empty")
	Collection<Customer> noCurriculumRegistered();

	// 20 The list of customers who have not updated their curricula for more
	// than a year
	@Query("select c from Customer c where c.curriculum.valid > current_date")
	Collection<Customer> noCurriculumUpdated();

}
