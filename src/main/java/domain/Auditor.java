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
public class Auditor extends Actor {
	private Collection<AuditionRecord> auditionRecords;

	// Constructors

	public Auditor() {
		auditionRecords = new ArrayList<AuditionRecord>();
	}

	// Relationships ---------------------------------------------

	@OneToMany(mappedBy = "auditor", fetch = FetchType.EAGER)
	@Valid
	public Collection<AuditionRecord> getAuditionRecords() {
		return auditionRecords;
	}

	public void setAuditionRecords(Collection<AuditionRecord> auditionRecords) {
		this.auditionRecords = auditionRecords;
	}
}
