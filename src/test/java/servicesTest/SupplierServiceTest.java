package servicesTest;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CreditCard;
import domain.Supplier;

import security.Authority;
import security.UserAccount;
import services.SupplierService;
import utilities.AbstractTest;
@Transactional(noRollbackFor = Exception.class)
public class SupplierServiceTest extends AbstractTest{
	@Autowired
	private SupplierService supS;
	
	@Test
	public void testCreate(){
		Supplier s = supS.Create();
		Assert.isTrue(s.getId()==0);
	}
	@Test
	public void testSave(){
		CreditCard cc2= new CreditCard();
		cc2.setHolderName("Maria");
		cc2.setBrandName("LaCaixa");
		cc2.setNumber("4451-0213-6776-1290");
		cc2.setExpirationMonth(12);
		cc2.setExpirationYear(2015);
		cc2.setCvv(975);
		
		UserAccount userSup = new UserAccount();
		userSup.setUsername("supplier4");
		userSup.setPassword("02adbc4ce4aa2e4ae759e998a0ebf8fb");
		
		Collection<Authority> authoritiesS = new HashSet<Authority>();
		Authority authorityS = new Authority();
		authorityS.setAuthority("SUPPLIER");
		authoritiesS.add(authorityS);
		userSup.setAuthorities(authoritiesS);
		
		Supplier s = supS.Create();
		s.setName("Sara");
		s.setSurname("Diaz");
		s.setEmail("sardia@gmail.com");
		s.setCreditCard(cc2);
		s.setUserAccount(userSup);
		supS.save(s);
		
	}
}
