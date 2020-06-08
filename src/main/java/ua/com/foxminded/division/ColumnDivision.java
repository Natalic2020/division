package ua.com.foxminded.division;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class ColumnDivision {

	private static final String MINUS = "-";
	private static final String WHITESPASE = " ";

	List<HashMap<String, String>> stepsDivision = new ArrayList<>();

	StringJoiner quotient = new StringJoiner("");
	StringJoiner currentNumerals = new StringJoiner("");

	int currentRemainder = 0;

	String subtrahendHeard = "";
	String underlineHeard = "";
	
	final int divider;
	final int dividend;

	public ColumnDivision(final int divider) {
		this(0,divider); 
	}

	public ColumnDivision(final int dividend, final int divider) {
		this.divider = divider;
		this.dividend = dividend;
	}

	protected String getdivisionWhenDividendZerro() {
		StringJoiner outputDivisionJoiner = new StringJoiner("\n");
		outputDivisionJoiner.add(String.format("_0|%d", divider));
		outputDivisionJoiner.add(String.format(" 0|%s", outputLines(String.valueOf(divider).length())));
		outputDivisionJoiner.add("  |0");
		return outputDivisionJoiner.toString();
	}

	protected HashMap<String, Object> getValueforOutputColumnDivisionToConsole() {
		return prepareResult();
	}

	protected HashMap<String, Object> prepareResult() {

		String[] dividendSplitNumber = String.valueOf(dividend).split("");
		boolean isFirstStep = true;
		
		for (int i = 0; i < dividendSplitNumber.length; i++) {

			String currentNumeral = dividendSplitNumber[i];
			getCurrentNumerals(currentNumeral);
			Integer minuend = Integer.parseInt(currentNumerals.toString());

			if (minuend >= divider) {

				int currentQuotient = minuend / divider;
				int subtrahend = currentQuotient * divider;
				int digitPosition = i + 1;
				
				if (isFirstStep) {			
					setHeadParametres( subtrahend, digitPosition);
					isFirstStep = false;
				} else {
					setBody(subtrahend, digitPosition);
				}
				quotient.add(String.valueOf(currentQuotient));
				currentRemainder = minuend - subtrahend;
				currentNumerals = new StringJoiner("");
			} else {
				if (!quotient.toString().isEmpty()) {
					quotient.add("0");
				}
			}
		}
		setHead();
		return setParametersColumnDivision(getRemainder());
	}

	private void setHeadParametres(final int subtrahend,final int digitPosition) {
		subtrahendHeard = String.format("%s%d%s|", getWhiteSpacesSubtrahend(digitPosition, subtrahend), subtrahend, getWhiteSpacesToVerticalLine(digitPosition));
		underlineHeard = String.format("%s%s%s|", getWhiteSpacesSubtrahend(digitPosition, subtrahend), getUnderLines(subtrahend), getWhiteSpacesToVerticalLine(digitPosition));
	}
	
	private void setHead() {
		HashMap<String, String> stepDivisionHead = new HashMap<>();
		stepDivisionHead.put("minuend", String.format("_%d|%d", dividend, divider));
		stepDivisionHead.put("subtrahend", String.format("%s%s", subtrahendHeard, getLineBetweenDividerAndQuotient()));
		stepDivisionHead.put("underline", String.format("%s%s", underlineHeard,  quotient));
		stepsDivision.add(0, stepDivisionHead);
	}
	
	private void setBody(final int subtrahend, final int digitPosition) {
		HashMap<String, String> currentStepDivisionString = new HashMap<>();
		currentStepDivisionString.put("minuend",
				String.format("%s_%s", getWhiteSpacesMinuend(digitPosition), currentNumerals));
		currentStepDivisionString.put("subtrahend",
				String.format("%s%d", getWhiteSpacesSubtrahend(digitPosition, subtrahend), subtrahend));
		currentStepDivisionString.put("underline",
				String.format("%s%s", getWhiteSpacesSubtrahend(digitPosition, subtrahend), getUnderLines(subtrahend)));
		stepsDivision.add(currentStepDivisionString);
	}

	private String getRemainder() {
		return String.format("%s %d", getWhitespacesRemainder(), currentRemainder);
	}
	
	private HashMap<String, Object> setParametersColumnDivision(final String remainder) {
		HashMap<String, Object> parametersColunmDivision = new HashMap<>();
		parametersColunmDivision.put("stepsDivision", stepsDivision);
		parametersColunmDivision.put("remainder", remainder);
		return parametersColunmDivision;
	}

	private String getWhiteSpacesMinuend(final int digitPosition) {
		int whiteSpacelength = digitPosition - String.valueOf(currentNumerals).length();
		return outputWhitespaces(whiteSpacelength);
	}

	private String getWhiteSpacesSubtrahend(final int digitPosition, final int subtrahend) {
		int whiteSpacelength = digitPosition - String.valueOf(subtrahend).length();
		return outputWhitespaces(whiteSpacelength + 1);
	}
	
	private String getWhiteSpacesToVerticalLine(final int digitPosition) {
		return outputWhitespaces(String.valueOf(dividend).length() - digitPosition);
	}
	
	private String getWhitespacesRemainder() {
		return outputWhitespaces(String.valueOf(dividend).length() - String.valueOf(currentRemainder).length());
	}
	
	private String getUnderLines(final int subtrahend) {
		return outputLines(String.valueOf(subtrahend).length());
	}
	
	private String getLineBetweenDividerAndQuotient() {
		if (String.valueOf(divider).length() > quotient.length()) {
			return outputLines(String.valueOf(divider).length());
		}
		return outputLines(quotient.length());
	}
	
	private void getCurrentNumerals(final String currentNumeral) {
		if (currentRemainder != 0) {
			currentNumerals.add(String.valueOf(currentRemainder) + currentNumeral);
			currentRemainder = 0;
		} else {
			currentNumerals.add(currentNumeral);
		}
	}

	private String outputLines(final int lenghtLine) {
		return printSomeSameCharacters(lenghtLine, MINUS);
	}

	private String outputWhitespaces(final int lenghtLine) {
		return printSomeSameCharacters(lenghtLine, WHITESPASE);
	}

	private String printSomeSameCharacters(final int lenghtLine, final String simbol) {
		StringJoiner outputSymbol = new StringJoiner("");
		for (int i = 0; i < lenghtLine; i++) {
			outputSymbol.add(simbol);
		}
		return outputSymbol.toString();
	}
}
