package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Assessment;
import domain.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer>{

	@Query("select s from Supplier s where s.userAccount.id=?1")
	Supplier findSupplierByUAId(int id);
	
	@Query("select c.supplierAssessment from Contract c where c.contractor.userAccount.id = ?1 and c.supplierAssessment is not null")
	Collection<Assessment> assessmentBySupplierUAId(int id);
}
