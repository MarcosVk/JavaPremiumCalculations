package com.example.coverageLevel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CooverageLevelMain {
	public static Scanner sc = new Scanner(System.in);
	private static BigDecimal termFactor;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		termFactor=termfactorMain();
		realPropertyPolicyLimitMain();
		floodCoverageMain();
	}

	public static BigDecimal termfactorMain() {
		LocalDate eff = null;
		while (eff == null) {
			System.out.print("Enter effective date: ");
			String enterEfffDate = sc.nextLine();
			try {
				eff=parseDate(enterEfffDate);
			} catch (Exception e) {
				System.out.println("Invalid date format. Please use MM-dd-yyyy (eg 03-31-2024)");
			}
		}
		LocalDate exp = null;
		while (exp == null) {
			System.out.print("Enter expiration date: ");
			String enterExpDate = sc.nextLine();
			try {
				LocalDate parsedExp = parseDate(enterExpDate);
				if (parsedExp.isBefore(eff)) {
					System.out.println("Expiration cannot be greater than effective date. Enter correct expiration date.");
				}else{
					exp=parsedExp;
				}
			} catch (Exception e) {
				System.out.println("Invalid date format. Please use MM-dd-yyyy (eg 03-31-2024)");
			}
		}

		BigDecimal termFact = TermFactor.calculateTermFactor(eff, exp);

		System.out.println(termFact);
		return termFact;
	}
	public static LocalDate parseDate(String input) {
		List<String> dateFormats=Arrays.asList("MM-dd-yyyy", "MM/dd/yyyy", "M/d/yyyy", "yyyy-MM-dd");
		
		for(String format:dateFormats) {
			try {
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(format);
				return LocalDate.parse(input, dateFormat);
			}catch(DateTimeParseException e) {
				
			}
		}
		return null;
	}

	public static void realPropertyPolicyLimitMain() {

		double realPropertyPolicyLimit = RealPropertyPolicyLimit.calculateRealPropertyPolicyLimit(sc);

		System.out.printf(Locale.US, "Real Property Policy Limit: $%,.2f%n", realPropertyPolicyLimit);
	}
	public static void floodCoverageMain() {
		String isIncluded=floodCoverageIsIncludedMain();
		calculateFloodPremium(isIncluded);
	}
	public static BigDecimal calculateFloodPremium(String isIncluded) {
		if(isIncluded.toLowerCase().equals("excluded")) {
			System.out.println("Flood coverage is excluded from this policy");
			 return BigDecimal.ZERO;
		}
		BigDecimal floodPremium =FloodCoverage.floodCoverage(sc,termFactor );
		System.out.println("The Flood coverage premium is: "+floodPremium);
		return floodPremium;
		 
	}
	public static String floodCoverageIsIncludedMain() {
		while(true) {
			System.out.println("Is Flood coverage included or excluded? ");
			String isIncluded=sc.nextLine();
			if(isIncluded.toLowerCase().equals("included")|| isIncluded.toLowerCase().equals("excluded"))
			{
				return isIncluded;
			}
			System.out.println("Invalid coverage status: " + isIncluded);
		}
	}
}
