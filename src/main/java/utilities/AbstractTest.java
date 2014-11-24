package utilities;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.LoginService;

@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AbstractTest {

	// Supporting services --------------------------------

		@Autowired
		private LoginService loginService;
		
		// Set up and tear down -------------------------------
		
		@Before
		public void setUp() {
			PopulateDatabase.main(null);
		}

		// Supporting methods ---------------------------------

		public void authenticate(String username) {
			UserDetails userDetails;
			TestingAuthenticationToken authenticationToken;
			SecurityContext context;
		
			if (username == null)
				authenticationToken = null;
			else {
				userDetails = loginService.loadUserByUsername(username);
				authenticationToken = new TestingAuthenticationToken(userDetails, null);		    
			}
			
			context = SecurityContextHolder.getContext();
			context.setAuthentication(authenticationToken);
		}
}
