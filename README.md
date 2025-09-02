# SASChallenge
Coding challenge for a SAS interview

This is a simple, menu-driven console application using Java 21.

## Project Structure

SASChallenge/  
|── src/  
|&nbsp;&nbsp;&nbsp;&nbsp;└── com/  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── Nash/  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── Packages/  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── main/  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|── TaskIt.java  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── domain/  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|── Task.java  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|── TaskManager.java  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|── Status.java  
|── savedTaskList.ser  
|── README.md  

# Assumptions

User has command line knowledge  
User has previously installed Java 21 or newer


# Setup Instructions

Follow these instructions to run TaskIt on your local machine

### Prerequisites

- **Java 21** or higher: [Download here](https://jdk.java.net/21/)
- Bash Terminal

### Clone the Repository

```bash
git clone https://github.com/zmartens13/SASChallenge.git
cd SASChallenge
```

### Build the Program

```bash
javac -d out src/com/Nash/Packages/*/*.java
```

### Run the Program

```bash
java -cp out com.Nash.Packages.main.TaskIt
```

### Test the Program
#### Scenario: User adds a task  
    Given the user is at the add task menu, and chooses to add a task  
    When the user enters a valid title, description, and date  
    And presses enter
    Then the user should see a confirmation message
    And be asked if they want to add another task  

#### Scenario: User lists tasks
    Given the user is at the list task menu
    When the user chooses to list a type of tasks
    And presses enter
    Then the user should see a print out of the tasks they have added
    And be asked if they want to list a different type of task  

#### Scenario: User updates a task
    Given the user is at the update task menu, and chooses to update a task
    When the user enters a valid title, description, and date
    And presses enter
    Then the user should see a confirmation message
    And be asked if they want to update another task
