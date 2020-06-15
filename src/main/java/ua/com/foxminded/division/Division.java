package ua.com.foxminded.division;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class Division {

	private static final String UNDERSCORE_SIGN = "-";
	private static final String SINGLE_INDENT = " ";

	public String columnDivision(final int dividend, final int divider) {
		return divideColumn(Math.abs(dividend), Math.abs(divider));
	}

	private String divideColumn(final int dividend, final int divider) {
		if (divider == 0) {
			throw new IllegalArgumentException("Error! Сannot be divided by zero");
		}
		return outputDivision(dividend, divider);
	}

	private String outputDivision(final int dividend, final int divider) {
		HashMap<String, Object> parametersColunmDivision = prepareResult(dividend, divider);
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

	private HashMap<String, Object> prepareResult(final int dividend, final int divider) {

		char[] dividendCharArray = String.valueOf(dividend).toCharArray();
		List<HashMap<String, String>> stepsDivision = new ArrayList<>();
		StringJoiner quotient = new StringJoiner("");
		StringJoiner currentNumerals = new StringJoiner("");
		int remainder = 0;

		for (int i = 0; i < dividendCharArray.length; i++) {
			char currentNumeral = dividendCharArray[i];
			if (i == 0 && currentNumeral == '0') {
				break;
			}
			Integer minuend = receiveMinuend(currentNumerals, remainder, currentNumeral);

			if (minuend >= divider) {
				int digitPosition = i + 1;
				int currentQuotient = minuend / divider;
				int subtrahend = currentQuotient * divider;

				stepsDivision.add(prepareBody(subtrahend, digitPosition, minuend));
				quotient.add(String.valueOf(currentQuotient));
				remainder = minuend - subtrahend;
				currentNumerals = new StringJoiner("");
			} else {
				if (!quotient.toString().isEmpty()) {
					quotient.add("0");
				}
			}
		}
		if (quotient.length()== 0) {
			quotient.add("0");
		}
		prepareHead(dividend, divider, quotient, stepsDivision);
		return saveParametersColumnDivision(prepareRemainder(dividend, remainder), stepsDivision);
	}

	private Integer receiveMinuend(StringJoiner currentNumerals, int remainder, char currentNumeral) {
		String currentRemainder = (remainder == 0) ? "" : String.valueOf(remainder);
		currentNumerals.add(currentRemainder + currentNumeral);
		return Integer.parseInt(currentNumerals.toString());
	}

	private void prepareHead(final int dividend, final int divider, final StringJoiner quotient,
			final List<HashMap<String, String>> stepsDivision) {
		String subtrahendHeard = " 0";
		String underlineHeard = " -";
		if (!stepsDivision.isEmpty()) {
			subtrahendHeard = stepsDivision.get(0).get("subtrahend").replaceAll("\n|\r\n", "");
			underlineHeard = stepsDivision.get(0).get("underline").replaceAll("\n|\r\n", "");
			stepsDivision.remove(0);
		}
		String whiteSpacesToVerticalLine = printCharacters(
				String.valueOf(dividend).length() - subtrahendHeard.length() + 1, SINGLE_INDENT);
		String lineBetweenDividerAndQuotient = outputLineBetweenDividerAndQuotient(divider, quotient);
		HashMap<String, String> stepDivisionHead = new HashMap<>();

		stepDivisionHead.put("minuend", String.format("_%d|%d%n", dividend, divider));
		stepDivisionHead.put("subtrahend",
				String.format("%s%s|%s%n", subtrahendHeard, whiteSpacesToVerticalLine, lineBetweenDividerAndQuotient));
		stepDivisionHead.put("underline",
				String.format("%s%s|%s%n", underlineHeard, whiteSpacesToVerticalLine, quotient));
		stepsDivision.add(0, stepDivisionHead);
	}

	private HashMap<String, String> prepareBody(final int subtrahend, final int digitPosition, final Integer minuend) {
		HashMap<String, String> currentStepDivisionString = new HashMap<>();
		currentStepDivisionString.put("minuend",
				String.format("%s_%s%n", outputWhiteSpaces(digitPosition, minuend), minuend));
		currentStepDivisionString.put("subtrahend",
				String.format("%s %d%n", outputWhiteSpaces(digitPosition, subtrahend), subtrahend));
		currentStepDivisionString.put("underline",
				String.format("%s %s%n", outputWhiteSpaces(digitPosition, subtrahend),
						printCharacters(String.valueOf(subtrahend).length(), UNDERSCORE_SIGN)));
		return currentStepDivisionString;
	}

	private String prepareRemainder(final int dividend, final int remainder) {
		String whitespacesRemainder = printCharacters(
				String.valueOf(dividend).length() - String.valueOf(remainder).length(), SINGLE_INDENT);
		return String.format("%s %d", whitespacesRemainder, remainder);
	}

	private HashMap<String, Object> saveParametersColumnDivision(final String remainder,
			final List<HashMap<String, String>> stepsDivision) {
		HashMap<String, Object> parametersColunmDivision = new HashMap<>();
		parametersColunmDivision.put("stepsDivision", stepsDivision);
		parametersColunmDivision.put("remainder", remainder);
		return parametersColunmDivision;
	}

	private String outputWhiteSpaces(final int digitPosition, final int number) {
		int whiteSpacelength = digitPosition - String.valueOf(number).length();
		return printCharacters(whiteSpacelength, SINGLE_INDENT);
	}

	private String outputLineBetweenDividerAndQuotient(final int divider, final StringJoiner quotient) {
		if (String.valueOf(divider).length() > quotient.length()) {
			return printCharacters(String.valueOf(divider).length(), UNDERSCORE_SIGN);
		}
		return printCharacters(quotient.length(), UNDERSCORE_SIGN);
	}

	private String printCharacters(final int lenghtLine, final String simbol) {
		StringJoiner outputSymbol = new StringJoiner("");
		for (int i = 0; i < lenghtLine; i++) {
			outputSymbol.add(simbol);
		}
		return outputSymbol.toString();
	}
}
