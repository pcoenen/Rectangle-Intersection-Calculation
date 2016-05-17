package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Rectangle {
//	public Rectangle(double lox, double loy, double rbx, double rby){
//		setLox(lox);
//		setLoy(loy);
//		setRbx(rbx);
//		setRby(rby);
//	}
	
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
	
	/*
	public ArrayList<double[]> getIntersectionPoints2(Rectangle rect2) {
			ArrayList<double[]> intersectionPoints = new ArrayList<>();
			
			// overlappende rechthoek
			double x1 = Math.max(this.getLox(), rect2.getLox());
			double y1 = Math.max(this.getLoy(), rect2.getLoy());
			double x2 = Math.min(this.getRbx(), rect2.getRbx());
			double y2 = Math.min(this.getRby(), rect2.getRby());
			
			if (x1 > x2 || y1 > y2) {
				return intersectionPoints;
			}
			
			// mogelijke snijpunten =  hoekpunten van overlappende rechthoek
			HashSet<double[]> possibleIntersectionPoints = new HashSet<>();
			possibleIntersectionPoints.add(new double[] {x1,y1});
			if(x1 != x2){
				possibleIntersectionPoints.add(new double[] {x2,y1});
			}
			if(y1 != y2){
				possibleIntersectionPoints.add(new double[] {x1,y2});
			}
			if(x1 != x2 && y1 != y2){
				possibleIntersectionPoints.add(new double[] {x2,y2});
			}			
			for (double[] point : possibleIntersectionPoints) {
				if((point[0] == this.getLox() || point[0] == this.getRbx()) 
						&& (point[1] == rect2.getLoy() || point[1] == rect2.getRby())){
					// add point as intersection point
					intersectionPoints.add(point);
				} else if((point[0] == rect2.getLox() || point[0] == rect2.getRbx()) 
						&& (point[1] == this.getLoy() || point[1] == this.getRby())){
					// add point as intersection point
					intersectionPoints.add(point);
				}
			}			
			return intersectionPoints;
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
	*/
	
	public Coordinate getLo() {
		return this.lo;
	}
	
	public Coordinate getRb() {
		return this.rb;
	}
	
	private final Coordinate lo;
	private final Coordinate rb;
	
}
