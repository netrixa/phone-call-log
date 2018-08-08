package com.ecobank.backend.persistence.domain.backend;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.ecobank.enums.Direction;

import java.util.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;


@Entity
public class PhoneCallLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String userId;
	private String otherUserId;
	@Enumerated(EnumType.STRING)
	private Direction direction;
	private long duration;
	private int length;
	private Date createdOn;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "file_upload_id")
	private FileUpload fileUpload;
	public FileUpload getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	@Column(name="tstamp")
	private String timestamp;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOtherUserId() {
		return otherUserId;
	}
	public void setOtherUserId(String otherUserId) {
		this.otherUserId = otherUserId;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	@PrePersist
	private void initDate(){
		this.createdOn=Calendar.getInstance().getTime();
	}
}
