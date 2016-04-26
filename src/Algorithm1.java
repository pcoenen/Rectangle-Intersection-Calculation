import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Algorithm1 {
	Algorithm1(HashSet<Rectangle> rechthoeken) {
		setRechthoeken(rechthoeken);
	}
	
	void run(){
		ArrayList<Double[]> intersections = null;
		for(Rectangle rect1 : getRechthoeken()){
			for(Rectangle rect2 : getRechthoeken()){
				if(rect1 != rect2 && hasIntersection(rect1, rect2)){
					ArrayList<Double[]> result = getIntersectionPoints(rect1, rect2);
					intersections.addAll(result);
				}				
			}
		}
	}
	
	private boolean hasIntersection(Rectangle rect1, Rectangle rect2){
		boolean intersection = true;
		if(rect2.getLox() > rect1.getRbx()){
			intersection = false;
		} else if(rect2.getRby() < rect1.getLoy()){
			intersection = false;
		} else if(rect1.getLox() > rect2.getRbx()){
			intersection = false;
		} else if(rect1.getRby() < rect1.getLoy()){
			intersection = false;
		} else if(rect1.getLox() < rect2.getLox() && rect1.getLoy() < rect2.getLoy()
					&& rect2.getRbx() < rect1.getRbx() && rect2.getRby() < rect1.getRby()){
			//Rechthoeken liggen in elkaar
			intersection = false;
		} else if(rect2.getLox() < rect1.getLox() && rect2.getLoy() < rect1.getLoy()
				&& rect1.getRbx() < rect2.getRbx() && rect1.getRby() < rect2.getRby()){
			//Rechthoeken liggen in elkaar
			intersection = false;
		}
		return intersection;
	}
	
	private ArrayList<Double[]> getIntersectionPoints(Rectangle rect1, Rectangle rect2){
		

		return null;
	}
	
	private HashSet<Rectangle> getRechthoeken() {
		return rechthoeken;
	}

	private void setRechthoeken(HashSet<Rectangle> rechthoeken) {
		this.rechthoeken = rechthoeken;
	}

	private HashSet<Rectangle> rechthoeken;
	
}