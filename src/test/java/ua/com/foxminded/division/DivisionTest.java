package ua.com.foxminded.division;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DivisionTest {

	Division division = new Division();

	@Test
	void divideColumn_shouldThrowIllegalArgumentException_whenInputDivinderZerro() {
		final int divindend = 78945;
		final int divinder = 0;

		assertThrows(IllegalArgumentException.class, () -> {
			division.divideColumn(divindend, divinder);
		});
	}

	@Test
	void divideColumn_shouldThrowIllegalArgumentException_whenInputDivinderZerroAndDividendZerro() {
		final int divindend = 0;
		final int divinder = 0;

		assertThrows(IllegalArgumentException.class, () -> {
			division.divideColumn(divindend, divinder);
		});
	}

	@Test
	void divideColumn_shouldReturnZerro_whenInputDivinderNoZerroAndDividendZerro() {
		final int divindend = 0;
		final int divinder = 158;
		division.divideColumn(divindend, divinder);
	}

	@Test
	void divideColumn_() {
		final int divindend = 78945;
		final int divinder = 4;
		division.divideColumn(divindend, divinder);
	}

	@Test
	void divideColumn_GetRemeinderZerroAfterFirstIterations() {
		final int divindend = 78945;
		final int divinder = 7;
		division.divideColumn(divindend, divinder);
	}

	@Test
	void divideColumn_FirstNumberDividerIsLessThanFirstNumberDivider() {
		final int divindend = 10945;
		final int divinder = 8;
		division.divideColumn(divindend, divinder);
	}

	@Test
	void divideColumn_HasZerroInTheMiddle() {
		final int divindend = 45045;
		final int divinder = 45;
		division.divideColumn(divindend, divinder);
	}

	@Test
	void divideColumn_GetRemeinderZerroInTheMiddleOfIterations() {
		final int divindend = 4545;
		final int divinder = 45;
		division.divideColumn(divindend, divinder);
	}

}
