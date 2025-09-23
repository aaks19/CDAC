package com.tm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.tm.core.Status;
import com.tm.core.Task;
import com.tm.customException.TaskHandingException;

public class TaskMangerServiceImpl implements TaskManagerService {

	private List<Task> taskList;

	// constructor
	public TaskMangerServiceImpl() {

		this.taskList = new ArrayList<>(1000);

		// add task
		// taskList.add(new Task("task1", Status.valueOf("PENDING"), true, "tasks1....",
		// LocalDate.parse("1999-02-21")));

	}

	// 1) ADD task
	@Override
	public String addTask(String taskName, String description, String taskDate)
			throws TaskHandingException {

		// Newly added task should have default status as PENDING and active=true
		// add task

		Task t1 = new Task(taskName, Status.PENDING, true, description, LocalDate.parse(taskDate));
		taskList.add(t1);

		return null;
	}

	// 2) Display Task
	@Override
	public void displayTask() {

		for (Task t : taskList) {
			System.out.println(t);
		}

	}

	// 3) Delete a task
	@Override
	public String deleteTask(int id) {

//		Task t1 = new Task(id);
//		taskList.remove(t1);
//		Task t1 = new Task(id);
//		t1.setActive(false);
//		t1.setStatus(Status.COMPLETED);
//		return "task " + id + " deleted";
		
		taskList.stream()
				.filter(i->i.getId() == id)
				.forEach(i->{i.setActive(false);
								i.setStatus(Status.COMPLETED);});
		
		return "Task: "+id+" is deleted"; 
	//	taskList.stream().filter(i->id ).forEach(i->i.ge);
	}

	// 4) Update task status

	@Override
	public String updateTask(int id, String status) throws TaskHandingException {

//		taskList.stream().filter(p -> p.getId() == id).forEach(p -> p.setStatus(Status.valueOf(status)));
		taskList.stream().filter(p -> p.getId() == id).forEach(p ->{
			if(status == "complete".toUpperCase()) {
				p.setStatus(Status.valueOf(status));
				p.setActive(false);
			}
			p.setStatus(Status.valueOf(status));
		});
		return "Update Status Completed";
	}

	// 5) Display all pending tasks
	@Override
	public void displayAllPendingTask() {

		taskList.stream().filter(p -> p.getStatus().equals(Status.PENDING)).forEach(i -> System.out.println(i));

	}

	// 6) Display all pending tasks for today
	@SuppressWarnings("static-access")
	@Override
	public void displayAllPendingTaskToday() {
		
		taskList.stream().filter(p->p.getStatus().equals(Status.PENDING) && p.getTaskDate().equals(p.getTaskDate().now())).forEach(p->System.out.println(p));;

	}

	// 7) Display all tasks sorted by taskDate  
	@Override
	public void displayAllPendingTaskSortedByTaskDate() {
		
		Comparator<Task> comp = (c1,c2)->c1.getTaskDate().compareTo(c2.getTaskDate());
		taskList.stream().filter(p->p.getStatus().equals(Status.PENDING)).sorted(comp).forEach(i->System.out.println(i));
		
	}

}
