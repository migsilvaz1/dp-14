package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curriculum;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Integer>{

	@Query("select c.curriculum from Consumer c where c.curriculum is not null")
	Collection<Curriculum> consumerCurricula();
	
	@Query("select s.curriculum from Supplier s where s.curriculum is not null")
	Collection<Curriculum> supplierCurricula();
}
