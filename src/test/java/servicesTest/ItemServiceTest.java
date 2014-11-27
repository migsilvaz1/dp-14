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
		authenticate("supplier2");
		itemService.itemsBySupplierUAId(0);
	}

	@Test
	public void checkitemsByKeyword() {
		itemService.itemsByKeyword(".");
	}

	@Test
	public void checkCreate() {
		authenticate("supplier2");
		itemService.create();
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

	}

	@Test
	public void checkDelete() {
		Item res = itemService.findOne(14);
		itemService.delete(res);
		System.out.println("Deleted");
	}

}
