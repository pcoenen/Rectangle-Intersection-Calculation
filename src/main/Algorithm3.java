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
		HashSet<Rectangle> active = new HashSet<>();
		while(!queue.isEmpty()){
			StructureForPQ struct = queue.poll();
			//Als de rechthoek zich nog niet actief is
			if(struct.getStatus() == 0){
				//Zet de eindwaarde van de rechthoek in de queue
				queue.add(new StructureForPQ(struct.getRechthoek(), 1, struct.getRechthoek().getRbx()));
				//Vergelijk alle rechthoeken die actief zijn
				intersections.addAll(check(struct.getRechthoek(), active));
				//zet hem in de actieve lijst
				active.add(struct.getRechthoek());
			//Als de rechthoek wel al actief is
			} else if(struct.getStatus() == 1){
				//Haal rechthoek uit actieve lijst
				active.remove(struct.getRechthoek());
			}
		}
		return intersections;
	}	
	
	ArrayList<double[]> check(Rectangle rect1, HashSet<Rectangle> actieveRect){
		ArrayList<double[]> intersections = new ArrayList<>();
		for(Rectangle rect2 : actieveRect){
			// TODO: controleer of de snijpunten tussen rect2 en rect1 al berekend zijn, anders hebben we alle snijpunten dubbel
			ArrayList<double[]> result = rect1.getIntersectionPoints(rect2);
			intersections.addAll(result);
		}
		return intersections;
	}
}
