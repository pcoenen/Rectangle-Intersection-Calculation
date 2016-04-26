import java.util.ArrayList;
import java.util.HashSet;

public class Algorithm1 extends Algorithm {
	
	Algorithm1(HashSet<Rectangle> rechthoeken) {
		super(rechthoeken);
	}

	void run(){
		ArrayList<double[]> intersections = new ArrayList<>();
		for(Rectangle rect1 : getRechthoeken()){
			for(Rectangle rect2 : getRechthoeken()){
				// TODO: controleer of de snijpunten tussen rect2 en rect1 al berekend zijn, anders hebben we alle snijpunten dubbel
				if(rect1 != rect2 && hasIntersection(rect1, rect2)){
					ArrayList<double[]> result = getIntersectionPoints(rect1, rect2);
					intersections.addAll(result);
				}				
			}
		}
		// TODO: only for debug purposes
		for (double[] intersection : intersections) {
			System.out.println(intersection[0] + " " + intersection[1]);
		}
	}
	
	private boolean hasIntersection(Rectangle rect1, Rectangle rect2) {
		/* TODO: test is lichtjes overbodig omdat er in getIntersectionPoints nog een extra check wordt gedaan 
		 * om te controleren of de rechthoeken snijpunten hebben
		 * blijkbaar vangen we hier nog niet alle gevallen zonder intersectie op
		 * */
		boolean intersection = true;
		if(rect2.getLox() > rect1.getRbx()) {
			intersection = false;
		} else if(rect2.getRby() < rect1.getLoy()) {
			intersection = false;
		} else if(rect1.getLox() > rect2.getRbx()) {
			intersection = false;
		} else if(rect1.getRby() < rect1.getLoy()) {
			intersection = false;
		} else if(rect1.getLox() < rect2.getLox() && rect1.getLoy() < rect2.getLoy()
					&& rect2.getRbx() < rect1.getRbx() && rect2.getRby() < rect1.getRby()) {
			//Rechthoeken liggen in elkaar
			intersection = false;
		} else if(rect2.getLox() < rect1.getLox() && rect2.getLoy() < rect1.getLoy()
				&& rect1.getRbx() < rect2.getRbx() && rect1.getRby() < rect2.getRby()) {
			//Rechthoeken liggen in elkaar
			intersection = false;
		}
		return intersection;
	}
	
	private ArrayList<double[]> getIntersectionPoints(Rectangle rect1, Rectangle rect2) {
		ArrayList<double[]> intersectionPoints = new ArrayList<>();
		
		// overlappende rechthoek
		double x1 = Math.max(rect1.getLox(), rect2.getLox());
		double y1 = Math.max(rect1.getLoy(), rect2.getLoy());
		double x2 = Math.min(rect1.getRbx(), rect2.getRbx());
		double y2 = Math.min(rect1.getRby(), rect2.getRby());
		
		if (x1 >= x2 || y1 >= y2) {
			return intersectionPoints;
		}
		
		// mogelijke snijpunten =  hoekpunten van overlappende rechthoek
		double[][] possibleIntersectionPoints = {{x1,y1}, {x2,y1}, {x1,y2}, {x2,y2}};
		for (double[] point : possibleIntersectionPoints) {
			if (point[0] == rect1.getLox() && point[1] != rect1.getLoy()) {
				// add point as intersection point
				intersectionPoints.add(point);
			} else if (point[0] == rect1.getRbx() && point[1] != rect1.getRby()) {
				// add point as intersection point
				intersectionPoints.add(point);
			} else if (point[1] == rect1.getLoy() && point[0] != rect1.getLox()) {
				// add point as intersection point
				intersectionPoints.add(point);
			} else if (point[1] == rect1.getRby() && point[0] != rect1.getRbx()) {
				// add point as intersection point
				intersectionPoints.add(point);
			}
		}
		
		return intersectionPoints;
	}
	
}
