package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Supplier extends Customer {
	private Collection<Item> items;
	private Collection<Contract> contracts;

	// Constructors

	public Supplier() {
		items = new ArrayList<Item>();
		contracts = new ArrayList<Contract>();
	}

	// Relationships ---------------------------------------------

	@ManyToMany(fetch = FetchType.EAGER)
	@Valid
	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}

	@OneToMany(mappedBy = "contractor", fetch = FetchType.EAGER)
	@Valid
	public Collection<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(Collection<Contract> contracts) {
		this.contracts = contracts;
	}

	@Override
	public String toString() {
		return "Supplier [items=" + items + ", contracts=" + contracts + "]";
	}

}
