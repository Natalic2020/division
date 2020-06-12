package ua.com.foxminded.division;

import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class Division {

	public void columnDivision(final int dividend, final int divider) {
		divideColumn(Math.abs(dividend), Math.abs(divider));
	}

	public String divideColumn(final int dividend, final int divider) {

		if (divider == 0) {
			throw new IllegalArgumentException("Error! Сannot be divided by zero");
		}
		if (dividend == 0) {
			ColumnDivision resultDivision = new ColumnDivision();
			return resultDivision.getdivisionWhenDividendZerro(divider);
		}
		return outputDivisionToConsole(dividend, divider);
	}

	private String outputDivisionToConsole(final int dividend, final int divider) {

		ColumnDivision columnDivision = new ColumnDivision();
		HashMap<String, Object> parametersColunmDivision = columnDivision.prepareResult(dividend, divider);
		final List<HashMap<String, String>> stepsDivision = (List<HashMap<String, String>>) parametersColunmDivision
				.get("stepsDivision");
		final String remainder = (String) parametersColunmDivision.get("remainder");

		StringJoiner outputDivisionJoiner = new StringJoiner("");
		for (HashMap<String, String> stepDivision : stepsDivision) {
			outputDivisionJoiner.add(stepDivision.get("minuend"));
			outputDivisionJoiner.add(stepDivision.get("subtrahend"));
			outputDivisionJoiner.add(stepDivision.get("underline"));
		}
		outputDivisionJoiner.add(remainder);
		return outputDivisionJoiner.toString();
	}
}
