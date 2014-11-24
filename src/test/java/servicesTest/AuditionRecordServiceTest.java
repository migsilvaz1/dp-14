package servicesTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.AuditionRecordService;
import services.AuditorService;
import services.ContractService;
import utilities.AbstractTest;
import domain.AuditionRecord;
import domain.Contract;
@Transactional(noRollbackFor = Exception.class)
public class AuditionRecordServiceTest extends AbstractTest{
	@Autowired
	private AuditionRecordService audRecordS;
	@Autowired
	private AuditorService audS;
	@Autowired
	private ContractService contractS;
	
	@Test
	public void testCreate(){
		AuditionRecord ar = audRecordS.create();
		Assert.isTrue(ar.getId()==0);
	}
	@Test
	public void testSave(){	
		
		Contract c = contractS.findOneToEdit(47);
		authenticate("auditor1");
		AuditionRecord ar = audRecordS.create();
		ar.setStatement("Statement audition record 1");
		ar.setIncidencesDescription("blah");
		ar.setAuditor(audS.findByPrincipal());
		ar.setContract(c);
		ar.setNumberIncidences(0);
		audRecordS.save(ar);
		
	}
	@Test
	public void testDelete(){
		authenticate("auditor1");
		AuditionRecord ar = audRecordS.create();
		ar.setStatement("Statement audition record 1");
		ar.setIncidencesDescription("blah");
		ar.setAuditor(audS.findByPrincipal());
		ar.setNumberIncidences(0);
		audRecordS.save(ar);
		audRecordS.delete(ar);
	}
}
