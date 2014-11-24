package servicesTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.ConsumerService;
import utilities.AbstractTest;
import domain.Assessment;
import domain.Consumer;
import domain.CreditCard;
import domain.Curriculum;
import domain.Folder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional(noRollbackFor = Exception.class)
public class ConsumerServiceTest extends AbstractTest {

	// Service under test -------------------------------------------------
	@Autowired
	private ConsumerService consumerService;

	// Login

	@Autowired
	private LoginService loginService;

	public void authenticate(String username) {
		UserDetails userDetails;
		TestingAuthenticationToken authenticationToken;
		SecurityContext context;

		userDetails = loginService.loadUserByUsername(username);
		authenticationToken = new TestingAuthenticationToken(userDetails, null);
		context = SecurityContextHolder.getContext();
		context.setAuthentication(authenticationToken);
	}

	// Test
	@Test
	public void checkCreate() {
		Consumer res;
		CreditCard cc1 = new CreditCard();
		cc1.setHolderName("Pedro");
		cc1.setBrandName("Santander");
		cc1.setNumber("5481-4893-6792-7235");
		cc1.setExpirationMonth(10);
		cc1.setExpirationYear(2015);
		cc1.setCvv(105);

		res = consumerService.Create();
		res.setName("Pedro");
		res.setSurname("Antonio");
		res.setEmail("allala@lala.com");
		res.setCreditCard(cc1);

		Collection<Folder> folders = new ArrayList<Folder>();
		Folder inbox = new Folder();
		Folder outbox = new Folder();
		folders.add(inbox);
		folders.add(outbox);
		res.setFolders(folders);

		Curriculum curriculum = new Curriculum();
		res.setCurriculum(curriculum);
		res.setTicker("123-456");
		res.setRating(30.0);

		System.out.println(res);

	}

	@Test
	public void checkSave() {
		UserAccount user = new UserAccount();
		user.setUsername("consumer5");
		user.setPassword("4ee9c60cc32e6615c3c45dc6822c45de");
		Collection<Authority> authorities = new HashSet<Authority>();
		Authority authority = new Authority();
		authority.setAuthority("CONSUMER");
		authorities.add(authority);
		user.setAuthorities(authorities);
		Consumer res;

		CreditCard cc1 = new CreditCard();
		cc1.setHolderName("Pedro");
		cc1.setBrandName("Santander");
		cc1.setNumber("5481-4893-6792-7235");
		cc1.setExpirationMonth(10);
		cc1.setExpirationYear(2015);
		cc1.setCvv(105);

		res = new Consumer();
		res.setUserAccount(user);

		res.setName("Pedro");
		res.setSurname("Antonio");
		res.setEmail("allala@lala.com");
		res.setCreditCard(cc1);

		Collection<Folder> folders = new ArrayList<Folder>();
		Folder inbox = new Folder();
		Folder outbox = new Folder();
		folders.add(inbox);
		folders.add(outbox);
		res.setFolders(folders);
		/*
		 * Curriculum curriculum = new Curriculum();
		 * res.setCurriculum(curriculum);
		 */
		res.setTicker("123-456");
		res.setRating(30.0);

		consumerService.save(res);
		System.out.println("Save");
	}

	@Test
	public void findAllTest() {
		Collection<Consumer> res;
		authenticate("consumer1");
		res = consumerService.findAll();
		Consumer consumerConnect = consumerService.findByPrincipal();
		System.out.println("consumer id: " + consumerConnect.getId());
		for (Consumer c : res) {
			System.out.println(c.getId());
		}
	}

	@Test
	public void checkassessmentByConsumerUAId() {
		Collection<Assessment> all;
		authenticate("consumer1");
		all = consumerService.assessmentByConsumerUAId(consumerService
				.findByPrincipal().getUserAccount().getId());
		for (Assessment a : all) {
			System.out.println("Assessment comment: " + a.getComment());
		}
	}
}
