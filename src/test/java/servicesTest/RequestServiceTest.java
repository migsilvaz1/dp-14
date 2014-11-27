package servicesTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import services.ConsumerService;
import services.ItemService;
import services.RequestService;
import utilities.AbstractTest;
import domain.Consumer;
import domain.Request;

@Transactional(noRollbackFor = Exception.class)
public class RequestServiceTest extends AbstractTest {

	// Service under test -------------------------------------------------
	@Autowired
	private RequestService requestService;

	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private ItemService itemService;

	// Test
	@Test
	public void checkCreate() {
		authenticate("consumer1");
		requestService.create();
	}

	public void checkSave() throws ParseException {

		authenticate("consumer1");
		Request req = requestService.create();
		req.setItem(itemService.findOne(14));
		req.setCode("123-456");
		req.setDescription("description 2");
		req.setRequestedStart(new SimpleDateFormat("dd/MM/yyyy").parse("25/04/2013"));
		req.setRequestedEnd(new SimpleDateFormat("dd/MM/yyyy").parse("25/04/2015"));
		requestService.save(req);

	}

	@Test
	public void checkDelete() {
		authenticate("consumer1");
		requestService.delete(requestService.findOne(44));
	}

	@Test
	public void findAllTest() {
		Collection<Request> requests;
		authenticate("consumer1");
		requests = requestService.findAll();
		Consumer consumerConnect = consumerService.findByPrincipal();
		System.out.println("consumer id: " + consumerConnect.getId());
		for (Request r : requests) {
			System.out.println("Request id: " + r.getId());
		}
	}

	@Test
	public void checkrequestByConsumerUAId() {
		Collection<Request> requests;
		authenticate("consumer1");
		requests = requestService.requestByConsumerUAId(consumerService
				.findByPrincipal().getUserAccount().getId());
		for (Request r : requests) {
			System.out.println("Request description: " + r.getDescription());
		}
	}
}
