package utilities;

public class Point {
	public Point(double x, double y){
		setX(x);
		setY(y);
	}
	public double getX() {
		return x;
	}
	private void setX(double x) {
		this.x = x;
	}
	
	private double x;
	
	public double getY() {
		return y;
	}
	private void setY(double y) {
		this.y = y;
	}
	private double y;
}
