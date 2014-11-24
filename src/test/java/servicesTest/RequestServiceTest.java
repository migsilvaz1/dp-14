package servicesTest;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import services.ConsumerService;
import services.RequestService;
import utilities.AbstractTest;
import domain.Consumer;
import domain.Item;
import domain.Request;

@Transactional(noRollbackFor = Exception.class)
public class RequestServiceTest extends AbstractTest {

	// Service under test -------------------------------------------------
	@Autowired
	private RequestService requestService;

	@Autowired
	private ConsumerService consumerService;

	// Test
	@Test
	public void checkCreate() {
		authenticate("consumer1");
		Request req;

		req = requestService.create();
		req.setCode("123-456");
		req.setDescription("description 1");

		Item item = new Item();
		req.setItem(item);

		Consumer c = new Consumer();
		req.setConsumer(c);
		System.out.println(req);
	}

	/*@Test
	public void checkSave() {

		authenticate("consumer1");
		Request req = requestService.create();
		req.setCode("123-456");
		req.setDescription("description 2");
		req.setRequestedStart(new Date());
		req.setRequestedEnd(new Date());
		requestService.save(req);
		System.out.println(req.getId());
		System.out.println("Save");

	}

	@Test
	public void checkDelete() {
		authenticate("consumer1");
		Request req = requestService.create();
		req.setCode("123-456");
		req.setDescription("description 3");
		req.setId(1212);
		requestService.save(req);
		System.out.println(req.getId() + " this is the id");
		requestService.delete(req);
		System.out.println("Deleted");
	}*/

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
