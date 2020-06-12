package ua.com.foxminded.division;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class ColumnDivision {

	private static final String MINUS = "-";
	private static final String WHITESPASE = " ";

	protected String getdivisionWhenDividendZerro(final int divider) {
		StringJoiner outputDivisionJoiner = new StringJoiner("\n");
		outputDivisionJoiner.add(String.format("_0|%d", divider));
		outputDivisionJoiner.add(String.format(" 0|%s", printCharacters(String.valueOf(divider).length(), MINUS)));
		outputDivisionJoiner.add("  |0");
		return outputDivisionJoiner.toString();
	}

	protected HashMap<String, Object> prepareResult(final int dividend, final int divider) {

		char[] dividendSplitNumber = String.valueOf(dividend).toCharArray();
		List<HashMap<String, String>> stepsDivision = new ArrayList<>();
		HashMap<String, String> currentStepDivisionString = new HashMap<>();
		StringJoiner quotient = new StringJoiner("");

		StringJoiner currentNumerals = new StringJoiner("");
		String currentRemainder = "";
		
		for (int i = 0; i < dividendSplitNumber.length; i++) {

			char currentNumeral = dividendSplitNumber[i];
			currentNumerals.add(currentRemainder + currentNumeral);
			currentRemainder = "";
			Integer minuend = Integer.parseInt(currentNumerals.toString());

			if (minuend >= divider) {
				int digitPosition = i + 1;
				int currentQuotient = minuend / divider;
				int subtrahend = currentQuotient * divider;
			
				 currentStepDivisionString = prepareBody(subtrahend, digitPosition, currentNumerals);
				 stepsDivision.add(currentStepDivisionString);
				 quotient.add(String.valueOf(currentQuotient));
				 currentRemainder = receiveCurrentRemainer(minuend, subtrahend);
				 currentNumerals = new StringJoiner("");
				 
			} else {
				if (!quotient.toString().isEmpty()) {
					quotient.add("0");
				}
			}
		}
		prepareHead(dividend, divider,  quotient, stepsDivision);
		return saveParametersColumnDivision(prepareRemainder(dividend, currentRemainder), stepsDivision);
	}

	private String receiveCurrentRemainer(Integer minuend, int subtrahend) {
		String currentRemainder;
		if (minuend.equals(subtrahend)) {
			currentRemainder = "";
		 }else {
			currentRemainder = String.valueOf(minuend - subtrahend);
		 }
		return currentRemainder;
	}

	private void prepareHead(final int dividend, final int divider,  final StringJoiner quotient, final List<HashMap<String, String>> stepsDivision) {
		String subtrahendHeard = stepsDivision.get(0).get("subtrahend");
		String underlineHeard = stepsDivision.get(0).get("underline");
		String whiteSpacesToVerticalLine = printCharacters(String.valueOf(dividend).length() - subtrahendHeard.length() + 1, WHITESPASE) ;
		String lineBetweenDividerAndQuotient = outputLineBetweenDividerAndQuotient(divider, quotient);
		
		stepsDivision.remove(0);
		HashMap<String, String> stepDivisionHead = new HashMap<>();
		
		stepDivisionHead.put("minuend", String.format("_%d|%d", dividend, divider));
		stepDivisionHead.put("subtrahend", String.format("%s%s|%s", subtrahendHeard, whiteSpacesToVerticalLine, lineBetweenDividerAndQuotient));
		stepDivisionHead.put("underline", String.format("%s%s|%s", underlineHeard, whiteSpacesToVerticalLine, quotient));
		stepsDivision.add(0, stepDivisionHead);
	}
	
	private HashMap<String, String> prepareBody(final int subtrahend, final int digitPosition, final StringJoiner currentNumerals) {
		HashMap<String, String> currentStepDivisionString = new HashMap<>();
		currentStepDivisionString.put("minuend",
				String.format("%s_%s", outputWhiteSpacesMinuend(digitPosition, currentNumerals), currentNumerals));
		currentStepDivisionString.put("subtrahend",
				String.format("%s%d", outputWhiteSpacesSubtrahend(digitPosition, subtrahend), subtrahend));
		currentStepDivisionString.put("underline",
				String.format("%s%s", outputWhiteSpacesSubtrahend(digitPosition, subtrahend), printCharacters(String.valueOf(subtrahend).length(), MINUS)));
		return currentStepDivisionString;
	}

	private String prepareRemainder(final int dividend, final String currentRemainder) {
		String remainder = (currentRemainder.isEmpty() ? "0" : currentRemainder);
		String whitespacesRemainder = printCharacters(String.valueOf(dividend).length() - remainder.length(), WHITESPASE);
		return String.format("%s %s", whitespacesRemainder, remainder);
	}
	
	private HashMap<String, Object> saveParametersColumnDivision(final String remainder, final List<HashMap<String, String>> stepsDivision) {
		HashMap<String, Object> parametersColunmDivision = new HashMap<>();
		parametersColunmDivision.put("stepsDivision", stepsDivision);
		parametersColunmDivision.put("remainder", remainder);
		return parametersColunmDivision;
	}

	private String outputWhiteSpacesMinuend(final int digitPosition, final StringJoiner currentNumerals) {
		int whiteSpacelength = digitPosition - String.valueOf(currentNumerals).length();
		return printCharacters(whiteSpacelength, WHITESPASE);
	}

	private String outputWhiteSpacesSubtrahend(final int digitPosition, final int subtrahend) {
		int whiteSpacelength = digitPosition - String.valueOf(subtrahend).length();
		return printCharacters(whiteSpacelength + 1, WHITESPASE);
	}
	
	private String outputLineBetweenDividerAndQuotient(final int divider, final StringJoiner quotient) {
		if (String.valueOf(divider).length() > quotient.length()) {
			return printCharacters(String.valueOf(divider).length(), MINUS);
		}
		return printCharacters(quotient.length(), MINUS);
	}

	private String printCharacters(final int lenghtLine, final String simbol) {
		StringJoiner outputSymbol = new StringJoiner("");
		for (int i = 0; i < lenghtLine; i++) {
			outputSymbol.add(simbol);
		}
		return outputSymbol.toString();
	}
}
