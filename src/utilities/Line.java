package utilities;

public class Line {
	public Line(Point startPoint, Point endPoint){
		setStartPoint(startPoint);
		setEndPoint(endPoint);
	}
	
	public Line(double startX, double startY, double endX, double endY){
		setStartPoint(new Point(startX, startY));
		setEndPoint(new Point(endX, endY));
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	private void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	
	private Point startPoint;
	
	public Point getEndPoint() {
		return endPoint;
	}
	private void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	private Point endPoint;
}
