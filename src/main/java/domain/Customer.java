package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {
	private String ticker;
	private CreditCard creditCard;
	private Double rating;
	private Curriculum curriculum;


	@Pattern(regexp = "^\\w+(-\\w+)?$")
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@Valid
	@NotNull
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@Range(min = 0, max = 100)
	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	// Relationships ---------------------------------------------

	@OneToOne(optional = true)
	@Valid
	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	@Override
	public String toString() {
		return "Customer [ticker=" + ticker + ", creditCard=" + creditCard
				+ ", rating=" + rating + ", curriculum=" + curriculum + "]";
	}

}
