package main;

import java.util.HashSet;

public class Rectangle {
	public Rectangle(Coordinate lo, Coordinate rb){
		this.lo = lo;
		this.rb = rb;
	}
	
	public HashSet<Coordinate> getIntersectionPoints(Rectangle rect2) {
		HashSet<Coordinate> intersectionPoints = new HashSet<>();
		
		// overlappende rechthoek
		double x1 = Math.max(this.getLo().getX(), rect2.getLo().getX());
		double y1 = Math.max(this.getLo().getY(), rect2.getLo().getY());
		double x2 = Math.min(this.getRb().getX(), rect2.getRb().getX());
		double y2 = Math.min(this.getRb().getY(), rect2.getRb().getY());
		
		// return lege set
		if (x1 > x2 || y1 > y2) return intersectionPoints;
		
		// mogelijke snijpunten = hoekpunten van overlappende rechthoek
		HashSet<Coordinate> possibleIntersectionPoints = new HashSet<>();
		possibleIntersectionPoints.add(new Coordinate(x1, y1));
		if(x1 != x2) possibleIntersectionPoints.add(new Coordinate(x2, y1));
		if(y1 != y2) possibleIntersectionPoints.add(new Coordinate(x1,y2));
		if(x1 != x2 && y1 != y2) possibleIntersectionPoints.add(new Coordinate(x2,y2));
		
		for (Coordinate point : possibleIntersectionPoints) {
			if((point.getX() == this.getLo().getX() || point.getX() == this.getRb().getX()) 
					&& (point.getY() == rect2.getLo().getY() || point.getY() == rect2.getRb().getY())){
				// add point as intersection point
				intersectionPoints.add(point);
			} else if((point.getX() == rect2.getLo().getX() || point.getX() == rect2.getRb().getX()) 
					&& (point.getY() == this.getLo().getY() || point.getY() == this.getRb().getY())){
				// add point as intersection point
				intersectionPoints.add(point);
			}
		}
		return intersectionPoints;
	}
	
	public Coordinate getLo() {
		return this.lo;
	}
	
	public Coordinate getRb() {
		return this.rb;
	}
	
	private final Coordinate lo;
	private final Coordinate rb;
	
}
