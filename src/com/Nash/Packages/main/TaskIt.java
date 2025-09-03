package com.Nash.Packages.main;

import com.Nash.Packages.controller.TaskManager;

public class TaskIt {

	private static int SAFE_EXIT = 0;

	public static void main(String[] args) {

		TaskManager manager = new TaskManager();

		manager.start();

		int selection = 0;
		
		while (selection != 5) {
			selection = manager.mainMenu();
			if (selection == 1) {
				manager.addTask();
			} else if (selection == 2) {
				manager.listTasks();
			} else if (selection == 3) {
				manager.updateStatus();
			}  else if (selection == 4) {
				manager.updateTask();
			} else if (selection == 5) {
				manager.deleteTask();
			} else if (selection == 6) {
				System.out.println("Goodbye");
				System.exit(SAFE_EXIT);
			} else {
				System.out.println("Please select a listed option");
			}
		}
	}

}