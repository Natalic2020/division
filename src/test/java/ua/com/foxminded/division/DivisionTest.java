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
		final String expected = //
				"_0|158\r\n" + //
				" 0|---\r\n" + //
				"  |0";

		final String actual = division.divideColumn(divindend, divinder);
		assertEquals(expected, actual);
	}
	
	@Test
	void divideColumn_100_4() {
		final int divindend = 100;
		final int divinder = 4;
		final String expected = //
				"_100|4\r\n" + 
				"  8 |--\r\n" + 
				"  - |25\r\n" + 
				" _20\r\n" + 
				"  20\r\n" + 
				"  --\r\n" + 
				"   0";

		final String actual = division.divideColumn(divindend, divinder);
		assertEquals(expected, actual);
	}
	

	@Test
	void divideColumn_shouldReturnQuotientLengthEqualsDivindendLenght_whenDivindend5NumberDivinder1Number() {
		final int divindend = 78945;
		final int divinder = 4;
		final String expected = 
				"_78945|4\r\n" + // 
				" 4    |-----\r\n" + // 
				" -    |19736\r\n" + //
				"_38\r\n" + //
				" 36\r\n" + //
				" --\r\n" + //
				" _29\r\n" + //
				"  28\r\n" + //
				"  --\r\n" + //
				"  _14\r\n" + //
				"   12\r\n" + //
				"   --\r\n" + //
				"   _25\r\n" + //
				"    24\r\n" + //
				"    --\r\n" + //
				"     1";

		final String actual = division.divideColumn(divindend, divinder);
		assertEquals(expected, actual);
	}

	@Test
	void divideColumn_shouldOutputShiftAfterFirstStep_whenFirstNumberDividentEqualsDivinder() {
		final int divindend = 78945;
		final int divinder = 7;
		final String expected =
				"_78945|7\r\n" + // 
				" 7    |-----\r\n" + // 
				" -    |11277\r\n" + //
				" _8\r\n" + //
				"  7\r\n" + //
				"  -\r\n" + //
				" _19\r\n" + //
				"  14\r\n" + //
				"  --\r\n" + //
				"  _54\r\n" + //
				"   49\r\n" + //
				"   --\r\n" + //
				"   _55\r\n" + //
				"    49\r\n" + //
				"    --\r\n" + //
				"     6";

		final String actual = division.divideColumn(divindend, divinder);
		assertEquals(expected, actual);
	}

	@Test
	void divideColumn_shouldOutputShiftFirstStep_FirstNumberDividerIsLessThanFirstNumberDivider() {
		final int divindend = 10945;
		final int divinder = 8;
		final String expected = //
				"_10945|8\r\n" + //
				"  8   |----\r\n" + // 
				"  -   |1368\r\n" + //
				" _29\r\n" + //
				"  24\r\n" + //
				"  --\r\n" + //
				"  _54\r\n" + //
				"   48\r\n" + //
				"   --\r\n" + //
				"   _65\r\n" + //
				"    64\r\n" + //
				"    --\r\n" + //
				"     1"; //

		final String actual = division.divideColumn(divindend, divinder);
		assertEquals(expected, actual);
	}

	@Test
	void divideColumn_shouldOutputZerroInQuontient_whenStepNeedTwoDigits() {
		final int divindend = 4545;
		final int divinder = 45;
		final String expected = //
				"_4545|45\r\n" + //
				" 45  |---\r\n" + //
				" --  |101\r\n" + //
				"  _45\r\n" + //
				"   45\r\n" + //
				"   --\r\n" + //
				"    0";//

		final String actual = division.divideColumn(divindend, divinder);
		assertEquals(expected, actual);
	}
	
	@Test
	void divideColumn_shouldOutputTwoZerroInQuontient_whenStepNeedThreeDigits() {
		final int divindend = 453453;
		final int divinder = 453;
		final String expected = //
				"_453453|453\r\n" + //
				" 453   |----\r\n" + //
				" ---   |1001\r\n" + //
				"   _453\r\n" + //
				"    453\r\n" + //
				"    ---\r\n" + //
				"      0";//

		final String actual = division.divideColumn(divindend, divinder);
		assertEquals(expected, actual);
	}
	
	@Test
	void divideColumn_shouldOutputTwoZerroInQuontient_whenDivindendHasZerroStepNeedThreeDigits() {
		final int divindend = 45045;
		final int divinder = 45;
		final String expected =
				"_45045|45\r\n" + // 
				" 45   |----\r\n" + //
				" --   |1001\r\n" + //
				"  _045\r\n" + //
				"    45\r\n" + //
				"    --\r\n" + //
				"     0";

		final String actual = division.divideColumn(divindend, divinder);
		assertEquals(expected, actual);
	}	
	
	
}
