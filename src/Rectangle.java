
public class Rectangle {
	Rectangle(double lox, double loy, double rbx, double rby){
		setLox(lox);
		setLoy(loy);
		setRbx(rbx);
		setRby(rby);
	}
	
	public double getRby() {
		return rby;
	}
	public void setRby(double rby) {
		this.rby = rby;
	}

	public double getLox() {
		return lox;
	}

	public void setLox(double lox) {
		this.lox = lox;
	}

	public double getLoy() {
		return loy;
	}

	public void setLoy(double loy) {
		this.loy = loy;
	}

	public double getRbx() {
		return rbx;
	}

	public void setRbx(double rbx) {
		this.rbx = rbx;
	}

	private double lox;
	private double loy;
	private double rbx;
	private double rby;
	
}
