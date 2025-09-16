package calc;

public class Division {
	
	private double value = 1;
	
	public Division()  {
	}
	
	public Division(double value) {
		this.value = value;
	}
	
	public double getDivision(double a, double b) {
		this.value = a / b;
		return this.value;
	}
	
	public Double divide(double div) {
		if (div == 0) {
			return null;
		}
		this.value /= div;
		return this.value;
	}
}
