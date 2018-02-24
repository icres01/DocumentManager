package com.ibm.migration.samples.document.entities;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 * Bean implementation class for Enterprise Bean: File
 */
@Entity (name="File")
@Table (name = "FILE  ")
@NamedQueries({ 
	@NamedQuery(name = "findFileByUserid", query = "select object(o) from File o where  o.userId = ?1"),
		
	@NamedQuery(name = "findAll", query = "select object(o) from File o") 
})
public class FileBean {

	@Id
	@Column(name="ID")
	Integer id;
	
	@Column(name="USERID")
	String userId;
	
	@Column(name="FILENAME")
	String filename;
	
	@Column(name="TIMESTAMP")
	String timestamp;
	
	public FileBean(){
	}
	
	public FileBean(java.lang.Integer id) {
		this.setId(id);
	}

	public FileBean(String userId, String filename, String timestamp) {
		Random random = new Random();
		Integer newId = Integer.valueOf(random.nextInt());
		this.setId(Math.abs(newId));
		this.setUserId(userId);
		this.setFilename(filename);
		this.setTimestamp(timestamp);
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getId() {
		return id;
	}

	private void setId(Integer id) {
		this.id = id;
	}
}