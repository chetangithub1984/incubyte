# Project: Incubyte Test Automation Framework

## Overview
The Incubyte Test Automation Framework is a robust Selenium-based automation framework designed to support efficient test automation for web applications. The framework includes modular Page Object Model (POM) classes, utilities for test data management, and support for dynamic data generation.

---

## Features
- **Centralized Configuration**: we have config.properties file for better maintainability.
- **Modular Design**: Follows the Page Object Model (POM) structure for better code reusability.
- **Test Data Management**: Integrated Excel utilities for dynamic test data updates.
- **Dynamic Data Generators**: Includes utilities for generating random names, emails, and passwords.
- **Selenium WebDriver Integration**: Simplified interaction with web elements.

---

## File Structure

```
Incubyte/
|
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── incubyte/
│   │   │   │   │   ├── base/         # Base classes
│   │   │   │   │   ├── pages/        # Page Object Model (POM) classes
│   │   │   │   │   ├── reports/      # Reporting utilities
│   │   │   │   │   ├── runner/       # Test runners
│   │   │   │   │   ├── stepdefinition/ # Step definitions for Cucumber
│   │   │   │   │   ├── utils/        # Utility classes (e.g., constants, helpers)
│   │   ├── resources/                # Configuration and resource files
│   └── test/
│       ├── java/
│       │   ├── com/
│       │   │   ├── incubyte/
│       │   │   │   ├── features/     # Gherkin feature files
│       │   │   │   ├── testData/     # Test data (e.g., Excel files)
│       ├── resources/                # Configuration files
├── pom.xml                           # Maven configuration
└── README.md                         # Project documentation
```

---

## Setup Instructions

### Prerequisites
1. **Java Development Kit (JDK):** Version 8 or higher.
2. **Maven:** Ensure Maven is installed and configured in your environment.
3. **IDE:** Use IntelliJ IDEA, Eclipse, or any preferred IDE.
4. **Browser Drivers:** Ensure the required WebDriver executables (e.g., ChromeDriver) are available in your PATH.

### Steps to Set Up
1. Clone the repository:
   ```bash
   git clone <repository_url>
   ```

2. Navigate to the project directory:
   ```bash
   cd Incubyte
   ```

3. Install dependencies:
   ```bash
   mvn clean install
   ```

4. Run tests:
   ```bash
   mvn test
   ```

---

## Running Tests

### Using Maven
- Run all tests:
  ```bash
  mvn test
  ```

- Run specific tests with a Cucumber tag:
  ```bash
  mvn test -Dcucumber.options="--tags @YourTagName"
  ```

- In order to run this framework, please execute `CucumberRunner.java`.
- For parallel testing, the framework leverages `ThreadLocal` concepts to manage WebDriver instances independently.

### Generating Reports
- Test execution reports will be generated in the `target` directory.

---

## Contribution Guidelines
1. Fork the repository and create a new branch for your feature or bugfix.
2. Commit your changes with clear and concise messages.
3. Push your branch and create a Pull Request.


---

## Contact
For questions or support, please contact [patel.chetan84@gmail.com].

