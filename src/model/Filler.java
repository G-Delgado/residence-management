package model;

public class Filler {
	private double w;
	private double max;
	
	public Filler(double w, double m) {
		this.w = w;
		max = m;
	}
	
	public void fill() {
		if (w < max) {
			w += 0.14;
		}
	}
	
	public double getWidth() {
		return w;
	}
	
	public void setWidth(double w) {
		this.w = w;
	}
}
