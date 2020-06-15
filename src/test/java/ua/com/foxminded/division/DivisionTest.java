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
			division.columnDivision(divindend, divinder);
		});
	}

	@Test
	void divideColumn_shouldThrowIllegalArgumentException_whenInputDivinderZerroAndDividendZerro() {
		final int divindend = 0;
		final int divinder = 0;

		assertThrows(IllegalArgumentException.class, () -> {
			division.columnDivision(divindend, divinder);
		});
	}

	@Test
	void divideColumn_shouldReturnZerro_whenInputDivinderNoZerroAndDividendZerro() {
		final int divindend = 0;
		final int divinder = 158;
		final String expected = String.format("%s%n%s%n%s%n%s", //
				"_0|158", // 
				" 0|---", //
				" -|0", // 
				" 0");

		   final String actual = division.columnDivision(divindend, divinder);
		assertEquals(expected, actual);
	}	
	
	@Test
	void divideColumn_100_4() {
		final int divindend = 100;
		final int divinder = 4;
		final String expected = String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s", //
				"_100|4", // 
				"  8 |--", // 
				"  - |25", // 
				" _20", // 
				"  20", // 
				"  --", // 
				"   0");

		final String actual = division.columnDivision(divindend, divinder);
		assertEquals(expected, actual);
	}
	

	@Test
	void divideColumn_shouldReturnQuotientLengthEqualsDivindendLenght_whenDivindend5NumberDivinder1Number() {
		final int divindend = 78945;
		final int divinder = 4;
		final String expected = String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s", //
				"_78945|4",  // 
				" 4    |-----",  // 
				" -    |19736",  //
				"_38"         ,  //
				" 36"         ,  //
				" --",          //
				" _29",  //
				"  28",  //
				"  --",  //
				"  _14",  //
				"   12",  //
				"   --",  //
				"   _25",  //
				"    24",  //
				"    --",  //
				"     1");

		final String actual = division.columnDivision(divindend, divinder);
		assertEquals(expected, actual);
	}

	@Test
	void divideColumn_shouldOutputShiftAfterFirstStep_whenFirstNumberDividentEqualsDivinder() {
		final int divindend = 78945;
		final int divinder = 7;
		final String expected = String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s", //
				"_78945|7",  // 
				" 7    |-----",  // 
				" -    |11277",  //
				" _8",  //
				"  7",  //
				"  -",  //
				" _19",//
				"  14",  //
				"  --",  //
				"  _54",  //
				"   49",  //
				"   --",  //
				"   _55",  //
				"    49",  //
				"    --",  //
				"     6" );

		final String actual = division.columnDivision(divindend, divinder);
		assertEquals(expected, actual);
	}

	@Test
	void divideColumn_shouldOutputShiftFirstStep_FirstNumberDividerIsLessThanFirstNumberDivider() {
		final int divindend = 10945;
		final int divinder = 8;
		final String expected = String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s", //
				"_10945|8",//
				"  8   |----",// 
				"  -   |1368",//
				" _29",//
				"  24",//
				"  --",//
				"  _54",//
				"   48",//
				"   --",//
				"   _65",//
				"    64",  //
				"    --",  //
				"     1"); //

		final String actual = division.columnDivision(divindend, divinder);
		assertEquals(expected, actual);
	}

	@Test
	void divideColumn_shouldOutputZerroInQuontient_whenStepNeedTwoDigits() {
		final int divindend = 4545;
		final int divinder = 45;
		final String expected = String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s", //
				"_4545|45",//
				" 45  |---",//
				" --  |101",//
				"  _45",//
				"   45",//
				"   --",//
				"    0");//

		final String actual = division.columnDivision(divindend, divinder);
		assertEquals(expected, actual);
	}
	
	@Test
	void divideColumn_shouldOutputTwoZerroInQuontient_whenStepNeedThreeDigits() {
		final int divindend = 453453;
		final int divinder = 453;
		final String expected = String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s", //
				"_453453|453",//
				" 453   |----",//
				" ---   |1001",//
				"   _453",//
				"    453",//
				"    ---",//
				"      0");//

		final String actual = division.columnDivision(divindend, divinder);
		assertEquals(expected, actual);
	}
	
	@Test
	void divideColumn_shouldOutputTwoZerroInQuontient_whenDivindendHasZerroStepNeedThreeDigits() {
		final int divindend = 45045;
		final int divinder = 45;
		final String expected = String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s", //
				"_45045|45",// 
				" 45   |----",//
				" --   |1001",//
				"   _45",//
				"    45",//
				"    --",//
				"     0");

		final String actual = division.columnDivision(divindend, divinder);
		assertEquals(expected, actual);
	}		
}
