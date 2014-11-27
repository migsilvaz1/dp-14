package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Folder;

import repositories.FolderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class FolderService {

	// Managed repository

	@Autowired
	private FolderRepository folderRepository;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private AuditorService auditorService;

	// Constructor

	public FolderService() {
		super();
	}

	// CRUD

	public Folder create() {
		Folder folder = new Folder();
		folder.setErasable(true);
		return folder;
	}

	public Folder save(Folder folder) {
		UserAccount uA = LoginService.getPrincipal();
		Assert.isTrue(!folder.getName().isEmpty());
		if (folder.getId() == 0) {
			Authority auth = uA.getAuthorities().iterator().next();
			Authority supplier = new Authority();
			supplier.setAuthority(Authority.SUPPLIER);
			Authority consumer = new Authority();
			consumer.setAuthority(Authority.CONSUMER);
			Authority admin = new Authority();
			admin.setAuthority(Authority.ADMIN);
			Authority auditor = new Authority();
			auditor.setAuthority(Authority.AUDITOR);
			if (auth.equals(supplier)) {
				folder.setActor(supplierService.findByPrincipal());
			} else if (auth.equals(consumer)) {
				folder.setActor(consumerService.findByPrincipal());
			} else if (auth.equals(admin)) {
				folder.setActor(administratorService.findByPrincipal());
			} else if (auth.equals(auditor)) {
				folder.setActor(auditorService.findByPrincipal());
			}
			folder.setErasable(true);
		} else {
			Assert.isTrue(folder.getActor().getUserAccount().getId() == uA
					.getId());
		}
		if (folder.getParent() != null) {
			Assert.isTrue(folder.getParent().getId() != folder.getId());
		}
		if (!folder.getChildren().isEmpty()) {
			boolean check = true;
			for (Folder f : folder.getChildren()) {
				if (f.getId() == folder.getId()) {
					check = false;
					break;
				}
			}
			Assert.isTrue(check);

		}

		return folderRepository.save(folder);
	}

	public void delete(Folder folder) {
		UserAccount uA = LoginService.getPrincipal();
		Assert.isTrue(uA.getId() == folder.getActor().getUserAccount().getId());
		Assert.isTrue(folder.getErasable());
		Assert.isTrue(folder.getMessages().isEmpty());
		folderRepository.delete(folder);
	}

	public Collection<Folder> findAll() {
		return folderRepository.findAll();
	}
	public Folder findOne(int id){
		return folderRepository.findOne(id);
	}

	// Other business methods

	public Folder saveAux(Folder f) {
		return folderRepository.save(f);
	}
	
	public void meterFolder(Folder parent, Folder son){
		UserAccount uA = LoginService.getPrincipal();
		Assert.isTrue(uA.getId() == parent.getActor().getUserAccount().getId());
		Assert.isTrue(uA.getId() == son.getActor().getUserAccount().getId());
		parent.getChildren().add(son);
		son.setParent(parent);
		folderRepository.save(son);
		folderRepository.save(parent);
	}
	
	public void sacarFolder(Folder son){
		UserAccount uA = LoginService.getPrincipal();
		Assert.isTrue(uA.getId() == son.getActor().getUserAccount().getId());
		Folder parent = son.getParent();
		parent.getChildren().remove(son);
		son.setParent(null);
		folderRepository.save(son);
		folderRepository.save(parent);
	}
}
