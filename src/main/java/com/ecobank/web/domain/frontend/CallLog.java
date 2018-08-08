package com.ecobank.web.domain.frontend;

import com.ecobank.enums.Direction;

public class CallLog {

	private long id;
	private String userId;
	private String otherUserId;
	private Direction direction;
	private long duration;
	private int length;
	private String timestamp;
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
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
	
	
}
