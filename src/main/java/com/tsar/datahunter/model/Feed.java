package com.tsar.datahunter.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="feed")
public class Feed implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1280858187701493808L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String description;
	
	private Date publishedDate;
	
	private String guid;
	
	private Date savedToDb;

	public Feed() {
		super();
	}
	

	public Feed(String title, String description, Date publishedDate, String guid) {
		super();
		this.title = title;
		this.description = description;
		this.publishedDate = publishedDate;
		this.guid = guid;
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getPublishedDate() {
		return publishedDate;
	}


	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}


	public String getGuid() {
		return guid;
	}


	public void setGuid(String guid) {
		this.guid = guid;
	}
	

	public Date getSavedToDb() {
		return savedToDb;
	}


	public void setSavedToDb(Date savedToDb) {
		this.savedToDb = savedToDb;
	}


	@Override
    public String toString() {
        return "Feed [title=" + title + ", description=" + description
                + ",  published date=" + publishedDate + ", guid=" + guid +", savedTodb="+savedToDb
                + "]";
    }
}
