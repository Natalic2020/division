package ua.com.foxminded.division;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class ColumnDivision {

	private static final String MINUS = "-";
	private static final String WHITESPASE = " ";

	protected String getdivisionWhenDividendZerro(final int divider) {
		StringJoiner outputDivisionJoiner = new StringJoiner("");
		outputDivisionJoiner.add(String.format("_0|%d%n", divider));
		outputDivisionJoiner.add(String.format(" 0|%s%n", printCharacters(String.valueOf(divider).length(), MINUS)));
		outputDivisionJoiner.add("  |0");
		return outputDivisionJoiner.toString();
	}

	protected HashMap<String, Object> prepareResult(final int dividend, final int divider) {

		char[] dividendSplitNumber = String.valueOf(dividend).toCharArray();
		List<HashMap<String, String>> stepsDivision = new ArrayList<>();
		HashMap<String, String> currentStepDivisionString = new HashMap<>();
		StringJoiner quotient = new StringJoiner("");
		StringJoiner currentNumerals = new StringJoiner("");
		int remainder = 0;
		
		for (int i = 0; i < dividendSplitNumber.length; i++) {

			char currentNumeral = dividendSplitNumber[i];
			Integer minuend = receiveMinuend(currentNumerals, remainder, currentNumeral);

			if (minuend >= divider) {
				int digitPosition = i + 1;
				int currentQuotient = minuend / divider;
				int subtrahend = currentQuotient * divider;
			
				 currentStepDivisionString = prepareBody(subtrahend, digitPosition, minuend);
				
				 stepsDivision.add(currentStepDivisionString);
				 quotient.add(String.valueOf(currentQuotient));
				 remainder = minuend - subtrahend;
				 currentNumerals = new StringJoiner("");
				 
			} else {
				if (!quotient.toString().isEmpty()) {
					quotient.add("0");
				}
			}
		}
		prepareHead(dividend, divider,  quotient, stepsDivision);
		return saveParametersColumnDivision(prepareRemainder(dividend, remainder), stepsDivision);
	}

	private Integer receiveMinuend(StringJoiner currentNumerals, int remainder, char currentNumeral) {
		String currentRemainder = (remainder == 0) ? "" : String.valueOf(remainder); 
		currentNumerals.add(currentRemainder + currentNumeral);
		return Integer.parseInt(currentNumerals.toString());
	}

	private void prepareHead(final int dividend, final int divider,  final StringJoiner quotient, final List<HashMap<String, String>> stepsDivision) {
		String subtrahendHeard = stepsDivision.get(0).get("subtrahend").replaceAll("\n|\r\n", "");
		String underlineHeard = stepsDivision.get(0).get("underline").replaceAll("\n|\r\n", "");
		String whiteSpacesToVerticalLine = printCharacters(String.valueOf(dividend).length() - subtrahendHeard.length() + 1, WHITESPASE) ;
		String lineBetweenDividerAndQuotient = outputLineBetweenDividerAndQuotient(divider, quotient);
		
		stepsDivision.remove(0);
		HashMap<String, String> stepDivisionHead = new HashMap<>();
		
		stepDivisionHead.put("minuend", String.format("_%d|%d%n", dividend, divider));
		stepDivisionHead.put("subtrahend", String.format("%s%s|%s%n", subtrahendHeard, whiteSpacesToVerticalLine, lineBetweenDividerAndQuotient));
		stepDivisionHead.put("underline", String.format("%s%s|%s%n", underlineHeard, whiteSpacesToVerticalLine, quotient));
		stepsDivision.add(0, stepDivisionHead);
	}
	
	private HashMap<String, String> prepareBody(final int subtrahend, final int digitPosition, final Integer minuend) {
		HashMap<String, String> currentStepDivisionString = new HashMap<>();
		currentStepDivisionString.put("minuend",
				String.format("%s_%s%n", outputWhiteSpaces(digitPosition, minuend), minuend));
		currentStepDivisionString.put("subtrahend",
				String.format("%s %d%n", outputWhiteSpaces(digitPosition, subtrahend), subtrahend));
		currentStepDivisionString.put("underline",
				String.format("%s %s%n", outputWhiteSpaces(digitPosition, subtrahend), printCharacters(String.valueOf(subtrahend).length(), MINUS)));
		return currentStepDivisionString;
	}

	private String prepareRemainder(final int dividend, final int remainder) {
		String whitespacesRemainder = printCharacters(String.valueOf(dividend).length() - String.valueOf(remainder).length(), WHITESPASE);
		return String.format("%s %d", whitespacesRemainder, remainder);
	}
	
	private HashMap<String, Object> saveParametersColumnDivision(final String remainder, final List<HashMap<String, String>> stepsDivision) {
		HashMap<String, Object> parametersColunmDivision = new HashMap<>();
		parametersColunmDivision.put("stepsDivision", stepsDivision);
		parametersColunmDivision.put("remainder", remainder);
		return parametersColunmDivision;
	}

	private String outputWhiteSpaces(final int digitPosition, final int number) {
		int whiteSpacelength = digitPosition - String.valueOf(number).length();
		return printCharacters(whiteSpacelength, WHITESPASE);
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
