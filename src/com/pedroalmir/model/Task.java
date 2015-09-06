/**
 * 
 */
package com.pedroalmir.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Model class which will store the task items
 * 
 * @author Pedro Almir
 */
@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String author;
	private String summary;
	
	@Column(columnDefinition = "longtext")
	private String description;
	private String url;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	
	private boolean finished;

	/**
	 * @param author
	 * @param summary
	 * @param description
	 * @param url
	 */
	public Task(String author, String summary, String description, String url, String dueDate) {
		this.author = author;
		this.summary = summary;
		this.description = description;
		this.url = url;
		try {
			this.dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.created = new Date();
		this.finished = false;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the finished
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * @param finished
	 *            the finished to set
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	/**
	 * @return summary
	 */
	public String getShortDescription() {
		return summary;
	}

	/**
	 * @param shortDescription
	 */
	public void setShortDescription(String shortDescription) {
		this.summary = shortDescription;
	}

	/**
	 * @return description
	 */
	public String getLongDescription() {
		return description;
	}

	/**
	 * @param longDescription
	 */
	public void setLongDescription(String longDescription) {
		this.description = longDescription;
	}
	
	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	@Override
	public String toString() {
		return "Task [id=" + id + ", author=" + author + ", summary=" + summary + ", description=" + description + ", url=" + url + ", finished=" + finished + "]";
	}

}