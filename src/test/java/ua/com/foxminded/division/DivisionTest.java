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
				"  _0|158\n" + //
				"   0|---\n" + //
				"    |0";

		final String actual = division.divideColumn(divindend, divinder);
		assertEquals(expected, actual);
	}

	@Test
	void divideColumn_() {
		final int divindend = 78945;
		final int divinder = 4;
		final String expected = 
				"  _78945|4\n" + // 
				"   4    |-----\n" + // 
				"   -    |19736\n" + //
				"  _38\n" + //
				"   36\n" + //
				"   --\n" + //
				"   _29\n" + //
				"    28\n" + //
				"    --\n" + //
				"    _14\n" + //
				"     12\n" + //
				"     --\n" + //
				"     _25\n" + //
				"      24\n" + //
				"      --\n" + //
				"       1";

		final String actual = division.divideColumn(divindend, divinder);
		assertEquals(expected, actual);

	}

	@Test
	void divideColumn_GetRemeinderZerroAfterFirstIterations() {
		final int divindend = 78945;
		final int divinder = 7;
		final String expected =
				"  _78945|7\n" + // 
				"   7    |-----\n" + // 
				"   -    |11277\n" + //
				"   _8\n" + //
				"    7\n" + //
				"    -\n" + //
				"   _19\n" + //
				"    14\n" + //
				"    --\n" + //
				"    _54\n" + //
				"     49\n" + //
				"     --\n" + //
				"     _55\n" + //
				"      49\n" + //
				"      --\n" + //
				"       6";

		final String actual = division.divideColumn(divindend, divinder);
		assertEquals(expected, actual);
	}

	@Test
	void divideColumn_FirstNumberDividerIsLessThanFirstNumberDivider() {
		final int divindend = 10945;
		final int divinder = 8;
		final String expected = //
				"  _10945|8\n" + //
				"    8   |----\n" + // 
				"    -   |1368\n" + //
				"   _29\n" + //
				"    24\n" + //
				"    --\n" + //
				"    _54\n" + //
				"     48\n" + //
				"     --\n" + //
				"     _65\n" + //
				"      64\n" + //
				"      --\n" + //
				"       1"; //

		final String actual = division.divideColumn(divindend, divinder);
		assertEquals(expected, actual);
	}

	@Test
	void divideColumn_HasZerroInTheMiddle() {
		final int divindend = 45045;
		final int divinder = 45;
		final String expected =
				"  _45045|45\n" + // 
				"   45   |----\n" + //
				"   --   |1001\n" + //
				"    _045\n" + //
				"      45\n" + //
				"      --\n" + //
				"       0";

		final String actual = division.divideColumn(divindend, divinder);
		assertEquals(expected, actual);
	}

	@Test
	void divideColumn_GetRemeinderZerroInTheMiddleOfIterations() {
		final int divindend = 4545;
		final int divinder = 45;
		final String expected = //
				"  _4545|45\n" + //
				"   45  |---\n" + //
				"   --  |101\n" + //
				"    _45\n" + //
				"     45\n" + //
				"     --\n" + //
				"      0";//

		final String actual = division.divideColumn(divindend, divinder);
		assertEquals(expected, actual);
	}

}
