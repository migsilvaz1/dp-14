package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	@Query("select s.items from Supplier s where s.userAccount.id = ?1")
	Collection<Item> itemsBySupplierUAId(int id);

	// recordar pasar como parametro la key con % delante y detras
	@Query("select i from Item i inner join i.tags t where i.name like ?1 or i.description like ?1 or t.text like ?1")
	Collection<Item> itemsByKeyword(String key);

}
