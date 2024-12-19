Here’s a `README.md` file for the String Calculator project:

---

# String Calculator Project

This project implements a string calculator that can handle basic arithmetic using string inputs. The calculator can process numbers separated by commas or newlines, supports custom delimiters, and throws exceptions when negative numbers are encountered. This project follows **Test-Driven Development (TDD)**, where tests are written first and code is implemented to pass those tests.

## Project Overview

The main functionality of the string calculator is implemented in the `StringCalculator` class, and the tests for this functionality are written in the `StringCalculatorTest` class.

- **`StringCalculator.java`** contains the logic for adding numbers, handling custom delimiters, and throwing exceptions for negative numbers.
- **`StringCalculatorTest.java`** contains JUnit test cases that verify the correctness of the `StringCalculator` class.

---

## Features

- **Empty string input** returns a sum of 0.
- **Single number** returns the number itself.
- **Multiple numbers** separated by commas or newlines are added together.
- **Custom delimiters** can be defined at the beginning of the input string.
- **Negative numbers** throw an exception and list all negative numbers in the exception message.

---

## File Structure

The project is structured as follows:

```
src/
├── main/
│   └── 
│     └── StringCalculator.java
└── test/
    └─
      └── StringCalculatorTest.java
```

---

## How to Run the Project

### 1. **Clone the repository**

To clone the project, use the following command:

```bash
git clone https://github.com/your-username/string-calculator.git
cd string-calculator
```

### 2. **Set up the project**

Make sure you have **Java** and **JUnit** installed to run the tests.

For Maven, you can use the following `pom.xml` configuration to add the necessary dependencies:

```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.7.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.7.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### 3. **Run Tests**

You can run the tests using **Maven** or **Gradle**.

- **Using Maven**:

   ```bash
   mvn test
   ```

- **Using Gradle** (if using Gradle, ensure the `build.gradle` file is set up correctly):

   ```bash
   gradle test
   ```

---

## Key Classes and Methods

### 1. **StringCalculator.java**

The main class that implements the calculator functionality.

```java
public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        // Check for custom delimiter
        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf("\n");
            String delimiter = numbers.substring(2, delimiterEndIndex);
            String numbersPart = numbers.substring(delimiterEndIndex + 1);
            return sum(numbersPart, delimiter);
        }

        return sum(numbers, "[,\n]");
    }

    private int sum(String numbers, String delimiter) {
        String[] numArray = numbers.split(delimiter);
        int sum = 0;
        StringBuilder negativeNumbers = new StringBuilder();

        for (String num : numArray) {
            int number = Integer.parseInt(num);
            if (number < 0) {
                if (negativeNumbers.length() > 0) {
                    negativeNumbers.append(", ");
                }
                negativeNumbers.append(number);
            } else {
                sum += number;
            }
        }

        if (negativeNumbers.length() > 0) {
            throw new IllegalArgumentException("Negative numbers not allowed: " + negativeNumbers);
        }

        return sum;
    }
}
```

### 2. **StringCalculatorTest.java**

The test class with JUnit 5 test cases for different scenarios.

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    @Test
    public void testEmptyString() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void testSingleNumber() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(1, calculator.add("1"));
    }

    @Test
    public void testTwoNumbers() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1,5"));
    }

    @Test
    public void testNewLineDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    public void testCustomDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    public void testNegativeNumbers() {
        StringCalculator calculator = new StringCalculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("1,-2,3,-4");
        });
        assertEquals("Negative numbers not allowed: -2, -4", exception.getMessage());
    }
}
```

---

## How the String Calculator Works

1. **Empty String**: An empty input string returns a sum of `0`.
   - Example: `"" → 0`

2. **Single Number**: If the string contains a single number, it returns the number.
   - Example: `"5" → 5`

3. **Multiple Numbers**: Numbers separated by commas or newlines are summed.
   - Example: `"1,2,3" → 6` or `"1\n2,3" → 6`

4. **Custom Delimiters**: If the input starts with `//`, it defines a custom delimiter.
   - Example: `"//;\n1;2" → 3`

5. **Negative Numbers**: If the string contains negative numbers, an exception is thrown, listing all the negative numbers.
   - Example: `"1,-2,3,-4" → IllegalArgumentException: "Negative numbers not allowed: -2, -4"`

This `README.md` provides an overview of how to set up, run, and contribute to the String Calculator project, as well as details on how the calculator handles different input cases.
