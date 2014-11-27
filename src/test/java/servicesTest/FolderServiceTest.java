package servicesTest;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import services.FolderService;
import services.SupplierService;
import utilities.AbstractTest;
import domain.Folder;
import domain.Supplier;

@Transactional(noRollbackFor = Exception.class)
public class FolderServiceTest extends AbstractTest{

	// Service under test -------------------------------------------------
	@Autowired
	private FolderService folderService;

	@Autowired
	private SupplierService supplierService;

	@Test
	public void checkCreate() {
		Folder fol = folderService.create();
		System.out.println(fol);
	}

	@Test
	public void findAllTest() {
		authenticate("supplier2");
		Collection<Folder> folders;
		folders = folderService.findAll();
		Supplier supplierConnect = supplierService.findByPrincipal();
		System.out.println("supplier id: " + supplierConnect.getId());
		for (Folder f : folders) {
			System.out.println("Folder id: " + f.getId());
		}
	}
	@Test
	public void checkSave() {
		authenticate("consumer1");
		Folder fol = folderService.create();
		fol.setName("example");
		folderService.save(fol);
	}

	@Test
	public void checkDelete() {
		authenticate("admin1");
		Folder fol = folderService.findOne(20);
		folderService.delete(fol);
	}
}
