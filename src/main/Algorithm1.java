package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Algorithm1 extends Algorithm {
	
	Algorithm1(HashSet<Rectangle> rechthoeken) {
		super(rechthoeken);
	}

	ArrayList<double[]> run(){
		ArrayList<double[]> intersections = new ArrayList<>();
		HashSet<Rectangle> checkedRectangles = new HashSet<>();
		for(Rectangle rect1 : getRechthoeken()){
			for(Rectangle rect2 : getRechthoeken()){
				// TODO: controleer of de snijpunten tussen rect2 en rect1 al berekend zijn, anders hebben we alle snijpunten dubbel
				if(rect1 != rect2 && !checkedRectangles.contains(rect2) && hasIntersection(rect1, rect2)){
					ArrayList<double[]> result = rect1.getIntersectionPoints(rect2);
					intersections.addAll(result);
				}				
			}
			checkedRectangles.add(rect1);
		}
		// TODO: only for debug purposes
		for (double[] intersection : intersections) {
			System.out.println(intersection[0] + " " + intersection[1]);
		}
		return intersections;
	}	
}
