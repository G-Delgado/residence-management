package model;

public class Loader {
	private double degrees;
	
	public Loader (double d) {
		degrees = d;
	}
	
	public void load() {
		if (degrees == 360) {
			degrees = 0;
		} else {			
			degrees += 1;
		}
	}
	
	public double getDegrees() {
		return degrees;
	}
	
	public void setDegrees(double d ) {
		degrees = d;
	}
}
