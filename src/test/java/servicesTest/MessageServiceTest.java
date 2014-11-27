package servicesTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Folder;
import domain.Message;

import services.AdministratorService;
import services.MessageService;
import services.SupplierService;
import utilities.AbstractTest;
@Transactional(noRollbackFor = Exception.class)
public class MessageServiceTest extends AbstractTest{
	@Autowired
	private MessageService mensS;
	@Autowired
	private SupplierService supS;
	@Autowired
	private AdministratorService adminS;
	
	@Test
	public void testCreate(){
		Message m = mensS.create();
		Assert.isTrue(m.getId()==0);
	}
	@Test
	public void testSave(){
		authenticate("supplier1");
		Collection<Folder> folders = new HashSet<Folder>();
		Collection<Folder> foldersSender = supS.findByPrincipal().getFolders();
		Collection<Folder> foldersRecipient = supS.findSupplierByUAId(9).getFolders();
		for(Folder fS: foldersSender){
			if(fS.getName()=="outbox"){
				folders.add(fS);
				break;
			}
		}
		for(Folder fR: foldersRecipient){
			if(fR.getName()=="inbox"){
				folders.add(fR);
				break;
			}
		}
		Message m = mensS.create();
		m.setSubject("Subject of m");
		m.setBody("This is the body of");
		m.setRecipient(supS.findSupplierByUAId(9));
		m.setSender(supS.findByPrincipal());
		m.setFolders(folders);
		mensS.save(m);
		authenticate(null);
	}
	@Test
	public void testDelete(){
		
	}
	@Test
	public void testMessagesByUAId(){
		Collection<Message> messages = mensS.messagesByUAId(2);
		Assert.isTrue(messages.size()>0);
	}
	@Test
	public void testFindAll(){
		Collection<Message> messages = mensS.findAll();
		Assert.isTrue(messages.size()>0);
	}
	@Test
	public void testMoveMessage(){
		authenticate("admin1");
		List<Folder> listFrom = new ArrayList<Folder>(adminS.findByPrincipal().getFolders());
		Folder fFrom = (Folder) listFrom.get(0);
		Folder fTo = (Folder) listFrom.get(1);
		List<Message> messagesFrom = new ArrayList<Message>(mensS.messagesByUAId(2));
		Message m = messagesFrom.get(0);
		mensS.moveMessage(fFrom, fTo, m);
		authenticate(null);
		
	}
}
