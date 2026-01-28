package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import calculator.Calculator;
import calculator.DivisionByZeroException;
import calculator.NotSupportedOperationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThrows;


class CalculatorTest {

	private Calculator calculator = new Calculator();
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calculator.setCurrentValue(0.0);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@ParameterizedTest
    @ValueSource(doubles = {0.0, 1.5, -2.0, 100.0})
    void testGetCurrentValue(Double initialValue) {
        calculator.setCurrentValue(initialValue);
        assertThat(calculator.getCurrentValue(), is(initialValue));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 10.0, -5.5, 999.99})
    void testSetCurrentValue(Double value) {
        calculator.setCurrentValue(value);
        assertThat(calculator.getCurrentValue(), is(value));
    }
    
    @ParameterizedTest
    @CsvSource({
        "0.0, 5.0, '+', 5.0",
        "5.0, 3.0, '-', 2.0",
        "2.0, 4.0, '*', 8.0",
        "8.0, 2.0, '/', 4.0"
    })
    void testCalculate(Double initialValue, Double operand, char operator, Double expectedResult) throws Exception {
        calculator.setCurrentValue(initialValue);
        calculator.calculate(operand, operator);
        assertThat(calculator.getCurrentValue(), is(expectedResult));
    }

    @ParameterizedTest
    @MethodSource("calcException")
    void testCalculateException(Double currVal, double value, char operator, Class<? extends Exception> exception) {
    	calculator.setCurrentValue(currVal);
        Exception thrown = assertThrows(exception, () -> calculator.calculate(value, operator));

        
        if (exception == DivisionByZeroException.class) {
            assertThat(thrown.getMessage(), containsString("Division by zero"));
        } else if (exception == NotSupportedOperationException.class) {
            assertThat(thrown.getMessage(), containsString("Operator '" + operator + "' is not supported"));
        }

    }

    private static Stream<Arguments> calcException() {
        return Stream.of(
                Arguments.of(1.0, 0.0, '/', DivisionByZeroException.class), // Division by zero
                Arguments.of(5.0, 5.0, 'm', NotSupportedOperationException.class), // Unsupported operator
                Arguments.of(3.0, 2.0, '^', NotSupportedOperationException.class)  // Unsupported operator
        );
    }

    


}
