package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Item;
import domain.Request;

import repositories.ItemRepository;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RequestService requestService;
	
	public ItemService(){
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
		return itemRepository.save(item);
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
		itemRepository.delete(item);
	}
	
	public Collection<Item> itemsBySupplierUAId(int id){
		return itemRepository.itemsBySupplierUAId(id);
		
	}
	
	public Collection<Item> itemsByKeyword(String key){
		return itemRepository.itemsByKeyword("%" + key + "%");
	}
	
	public Collection<Item> findAll(){
		return itemRepository.findAll();
	}
	public Item findOne(int id){
		return itemRepository.findOne(id);
	}

}
