package domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PreUpdate;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity {
	private String webAddress;
	private String mission;
	private String vision;
	private String values;
	private String statement;
	private Date valid;

	@NotBlank
	@URL
	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	@NotBlank
	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	@NotBlank
	public String getVision() {
		return vision;
	}

	public void setVision(String vision) {
		this.vision = vision;
	}

	@NotBlank
	@Column(name = "_values")
	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	@NotBlank
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public Date getValid() {
		return valid;
	}

	public void setValid(Date valid) {
		this.valid = valid;
	}

	@PreUpdate
	protected void onUpdate() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, 1);
		valid = c.getTime();
	}

	// Relationships ---------------------------------------------
	/*
	 * private Customer customer;
	 * 
	 * @NotBlank
	 * 
	 * @OneToOne(optional = false) public Customer getCustomer() { return
	 * customer; }
	 * 
	 * public void setCustomer(Customer customer) { this.customer = customer; }
	 */

}
