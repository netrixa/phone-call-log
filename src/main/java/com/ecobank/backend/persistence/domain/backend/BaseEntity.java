package com.ecobank.backend.persistence.domain.backend;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseEntity {

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public BaseEntity() {

	}

	public Date getCreatedAt() {
		return createdAt;
	}

	@PrePersist
	public void setCreatedAt() {
		Date date = new Date();
		this.createdAt = date;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	@PreUpdate
	public void setUpdatedAt() {
		Date date = new Date();
		this.updatedAt = date;
	}
}
