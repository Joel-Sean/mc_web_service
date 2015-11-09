package mc.webservice.utils;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author david
 * 
 * Note: for providing setters to myBatis, this class is NOT immutable (although it looks like).
 *
 */
public class Percentage {
	private static final int SCALE = 4;
	
	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	private BigDecimal numerator;
	private BigDecimal denominator;
	private int scale = 4;						
	
	public Percentage() {}
	
	public Percentage(BigDecimal numerator, BigDecimal denominator, int scale) {
		this.numerator = numerator;
		this.denominator = denominator;
		this.scale = scale;
	}
	
	public BigDecimal getNumerator() {
		return numerator;
	}
	public void setNumerator(BigDecimal numerator) {
		this.numerator = numerator;
	}
	public BigDecimal getDenominator() {
		return denominator;
	}
	public void setDenominator(BigDecimal denominator) {
		this.denominator = denominator;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale >= 0 ? scale : 0;
	}
	
	@JsonIgnore
	public BigDecimal getDecimal() {
		return getDecimal(BigDecimal.ROUND_CEILING);
	}
	public BigDecimal getDecimal(int rounding) {
		if (numerator == null || denominator == null) {
			return null;
		}
		if (denominator.compareTo(BigDecimal.ZERO) == 0) {
			return null;
		}
		return (numerator.divide(denominator, scale, rounding));	
	}	
	
	@JsonIgnore
	public BigDecimal getPercentage() {
		if (numerator == null || denominator == null) {
			return null;
		}
		if (denominator.compareTo(BigDecimal.ZERO) == 0) {
			return null;
		}
		return (numerator.divide(denominator, scale, BigDecimal.ROUND_CEILING)).multiply(ONE_HUNDRED);	
	}		
	
	public static Percentage zero() {
		Percentage p = new Percentage();
		p.setNumerator(BigDecimal.ZERO);
		p.setDenominator(ONE_HUNDRED);
		p.setScale(SCALE);
		return p;
	}
	
	public static Percentage decimalValue(BigDecimal numerator) {
		if (numerator == null || numerator.compareTo(ONE_HUNDRED) > 0) {
			throw new IllegalArgumentException("Invalid numerator: " + numerator);
		}
		Percentage p = new Percentage();
		p.setNumerator(numerator);
		p.setDenominator(ONE_HUNDRED);
		p.setScale(SCALE);
		return p;
	}	
	
	public static Percentage zeroValue() {
		Percentage p = new Percentage();
		p.setNumerator(BigDecimal.ZERO);
		p.setDenominator(BigDecimal.ONE);
		p.setScale(SCALE);
		return p;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Percentage [numerator=");
		builder.append(numerator);
		builder.append(", denominator=");
		builder.append(denominator);
		builder.append(", scale=");
		builder.append(scale);
		builder.append("]");
		return builder.toString();
	}	

}

