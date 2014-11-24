package servicesTest;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import services.CurriculumService;
import utilities.AbstractTest;
import domain.Curriculum;

@Transactional(noRollbackFor = Exception.class)
public class CurriculumServiceTest extends AbstractTest{

	// Service under test -------------------------------------------------
	@Autowired
	private CurriculumService curriculumService;	

	@Test
	public void checkCreate() {
		Curriculum res = curriculumService.create();
		System.out.println(res);
	}

	@Test
	public void checkSave() {

		Curriculum cv = curriculumService.create();
		cv.setMission("mission");
		cv.setStatement("statement");
		cv.setValues("values");
		cv.setVision("vision");
		cv.setWebAddress("webaddress");

		curriculumService.save(cv);
		System.out.println(cv.getId());
		System.out.println("Save");

	}


	@Test
	public void checkfindOneToEdit() {
		System.out.println("checkfindOneToEdit: "
				+ curriculumService.findOne(11));
	}

	@Test
	public void checkconsumerCurricula() {
		Collection<Curriculum> curricula;
		authenticate("consumer1");
		curricula = curriculumService.consumerCurricula();
		for (Curriculum c : curricula) {
			System.out.println("Curriculum mision: " + c.getMission());
		}
	}
	
	@Test
	public void checksupplierCurricula() {
		Collection<Curriculum> curricula;
		authenticate("supplier1");
		curricula = curriculumService.supplierCurricula();
		for (Curriculum c : curricula) {
			System.out.println("Curriculum mision: " + c.getMission());
		}
	}
}
