package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Contract extends DomainEntity {

	private Date creationMoment;
	private String description;
	private Date startDate;
	private Date endDate;
	private Date dateContractHolderSign;
	private Date dateContractorSign;
	private Assessment consumerAssessment;
	private Assessment supplierAssessment;
	private Supplier contractor;
	private Request request;
	private AuditionRecord auditionRecord;
	private Collection<Invoice> invoices;

	// Constructor

	public Contract() {
		invoices = new ArrayList<Invoice>();
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getCreationMoment() {
		return creationMoment;
	}

	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
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
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getDateContractHolderSign() {
		return dateContractHolderSign;
	}

	public void setDateContractHolderSign(Date dateContractHolderSign) {
		this.dateContractHolderSign = dateContractHolderSign;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getDateContractorSign() {
		return dateContractorSign;
	}

	public void setDateContractorSign(Date dateContractorSign) {
		this.dateContractorSign = dateContractorSign;
	}

	@Valid
	@AttributeOverrides({
		@AttributeOverride(name="rating",
				column=@Column(name="consumerRating")),
		@AttributeOverride(name="comment",
				column=@Column(name="consumerComment"))
	})
	public Assessment getConsumerAssessment() {
		return consumerAssessment;
	}

	public void setConsumerAssessment(Assessment consumerAssessment) {
		this.consumerAssessment = consumerAssessment;
	}

	@Valid
	@AttributeOverrides({
		@AttributeOverride(name="rating",
				column=@Column(name="supplierRating")),
		@AttributeOverride(name="comment",
				column=@Column(name="supplierComment"))
	})
	public Assessment getSupplierAssessment() {
		return supplierAssessment;
	}

	public void setSupplierAssessment(Assessment supplierAssessment) {
		this.supplierAssessment = supplierAssessment;
	}

	// Relationships ---------------------------------------------

	@ManyToOne(optional = false)
	@Valid
	public Supplier getContractor() {
		return contractor;
	}

	public void setContractor(Supplier contractor) {
		this.contractor = contractor;
	}
	@ManyToOne(optional = false)
	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	@Valid
	public AuditionRecord getAuditionRecord() {
		return auditionRecord;
	}

	public void setAuditionRecord(AuditionRecord auditionRecord) {
		this.auditionRecord = auditionRecord;
	}

	@OneToMany(mappedBy = "contract", fetch = FetchType.EAGER)
	@Valid
	public Collection<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(Collection<Invoice> invoices) {
		this.invoices = invoices;
	}

}
