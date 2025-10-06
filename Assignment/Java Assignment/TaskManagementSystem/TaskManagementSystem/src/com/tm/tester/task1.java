package com.tm.tester;

import java.util.Scanner;

import com.tm.customException.TaskHandingException;
import com.tm.service.TaskManagerService;
import com.tm.service.TaskMangerServiceImpl;

public class task1 {

	public static void main(String[] args) throws TaskHandingException {

		// String taskName, String status, boolean active, String description, String
		// taskDate
		// test add task

		// upcasting
		TaskManagerService service = new TaskMangerServiceImpl();

		// service.add(new Task("task1", Status.valueOf("PENDING"), true, "tasks1....",
		// LocalDate.parse("1999-02-21")));
//		service.addTask("task1", "tasks1....", "1999-02-21");
//		service.addTask("task2", "tasks2....", "1990-02-21");
//		service.addTask("task3", "tasks3....", "1999-02-21");
//		service.addTask("task4", "tasks4....", "2025-09-23");
//		service.addTask("task5", "tasks5....", "2024-09-23");
//		service.displayTask();
//
//		System.out.println("-----------------------");
//		// task delete
//		service.deleteTask(2);
//		service.displayTask();
//
//		System.out.println("-----------------------");
//
//		// update status
//		service.updateTask(1, "IN_PROGRESS");
//		service.displayTask();
//
//		System.out.println("-----------------------");
//
//		// display all pending tasks
//		service.displayAllPendingTask();
//
//		System.out.println("-----------------------");
//		// display all pending tasks Today
//		service.displayAllPendingTaskToday();
//
//		System.out.println("-----------------------");
//		// display all pending tasks Today
//		service.displayAllPendingTaskSortedByTaskDate();

		try (Scanner sc = new Scanner(System.in)) {
			boolean flag = false;
			int choice;
			while (!flag) {
				try {
					System.out.println(
							"Enter choice:\n1.Add Task\n2.Delete a task\n3.Update task status\n"
							+ "4.Display all pending tasks\n5.Display all pending tasks for today\n"
							+ "6.Display all tasks sorted by taskDate\n7.Display all Task\n0.Exit");
					
					switch (sc.nextInt()) {
					//1.Add new task
					case 1: {
						System.out.println("Add New Task:");
						System.out.println("Enter Task Name , Description , Date");
						service.addTask(sc.next(), sc.next(), sc.next());
						System.out.println("Task Added...");
						break;
					}
					//2.delete a task
					case 2: {
						System.out.println("Delete a Task:");
						System.out.println("Enter Task Id to delete: ");
						service.deleteTask(sc.nextInt());
						System.out.println("Task Deleted...");
						break;
					}
					//3.Update task status
					case 3: {
						System.out.println("Update task status:");
						System.out.println("Enter Task Id and Status");
						service.updateTask(sc.nextInt(), sc.next().toUpperCase());
						System.out.println("Task Status Updated...");
						break;
					}
					//4.All Pending Task
					case 4: {
						System.out.println("All Pending Task:");
						service.displayAllPendingTask();
						break;
					}
					//5.Display all pending tasks for today
					case 5: {
						System.out.println("Display all pending tasks for today:");
						service.displayAllPendingTaskToday();
						break;
					}
					//6.Display all tasks sorted by taskDate
					case 6: {
						System.out.println("Display all tasks sorted by taskDate:");
						service.displayAllPendingTaskSortedByTaskDate();
						break;
					}
					//7.Display all task
					case 7:{
						System.out.println("All Task");
						service.displayTask();
						break;
					}
					case 0: {
						System.out.println("Exiting...");
						flag = true;
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + sc.nextInt());
					}
				} catch (Exception e) {

				}

			}
		}

	}
}
