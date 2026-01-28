package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import calculator.CalculatorAdvanced;
import calculator.NotSupportedOperationException;
import calculator.NumberNotInAreaException;

class CalculatorAdvancedTest {
	
	private CalculatorAdvanced calculatorAdvance = new CalculatorAdvanced();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calculatorAdvance.setCurrentValue(0.0);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@ParameterizedTest
    @MethodSource("factorialProvider")
    void testFactorial(int input, int expected) {
        int result = calculatorAdvance.factorial(input);
        assertThat(result, is(expected));
    }

    private static Stream<Arguments> factorialProvider() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(1, 1),
                Arguments.of(5, 120),
                Arguments.of(10, 3628800)
        );
    }

    @ParameterizedTest
    @MethodSource("powerProvider")
    void testPower(int base, int exponent, int expected) {
        int result = calculatorAdvance.power(base, exponent);
        assertThat(result, is(expected));
    }

    private static Stream<Arguments> powerProvider() {
        return Stream.of(
                Arguments.of(2, 3, 8),
                Arguments.of(5, 0, 1),
                Arguments.of(7, 2, 49)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateAdvancedProvider")
    void testCalculateAdvanced(double currentValue, char action, double expected, Class<? extends Exception> exception)
            throws NumberNotInAreaException, NotSupportedOperationException {
    	calculatorAdvance.setCurrentValue(currentValue);

        if (exception != null) {
            Exception thrown = assertThrows(exception, () -> calculatorAdvance.calculateAdvanced(action));
            if (exception == NumberNotInAreaException.class) {
                assertThat(thrown.getMessage(), containsString("not from [0,10]"));
            } else {
                assertThat(thrown.getMessage(), containsString("not supported"));
            }
        } else {
        	calculatorAdvance.calculateAdvanced(action);
            assertThat(calculatorAdvance.getCurrentValue(), is(expected));
        }
    }

    private static Stream<Arguments> calculateAdvancedProvider() {
        return Stream.of(
                Arguments.of(5.0, '!', 120.0, null),
                Arguments.of(2.0, '3', 8.0, null),
                Arguments.of(-1.0, '!', 0.0, NumberNotInAreaException.class),
                Arguments.of(11.0, '!', 0.0, NumberNotInAreaException.class),
                Arguments.of(3.0, '^', 0.0, NotSupportedOperationException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("armstrongProvider")
    void testIsArmstrong(int input, boolean expected) {
        boolean result = calculatorAdvance.isArmstrong(input);
        assertThat(result, is(expected));
    }

    private static Stream<Arguments> armstrongProvider() {
        return Stream.of(
                Arguments.of(153, true),
                Arguments.of(9474, true),
                Arguments.of(123, false)
        );
    }

    @ParameterizedTest
    @MethodSource("perfectProvider")
    void testIsPerfect(int input, boolean expected) {
        boolean result = calculatorAdvance.isPerfect(input);
        assertThat(result, is(expected));
    }

    private static Stream<Arguments> perfectProvider() {
        return Stream.of(
                Arguments.of(6, true),
                Arguments.of(28, true),
                Arguments.of(12, false)
        );
    }

    @ParameterizedTest
    @MethodSource("hacCharacteristicProvider")
    void testHacCharacteristic(double currentValue, char value, boolean expected, Class<? extends Exception> exception)
            throws NumberNotInAreaException, NotSupportedOperationException {
    	calculatorAdvance.setCurrentValue(currentValue);

        if (exception != null) {
            Exception thrown = assertThrows(exception, () -> calculatorAdvance.hacCharacteristic(value));
            if (exception == NumberNotInAreaException.class) {
                assertThat(thrown.getMessage(), containsString("must be greater than or equal to 1"));
            } else {
                assertThat(thrown.getMessage(), containsString("not supported"));
            }
        } else {
            boolean result = calculatorAdvance.hacCharacteristic(value);
            assertThat(result, is(expected));
        }
    }

    private static Stream<Arguments> hacCharacteristicProvider() {
        return Stream.of(
                Arguments.of(6.0, 'A', true, null),
                Arguments.of(6.0, 'P', true, null),
                Arguments.of(0.0, 'A', false, NumberNotInAreaException.class),
                Arguments.of(6.0, 'X', false, NotSupportedOperationException.class)
        );
    }
}
