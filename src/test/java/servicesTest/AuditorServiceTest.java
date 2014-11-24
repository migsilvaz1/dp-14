package servicesTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import security.UserAccount;
import services.AuditorService;
import utilities.AbstractTest;
import domain.Auditor;
@Transactional(noRollbackFor = Exception.class)
public class AuditorServiceTest extends AbstractTest{
	@Autowired
	private AuditorService audS;
	
	@Test
	public void testCreate(){
		Auditor a= audS.create();
		Assert.isTrue(a.getId()==0);
	}
	@Test
	public void testSave(){
		UserAccount userAud = new UserAccount();
		userAud.setUsername("auditor4");
		userAud.setPassword("auditor");
		
		Auditor a = audS.create();
		a.setName("Ramon");
		a.setSurname("Ramierz");
		a.setEmail("ramram@gmail.com");
		a.setUserAccount(userAud);
		audS.save(a);
	}
	@Test
	public void testFindByPrincipal(){
		authenticate("auditor1");
		Auditor a = audS.findByPrincipal();
		Assert.isTrue(a.getId()>0);
		authenticate(null);
	}
	
}
