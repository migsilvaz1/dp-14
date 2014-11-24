package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer>{
	@Query("select r.contracts from Request r where r.consumer.userAccount.id = ?1")
	Collection<Contract> contractsByConsumerUAId(int id);
	
	@Query("select c from Request r inner join r.contracts c where r.consumer.userAccount.id = ?1 and c.dateContractHolderSign = null")
	Collection<Contract> contractsNotSignedByConsumerUAId(int id);
	
	@Query("select s.contracts from Supplier s where s.userAccount.id = ?1")
	Collection<Contract> contractsBySupplierUAId(int id);
	
	@Query("select c from Supplier s inner join s.contracts c where s.userAccount.id = ?1 and c.dateContractorSign = null")
	Collection<Contract> contractsNotSignedBySupplierUAId(int id);
	
	@Query("select c from Contract c where dateContractHolderSign = null or dateContractorSign = null and startDate <= CURRENT_TIMESTAMP")
	Collection<Contract> contractsCancelled();
	
	@Query("select a.contract from AuditionRecord a where a.auditor.userAccount.id = ?1")
	Collection<Contract> contractsByAuditorUAId(int id);
	
	@Query("select c from Contract c where c.auditionRecord = null")
	Collection<Contract> contractsNotAudited();

}
