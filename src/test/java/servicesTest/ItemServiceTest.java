package servicesTest;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import services.ItemService;
import services.SupplierService;
import utilities.AbstractTest;
import domain.Item;
import domain.Supplier;

@Transactional(noRollbackFor = Exception.class)
public class ItemServiceTest extends AbstractTest {

	// Service under test -------------------------------------------------
	@Autowired
	private ItemService itemService;
	@Autowired
	private SupplierService supplierService;

	@Test
	public void findAllTest() {
		Collection<Item> res;
		authenticate("supplier2");
		res = itemService.findAll();
		Supplier supplierConnect = supplierService.findByPrincipal();
		System.out.println("supplier id: " + supplierConnect.getId());
		for (Item i : res) {
			System.out.println("Item id: " + i.getId());
		}
	}

	@Test
	public void checkitemsBySupplierUAId() {
		Collection<Item> all;
		authenticate("supplier2");
		// CHECK
		all = itemService.itemsBySupplierUAId(0);
		for (Item i : all) {
			System.out.println(i);
		}
	}

	@Test
	public void checkitemsByKeyword() {
		Collection<Item> all;
		authenticate("supplier2");
		all = itemService.itemsByKeyword(".");
		for (Item i : all) {
			System.out.println(i);
		}
	}

	@Test
	public void checkCreate() {
		authenticate("supplier2");
		Item res = itemService.create();
		System.out.println(res);
	}

	@Test
	public void checkSave() {
		authenticate("supplier2");
		Item res = itemService.create();
		res.setName("item 1");
		res.setCode("item code 1");
		res.setDescription("item description 1");
		res.setPrice(1.0);

		itemService.save(res);
		System.out.println(res.getId());
		System.out.println("Save");

	}

	@Test
	public void checkDelete() {
		authenticate("supplier2");
		Item res = itemService.create();
		res.setName("New item");
		res.setCode("New item code 1");
		res.setDescription("New item description 1");
		res.setPrice(1.0);
		res.setId(1212);
		itemService.save(res);
		System.out.println(res.getId() + " this is the id");
		itemService.delete(res);
		System.out.println("Deleted");
	}

}
