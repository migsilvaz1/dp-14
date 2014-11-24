package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Tag;

import repositories.TagRepository;

@Service
@Transactional
public class TagService {

	@Autowired
	private TagRepository tagRepository;
	
	public ItemService itemService;
	
	public TagService(){
		super();
	}
	
	public Tag create(){
		Tag tag = new Tag();
		return tag;
	}
	
	public Tag save(Tag tag){
		Assert.isTrue(!tag.getText().isEmpty());
		return tagRepository.save(tag);
	}
	public Tag findOne(int id){
		return tagRepository.findOne(id);
	}
	public Collection<Tag> findAll(){
		return tagRepository.findAll();
	}
	
}
