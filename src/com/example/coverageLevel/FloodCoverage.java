package com.example.coverageLevel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FloodCoverage {
	private static final Map<String, BigDecimal> stateFloodRate = new HashMap<String, BigDecimal>();
	private static final double territoryFactor = 1.000;
	private static final BigDecimal RMFPolicyLevel = BigDecimal.valueOf(0.940);
	static {
		stateFloodRate.put("NY", new BigDecimal(0.0297));
		stateFloodRate.put("AL", new BigDecimal(0.550));
		stateFloodRate.put("AR", new BigDecimal(0.440));
		stateFloodRate.put("AZ", new BigDecimal(0.193));
		stateFloodRate.put("CA", new BigDecimal(0.0774));
	}

	public static BigDecimal floodCoverage(Scanner sc, BigDecimal termFactor) {

		while (true) {
			System.out.println("Enter state: ");
			String state = sc.nextLine();
			String Formattedstate = state.toUpperCase().trim();
			if (stateFloodRate.containsKey(Formattedstate)) {
				while (true) {
					try {
						System.out.println("Enter flood limit: ");
						String floodLimitInput = sc.nextLine().trim();
						double floodLimit = Double.parseDouble(floodLimitInput);
						BigDecimal getStateRate = stateFloodRate.get(Formattedstate);

						BigDecimal floodPremium = getStateRate.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
								.multiply(BigDecimal.valueOf(territoryFactor)).multiply(BigDecimal.valueOf(floodLimit))
								.multiply(RMFPolicyLevel).multiply(termFactor).setScale(6, RoundingMode.HALF_UP);
						return floodPremium;

					} catch (Exception e) {
						System.out.println("Invalid format. Please enter value in numbers (eg 120000)");
					}
				}
			}
			System.out.println("Please enter states which are available. eg(NY,AL,AR,AZ,CA)");
		}
	}

}
