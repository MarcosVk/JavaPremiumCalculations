package com.example.coverageLevel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class CooverageLevelMain {
	public static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		termfactorMain();
		realPropertyPolicyLimitMain();
		
	}
	public static void termfactorMain() {
		DateTimeFormatter dateFormat=DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate eff=null;
		while(eff==null) {
			System.out.print("Enter effective date: ");
			String enterEfffDate=sc.nextLine();
			
			try {
				eff=LocalDate.parse(enterEfffDate, dateFormat);
			}catch(Exception e) {
				System.out.println("Invalid date format. Please use MM-dd-yyyy (eg 03-31-2024)");
			}
		}
		LocalDate exp=null;
		while(exp==null) {
			System.out.print("Enter expiration date: ");
			String enterExpDate=sc.nextLine();
			try {
				exp=LocalDate.parse(enterExpDate, dateFormat);
			}catch(Exception e) {
				System.out.println("Invalid date format. Please use MM-dd-yyyy (eg 03-31-2024)");
			}
		}
		
		BigDecimal termFact=TermFactor.calculateTermFactor(eff,exp);
		
		System.out.println(termFact);
	}
	
	public static void realPropertyPolicyLimitMain() {
		
		double realPropertyPolicyLimit=RealPropertyPolicyLimit.calculateRealPropertyPolicyLimit(sc);
		
		System.out.printf(Locale.US,"Real Property Policy Limit: $%,.2f%n", realPropertyPolicyLimit);
	}
}
