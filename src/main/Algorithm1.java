package main;
import java.util.ArrayList;
import java.util.HashSet;

public class Algorithm1 extends Algorithm {
	
	public Algorithm1(HashSet<Rectangle> rechthoeken) {
		super(rechthoeken);
	}

	public ArrayList<double[]> run(){
		ArrayList<double[]> intersections = new ArrayList<>();
		HashSet<Rectangle> checkedRectangles = new HashSet<>();
		for(Rectangle rect1 : getRechthoeken()){
			for(Rectangle rect2 : getRechthoeken()){
				// TODO: controleer of de snijpunten tussen rect2 en rect1 al berekend zijn, anders hebben we alle snijpunten dubbel
				if(rect1 != rect2 && !checkedRectangles.contains(rect2)){
					ArrayList<double[]> result = rect1.getIntersectionPoints(rect2);
					intersections.addAll(result);
				}
				aantalChecks++;
			}
			checkedRectangles.add(rect1);
		}
		System.out.println("Aantal checks = " + aantalChecks);
		return intersections;
	}
	long aantalChecks = 0;
}