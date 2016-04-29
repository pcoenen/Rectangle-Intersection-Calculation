package main;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Algorithm2 extends Algorithm {
	
	Algorithm2(HashSet<Rectangle> rechthoeken) {
		super(rechthoeken);
	}

	ArrayList<double[]> run(){
		ArrayList<double[]> intersections = new ArrayList<>();
		// Alles toevoegen aan priority queue
		PriorityQueue<StructureForPQ> queue = new PriorityQueue<>(new structComparator());
		for(Rectangle rechthoek : getRechthoeken()){
			StructureForPQ struct = new StructureForPQ(rechthoek, 0, rechthoek.getLox());
			System.out.println("in queue = " + struct.getRechthoek());
			queue.add(struct);			
		}
		//Start algorimte
		HashSet<Rectangle> active = new HashSet<>();
		while(!queue.isEmpty()){
			StructureForPQ struct = queue.poll();
			System.out.println("Uit queue = " + struct.getRechthoek());
			System.out.println(struct.getxCoordinate());
			//Als de rechthoek zich nog niet actief is
			if(struct.getStatus() == 0){
				//zet hem in de actieve lijst
				active.add(struct.getRechthoek());
				//Zet de eindwaarde van de rechthoek in de queue
				queue.add(new StructureForPQ(struct.getRechthoek(), 1, struct.getRechthoek().getRbx()));
				//Vergelijk alle rechthoeken die actief zijn
				intersections.addAll(check(struct.getRechthoek(), active));
			//Als de rechthoek wel al actief is
			} else if(struct.getStatus() == 1){
				//Haal rechthoek uit actieve lijst
				System.out.println("removed = " + struct.getRechthoek());
				active.remove(struct.getRechthoek());
			}
		}
		return intersections;
	}	
	
	ArrayList<double[]> check(Rectangle rect1, HashSet<Rectangle> actieveRect){
		ArrayList<double[]> intersections = new ArrayList<>();
		HashSet<Rectangle> checkedRectangles = new HashSet<>();
		for(Rectangle rect2 : actieveRect){
			// TODO: controleer of de snijpunten tussen rect2 en rect1 al berekend zijn, anders hebben we alle snijpunten dubbel
			if(rect1 != rect2 && !checkedRectangles.contains(rect2)){
				ArrayList<double[]> result = rect1.getIntersectionPoints(rect2);
				intersections.addAll(result);
			}				
		}
		checkedRectangles.add(rect1);
		return intersections;
	}
}
