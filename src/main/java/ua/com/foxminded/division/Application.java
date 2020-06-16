package ua.com.foxminded.division;

public class Application {

	public static void main(String[] args) {
		final int divindend = 78945;
		final int divinder = 4;
		Division division = new Division();
		final String resultDivision = division.columnDivision(divindend, divinder);
        System.out.println(resultDivision);	
	}
}
