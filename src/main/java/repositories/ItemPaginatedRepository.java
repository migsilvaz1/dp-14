package repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Item;

@Repository
public interface ItemPaginatedRepository extends JpaRepository<Item, Integer> {

	@Query("select s.items from Supplier s where s.userAccount.id = ?1")
	Page<Item> itemsBySupplierUAId(int id, Pageable page);


	@Query("select i from Item i inner join i.tags t where i.name like ?1 or i.description like ?1 or t.text like ?1")
	Page<Item> itemsByKeyword(String key, Pageable page);

}
