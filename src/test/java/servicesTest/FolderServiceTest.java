package servicesTest;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import services.FolderService;
import services.SupplierService;
import utilities.AbstractTest;
import domain.Actor;
import domain.Folder;
import domain.Message;
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
/*
	@Test
	public void checkSave() {

		Folder fol = folderService.create();

		Actor actor = new Actor();
		fol.setActor(actor);

		fol.setName("folder 1");

		Collection<Folder> children = new ArrayList<Folder>();
		fol.setChildren(children);

		fol.setErasable(false);

		Collection<Message> messages = new ArrayList<Message>();
		fol.setMessages(messages);

		Folder parent = new Folder();
		fol.setParent(parent);

		folderService.save(fol);
		System.out.println(fol.getId());
		System.out.println("Save");
	}

	@Test
	public void checkDelete() {

		Folder fol = folderService.create();
		fol.setName("folder 1");

		Actor actor = new Actor();
		fol.setActor(actor);

		Collection<Folder> children = new ArrayList<Folder>();
		fol.setChildren(children);

		fol.setErasable(false);

		Collection<Message> messages = new ArrayList<Message>();
		fol.setMessages(messages);

		Folder parent = new Folder();
		fol.setParent(parent);

		folderService.delete(fol);
		System.out.println(fol.getId());
		System.out.println("Deleted");
	}
*/
}
