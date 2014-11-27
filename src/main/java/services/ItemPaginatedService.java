package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ItemPaginatedRepository;
import domain.Item;
import domain.Request;

@Service
@Transactional
public class ItemPaginatedService {
	
	@Autowired
	private ItemPaginatedRepository itemPaginatedRepository;
	
	@Autowired
	private RequestService requestService;
	
	public ItemPaginatedService(){
		super();
	}
	
	public Item create(){
		Item item= new Item();
		return item;
	}
	
	public Item save(Item item){
		Assert.isTrue(item.getPrice()>=1);
		Assert.isTrue(!item.getCode().isEmpty());
		Assert.isTrue(!item.getName().isEmpty());
		Assert.isTrue(!item.getDescription().isEmpty());
		return itemPaginatedRepository.save(item);
	}
	
	public void delete(Item item){
		Assert.isTrue(item.getTags().isEmpty());
		Collection<Request> requests = requestService.findAll();
		Boolean check = true;
		for(Request r: requests){
			if(r.getItem().getId()==item.getId()){
				check = false;
				break;
			}
		}
		Assert.isTrue(check);
		itemPaginatedRepository.delete(item);
	}
	
	public Page<Item> itemsBySupplierUAId(int id, Pageable page){
		return itemPaginatedRepository.itemsBySupplierUAId(id, page);
		
	}
	
	public Page<Item> itemsByKeyword(String key, Pageable page){
		return itemPaginatedRepository.itemsByKeyword("%" + key + "%", page);
	}
	
	public Page<Item> findAll(PageRequest request){
		return itemPaginatedRepository.findAll(request);
	}
	public Item findOne(int id){
		return itemPaginatedRepository.findOne(id);
	}

}
