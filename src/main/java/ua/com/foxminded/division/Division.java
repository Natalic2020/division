package ua.com.foxminded.division;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Division {

	private static final String MINUS = "-";
	private static final String UNDERLINE = "_";
	private static final String VERTICAL_LINE = "|";
	private static final String WHITESPASE = " ";

	public void columnDivision(final int dividend, final int divider) {
		divideColumn(Math.abs(dividend), Math.abs(divider));
	}

	public String divideColumn(final int dividend, final int divider) {

		if (divider == 0) {
			throw new IllegalArgumentException("Error! Сannot be divided by zero");
		}
		if (dividend == 0) {
			return OutputDivisionToConsoleWhenDividendZerro(divider);
		}
		return OutputDivisionToConsole(dividend, divider);
	}

	private String OutputDivisionToConsoleWhenDividendZerro(final int divider) {
		StringJoiner outputDivisionJoiner = new StringJoiner("\n");
		outputDivisionJoiner.add(String.format("_0|%d", divider));
		outputDivisionJoiner.add(String.format(" 0|%s", outputLines(String.valueOf(divider).length())));
		outputDivisionJoiner.add("  |0");
		return outputDivisionJoiner.toString();
	}

	private String OutputDivisionToConsole(final int dividend, final int divider) {

		HashMap<String, Object> parametersColunmDivision = getValueforOutputColumnDivisionToConsole(dividend, divider);
		final List<HashMap<String, String>> stepsDivision = (List<HashMap<String, String>>) parametersColunmDivision
				.get("stepsDivision");
		final String remainder = (String) parametersColunmDivision.get("remainder");

		StringJoiner outputDivisionJoiner = new StringJoiner("\n");
		for (HashMap<String, String> stepDivision : stepsDivision) {
			outputDivisionJoiner.add(stepDivision.get("minuend"));
			outputDivisionJoiner.add(stepDivision.get("subtrahend"));
			outputDivisionJoiner.add(stepDivision.get("underline"));
		}
		outputDivisionJoiner.add(remainder);
		return outputDivisionJoiner.toString();
	}

	private HashMap<String, Object> getValueforOutputColumnDivisionToConsole(final int dividend, final int divider) {

		String[] dividendSplitNumber = String.valueOf(dividend).split("");

		HashMap<String, Object> parametersColunmDivision = new HashMap<String, Object>();
		List<HashMap<String, String>> stepsDivision = new ArrayList<>();

		StringJoiner quotient = new StringJoiner("");
		StringJoiner currentNumerals = new StringJoiner("");

		int currentRemainder = 0;
		boolean isNeedNextNumeral = false;
		boolean isFirstStep = true;

		String subtrahendHeard = "";
		String lastSubtrahendLength = "";
		String underlineHeard = "";

		int currentPositionNumber = 0;

		for (int i = 0; i < dividendSplitNumber.length; i++) {
			String currentNumeral = dividendSplitNumber[i];

			if (isNeedNextNumeral) {
				currentNumerals.add(currentNumeral);
			} else if (currentRemainder != 0) {
				currentNumerals.add(String.valueOf(currentRemainder) + currentNumeral);
			} else {
				currentNumerals.add(currentNumeral);
			}

			Integer minuend = Integer.parseInt(currentNumerals.toString());

			if (minuend >= divider) {
				isNeedNextNumeral = false;

				int currentQuotient = minuend / divider;
				int subtrahend = currentQuotient * divider;

				if (isFirstStep) {
				
					lastSubtrahendLength = String.valueOf(subtrahend);
					int shiftFirstDigit = getShiftSubtrahend(minuend, subtrahend);
					subtrahendHeard = String.format("%s%d", outputWhitespaces(shiftFirstDigit) ,subtrahend);
					underlineHeard = String.format("%s%s", outputWhitespaces(shiftFirstDigit) , outputLines(String.valueOf(subtrahend).length()));
					currentPositionNumber = currentPositionNumber + shiftFirstDigit;

					isFirstStep = false;
				} else {
					if (minuend == subtrahend) {
						currentPositionNumber = currentPositionNumber + String.valueOf(minuend).length();
					} else if (lastSubtrahendLength.length() == currentNumerals.length()) {
						currentPositionNumber = currentPositionNumber + 1;
					}

					String whiteSpacesBeforeNumber = outputWhitespaces(currentPositionNumber);
					HashMap<String, String> currentStepDivisionString = new HashMap<>();
					currentStepDivisionString.put("minuend",
							String.format("%s%s%s", whiteSpacesBeforeNumber, "_", currentNumerals));
					String shiftSubtrahend = outputWhitespaces(
							getShiftSubtrahend(currentNumerals.toString(), subtrahend));
					currentStepDivisionString.put("subtrahend",
							String.format("%s %s%d", whiteSpacesBeforeNumber, shiftSubtrahend, subtrahend));
					currentStepDivisionString.put("underline", String.format("%s %s%s", whiteSpacesBeforeNumber, shiftSubtrahend,
							outputLines(String.valueOf(subtrahend).length())));
					stepsDivision.add(currentStepDivisionString);
				}

				quotient.add(String.valueOf(currentQuotient));
				currentRemainder = minuend - subtrahend;
				lastSubtrahendLength = String.valueOf(subtrahend);
				currentNumerals = new StringJoiner("");
			} else {
				isNeedNextNumeral = true;
				if (!quotient.toString().isEmpty()) {
					quotient.add("0");
				}
			}
		}

		HashMap<String, Object> parametersStepDivisionHead = new HashMap<>();
		parametersStepDivisionHead.put("dividend", dividend);
		parametersStepDivisionHead.put("divider", divider);
		parametersStepDivisionHead.put("quotient", quotient);
		parametersStepDivisionHead.put("subtrahendHeard", subtrahendHeard);
		parametersStepDivisionHead.put("underlineHeard", underlineHeard);
		
		HashMap<String, String> stepDivisionHead = getStepDivisionHead( parametersStepDivisionHead); 

		stepsDivision.add(0, stepDivisionHead);

		String shiftRemainder = outputWhitespaces(
				getShiftSubtrahend(dividend, currentRemainder));
		final String remainder = String.format("%s %d", shiftRemainder, currentRemainder);

		parametersColunmDivision.put("stepsDivision", stepsDivision);
		parametersColunmDivision.put("remainder", remainder);

		return parametersColunmDivision;
	}

	private HashMap<String, String> getStepDivisionHead(final HashMap<String, Object> parametersStepDivisionHead) {
		final int dividend = (int) parametersStepDivisionHead.get("dividend");
		final int divider = (int) parametersStepDivisionHead.get("divider");
		final StringJoiner quotient = (StringJoiner) parametersStepDivisionHead.get("quotient");
		final String subtrahendHeard = (String) parametersStepDivisionHead.get("subtrahendHeard");
		final String underlineHeard = (String) parametersStepDivisionHead.get("underlineHeard");
		
		HashMap<String, String> stepDivisionHead = new HashMap<>();
		
		final String firstLineHead = String.format("_%d|%d", dividend, divider);
		stepDivisionHead.put("minuend", firstLineHead);
		
		String whiteSpacesToVerticalLine = outputWhitespaces(String.valueOf(dividend).length() - subtrahendHeard.length());
		String lineBetweenDividerAndQuotient = getLineBetweenDividerAndQuotient(divider, quotient);
		
	    String secondLineHead = String.format(" %s%s|%s",  subtrahendHeard, whiteSpacesToVerticalLine, lineBetweenDividerAndQuotient);
	    stepDivisionHead.put("subtrahend", secondLineHead);
	    
	    String thirdLineHead = String.format(" %s%s|%s",  underlineHeard, whiteSpacesToVerticalLine, quotient);
        stepDivisionHead.put("underline", thirdLineHead);
		return stepDivisionHead;
	}

	private String getLineBetweenDividerAndQuotient(final int divider, StringJoiner quotient) {
		String lineBetweenDividerAndQuotient;
		if (String.valueOf(divider).length() > quotient.length()) {
			lineBetweenDividerAndQuotient = outputLines(String.valueOf(divider).length());
		}else{
			lineBetweenDividerAndQuotient = outputLines(quotient.length());
	   }
		return lineBetweenDividerAndQuotient;
	}

	private int getShiftSubtrahend(int minuend, int subtrahend) {
		return String.valueOf(minuend).length() - String.valueOf(subtrahend).length();
	}
	
	private int getShiftSubtrahend(String minuend, int subtrahend) {
		return minuend.length() - String.valueOf(subtrahend).length();
	}
	
	
	private String outputLines(int lenghtLine) {
		return printSomeSameCharacters(lenghtLine, MINUS);
	}

	private String outputWhitespaces(int lenghtLine) {
		return printSomeSameCharacters(lenghtLine, WHITESPASE);
	}

	private String printSomeSameCharacters(int lenghtLine, String simbol) {
		StringJoiner outputSymbol = new StringJoiner("");
		for (int i = 0; i < lenghtLine; i++) {
			outputSymbol.add(simbol);
		}
		return outputSymbol.toString();
	}
}
