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
		
		Map<String, Object> outputparametersColumnDivision = this.getValueforOutputColumnDivisionToConsole(dividend,
				divider);
		OutputDivisionToConsole outputDivisionToConsole = new OutputDivisionToConsole(outputparametersColumnDivision);
		outputDivisionToConsole.drawColumnDivisionToConsole();
	}

	private Map<String, Object> getValueforOutputColumnDivisionToConsole(final int dividend, final int divider) { 
		
		Map<String, Object> outputparametersColumnDivision = new HashMap<>();

		outputparametersColumnDivision.put("dividend", dividend);
		outputparametersColumnDivision.put("divider", divider);

		String[] dividendSplitNumber = String.valueOf(dividend).split(""); // разделение на цифры делимого
		List<String[]> arrayOfValue = new ArrayList<String[]>();
		String resultOfDivision = "";

		int remainderOfdivision = 0; // остаток от деления remainder of the division

		boolean isNeedNextNumeral = false;
		String currentNumerals = "";
		
		for (int i = 0; i < dividendSplitNumber.length; i++) {
			
			String currentNumeral = dividendSplitNumber[i]; // текущие цифры
			
			if (isNeedNextNumeral) {		
				currentNumerals = currentNumerals + currentNumeral;
			} else if (remainderOfdivision != 0){
				currentNumerals = String.valueOf(remainderOfdivision) + currentNumeral;
			}else {
				currentNumerals = currentNumeral;
			}
			
			Integer currentNumber = Integer.parseInt(currentNumerals); // текущие цифры
			
			System.out.println(i);
			System.out.println("currentNumeral                            " + currentNumber);
			if (currentNumber >= divider) {

				isNeedNextNumeral = false;
				
				int currentQuotient = currentNumber / divider;

				int multiplicationCurrentQuotientUndDivider = currentQuotient * divider;

				resultOfDivision = resultOfDivision + String.valueOf(currentQuotient);

				String[] currentValueForQuotientUndDivider = { currentNumerals, String.valueOf(multiplicationCurrentQuotientUndDivider) };

				System.out
						.println("multiplicationCurrentQuotientUndDivider  " + multiplicationCurrentQuotientUndDivider);
				arrayOfValue.add(currentValueForQuotientUndDivider);

				remainderOfdivision = currentNumber - multiplicationCurrentQuotientUndDivider;

				currentNumerals = "";
				
			} else {	
				isNeedNextNumeral = true;
				if (resultOfDivision !="" ) {
					resultOfDivision = resultOfDivision + 0;
				}
			}	
		}

		outputparametersColumnDivision.put("arrayOfValue", arrayOfValue);
		outputparametersColumnDivision.put("resultOfDivision", resultOfDivision);
		outputparametersColumnDivision.put("remainderOfdivision", remainderOfdivision);

		System.out.println(" remainderOfdivision " + remainderOfdivision);
		System.out.println(" resultOfDivision  " + resultOfDivision);
		return outputparametersColumnDivision;
	}

}

