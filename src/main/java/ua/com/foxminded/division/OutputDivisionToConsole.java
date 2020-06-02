package ua.com.foxminded.division;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputDivisionToConsole {

	private static final String MINUS = "-";
	private static final String UNDERLINE = "_";
	private static final String VERTICAL_LINE = "|";
	private static final String WHITESPASE = " ";

	private int currentPositionNumber;
	private final String dividend ;
	private final String divider ;
	private final List<String[]> arrayOfValue ;
	private final String resultOfDivision  ;
	private final String remainderOfdivision ;
	private final int shiftFirstNumeral;
	
	
	public OutputDivisionToConsole(Map<String, Object> outputparametersColumnDivision) {
		currentPositionNumber = 3;
		dividend = outputparametersColumnDivision.get("dividend").toString();
		divider = outputparametersColumnDivision.get("divider").toString();
		arrayOfValue = (List) outputparametersColumnDivision.get("arrayOfValue");
		resultOfDivision = outputparametersColumnDivision.get("resultOfDivision").toString();
		remainderOfdivision = outputparametersColumnDivision.get("remainderOfdivision").toString();
		shiftFirstNumeral = arrayOfValue.get(0)[0].length() - arrayOfValue.get(0)[1].length();
	}

	public void drawColumnDivisionToConsole() {

		drawHeader();
		drawBody();
		drawRemainder();
	}

	private void drawHeader() {
		
		drawFirstLineHeader();
		currentPositionNumber = currentPositionNumber + shiftFirstNumeral;
		drawSecondLineHeader();
		drawThirdLineHeader();
	}

	private void drawFirstLineHeader() {
		outputWhitespace(currentPositionNumber - 1);
		System.out.print(UNDERLINE);
		System.out.print(dividend);
		System.out.print(VERTICAL_LINE);
		System.out.println(divider);
	}
	
	private void drawSecondLineHeader() {
		outputWhitespace(currentPositionNumber);
		System.out.print(arrayOfValue.get(0)[1]);
		outputWhitespace(dividend.length() - arrayOfValue.get(0)[1].length()-shiftFirstNumeral);
		System.out.print(VERTICAL_LINE);
		drawLine(divider.length() > resultOfDivision.length() ? divider.length() : resultOfDivision.length());
		System.out.println("");
	}
	
	private void drawThirdLineHeader() {
		outputWhitespace(currentPositionNumber);
		drawLine(String.valueOf(arrayOfValue.get(0)[1]).length());
		outputWhitespace(dividend.length() - arrayOfValue.get(0)[1].length()-shiftFirstNumeral);
		System.out.print(VERTICAL_LINE);
		System.out.println(resultOfDivision);
	}

	private void drawBody() {
		for (int i = 1; i < arrayOfValue.size(); i++) {
			
			if (Integer.parseInt(arrayOfValue.get(i)[0]) == Integer.parseInt(arrayOfValue.get(i)[1]) ) {
				currentPositionNumber = currentPositionNumber + arrayOfValue.get(i)[1].length();
			}else if (arrayOfValue.get(i-1)[1].length()  == arrayOfValue.get(i)[0].length() ) {
				currentPositionNumber = currentPositionNumber + 1;
			}	
			outputWhitespace(currentPositionNumber - 1);
			System.out.print(UNDERLINE);
			System.out.println(arrayOfValue.get(i)[0]);
			int shiftNumeral = arrayOfValue.get(i)[0].length() - arrayOfValue.get(i)[1].length();
			currentPositionNumber = currentPositionNumber + shiftNumeral;
			outputWhitespace(currentPositionNumber);
			System.out.println(arrayOfValue.get(i)[1]);
			outputWhitespace(currentPositionNumber);
			int lenghtSubtrahend = arrayOfValue.get(i)[1].length();
			drawLine(lenghtSubtrahend);
			System.out.println("");
		}
	}

	private void drawRemainder() {
		int shiftRemainder = arrayOfValue.get(arrayOfValue.size()-1)[1].length() - remainderOfdivision.length();
		outputWhitespace(currentPositionNumber + shiftRemainder);
		System.out.println(remainderOfdivision);
	}
	
	private void drawLine(int lenghtLine) {
		printSomeSameCharacters( lenghtLine, MINUS);
	}

	private void outputWhitespace(int lenghtLine) {
		printSomeSameCharacters( lenghtLine, WHITESPASE);
	}

	private void printSomeSameCharacters(int lenghtLine, String simbol) {
		StringJoiner textReverse = new StringJoiner("");
		for (int i = 0; i < lenghtLine; i++) {
			textReverse.add(simbol);
		}
		System.out.print(textReverse.toString());
	}	
}
