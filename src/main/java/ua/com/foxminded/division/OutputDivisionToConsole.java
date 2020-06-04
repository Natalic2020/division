package ua.com.foxminded.division;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputDivisionToConsole {

	private static final String MINUS = "-";
	private static final String UNDERLINE = "_";
	private static final String VERTICAL_LINE = "|";
	private static final String WHITESPASE = " ";

	private int currentPositionNumber = 3;
	private String dividend;
	private String divider;
	private List<String[]> stepsDivisionList;
	private String quotient;
	private String remainder;
	private int shiftFirstDigit = 0;
	private StringJoiner outputDivisionJoiner = new StringJoiner("");

	public OutputDivisionToConsole(final String divider) {
		this.divider = divider;
	}

	public OutputDivisionToConsole(Map<String, Object> parametersColumnDivision) {
		dividend = parametersColumnDivision.get("dividend").toString();
		divider = parametersColumnDivision.get("divider").toString();
		quotient = parametersColumnDivision.get("quotient").toString();
		remainder = parametersColumnDivision.get("remainder").toString();
		stepsDivisionList = (List) parametersColumnDivision.get("stepsDivisionList");
		shiftFirstDigit = stepsDivisionList.get(0)[0].length()
				- stepsDivisionList.get(0)[1].length();
	}

	public String drawColumnDivisionToConsoleWhenDividendZerro() {
		outputWhitespaces(currentPositionNumber - 1);
		outputDivisionJoiner.add(UNDERLINE);
		outputDivisionJoiner.add("0");
		outputDivisionJoiner.add(VERTICAL_LINE);
		outputDivisionJoiner.add(divider);
		outputDivisionJoiner.add("\n");

		outputWhitespaces(currentPositionNumber);
		outputDivisionJoiner.add("0");
		outputDivisionJoiner.add(VERTICAL_LINE);
		outputLines(divider.length());
		outputDivisionJoiner.add("\n");

		outputWhitespaces(currentPositionNumber + 1);
		outputDivisionJoiner.add(VERTICAL_LINE);
		outputDivisionJoiner.add("0");

		return outputDivisionJoiner.toString();
	}

	public String drawColumnDivisionToConsole() {
		drawHeader();
		drawBody();
		drawRemainder();
		return outputDivisionJoiner.toString();
	}

	private void drawHeader() {
		drawFirstLineHeader();
		currentPositionNumber += shiftFirstDigit;
		drawSecondLineHeader();
		drawThirdLineHeader();
	}

	private void drawFirstLineHeader() {
		outputWhitespaces(currentPositionNumber - 1);
		outputDivisionJoiner.add(UNDERLINE);
		outputDivisionJoiner.add(dividend);
		outputDivisionJoiner.add(VERTICAL_LINE);
		outputDivisionJoiner.add(divider);
		outputDivisionJoiner.add("\n");
	}

	private void drawSecondLineHeader() {
		outputWhitespaces(currentPositionNumber);
		outputDivisionJoiner.add(stepsDivisionList.get(0)[1]);
		outputWhitespaces(dividend.length() - stepsDivisionList.get(0)[1].length() - shiftFirstDigit);
		outputDivisionJoiner.add(VERTICAL_LINE);
		outputLines(divider.length() > quotient.length() ? divider.length() : quotient.length());
		outputDivisionJoiner.add("\n");
	}

	private void drawThirdLineHeader() {
		outputWhitespaces(currentPositionNumber);
		outputLines(String.valueOf(stepsDivisionList.get(0)[1]).length());
		outputWhitespaces(dividend.length() - stepsDivisionList.get(0)[1].length() - shiftFirstDigit);
		outputDivisionJoiner.add(VERTICAL_LINE);
		outputDivisionJoiner.add(quotient);
		outputDivisionJoiner.add("\n");
	}

	private void drawBody() {
		for (int i = 1; i < stepsDivisionList.size(); i++) {

			if (Integer.parseInt(stepsDivisionList.get(i)[0]) == Integer
					.parseInt(stepsDivisionList.get(i)[1])) {
				currentPositionNumber = currentPositionNumber + stepsDivisionList.get(i)[1].length();
			} else if (stepsDivisionList.get(i - 1)[1].length() == stepsDivisionList.get(i)[0]
					.length()) {
				currentPositionNumber = currentPositionNumber + 1;
			}
			outputWhitespaces(currentPositionNumber - 1);
			outputDivisionJoiner.add(UNDERLINE);
			outputDivisionJoiner.add(stepsDivisionList.get(i)[0]);
			outputDivisionJoiner.add("\n");
			int shiftNumeral = stepsDivisionList.get(i)[0].length()
					- stepsDivisionList.get(i)[1].length();
			currentPositionNumber = currentPositionNumber + shiftNumeral;
			outputWhitespaces(currentPositionNumber);
			outputDivisionJoiner.add(stepsDivisionList.get(i)[1]);
			outputDivisionJoiner.add("\n");
			outputWhitespaces(currentPositionNumber);
			int lenghtSubtrahend = stepsDivisionList.get(i)[1].length();
			outputLines(lenghtSubtrahend);
			outputDivisionJoiner.add("\n");
		}
	}

	private void drawRemainder() {
		int shiftRemainder = stepsDivisionList.get(stepsDivisionList.size() - 1)[1].length()
				- remainder.length();
		outputWhitespaces(currentPositionNumber + shiftRemainder);
		outputDivisionJoiner.add(remainder);
	}

	private void outputLines(int lenghtLine) {
		printSomeSameCharacters(lenghtLine, MINUS);
	}

	private void outputWhitespaces(int lenghtLine) {
		printSomeSameCharacters(lenghtLine, WHITESPASE);
	}

	private void printSomeSameCharacters(int lenghtLine, String simbol) {
		for (int i = 0; i < lenghtLine; i++) {
			outputDivisionJoiner.add(simbol);
		}
	}
}
