package servicesTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import services.AdministratorService;
import utilities.AbstractTest;
@Transactional(noRollbackFor = Exception.class)
public class AdministratorServiceTest extends AbstractTest {

	// Service under test -------------------------------------------------
	@Autowired
	private AdministratorService administratorService;

	@Test
	public void findByPrincipalTest() {
		authenticate("admin1");
		System.out.println("Test 1:");
		System.out.println("findByPrincipalTest "
				+ administratorService.findByPrincipal());
	}

	@Test
	public void sharedCreditCardsTest() {
		System.out.println("Test 3:");
		System.out.println("sharedCreditCardsTest: "
				+ administratorService.sharedCreditCards());
	}

	@Test
	public void nameConsumerMoreRequestsTest() {
		System.out.println("Test 4:");
		System.out.println("nameConsumerMoreRequestsTest: "
				+ administratorService.nameConsumerMoreRequests());
	}

	@Test
	public void itemStatisticsTest() {
		System.out.println("Test 5:");
		System.out.println("itemStatisticsTest: "
				+ administratorService.itemStatistics());
	}

	@Test
	public void nameSuppliersMostLeastItemsTest() {
		System.out.println("Test 6:");
		System.out.println("nameSuppliersMostLeastItemsTest: "
				+ administratorService.nameSuppliersMostLeastItems());
	}

	@Test
	public void contractNotSignedTest() {
		System.out.println("Test 7:");
		System.out.println("contractNotSignedTest: "
				+ administratorService.contractNotSigned());
	}

	@Test
	public void consumerWhitMostSignedContractsTest() {
		System.out.println("Test 8:");
		System.out.println("contractNotSignedTest: "
				+ administratorService.consumerWhitMostSignedContracts());
	}

	@Test
	public void supplierWhitMostSignedContractsTest() {
		System.out.println("Test 9:");
		System.out.println("supplierWhitMostSignedContractsTest: "
				+ administratorService.supplierWhitMostUnpayedInvoices());
	}

	@Test
	public void consumerWhitMostUnpayedInvoicesTest() {
		System.out.println("Test 10:");
		System.out.println("consumerWhitMostUnpayedInvoicesTest: "
				+ administratorService.consumerWhitMostUnpayedInvoices());
	}

	@Test
	public void supplierWhitMostUnpayedInvoices() {
		System.out.println("Test 11:");
		System.out.println("supplierWhitMostUnpayedInvoicesTest: "
				+ administratorService.supplierWhitMostUnpayedInvoices());
	}

	@Test
	public void consumerMostPayedTest() {
		System.out.println("Test 12:");
		System.out.println("consumerMostPayedTest: "
				+ administratorService.consumerMostPayed());
	}

	@Test
	public void supplierMostEarnedTest() {
		System.out.println("Test 13:");
		System.out.println("supplierMostEarnedTest: "
				+ administratorService.supplierMostEarned());
	}

	@Test
	public void auditorStatisticsTest() {
		System.out.println("Test 14:");
		System.out.println("auditorStatisticsTest: "
				+ administratorService.auditorStatistics());
	}

	@Test
	public void incidencesStatisticsTest() {
		System.out.println("Test 15:");
		System.out.println("incidencesStatisticsTest: "
				+ administratorService.incidencesStatistics());
	}

	@Test
	public void auditorMostAuditedTest() {
		System.out.println("Test 16:");
		System.out.println("auditorMostAuditedTest: "
				+ administratorService.auditorMostAudited());
	}

	@Test
	public void auditorLeastAuditedTest() {
		System.out.println("Test 17:");
		System.out.println("auditorLeastAuditedTest: "
				+ administratorService.auditorLeastAudited());
	}

	@Test
	public void curriculaStatisticsTest() {
		System.out.println("Test 18:");
		System.out.println("curriculaStatisticsTest: "
				+ administratorService.curriculaStatistics());
	}

	
	@Test
	public void numberItemsNotTaggedTest() {
		System.out.println("Test 19:");
		System.out.println("numberItemsNotTaggedTest: "
				+ administratorService.numberItemsNotTagged());
	}

	@Test
	public void noCurriculumRegisteredTest() {
		System.out.println("Test 20:");
		System.out.println("noCurriculumRegisteredTest: "
				+ administratorService.noCurriculumRegistered());
	}

	@Test
	public void noCurriculumUpdatedTest() {
		System.out.println("Test 21:");
		System.out.println("noCurriculumUpdatedTest: "
				+ administratorService.noCurriculumUpdated());
	}

	@Test
	public void consumerRequestsTest() {
		System.out.println("Test 22:");
		System.out.println("consumerRequestsTest: "
				+ administratorService.consumerRequests());
	}
}
