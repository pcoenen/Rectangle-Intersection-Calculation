package main;
import java.util.HashSet;

public class Algorithm1 extends Algorithm {
	
	public Algorithm1(HashSet<Rectangle> rechthoeken) {
		super(rechthoeken);
	}

	public HashSet<Coordinate> run(){
		HashSet<Coordinate> intersections = new HashSet<>();
		HashSet<Rectangle> checkedRectangles = new HashSet<>();
		for(Rectangle rect1 : getRechthoeken()){
			for(Rectangle rect2 : getRechthoeken()){
				if(rect1 != rect2 && !checkedRectangles.contains(rect2)){
					HashSet<Coordinate> result = rect1.getIntersectionPoints(rect2);
					intersections.addAll(result);
				}
			}
			checkedRectangles.add(rect1);
		}
		return intersections;
	}
}