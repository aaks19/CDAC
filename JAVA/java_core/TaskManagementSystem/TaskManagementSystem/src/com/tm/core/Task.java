package com.tm.core;

import java.time.LocalDate;
import java.util.Objects;

public class Task {

	// You can create a class Task with fields like taskId, taskName, description,
	// taskDate, status, active.

	// static taskid
//	private static int taskid;// automatic generate

	// non-Static

	private String taskName;
	private Status status;
	private boolean active;
	private String description;
	private LocalDate taskDate;

	// counter
	private static int taskCounter = 0;
	private int id;

	// constructor
	@SuppressWarnings("static-access")
	public Task(String taskName, Status status, boolean active, String description, LocalDate taskDate) {
		super();
		this.id = ++taskCounter;
		this.taskName = taskName;
		this.status = status;
		this.active = active;
		this.description = description;
		this.taskDate = taskDate;

	}

	public Task(String taskName, String description, LocalDate taskDate) {
		this.taskName = taskName;
		this.description = description;
		this.taskDate = taskDate;
	}

	public Task(int id) {
		this.id = id;
	}

	// getter and setters

	public String getTaskName() {
		return taskName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(LocalDate taskDate) {
		this.taskDate = taskDate;
	}

	public int getTaskCounter() {
		return taskCounter;
	}

	public void setTaskCounter(int taskCounter) {
		this.taskCounter = taskCounter;
	}

	// toString
	@Override
	public String toString() {
		return "Task [" + "TaskId " + id + " taskName=" + taskName + ", status=" + status + ", active=" + active
				+ ", description=" + description + ", taskDate=" + taskDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	// equals method
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Task) {
			return this.id == (((Task) obj).getId());
		}
		return false;
	}

}
