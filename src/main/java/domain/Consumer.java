package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Consumer extends Customer {

	private Collection<Request> requests;

	// Constructors

	public Consumer() {
		requests = new ArrayList<Request>();
	}

	// Relationships ---------------------------------------------

	@OneToMany(mappedBy = "consumer", fetch = FetchType.EAGER)
	@Valid
	public Collection<Request> getRequests() {
		return requests;
	}

	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}

}
