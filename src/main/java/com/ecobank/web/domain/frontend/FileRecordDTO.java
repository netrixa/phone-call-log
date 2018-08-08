package com.ecobank.web.domain.frontend;

import java.text.DecimalFormat;

public class FileRecordDTO {

	
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	private String serviceProvider;
	private String singer;
	private String toneName;
	private String vasCode;
	private int count;
	private double charge;
	private double revenue;
	public String getRevenue() {
	    DecimalFormat formatter = new DecimalFormat();
	    formatter.format(revenue);
	    formatter.setGroupingUsed(false);
	    formatter.setMinimumFractionDigits(2);
	    formatter.setMaximumFractionDigits(2);
	    formatter.setDecimalSeparatorAlwaysShown(true);
	    formatter.setParseIntegerOnly(false);
		return formatter.format(revenue);
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	public String getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getToneName() {
		return toneName;
	}
	public void setToneName(String toneName) {
		this.toneName = toneName;
	}
	public String getVasCode() {
		return vasCode;
	}
	public void setVasCode(String vasCode) {
		this.vasCode = vasCode;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	@Override
	public String toString() {
		return "FileRecordDTO [serviceProvider=" + serviceProvider + ", singer=" + singer + ", toneName=" + toneName
				+ ", vasCode=" + vasCode + ", count=" + count + ", charge=" + charge + ", revenue=" + revenue + "]";
	}
	
	
}
