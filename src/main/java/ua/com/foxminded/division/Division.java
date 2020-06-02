package ua.com.foxminded.division;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Division {

	public void columnDivision(final int dividend, final int divider) {
		divideColumn(Math.abs(dividend), Math.abs(divider));
	}

	public void divideColumn(final int dividend, final int divider) {

		if (divider == 0) {
            throw new IllegalArgumentException("Error! Сannot be divided by zero");
        }	
		if (dividend == 0) {
			
			OutputDivisionToConsole outputDivisionToConsole = new OutputDivisionToConsole(String.valueOf(divider));	
			outputDivisionToConsole.drawColumnDivisionToConsoleWhenDividendZerro();
			return;
		}		
		Map<String, Object> outputparametersColumnDivision = this.getValueforOutputColumnDivisionToConsole(dividend,
				divider);
		OutputDivisionToConsole outputDivisionToConsole = new OutputDivisionToConsole(outputparametersColumnDivision);
		outputDivisionToConsole.drawColumnDivisionToConsole();
	}

	private Map<String, Object> getValueforOutputColumnDivisionToConsole(final int dividend, final int divider) { 
		
		Map<String, Object> outputparametersColumnDivision = new HashMap<>();

		outputparametersColumnDivision.put("dividend", dividend);
		outputparametersColumnDivision.put("divider", divider);

		String[] dividendSplitNumber = String.valueOf(dividend).split(""); 
		List<String[]> intermediateResultList = new ArrayList<String[]>();
		StringJoiner resultOfDivision = new StringJoiner("");
		int remainderOfdivision = 0; // остаток от деления remainder of the division
		boolean isNeedNextNumeral = false;
		StringJoiner currentNumerals = new StringJoiner("");
		
		for (int i = 0; i < dividendSplitNumber.length; i++) {
			String currentNumeral = dividendSplitNumber[i]; 
			
			if (isNeedNextNumeral) {		
				currentNumerals.add(currentNumeral) ;
			} else if (remainderOfdivision != 0){
				currentNumerals.add(String.valueOf(remainderOfdivision) + currentNumeral);
			}else {
				currentNumerals.add(currentNumeral);
			}
			
			Integer currentNumber = Integer.parseInt(currentNumerals.toString()); 
			
			if (currentNumber >= divider) {
				isNeedNextNumeral = false;	
				int currentQuotient = currentNumber / divider;
				int multiplicationCurrentQuotientUndDivider = currentQuotient * divider;
				resultOfDivision.add(String.valueOf(currentQuotient));
				String[] currentValueForQuotientUndDivider = { currentNumerals.toString(), String.valueOf(multiplicationCurrentQuotientUndDivider) };
				intermediateResultList.add(currentValueForQuotientUndDivider);
				remainderOfdivision = currentNumber - multiplicationCurrentQuotientUndDivider;
				currentNumerals = new StringJoiner("");	
			} else {	
				isNeedNextNumeral = true;
				if (!resultOfDivision.toString().isEmpty()){
					resultOfDivision.add("0");
				}
			}	
		}
		outputparametersColumnDivision.put("arrayOfValue", intermediateResultList);
		outputparametersColumnDivision.put("resultOfDivision", resultOfDivision);
		outputparametersColumnDivision.put("remainderOfdivision", remainderOfdivision);

		return outputparametersColumnDivision;
	}
}

