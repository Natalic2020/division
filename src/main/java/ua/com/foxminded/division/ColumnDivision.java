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

	//String subtrahendHeard = "";
	//String underlineHeard = "";
	
//	final int divider;
//	final int dividend;

//	public ColumnDivision(final int divider) {
//		this(0,divider); 
//	}
//
//	public ColumnDivision(final int dividend, final int divider) {
//		this.divider = divider;
//		this.dividend = dividend;
//	}

	protected String getdivisionWhenDividendZerro(final int divider) {
		StringJoiner outputDivisionJoiner = new StringJoiner("\n");
		outputDivisionJoiner.add(String.format("_0|%d", divider));
		outputDivisionJoiner.add(String.format(" 0|%s", printCharacters(String.valueOf(divider).length(), MINUS)));
		outputDivisionJoiner.add("  |0");
		return outputDivisionJoiner.toString();
	}

	protected HashMap<String, Object> prepareResult(final int dividend, final int divider) {

		char[] dividendSplitNumber = String.valueOf(dividend).toCharArray();
		
		boolean isFirstStep = true;
		
		for (int i = 0; i < dividendSplitNumber.length; i++) {

			char currentNumeral = dividendSplitNumber[i];
			receiveCurrentNumerals(currentNumeral);
			Integer minuend = Integer.parseInt(currentNumerals.toString());
			HashMap<String, String> headParametres = new HashMap<>();
			if (minuend >= divider) {
				int digitPosition = i + 1;
			//	headParametres = prepareStepDivision(isFirstStep, digitPosition, minuend, divider, dividend);
				isFirstStep = prepareStepDivision(isFirstStep, digitPosition, minuend, divider, dividend);
			} else {
				if (!quotient.toString().isEmpty()) {
					quotient.add("0");
				}
			}
		}
		HashMap<String, String> headParametres = new HashMap<>();  // remove
		prepareHead(dividend, divider, headParametres);
		return saveParametersColumnDivision(prepareRemainder(dividend));
	}

	private boolean prepareStepDivision(boolean isFirstStep, int digitPosition, Integer minuend, final int divider, final int dividend) {
		int currentQuotient = minuend / divider;
		int subtrahend = currentQuotient * divider;
		
		//if (isFirstStep) {			
			//HashMap<String, String> headParametres = prepareHeadParametres( subtrahend, digitPosition, dividend);
			//isFirstStep = false;
	//	} else {
			prepareBody(subtrahend, digitPosition);
		//}
		quotient.add(String.valueOf(currentQuotient));
		currentRemainder = minuend - subtrahend;
		currentNumerals = new StringJoiner("");
		return isFirstStep;
	}

	private 
	HashMap<String, String> prepareHeadParametres(final int subtrahend, final int digitPosition, final int dividend) {
		String  whiteSpacesToVerticalLine = printCharacters(String.valueOf(dividend).length() - digitPosition, WHITESPASE);
		HashMap<String, String> headParametres = new HashMap<>();
		String subtrahendHeard = String.format("%s%d%s|", outputWhiteSpacesSubtrahend(digitPosition, subtrahend), subtrahend, whiteSpacesToVerticalLine);
		String underlineHeard = String.format("%s%s%s|", outputWhiteSpacesSubtrahend(digitPosition, subtrahend), printCharacters(String.valueOf(subtrahend).length(), MINUS), whiteSpacesToVerticalLine);
		headParametres.put("subtrahendHeard", subtrahendHeard);
		headParametres.put("underlineHeard", underlineHeard);
		return headParametres;
	}
	
	private void prepareHead(final int dividend, final int divider, HashMap<String, String> headParametres) {
		String subtrahendHeard = stepsDivision.get(0).get("subtrahend");
		String underlineHeard = stepsDivision.get(0).get("underline");
		String whiteSpacesToVerticalLine = printCharacters(String.valueOf(dividend).length() - subtrahendHeard.length() + 1, WHITESPASE) ;
		
		stepsDivision.remove(0);
		HashMap<String, String> stepDivisionHead = new HashMap<>();
		
		stepDivisionHead.put("minuend", String.format("_%d|%d", dividend, divider));
		stepDivisionHead.put("subtrahend", String.format("%s%s|%s", subtrahendHeard, whiteSpacesToVerticalLine, outputLineBetweenDividerAndQuotient(divider)));
		stepDivisionHead.put("underline", String.format("%s%s|%s", underlineHeard, whiteSpacesToVerticalLine, quotient));
		stepsDivision.add(0, stepDivisionHead);
	}
	
	private void prepareBody(final int subtrahend, final int digitPosition) {
		HashMap<String, String> currentStepDivisionString = new HashMap<>();
		currentStepDivisionString.put("minuend",
				String.format("%s_%s", outputWhiteSpacesMinuend(digitPosition), currentNumerals));
		currentStepDivisionString.put("subtrahend",
				String.format("%s%d", outputWhiteSpacesSubtrahend(digitPosition, subtrahend), subtrahend));
		currentStepDivisionString.put("underline",
				String.format("%s%s", outputWhiteSpacesSubtrahend(digitPosition, subtrahend), printCharacters(String.valueOf(subtrahend).length(), MINUS)));
		stepsDivision.add(currentStepDivisionString);
	}

	private String prepareRemainder(final int dividend) {
		String whitespacesRemainder = printCharacters(String.valueOf(dividend).length() - String.valueOf(currentRemainder).length(), WHITESPASE);
		return String.format("%s %d", whitespacesRemainder, currentRemainder);
	}
	
	private HashMap<String, Object> saveParametersColumnDivision(final String remainder) {
		HashMap<String, Object> parametersColunmDivision = new HashMap<>();
		parametersColunmDivision.put("stepsDivision", stepsDivision);
		parametersColunmDivision.put("remainder", remainder);
		return parametersColunmDivision;
	}

	private String outputWhiteSpacesMinuend(final int digitPosition) {
		int whiteSpacelength = digitPosition - String.valueOf(currentNumerals).length();
		return printCharacters(whiteSpacelength, WHITESPASE);
	}

	private String outputWhiteSpacesSubtrahend(final int digitPosition, final int subtrahend) {
		int whiteSpacelength = digitPosition - String.valueOf(subtrahend).length();
		return printCharacters(whiteSpacelength + 1, WHITESPASE);
	}
	
	private String outputLineBetweenDividerAndQuotient(final int divider) {
		if (String.valueOf(divider).length() > quotient.length()) {
			return printCharacters(String.valueOf(divider).length(), MINUS);
		}
		return printCharacters(quotient.length(), MINUS);
	}
	
	private void receiveCurrentNumerals(final char currentNumeral) {
		if (currentRemainder != 0) {
			currentNumerals.add(String.valueOf(currentRemainder) + String.valueOf(currentNumeral));
			currentRemainder = 0;
		} else {
			currentNumerals.add(String.valueOf(currentNumeral));
		}
	}

	private String printCharacters(final int lenghtLine, final String simbol) {
		StringJoiner outputSymbol = new StringJoiner("");
		for (int i = 0; i < lenghtLine; i++) {
			outputSymbol.add(simbol);
		}
		return outputSymbol.toString();
	}
}
