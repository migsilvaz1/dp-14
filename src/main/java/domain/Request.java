package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Request extends DomainEntity {
	private String code;
	private String description;
	private Date requestedStart;
	private Date requestedEnd;
	private Consumer consumer;
	private Item item;
	private Collection<Contract> contracts;

	// Constructors

	public Request() {
		contracts = new ArrayList<Contract>();
	}

	@NotBlank
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getRequestedStart() {
		return requestedStart;
	}

	public void setRequestedStart(Date requestedStart) {
		this.requestedStart = requestedStart;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getRequestedEnd() {
		return requestedEnd;
	}

	public void setRequestedEnd(Date requestedEnd) {
		this.requestedEnd = requestedEnd;
	}

	// Relationships ---------------------------------------------

	@ManyToOne(optional=false)
	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	
	@ManyToOne(optional = false)
	@Valid
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@OneToMany(mappedBy = "request", fetch = FetchType.EAGER)
	@Valid
	public Collection<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(Collection<Contract> contracts) {
		this.contracts = contracts;
	}
}
