package com.kordulup.ticketing.entities;

import javax.persistence.Entity;

@Entity
public class Hall extends AbstractEntity {

	private String name;
	private int seatRow;
	private int seatColumn;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}

	public int getSeatColumn() {
		return seatColumn;
	}

	public void setSeatColumn(int seatColumn) {
		this.seatColumn = seatColumn;
	}

	@Override
	public String toString() {
		return "Hall [name=" + name + ", seatRow=" + seatRow + ", seatColumn=" + seatColumn + "]";
	}
	
}
