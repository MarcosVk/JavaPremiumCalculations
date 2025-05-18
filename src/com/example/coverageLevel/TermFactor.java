package com.example.coverageLevel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TermFactor {
	public static BigDecimal calculateTermFactor(LocalDate effDate,LocalDate expDate) {
		if(effDate==null||expDate==null) {
			throw new IllegalArgumentException("Date cannot be null");
		}
		
		long actualTermDays=ChronoUnit.DAYS.between(effDate, expDate);
		
		LocalDate effPlusOneYear=effDate.plusMonths(12);
		
		long fullTermDays=ChronoUnit.DAYS.between(effDate, effPlusOneYear);
		
		BigDecimal termFactor=BigDecimal.valueOf(actualTermDays).divide
				(BigDecimal.valueOf(fullTermDays),6,RoundingMode.HALF_UP)
				.setScale(3,RoundingMode.HALF_UP);
		
		return termFactor;
	}
}
