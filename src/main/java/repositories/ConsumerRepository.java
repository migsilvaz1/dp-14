package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Assessment;
import domain.Consumer;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Integer>{

	@Query("select c from Consumer c where c.userAccount.id=?1")
	Consumer findConsumerByUAId(int id);
	
	@Query("select c.consumerAssessment from Request r inner join r.contracts c where r.consumer.userAccount.id = ?1 and c.consumerAssessment is not null")
	Collection<Assessment> assessmentByConsumerUAId(int id);
}
