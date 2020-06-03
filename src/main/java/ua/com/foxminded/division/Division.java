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

	public String divideColumn(final int dividend, final int divider) {

		String	outputResultDivision = "";
		if (divider == 0) {
            throw new IllegalArgumentException("Error! Сannot be divided by zero");
        }	
		if (dividend == 0) {	
			OutputDivisionToConsole outputDivisionToConsole = new OutputDivisionToConsole(String.valueOf(divider));	
			outputResultDivision = outputDivisionToConsole.drawColumnDivisionToConsoleWhenDividendZerro();
			 return outputResultDivision;
		}		
		Map<String, Object> outputParametersColumnDivision = this.getValueforOutputColumnDivisionToConsole(dividend,
				divider);
		OutputDivisionToConsole outputDivisionToConsole = new OutputDivisionToConsole(outputParametersColumnDivision);
		outputResultDivision = outputDivisionToConsole.drawColumnDivisionToConsole();
		
		return outputResultDivision;
	}

	private Map<String, Object> getValueforOutputColumnDivisionToConsole(final int dividend, final int divider) { 
		
		Map<String, Object> parametersColumnDivision = new HashMap<>();

		parametersColumnDivision.put("dividend", dividend);
		parametersColumnDivision.put("divider", divider);

		String[] dividendSplitNumber = String.valueOf(dividend).split(""); 
		List<String[]> intermediateResultDivisionList = new ArrayList<>();
		StringJoiner quotient = new StringJoiner("");
		int remainder = 0; 
		boolean isNeedNextNumeral = false;
		StringJoiner currentNumerals = new StringJoiner("");
		
		for (int i = 0; i < dividendSplitNumber.length; i++) {
			String currentNumeral = dividendSplitNumber[i]; 
			
			if (isNeedNextNumeral) {		
				currentNumerals.add(currentNumeral) ;
			} else if (remainder != 0){
				currentNumerals.add(String.valueOf(remainder) + currentNumeral);
			}else {
				currentNumerals.add(currentNumeral);
			}
			
			Integer currentNumber = Integer.parseInt(currentNumerals.toString()); 
			
			if (currentNumber >= divider) {
				isNeedNextNumeral = false;	
				int currentQuotient = currentNumber / divider;
				int multiplicationCurrentQuotientUndDivider = currentQuotient * divider;
				quotient.add(String.valueOf(currentQuotient));
				String[] currentValueForQuotientUndDivider = { currentNumerals.toString(), String.valueOf(multiplicationCurrentQuotientUndDivider) };
				intermediateResultDivisionList.add(currentValueForQuotientUndDivider);
				remainder = currentNumber - multiplicationCurrentQuotientUndDivider;
				currentNumerals = new StringJoiner("");	
			} else {	
				isNeedNextNumeral = true;
				if (!quotient.toString().isEmpty()){
					quotient.add("0");
				}
			}	
		}
		parametersColumnDivision.put("arrayOfValue", intermediateResultDivisionList);
		parametersColumnDivision.put("quotient", quotient);
		parametersColumnDivision.put("remainder", remainder);

		return parametersColumnDivision;
	}
}
