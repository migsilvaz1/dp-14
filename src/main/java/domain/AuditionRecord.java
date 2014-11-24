package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class AuditionRecord extends DomainEntity {

	private Date creationMoment;
	private String statement;
	private Integer numberIncidences;
	private String incidencesDescription;
	private Auditor auditor;
	private Contract contract;

	// Constructors

	public AuditionRecord() {
		super();
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
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	@Min(0)
	public Integer getNumberIncidences() {
		return numberIncidences;
	}

	public void setNumberIncidences(Integer numberIncidences) {
		this.numberIncidences = numberIncidences;
	}

	public String getIncidencesDescription() {
		return incidencesDescription;
	}

	public void setIncidencesDescription(String incidencesDescription) {
		this.incidencesDescription = incidencesDescription;
	}

	// Relationships ---------------------------------------------

	@ManyToOne(optional = false)
	@Valid
	public Auditor getAuditor() {
		return auditor;
	}

	public void setAuditor(Auditor auditor) {
		this.auditor = auditor;
	}

	@OneToOne(optional = false)
	@Valid
	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
}
