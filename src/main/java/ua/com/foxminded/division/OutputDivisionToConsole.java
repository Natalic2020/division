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
	private List<String[]> intermediateResultList ;
	private String resultOfDivision  ;
	private String remainderOfdivision ;
	private int shiftFirstNumeral = 0;
	private StringJoiner outputDivisionJoiner = new StringJoiner(""); 
	
	
	public OutputDivisionToConsole(final String divider) {
		this.divider = divider;
	}

	public OutputDivisionToConsole(Map<String, Object> outputparametersColumnDivision) {
		dividend = outputparametersColumnDivision.get("dividend").toString();
		divider = outputparametersColumnDivision.get("divider").toString();
		intermediateResultList = (List) outputparametersColumnDivision.get("arrayOfValue");
		resultOfDivision = outputparametersColumnDivision.get("resultOfDivision").toString();
		remainderOfdivision = outputparametersColumnDivision.get("remainderOfdivision").toString();
		shiftFirstNumeral = intermediateResultList.get(0)[0].length() - intermediateResultList.get(0)[1].length();
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
		currentPositionNumber = currentPositionNumber + shiftFirstNumeral;
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
		outputDivisionJoiner.add(intermediateResultList.get(0)[1]);
		outputWhitespace(dividend.length() - intermediateResultList.get(0)[1].length()-shiftFirstNumeral);
		outputDivisionJoiner.add(VERTICAL_LINE);
		drawLine(divider.length() > resultOfDivision.length() ? divider.length() : resultOfDivision.length());
		outputDivisionJoiner.add("\n");
	}
	
	private void drawThirdLineHeader() {
		outputWhitespace(currentPositionNumber);
		drawLine(String.valueOf(intermediateResultList.get(0)[1]).length());
		outputWhitespace(dividend.length() - intermediateResultList.get(0)[1].length()-shiftFirstNumeral);
		outputDivisionJoiner.add(VERTICAL_LINE);
		outputDivisionJoiner.add(resultOfDivision);
		outputDivisionJoiner.add("\n");
	}

	private void drawBody() {
		for (int i = 1; i < intermediateResultList.size(); i++) {
			
			if (Integer.parseInt(intermediateResultList.get(i)[0]) == Integer.parseInt(intermediateResultList.get(i)[1]) ) {
				currentPositionNumber = currentPositionNumber + intermediateResultList.get(i)[1].length();
			}else if (intermediateResultList.get(i-1)[1].length()  == intermediateResultList.get(i)[0].length() ) {
				currentPositionNumber = currentPositionNumber + 1;
			}	
			outputWhitespace(currentPositionNumber - 1);
			outputDivisionJoiner.add(UNDERLINE);
			outputDivisionJoiner.add(intermediateResultList.get(i)[0]);
			outputDivisionJoiner.add("\n");
			int shiftNumeral = intermediateResultList.get(i)[0].length() - intermediateResultList.get(i)[1].length();
			currentPositionNumber = currentPositionNumber + shiftNumeral;
			outputWhitespace(currentPositionNumber);
			outputDivisionJoiner.add(intermediateResultList.get(i)[1]);
			outputDivisionJoiner.add("\n");
			outputWhitespace(currentPositionNumber);
			int lenghtSubtrahend = intermediateResultList.get(i)[1].length();
			drawLine(lenghtSubtrahend);
			outputDivisionJoiner.add("\n");
		}
	}

	private void drawRemainder() {
		int shiftRemainder = intermediateResultList.get(intermediateResultList.size()-1)[1].length() - remainderOfdivision.length();
		outputWhitespace(currentPositionNumber + shiftRemainder);
		outputDivisionJoiner.add(remainderOfdivision);
	}
	
	private void drawLine(int lenghtLine) {
		 printSomeSameCharacters( lenghtLine, MINUS);
	}

	private void outputWhitespace(int lenghtLine) {
		printSomeSameCharacters( lenghtLine, WHITESPASE);
	}

	private void printSomeSameCharacters(int lenghtLine, String simbol) {
		//StringJoiner textReverse = new StringJoiner("");
		for (int i = 0; i < lenghtLine; i++) {
			outputDivisionJoiner.add(simbol);
		}
		//return textReverse.toString();
	}	
}
