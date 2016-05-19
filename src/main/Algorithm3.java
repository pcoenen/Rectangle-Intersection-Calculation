package main;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;

public class Algorithm3 extends Algorithm {
	
	public Algorithm3(HashSet<Rectangle> rechthoeken) {
		super(rechthoeken);
	}

	public HashSet<Coordinate> run(){
		// HashMap<Integer, double[]> intersections = new HashMap<>();
		HashSet<Coordinate> intersections = new HashSet<>();
		// Alles toevoegen aan priority queue
		PriorityQueue<StructureForPQ> queue = new PriorityQueue<>(new StructPQComparator());
		for(Rectangle rechthoek : getRechthoeken()){
			StructureForPQ struct = new StructureForPQ(rechthoek, 0, rechthoek.getLo().getX());
			queue.add(struct);			
		}
		//Start algorimte
		TreeMap<Double, Rectangle> active = new TreeMap<Double, Rectangle>();
		while(!queue.isEmpty()){
			StructureForPQ struct = queue.poll();
			//Als de rechthoek zich nog niet actief is
			if(struct.getStatus() == 0){
				//Zet de eindwaarde van de rechthoek in de queue
				queue.add(new StructureForPQ(struct.getRechthoek(), 1, struct.getRechthoek().getRb().getX()));
				//Vergelijk alle rechthoeken die actief zijn
				intersections.addAll(check(struct.getRechthoek(), active));
				//zet hem in de actieve lijst
				active.put(struct.getRechthoek().getLo().getY(), struct.getRechthoek());
				active.put(struct.getRechthoek().getRb().getY(), struct.getRechthoek());
			//Als de rechthoek wel al actief is
			} else if(struct.getStatus() == 1){
				//Haal rechthoek uit actieve lijst
				active.remove(struct.getRechthoek().getLo().getY());
				active.remove(struct.getRechthoek().getRb().getY());
				intersections.addAll(check(struct.getRechthoek(), active));
			}
		}
		return intersections;
	}	
	
	HashSet<Coordinate> check(Rectangle rect1, TreeMap<Double, Rectangle> active){
		HashSet<Coordinate> intersections = new HashSet<>();
		double miny = rect1.getLo().getY();
		double maxy = rect1.getRb().getY();
		SortedMap<Double, Rectangle> possible_intersections = active.subMap(miny, maxy);
		for(Rectangle rect2 : possible_intersections.values()){
			intersections.addAll(rect1.getIntersectionPoints(rect2));
		}		
		return intersections;
	}
}
