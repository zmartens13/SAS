# SASChallenge
Challenge code for a SAS interview

This is a simple, menu-driven console application using Java 21.

## Project Structure

SASChallenge/
├── src/
│   └── com/
│      └── Nash/
│         └── Packages/
│            └── main/
│               ├── TaskIt.java
│            └── domain/
│               ├── Task.java
│               └── TaskManager.java
│               ├── Status.java
├── savedTaskList.ser
└── README.md

# Setup Instructions

## Getting Started

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
Add a few test scenarios to run through and show functionality
