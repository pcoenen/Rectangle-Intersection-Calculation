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
				// TODO: controleer of de snijpunten tussen rect2 en rect1 al berekend zijn, anders hebben we alle snijpunten dubbel
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