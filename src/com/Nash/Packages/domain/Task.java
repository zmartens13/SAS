package com.Nash.Packages.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String title;
	
	private String description;
	
	private LocalDate dueDate;
	
	private Status status;

	public Task(String title, String description, LocalDate date) {
		super();
		this.setTitle(title);
		this.setDescription(description);
		this.setDate(date);
		this.setStatus(Status.PENDING);
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

	public LocalDate getDate() {
		return dueDate;
	}

	public void setDate(LocalDate date) {
		this.dueDate = date;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status pending) {
		this.status = pending;
	}

	@Override
	public String toString() {
		return title + ", description=" + description + ", dueDate=" + dueDate + ", status=" + status;
	}
		
}
