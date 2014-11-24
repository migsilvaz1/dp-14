package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.AuditionRecord;

@Repository
public interface AuditionRecordRepository extends JpaRepository<AuditionRecord, Integer>{

	@Query("select ar from AuditionRecord ar where ar.auditor.id = ?1 ")
	Collection<AuditionRecord> findAllAuditionRecordsCreatedByAuditor(int id);
}
