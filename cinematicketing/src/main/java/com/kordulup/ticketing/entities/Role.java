package com.kordulup.ticketing.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.NaturalId;

@Entity
public class Role extends AbstractEntity {
	
	@Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
	private RoleName name;
	
	public Role() {
		
	}
	
	public Role(RoleName name) {
        this.name = name;
    }
	
	public RoleName getName() {
		return name;
	}
	public void setName(RoleName name) {
		this.name = name;
	}
}
