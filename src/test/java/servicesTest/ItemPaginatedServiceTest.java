package servicesTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import domain.Item;

import services.ItemPaginatedService;
import utilities.AbstractTest;

@Transactional(noRollbackFor = Exception.class)
public class ItemPaginatedServiceTest extends AbstractTest {
	
	@Autowired
	private ItemPaginatedService itemPaginatedService;
	
	@Test
	public void testItemsBySupplierUAId(){
		authenticate("supplier1");
		PageRequest request = new PageRequest(0, 2);
		Page<Item> page= itemPaginatedService.findAll(request);
		System.out.println("---------------------------Page-Information---------------------------");
		System.out.println(page);
		System.out.println("Number of elements: " + page.getNumberOfElements());
		System.out.println("Number: " + page.getNumber());
		System.out.println("Size: " + page.getSize());
		System.out.println("Total elements: " + page.getTotalElements());
		System.out.println("Total pages: " + page.getTotalPages());
	}

}
