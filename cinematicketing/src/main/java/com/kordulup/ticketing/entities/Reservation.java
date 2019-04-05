package com.kordulup.ticketing.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Reservation extends AbstractEntity{

	private Boolean checkedIn;
	
	@JsonBackReference(value="customer")
	@ManyToOne
	@JoinColumn(name="customerId", nullable= false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="projectionId", nullable= false)
	@JsonBackReference(value="projection")
	private Projection projection;
	
	private Date checkedTime;
	private Integer seatNumber;

	public Boolean getCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public Date getCheckedTime() {
		return checkedTime;
	}

	public void setCheckedTime(Date checkedTime) {
		this.checkedTime = checkedTime;
	}

	public Integer getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}	

	@Override
	public String toString() {
		return "Reservation [checkedIn=" + checkedIn + ", checkedTime=" + checkedTime + ", seatNumber=" + seatNumber + "]";
	}


}
