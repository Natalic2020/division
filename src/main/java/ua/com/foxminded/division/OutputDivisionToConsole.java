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
	private String dividend ;
	private String divider ;
	private List<String[]> intermediateResultDivisionList ;
	private String quotient  ;
	private String remainder ;
	private int shiftFirstDigit = 0;
	private StringJoiner outputDivisionJoiner = new StringJoiner(""); 
	
	
	public OutputDivisionToConsole(final String divider) {
		this.divider = divider;
	}

	public OutputDivisionToConsole(Map<String, Object> parametersColumnDivision) {
		dividend = parametersColumnDivision.get("dividend").toString();
		divider = parametersColumnDivision.get("divider").toString();
		intermediateResultDivisionList = (List) parametersColumnDivision.get("arrayOfValue");
		quotient = parametersColumnDivision.get("quotient").toString();
		remainder = parametersColumnDivision.get("remainder").toString();
		shiftFirstDigit = intermediateResultDivisionList.get(0)[0].length() - intermediateResultDivisionList.get(0)[1].length();
	}
	
	public String drawColumnDivisionToConsoleWhenDividendZerro() {
		outputWhitespace(currentPositionNumber - 1);
		outputDivisionJoiner.add(UNDERLINE);
		outputDivisionJoiner.add("0");
		outputDivisionJoiner.add(VERTICAL_LINE);
		outputDivisionJoiner.add(divider);
		outputDivisionJoiner.add("\n");
		
		outputWhitespace(currentPositionNumber);
		outputDivisionJoiner.add("0");
		outputDivisionJoiner.add(VERTICAL_LINE);
		drawLine(divider.length());
		outputDivisionJoiner.add("\n");
		
		outputWhitespace(currentPositionNumber + 1);
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
		outputWhitespace(currentPositionNumber - 1);
		outputDivisionJoiner.add(UNDERLINE);
		outputDivisionJoiner.add(dividend);
		outputDivisionJoiner.add(VERTICAL_LINE);
		outputDivisionJoiner.add(divider);
		outputDivisionJoiner.add("\n");
	}
	
	private void drawSecondLineHeader() {
		outputWhitespace(currentPositionNumber);
		outputDivisionJoiner.add(intermediateResultDivisionList.get(0)[1]);
		outputWhitespace(dividend.length() - intermediateResultDivisionList.get(0)[1].length()-shiftFirstDigit);
		outputDivisionJoiner.add(VERTICAL_LINE);
		drawLine(divider.length() > quotient.length() ? divider.length() : quotient.length());
		outputDivisionJoiner.add("\n");
	}
	
	private void drawThirdLineHeader() {
		outputWhitespace(currentPositionNumber);
		drawLine(String.valueOf(intermediateResultDivisionList.get(0)[1]).length());
		outputWhitespace(dividend.length() - intermediateResultDivisionList.get(0)[1].length()-shiftFirstDigit);
		outputDivisionJoiner.add(VERTICAL_LINE);
		outputDivisionJoiner.add(quotient);
		outputDivisionJoiner.add("\n");
	}

	private void drawBody() {
		for (int i = 1; i < intermediateResultDivisionList.size(); i++) {
	
			if (Integer.parseInt(intermediateResultDivisionList.get(i)[0]) == Integer.parseInt(intermediateResultDivisionList.get(i)[1]) ) {
				currentPositionNumber = currentPositionNumber + intermediateResultDivisionList.get(i)[1].length();
			}else if (intermediateResultDivisionList.get(i-1)[1].length()  == intermediateResultDivisionList.get(i)[0].length() ) {
				currentPositionNumber = currentPositionNumber + 1;
			}	
			outputWhitespace(currentPositionNumber - 1);
			outputDivisionJoiner.add(UNDERLINE);
			outputDivisionJoiner.add(intermediateResultDivisionList.get(i)[0]);
			outputDivisionJoiner.add("\n");
			int shiftNumeral = intermediateResultDivisionList.get(i)[0].length() - intermediateResultDivisionList.get(i)[1].length();
			currentPositionNumber = currentPositionNumber + shiftNumeral;
			outputWhitespace(currentPositionNumber);
			outputDivisionJoiner.add(intermediateResultDivisionList.get(i)[1]);
			outputDivisionJoiner.add("\n");
			outputWhitespace(currentPositionNumber);
			int lenghtSubtrahend = intermediateResultDivisionList.get(i)[1].length();
			drawLine(lenghtSubtrahend);
			outputDivisionJoiner.add("\n");
		}
	}

	private void drawRemainder() {
		int shiftRemainder = intermediateResultDivisionList.get(intermediateResultDivisionList.size()-1)[1].length() - remainder.length();
		outputWhitespace(currentPositionNumber + shiftRemainder);
		outputDivisionJoiner.add(remainder);
	}
	
	private void drawLine(int lenghtLine) {
		 printSomeSameCharacters( lenghtLine, MINUS);
	}

	private void outputWhitespace(int lenghtLine) {
		printSomeSameCharacters( lenghtLine, WHITESPASE);
	}

	private void printSomeSameCharacters(int lenghtLine, String simbol) {
		for (int i = 0; i < lenghtLine; i++) {
			outputDivisionJoiner.add(simbol);
		}
	}	
}
