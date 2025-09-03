package com.Nash.Packages.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.Nash.Packages.model.Status;
import com.Nash.Packages.model.Task;

public class TaskManager {

	/**
	 * The main list off user created tasks, should be saved upon exit
	 */
	ArrayList<Task> taskList = new ArrayList<Task>();

	/**
	 * String version of the taskList, used to make tasks printable to user
	 */
	ArrayList<String> stringTaskList;

	/**
	 * Input scanner to accept user input
	 */
	Scanner in = new Scanner(System.in);

	/**
	 * Responsible for correctly setting up the Taskit program to run as needed Such
	 * as loading saved tasks and starting interrupt listener
	 */
	public void start() {

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println();
			saveTaskList();
		}));

		loadTaskList();

	}

	/**
	 * Root menu of the TaskIt program
	 */
	public int mainMenu() {
		updateStringList();
		System.out.println("Welcome to Task-It");
		System.out.println("What would you like to do?");
		ArrayList<String> topMenu = new ArrayList<String>(Arrays.asList("Add a Task", "List current Tasks",
				"Update Task Status", "Update Task Fields", "Delete current Tasks", "Exit"));

		return displayOptions(topMenu);

	}

	/**
	 * A private helper function that keeps the string version of the TaskList
	 * updated
	 */
	private void updateStringList() {
		ArrayList<String> updatedList = new ArrayList<String>();
		for (Task t : taskList) {
			updatedList.add(t.toString());
		}
		stringTaskList = updatedList;

	}

	/**
	 * Displays the given array of strings to the user with numeric options making
	 * them selectable
	 * 
	 * @param choices An Arraylist of strings that are to be displayed
	 * @return Returns the value received from user input
	 */
	private int displayOptions(ArrayList<String> choices) {
		int index = 1;
		for (String choice : choices) {
			System.out.println(index + ". " + choice);
			index++;
		}
		return waitForInput();
	}

	/**
	 * Attempts to get input from the user Handles malformed inputs gracefully calls
	 * itself recursively until valid input is given
	 * 
	 * @return Returns the user given input
	 */
	private int waitForInput() {
		System.out.println("");
		System.out.print("Enter Choice: ");
		// Once System.in is closed it cannot be reopened
		try {
			int option = in.nextInt();
			// Needed to digest the enter key from user
			in.nextLine();
			return option;
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
			in.nextLine();
			return waitForInput();
		} catch (NoSuchElementException e) {
			System.out.println("Invalid input");
			in.nextLine();
			return waitForInput();
		}

	}

	/**
	 * This function will ask the user the type of task they want listed and then
	 * list only that type of task
	 */
	public void listTasks() {
		if (taskList.size() < 1) {
			System.out.println("There are no tasks to be listed");
			System.out.println("Returning to the Main Menu");
			System.out.println("=============================");
			System.out.println("");

			return;
		}
		int listChoice = -1;
		ArrayList<String> listMenu = new ArrayList<String>(
				Arrays.asList("Completed Tasks", "Pending Tasks", "All Tasks", "Go back"));
		while (listChoice != 0) {
			System.out.println("What type of tasks would you like to list?");
			listChoice = displayOptions(listMenu);
			// Could add conditional logic when tasklist is empty
			// or could be granular and display a info msg when there are no tasks
			if (listChoice == 1) {
				for (Task t : taskList) {
					if (t.getStatus() == Status.COMPLETE) {
						System.out.println(t.toString());
					}
				}
			} else if (listChoice == 2) {
				for (Task t : taskList) {
					if (t.getStatus() == Status.PENDING) {
						System.out.println(t.toString());
					}
				}
			} else if (listChoice == 3) {
				for (Task t : taskList) {
					System.out.println(t.toString());
				}
			} else if (listChoice == 4) {
				listChoice = 0;
			}
			System.out.println("=============================");
			System.out.println();

		}
	}

	/**
	 * This function will ask the user to define a new task then add is to the
	 * taskList to be saved
	 */
	public void addTask() {
		int addChoice = 1;
		System.out.println("Would you like to create a new task?: ");
		ArrayList<String> yesNo = new ArrayList<String>(Arrays.asList("Yes", "No"));
		addChoice = displayOptions(yesNo);

		while (addChoice == 1) {
			System.out.println("What would you like to title the new task?: ");
			String title = in.nextLine();
			System.out.println("What is the description of the new task?: ");
			String description = in.nextLine();
			System.out.println("What is the due date of the new task?: ");
			LocalDate date = validateDate();
			Task toAdd = new Task(title, description, date);
			taskList.add(toAdd);
			System.out.println("Task \"" + title + "\" has been added");
			System.out.println("=============================");
			System.out.println();
			System.out.println("Would you like to add another task?: ");

			addChoice = displayOptions(yesNo);
		}

	}

	/**
	 * 
	 */
	public void updateTask() {
		if (taskList.size() < 1) {
			System.out.println("There are no tasks to be updated");
			System.out.println("Returning to the Main Menu");
			System.out.println("=============================");
			System.out.println("");

			return;
		}

		int updateChoice = 1;

		

		ArrayList<String> yesNo = new ArrayList<String>(Arrays.asList("Yes", "No"));

		while (updateChoice == 1 && taskList.size() > 0) {
			int taskChoice = 0;
			ArrayList<String> taskFields = new ArrayList<String>(Arrays.asList("Title", "Description", "Due Date"));
			System.out.println("Which task would you like to update? \nEnter 0 to cancel");
			try {
				// Adjusting index for user readable input
				int updateIndex = displayOptions(stringTaskList) - 1;
				if (updateIndex == -1) {
					break;
				}
				while ((taskChoice != 1) && (taskChoice != 2) && (taskChoice != 3)) {
					taskChoice = displayOptions(taskFields);
					if (taskChoice == 1) {
						System.out.println("What would you like to make the updated task title?: ");
						taskList.get(updateIndex).setTitle(in.nextLine());
					} else if (taskChoice == 2) {
						System.out.println("What would you like to make the updated task description?: ");
						taskList.get(updateIndex).setDescription(in.nextLine());
					} else if (taskChoice == 3) {
						System.out.println("What would you like to make the updated task due date?: ");
						LocalDate date = validateDate();
						taskList.get(updateIndex).setDate(date);
					}else if (taskChoice == 0) {
						break;
					} else {
						System.out.println("Please enter a valid field choice, or use 0 to escape");
						System.out.println("=============================");
						System.out.println();
						continue;
					}
				}
				System.out.println("=============================");
				System.out.println("Would you like to update another task?: ");
				updateChoice = displayOptions(yesNo);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("=============================");
				System.out.println("That is not a valid task number please select again");
				System.out.println("=============================");
				continue;
			}

			// Update the list of current tasks before displaying to user
			System.out.println("Updating string list");

			updateStringList();
		}
	}

	/**
	 * This function will ask the user to select a task then update the status of
	 * that task
	 */
	public void updateStatus() {
		if (taskList.size() < 1) {
			System.out.println("There are no tasks to be updated");
			System.out.println("Returning to the Main Menu");
			System.out.println("=============================");
			System.out.println("");

			return;
		}

		int updateChoice = 1;

		while (updateChoice == 1 && taskList.size() > 0) {

			ArrayList<String> yesNo = new ArrayList<String>(Arrays.asList("Yes", "No"));
			System.out.println("Which task status would you like to update? \nEnter 0 to cancel");
			try {
				// Adjusting index for user readable input
				int updateIndex = displayOptions(stringTaskList) - 1;
				if (updateIndex == -1) {
					break;
				}
				System.out.println(
						"Are you sure you want to change status of the task: \"" + taskList.get(updateIndex) + "\"");
				updateChoice = displayOptions(yesNo);
				if (updateChoice == 1) {

					Task tasktoUpdate = taskList.get(updateIndex);
					Status s = (tasktoUpdate.getStatus() == Status.PENDING) ? Status.COMPLETE : Status.PENDING;
					tasktoUpdate.setStatus(s);
					System.out.println("Task \"" + taskList.get(updateIndex).getTitle() + "\" has been updated to "
							+ s.toString());

				} else {
					System.out.println("Task \"" + taskList.get(updateIndex).getTitle() + "\" has not been updated");
				}
				System.out.println("=============================");
				System.out.println("Would you like to update another task?: ");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("=============================");
				System.out.println("That is not a valid task number please select again");
				System.out.println("=============================");
				continue;
			}

			updateChoice = displayOptions(yesNo);
			// Update the list of current tasks before displaying to user
			updateStringList();
		}

	}

	/**
	 * This function will prompt the user to select a task for deleting then delete
	 * that task after printing out confirmation
	 */
	public void deleteTask() {

		/**
		 * This code block may be able to be separated to a method since it is called a
		 * few times
		 */
		int deleteChoice = 1;

		if (taskList.size() < 1) {
			System.out.println("There are no tasks to be deleted");
			System.out.println("=============================");
			System.out.println("Returning to the Main Menu");
			System.out.println("");
			return;
		}

		while (deleteChoice == 1 && taskList.size() > 0) {

			System.out.println("Which task would you like to delete?");
			System.out.println("Or choose 0 to return to the main menu");
			System.out.println("=============================");

			ArrayList<String> yesNo = new ArrayList<String>(Arrays.asList("Yes", "No"));

			try {
				// Adjusting index for user readable input
				int deleteIndex = displayOptions(stringTaskList) - 1;
				if (deleteIndex == -1) {
					break;
				}
				System.out.println("Are you sure you want to delete task: \"" + taskList.get(deleteIndex) + "\"");
				deleteChoice = displayOptions(yesNo);
				if (deleteChoice == 1) {
					System.out.println("Task \"" + taskList.get(deleteIndex).getTitle() + "\" has been deleted");
					taskList.remove(deleteIndex);
				} else {
					System.out.println("Task \"" + taskList.get(deleteIndex).getTitle() + "\" has not been deleted");
				}
				System.out.println("=============================");
				System.out.println("Would you like to delete another task?: ");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("=============================");
				System.out.println(
						"That is not a valid task number please select again, \nor choose zero to return to the main menu");
				System.out.println("=============================");

				continue;
			}

			deleteChoice = displayOptions(yesNo);

			// Update the list of current tasks before re-displaying to user
			updateStringList();
		}
	}

	/**
	 * Helper method that ingests input and validates if the given date is in the
	 * correct format
	 * 
	 * @return return the given date, formatted correctly
	 */
	private LocalDate validateDate() {
		LocalDate date = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		while (date == null) {
			System.out.print("Enter a date (dd/MM/yyyy): ");
			String input = in.nextLine();

			try {
				date = LocalDate.parse(input, formatter);
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date format. Please try again.");
			}
		}

		return date;
	}

	/**
	 * A private helper method that saves the tasks from memory into a file when the
	 * program closes
	 */
	private void saveTaskList() {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("savedTaskList.ser"))) {
			out.writeObject(taskList);
			System.out.println("Tasks saved to file.");
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A private helper method that loads the tasks from previous executions, listed
	 * in the serialized file
	 */
	@SuppressWarnings("unchecked")
	private void loadTaskList() {
		System.out.println("Loading Tasks from file");
		try {
			ObjectInputStream loader = new ObjectInputStream(new FileInputStream("savedTaskList.ser"));
			taskList = (ArrayList<Task>) loader.readObject();
			loader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Serialized task file cannot be found, attempting to create a new one");
			saveTaskList();
		} catch (IOException e) {
			System.out.println("Reading tasks from file interrupted");
			System.exit(0);
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find class in classpath");
			System.exit(0);
		}

	}
}
