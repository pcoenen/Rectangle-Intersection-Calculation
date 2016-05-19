package main;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Algorithm4 extends Algorithm {
	
	public Algorithm4(HashSet<Rectangle> rechthoeken) {
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
		TreeMap<Double, Rectangle> activeUp = new TreeMap<Double, Rectangle>();
		TreeMap<Double, Rectangle> activeDown = new TreeMap<Double, Rectangle>();
		while(!queue.isEmpty()){
			StructureForPQ struct = queue.poll();
			//Als de rechthoek zich nog niet actief is
			if(struct.getStatus() == 0){
				//Zet de eindwaarde van de rechthoek in de queue
				queue.add(new StructureForPQ(struct.getRechthoek(), 1, struct.getRechthoek().getRb().getX()));
				//Vergelijk alle rechthoeken die actief zijn
				for(Coordinate intersection : check(struct.getRechthoek(), activeDown, activeUp)){
					intersections.add(intersection);
				}
				//zet hem in de actieve lijst
				activeUp.put(struct.getRechthoek().getLo().getY(), struct.getRechthoek());
				activeDown.put(struct.getRechthoek().getRb().getY(), struct.getRechthoek());
			//Als de rechthoek wel al actief is
			} else if(struct.getStatus() == 1){
				//Haal rechthoek uit actieve lijst
				activeUp.remove(struct.getRechthoek().getLo().getY());
				activeDown.remove(struct.getRechthoek().getRb().getY());
				for(Coordinate intersection : check(struct.getRechthoek(), activeDown, activeUp)){
					intersections.add(intersection);
				}
			}
		}
		return intersections;
	}	
	
	HashSet<Coordinate> check(Rectangle rect1, TreeMap<Double, Rectangle> activeDown, TreeMap<Double, Rectangle> activeUp){
		HashSet<Coordinate> intersections = new HashSet<>();
		double checkValue = rect1.getLo().getY();
		double key;
		Rectangle rect2 = null;
		HashSet<Coordinate> result;
		while(!activeDown.isEmpty()){			
			try{
				key = activeDown.ceilingKey(checkValue);
			} catch(NullPointerException e){
				break;
			}
			if(rect2 != null){
				activeDown.put(rect2.getRb().getY(), rect2);
			}
			rect2 = activeDown.get(key);
			result = rect1.getIntersectionPoints(rect2);
			if(!result.isEmpty()){
				intersections.addAll(result);
				activeDown.remove(key);
				checkValue = key;
			} else {
				break;
			}
		}
		if(rect2 != null){
			activeDown.put(rect2.getRb().getY(), rect2);
			rect2 = null;
		}
		//Voor bovenste
		
		checkValue = rect1.getRb().getY();
		while(!activeUp.isEmpty()){
			try{
				key = activeUp.floorKey(checkValue);
			} catch(NullPointerException e){
				break;
			}
			if(rect2 != null){
				activeUp.put(rect2.getLo().getY(), rect2);
			}
			rect2 = activeUp.get(key);
			result = rect1.getIntersectionPoints(rect2);
			if(result.size() > 0){
				intersections.addAll(result);
				activeUp.remove(key);
				checkValue = key;
			} else {
				break;
			}
		}
		if(rect2 != null){
			activeUp.put(rect2.getLo().getY(), rect2);
		}		
		return intersections;
	}
}
