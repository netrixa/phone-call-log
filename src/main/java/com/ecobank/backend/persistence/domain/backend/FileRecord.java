package com.ecobank.backend.persistence.domain.backend;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CRBT_FILE_RECORD")
public class FileRecord extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String serviceProviderName;
	private String singer;
	private String toneName;
	private String vasCode;
	private int count;
	private double charge;
	private double revenue;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "file_upload_id")
	private FileUpload fileUpload;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	public String getSinger() {
		return singer.trim().toUpperCase();
	}
	public void setSinger(String singer) {
		this.singer = singer.trim().toUpperCase();
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
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	public FileUpload getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((singer == null) ? 0 : singer.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileRecord other = (FileRecord) obj;
		if (singer == null) {
			if (other.singer != null)
				return false;
		} else if (!singer.equals(other.singer))
			return false;
		return true;
	}
	
	
	
}
