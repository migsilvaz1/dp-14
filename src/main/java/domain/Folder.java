package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Folder extends DomainEntity {
	private String name;
	private boolean erasable;
	private Collection<Message> messages;
	private Collection<Folder> children;
	private Folder parent;
	private Actor actor;

	// Constructors

	public Folder() {
		messages = new ArrayList<Message>();
		children = new ArrayList<Folder>();
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	public boolean getErasable() {
		return erasable;
	}

	public void setErasable(boolean erasable) {
		this.erasable = erasable;
	}

	// Relationships ---------------------------------------------

	@ManyToMany(mappedBy = "folders", fetch = FetchType.EAGER)
	@Valid
	public Collection<Message> getMessages() {
		return messages;
	}

	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
	@Valid
	public Collection<Folder> getChildren() {
		return children;
	}

	public void setChildren(Collection<Folder> children) {
		this.children = children;
	}

	@ManyToOne(optional = true)
	@Valid
	public Folder getParent() {
		return parent;
	}

	public void setParent(Folder parent) {
		this.parent = parent;
	}

	@Valid
	@ManyToOne(optional = false)
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	@Override
	public String toString() {
		return "Folder [name=" + name + ", erasable=" + erasable
				+ ", messages=" + messages + ", children=" + children
				+ ", parent=" + parent + "]";
	}

}
