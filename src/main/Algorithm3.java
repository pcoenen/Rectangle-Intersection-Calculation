package main;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Algorithm3 extends Algorithm {
	
	public Algorithm3(HashSet<Rectangle> rechthoeken) {
		super(rechthoeken);
	}

	public ArrayList<double[]> run(){
		ArrayList<double[]> intersections = new ArrayList<>();
		// Alles toevoegen aan priority queue
		PriorityQueue<StructureForPQ> queue = new PriorityQueue<>(new StructPQComparator());
		for(Rectangle rechthoek : getRechthoeken()){
			StructureForPQ struct = new StructureForPQ(rechthoek, 0, rechthoek.getLox());
			queue.add(struct);			
		}
		//Start algorimte
		RedBlackBST<Double, Rectangle> activeUp = new RedBlackBST<>();
		RedBlackBST<Double, Rectangle> activeDown = new RedBlackBST<>();
		while(!queue.isEmpty()){
			StructureForPQ struct = queue.poll();
			//Als de rechthoek zich nog niet actief is
			if(struct.getStatus() == 0){
				//Zet de eindwaarde van de rechthoek in de queue
				queue.add(new StructureForPQ(struct.getRechthoek(), 1, struct.getRechthoek().getRbx()));
				//Vergelijk alle rechthoeken die actief zijn
				intersections.addAll(check(struct.getRechthoek(), activeDown, activeUp));
				//zet hem in de actieve lijst
				activeUp.put(struct.getRechthoek().getLoy(), struct.getRechthoek());
				activeDown.put(struct.getRechthoek().getRby(), struct.getRechthoek());
			//Als de rechthoek wel al actief is
			} else if(struct.getStatus() == 1){
				//Haal rechthoek uit actieve lijst
				activeUp.delete(struct.getRechthoek().getLoy());
				activeDown.delete(struct.getRechthoek().getRby());
				intersections.addAll(check(struct.getRechthoek(), activeDown, activeUp));
			}
		}
		return intersections;
	}	
	
	ArrayList<double[]> check(Rectangle rect1, RedBlackBST<Double, Rectangle> activeDown, RedBlackBST<Double, Rectangle> activeUp){
		ArrayList<double[]> intersections = new ArrayList<>();
		double checkValue = rect1.getLoy();
		boolean hasIntersection = true;
		double key;
		Rectangle rect2 = null;
		ArrayList<double[]> result;
		while(hasIntersection && !activeDown.isEmpty()){
			try{
				key = activeDown.ceiling(checkValue);
			} catch(NullPointerException e){
				break;
			}
			if(rect2 != null){
				activeDown.put(rect2.getRby(), rect2);
			}
			rect2 = activeDown.get(key);
			result = rect1.getIntersectionPoints(rect2);
			if(result.size() > 0){
				intersections.addAll(result);
				activeDown.delete(key);
				checkValue = key;
			} else {
				hasIntersection = false;
			}
		}
		if(rect2 != null){
			activeDown.put(rect2.getRby(), rect2);
			rect2 = null;
		}
		//Voor bovenste
		
		checkValue = rect1.getLoy();
		hasIntersection = true;
		while(hasIntersection && !activeUp.isEmpty()){
			try{
				key = activeUp.ceiling(checkValue);
			} catch(NullPointerException e){
				break;
			}
			if(rect2 != null){
				activeUp.put(rect2.getLoy(), rect2);
			}
			rect2 = activeUp.get(key);
			result = rect1.getIntersectionPoints(rect2);
			if(result.size() > 0){
				intersections.addAll(result);
				activeUp.delete(key);
				checkValue = key;
			} else {
				hasIntersection = false;
			}
		}
		if(rect2 != null){
			activeUp.put(rect2.getLoy(), rect2);
		}		
		return intersections;
	}
}
