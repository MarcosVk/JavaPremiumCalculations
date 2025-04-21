package com.example.coverageLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RealPropertyPolicyLimit {
	private boolean isAdded;
	private double insuredValue;
	
	public RealPropertyPolicyLimit(boolean isAdded,double insuredValue) {
		this.isAdded=isAdded;
		this.insuredValue=insuredValue;
	}
	
	public boolean getIsAdded() {
		return isAdded;
	}
	
	public double getInsuredValue() {
		return insuredValue;
	}
	
	public static double calculateRealPropertyPolicyLimit(Scanner sc) {
		System.out.print("Is Real Property (Coverage A) included? (yes/no): ");
		boolean isCoverageIncluded=sc.nextLine().equalsIgnoreCase("yes");
		
		if(!isCoverageIncluded) {
			System.out.println("Real Property Policy Limit: $0.00 (Coverage excluded)");
			return 0.0;
		}
		
		List<RealPropertyPolicyLimit> locations=inputLocations(sc);
		
		return totalValue(locations);
		
	}
	public static List<RealPropertyPolicyLimit> inputLocations(Scanner sc) {
		List<RealPropertyPolicyLimit> locations=new ArrayList<>();
		System.out.println("Enter location details (type 'done' to finish): ");
		while(true) {
			System.out.println("Location "+(locations.size()+1)+": ");
			
			System.out.println("Is this location added? (yes/no): ");
			String input=sc.nextLine();
			
			if(input.equalsIgnoreCase("done")) {
				break;
			}
			else if(input.equalsIgnoreCase("no")) {
				System.out.println("Location not added - skipped.");
	            continue;
			}else if(!input.equalsIgnoreCase("yes")) {
	            System.out.println("Invalid input! Please enter yes/no/done");
	            continue;
	        }
			boolean isAdded=input.equalsIgnoreCase("yes");
			
			System.out.println("Enter real property insured value $: ");
			double insuredValue=Double.parseDouble(sc.nextLine());
			
			locations.add(new RealPropertyPolicyLimit(isAdded,insuredValue));
		}
		return locations;
	}
	
	public static double totalValue(List<RealPropertyPolicyLimit> locations) {
		double totalValue=locations.stream().filter(RealPropertyPolicyLimit::getIsAdded)
				.mapToDouble(RealPropertyPolicyLimit::getInsuredValue).sum();
		return totalValue;
	}

}
