package com.tm.service;

import java.time.LocalDate;

import com.tm.core.Status;
import com.tm.customException.TaskHandingException;

public interface TaskManagerService {

	//1) Add Task
	String addTask(String taskName, String description, String taskDate) throws TaskHandingException;
	
	//2) Display Task
	void displayTask();
	
	//3) Delete Task
	String deleteTask(int id);
	
	//4) Update task status 
	String updateTask(int id, String status ) throws TaskHandingException;
	
	//5) Display all pending tasks 
	void displayAllPendingTask();
	
	//6) Display all pending tasks for today
	void displayAllPendingTaskToday();
	
	//7) Display all tasks sorted by taskDate
	void displayAllPendingTaskSortedByTaskDate();
}
