package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Tag extends DomainEntity {
	private String text;
	private Collection<Item> items;

	// Constructors

	public Tag() {
		items = new ArrayList<Item>();
	}

	@NotBlank
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	@Override
	public String toString() {
		return "Tag [text=" + text + ", items=" + items + "]";
	}

}
