package main;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Algorithm3 extends Algorithm {
	
	Algorithm3(HashSet<Rectangle> rechthoeken) {
		super(rechthoeken);
	}

	ArrayList<double[]> run(){
		ArrayList<double[]> intersections = new ArrayList<>();
		// Alles toevoegen aan priority queue
		PriorityQueue<StructureForPQ> queue = new PriorityQueue<>(new structComparator());
		for(Rectangle rechthoek : getRechthoeken()){
			StructureForPQ structPQ = new StructureForPQ(rechthoek, 0, rechthoek.getLox());
			queue.add(structPQ);			
		}
		//Start algorimte
		while(!queue.isEmpty()){
			StructureForPQ structPQ = queue.poll();
			//Als de rechthoek zich nog niet actief is
			if(structPQ.getStatus() == 0){
				//zet hem in de actieve lijst
				getActiveMin().put(structPQ.getRechthoek().getLoy(), structPQ.getRechthoek());
				getActiveMax().put(structPQ.getRechthoek().getRby(), structPQ.getRechthoek());
				//Zet de eindwaarde van de rechthoek in de queue
				queue.add(new StructureForPQ(structPQ.getRechthoek(), 1, structPQ.getRechthoek().getRbx()));
				//Vergelijk alle rechthoeken die actief zijn
				intersections.addAll(check(structPQ.getRechthoek()));
			//Als de rechthoek wel al actief is
			} else if(structPQ.getStatus() == 1){
				//Haal rechthoek uit actieve lijst
				getActiveMin().delete(structPQ.getRechthoek().getLoy());
				getActiveMin().delete(structPQ.getRechthoek().getRby());
			}
		}
		return intersections;
	}	
	
	ArrayList<double[]> check(Rectangle rect1){
		ArrayList<double[]> intersections = new ArrayList<>();
		Iterable<Double> possible_intersections = getActiveMin().keys(rect1.getLoy(), rect1.getRby());
		for(Double key : possible_intersections){
			// TODO: controleer of de snijpunten tussen rect2 en rect1 al berekend zijn, anders hebben we alle snijpunten dubbel
			Rectangle rect2 = getActiveMin().get(key);
			if(rect1 != rect2){
				ArrayList<double[]> result = rect1.getIntersectionPoints(rect2);
				intersections.addAll(result);
			}				
		}
		possible_intersections = getActiveMax().keys(rect1.getLoy(), rect1.getRby());
		for(Double key : possible_intersections){
			// TODO: controleer of de snijpunten tussen rect2 en rect1 al berekend zijn, anders hebben we alle snijpunten dubbel
			Rectangle rect2 = getActiveMax().get(key);
			if(rect1 != rect2){
				ArrayList<double[]> result = rect1.getIntersectionPoints(rect2);
				intersections.addAll(result);
			}				
		}
		return intersections;
	}
	
	BinarySearchTree<Double, Rectangle> activeMin = new BinarySearchTree<>();
	BinarySearchTree<Double, Rectangle> activeMax = new BinarySearchTree<>();
	
	private BinarySearchTree<Double, Rectangle> getActiveMin() {
		return activeMin;
	}

	private void setActiveMin(BinarySearchTree<Double, Rectangle> activeMin) {
		this.activeMin = activeMin;
	}

	private BinarySearchTree<Double, Rectangle> getActiveMax() {
		return activeMax;
	}

	private void setActiveMax(BinarySearchTree<Double, Rectangle> activeMax) {
		this.activeMax = activeMax;
	}
}
