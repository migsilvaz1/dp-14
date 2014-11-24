package servicesTest;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import services.TagService;
import utilities.AbstractTest;
import domain.Item;
import domain.Tag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class TagServiceTest extends AbstractTest {

	// Service under test -------------------------------------------------
	@Autowired
	private TagService tagService;

	@Test
	public void checkCreate() {
		Tag res = tagService.create();
		System.out.println(res);
	}

	@Test
	public void checkSave() {

		Tag res = tagService.create();
		res.setText("tag 1");
		Collection<Item> items = new ArrayList<Item>();
		res.setItems(items);

		tagService.save(res);
		System.out.println(res.getId());
		System.out.println("Save");
	}
}
