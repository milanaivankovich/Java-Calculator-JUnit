# Java Calculator & Advanced Logic Testing

A robust Java-based calculator application developed with a focus on comprehensive software quality and automated unit testing. This project demonstrates advanced usage of the **JUnit 5 (Jupiter)** framework to validate mathematical logic and handle edge cases.

## 🚀 Key Features

* **Core Calculations**: Standard arithmetic operations with precise state management.
* **Advanced Logic**: Complex algorithms including Factorials, Armstrong numbers, Perfect numbers, and Power functions.
* **Custom Exception Handling**: Robust validation for division by zero and "out of area" numerical inputs.

## 🧪 Testing Methodologies

This repository serves as a showcase for modern testing techniques:

* **Parameterized Testing**: Extensive use of `@ParameterizedTest` with `@MethodSource`, `@CsvSource`, and `@ValueSource` to run data-driven test suites efficiently.
* **Behavior Verification**: Utilizing **Hamcrest Matchers** (`assertThat`, `is`, `containsString`) for readable and expressive assertions.
* **Lifecycle Management**: Proper implementation of `@BeforeEach` and `@AfterEach` to ensure test isolation and clean state resets.
* **Edge Case Coverage**: Specific test suites designed to verify system stability under invalid inputs and boundary conditions.

## 🛠 Tech Stack

* **Language**: Java
* **Testing Framework**: JUnit 5 (Jupiter)
* **Assertion Library**: Hamcrest
* **Build Tool**: Maven/Eclipse Project Structure

## 📂 Project Structure

* `src/calculator`: Contains the core logic classes (`Calculator.java`, `CalculatorAdvanced.java`).
* `src/test`: Contains the test suites (`CalculatorTest.java`, `CalculatorAdvancedTest.java`) demonstrating full coverage of the core logic.

## 🏁 Getting Started

1. **Clone the repository**:
```bash
git clone https://github.com/milanaivankovich/Java-Calculator-JUnit.git

```


2. **Run tests**:
Open the project in your favorite IDE (Eclipse/IntelliJ) and run the test classes as **JUnit Tests**.

