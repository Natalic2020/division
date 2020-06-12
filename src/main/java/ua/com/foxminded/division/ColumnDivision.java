package ua.com.foxminded.division;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class ColumnDivision {

	private static final String MINUS = "-";
	private static final String WHITESPASE = " ";

	List<HashMap<String, String>> stepsDivision = new ArrayList<>();

	//StringJoiner quotient = new StringJoiner("");
	//StringJoiner currentNumerals = new StringJoiner("");

	// int currentRemainder = 0;

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
		StringJoiner quotient = new StringJoiner("");
//		boolean isFirstStep = true;
		StringJoiner currentNumerals = new StringJoiner("");
		String currentRemainder = "";
		
		for (int i = 0; i < dividendSplitNumber.length; i++) {

			char currentNumeral = dividendSplitNumber[i];
			currentNumerals.add(currentRemainder + currentNumeral);
			currentRemainder = "";
			Integer minuend = Integer.parseInt(currentNumerals.toString());
//			HashMap<String, String> headParametres = new HashMap<>();
			if (minuend >= divider) {
				int digitPosition = i + 1;
				int currentQuotient = minuend / divider;
				int subtrahend = currentQuotient * divider;
			
				 prepareStepDivision( digitPosition, minuend, dividend, divider, currentNumerals);
				 quotient.add(String.valueOf(currentQuotient));
				 currentRemainder = receiveCurrentRemainer(minuend, subtrahend);
				 currentNumerals = new StringJoiner("");
				 
			} else {
				if (!quotient.toString().isEmpty()) {
					quotient.add("0");
				}
			}
		}
		HashMap<String, String> headParametres = new HashMap<>();  // remove
		prepareHead(dividend, divider, headParametres,  quotient);
		
		return saveParametersColumnDivision(prepareRemainder(dividend, currentRemainder));
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

	private void prepareStepDivision( int digitPosition, Integer minuend, final int dividend, final int divider, StringJoiner currentNumerals) {
		int currentQuotient = minuend / divider;
    	int subtrahend = currentQuotient * divider;
	
		prepareBody(subtrahend, digitPosition, currentNumerals);
		
//		quotient.add(String.valueOf(currentQuotient));
//		currentRemainder = minuend - subtrahend;
//		currentNumerals = new StringJoiner("");
		
	}

//	private 
//	HashMap<String, String> prepareHeadParametres(final int subtrahend, final int digitPosition, final int dividend) {
//		String  whiteSpacesToVerticalLine = printCharacters(String.valueOf(dividend).length() - digitPosition, WHITESPASE);
//		HashMap<String, String> headParametres = new HashMap<>();
//		String subtrahendHeard = String.format("%s%d%s|", outputWhiteSpacesSubtrahend(digitPosition, subtrahend), subtrahend, whiteSpacesToVerticalLine);
//		String underlineHeard = String.format("%s%s%s|", outputWhiteSpacesSubtrahend(digitPosition, subtrahend), printCharacters(String.valueOf(subtrahend).length(), MINUS), whiteSpacesToVerticalLine);
//		headParametres.put("subtrahendHeard", subtrahendHeard);
//		headParametres.put("underlineHeard", underlineHeard);
//		return headParametres;
//	}
	
	private void prepareHead(final int dividend, final int divider, HashMap<String, String> headParametres, StringJoiner quotient) {
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
	
	private void prepareBody(final int subtrahend, final int digitPosition, StringJoiner currentNumerals) {
		HashMap<String, String> currentStepDivisionString = new HashMap<>();
		currentStepDivisionString.put("minuend",
				String.format("%s_%s", outputWhiteSpacesMinuend(digitPosition, currentNumerals), currentNumerals));
		currentStepDivisionString.put("subtrahend",
				String.format("%s%d", outputWhiteSpacesSubtrahend(digitPosition, subtrahend), subtrahend));
		currentStepDivisionString.put("underline",
				String.format("%s%s", outputWhiteSpacesSubtrahend(digitPosition, subtrahend), printCharacters(String.valueOf(subtrahend).length(), MINUS)));
		stepsDivision.add(currentStepDivisionString);
	}

	private String prepareRemainder(final int dividend, final String currentRemainder) {
		String remainder = (currentRemainder.isEmpty() ? "0" : currentRemainder);
		String whitespacesRemainder = printCharacters(String.valueOf(dividend).length() - remainder.length(), WHITESPASE);
		return String.format("%s %s", whitespacesRemainder, remainder);
	}
	
	private HashMap<String, Object> saveParametersColumnDivision(final String remainder) {
		HashMap<String, Object> parametersColunmDivision = new HashMap<>();
		parametersColunmDivision.put("stepsDivision", stepsDivision);
		parametersColunmDivision.put("remainder", remainder);
		return parametersColunmDivision;
	}

	private String outputWhiteSpacesMinuend(final int digitPosition, StringJoiner currentNumerals) {
		int whiteSpacelength = digitPosition - String.valueOf(currentNumerals).length();
		return printCharacters(whiteSpacelength, WHITESPASE);
	}

	private String outputWhiteSpacesSubtrahend(final int digitPosition, final int subtrahend) {
		int whiteSpacelength = digitPosition - String.valueOf(subtrahend).length();
		return printCharacters(whiteSpacelength + 1, WHITESPASE);
	}
	
	private String outputLineBetweenDividerAndQuotient(final int divider, StringJoiner quotient) {
		if (String.valueOf(divider).length() > quotient.length()) {
			return printCharacters(String.valueOf(divider).length(), MINUS);
		}
		return printCharacters(quotient.length(), MINUS);
	}
	
//	private String receiveCurrentNumerals(final char currentNumeral, int currentRemainder ) {
//		if (currentRemainder != 0) {
//			//currentRemainder = 0;
//			return String.valueOf(currentRemainder) + String.valueOf(currentNumeral);
//		} else {
//			return String.valueOf(currentNumeral);
//		}
//	}

	private String printCharacters(final int lenghtLine, final String simbol) {
		StringJoiner outputSymbol = new StringJoiner("");
		for (int i = 0; i < lenghtLine; i++) {
			outputSymbol.add(simbol);
		}
		return outputSymbol.toString();
	}
}
