package main;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import utilities.Line;
import utilities.LineCollection;
import utilities.Point;
import utilities.Structure;
import utilities.StructureComparator;
import utilities.Type;

public class Algorithm3 extends Algorithm {

	public Algorithm3(HashSet<Rectangle> rechthoeken) {
		super(rechthoeken);
	}
	
	ArrayList<double[]> intersections = new ArrayList<>();

	public ArrayList<double[]> run(){
		LineCollection lines =  createLinesOfRectangles(getRechthoeken());
		ArrayList<Structure> structures = createStructureOfLines(lines);
		ArrayList<Line> start = new ArrayList<>();
		ArrayList<Line> eind = new ArrayList<>();
		ArrayList<Line> verticaal = new ArrayList<>();
		recursive(structures, start, eind, verticaal);
		return this.intersections;
		
	}
	
	private void recursive(List<Structure> structures, ArrayList<Line> start, ArrayList<Line> eind, ArrayList<Line> verticaal){
		if(structures.size() == 1){
			for(Structure structure : structures){
				switch (structure.getType()) {
		        	case START_PUNT:
		        		start.add(structure.getLine());
		        		break;
		        	case EIND_PUNT:
		        		eind.add(structure.getLine());
		        		break;
		        	case VERTICAAL_LIJNSTUK:
		        		verticaal.add(structure.getLine());
		        		break;
				}
			}
		} else {
			// devide
			int pivot = structures.size()/2;
			List<Structure> structures1 = structures.subList(0, pivot);
			ArrayList<Line> start1 = new ArrayList<>();
			ArrayList<Line> eind1 = new ArrayList<>();
			ArrayList<Line> verticaal1 = new ArrayList<>();
			List<Structure> structures2 = structures.subList(pivot, structures.size());
			ArrayList<Line> start2 = new ArrayList<>();
			ArrayList<Line> eind2 = new ArrayList<>();
			ArrayList<Line> verticaal2 = new ArrayList<>();
			//conquer
			recursive(structures1, start1, eind1, verticaal1);
			recursive(structures2, start2, eind2, verticaal2);
			//merge
			double min1 = structures1.get(0).getXValue();
			double max2 = structures2.get(structures2.size()-1).getXValue();
			for(Line line : start1){
				if(line.getEndPoint().getX() > max2){
					for(Line vertical : verticaal2){
						double y = line.getStartPoint().getY();
						if(vertical.getStartPoint().getY() < y && vertical.getEndPoint().getY() > y){
							double[] intersection = {vertical.getStartPoint().getX(), y};
							this.intersections.add(intersection);
						}
					}
					start.add(line);
				}
			}
			for(Line line : eind2){
				if(line.getStartPoint().getX() < min1){
					for(Line vertical : verticaal1){
						double y = line.getStartPoint().getY();
						if(vertical.getStartPoint().getY() < y && vertical.getEndPoint().getY() > y){
							double[] intersection = {vertical.getStartPoint().getX(), y};
							this.intersections.add(intersection);
						}
					}
					eind.add(line);
				}
			}
			start.addAll(start2);
			eind.addAll(eind1);
			verticaal.addAll(verticaal1);
			verticaal.addAll(verticaal2);
		}	
	}
	private ArrayList<Structure> createStructureOfLines(LineCollection lines){
		ArrayList<Structure> structures = new ArrayList<>();
		for(Line line : lines.getVerticalLines()){
			structures.add(new Structure(line, Type.VERTICAAL_LIJNSTUK));
		}
		for(Line line : lines.getHorizontalLines()){
			structures.add(new Structure(line, Type.EIND_PUNT));
			structures.add(new Structure(line, Type.START_PUNT));
		}
		structures.sort(new StructureComparator());
		return structures;
	}
	
	private LineCollection createLinesOfRectangles(Collection<Rectangle> rectangles){
		ArrayList<Line> horizontal = new ArrayList<>();
		ArrayList<Line> vertical = new ArrayList<>();
		for(Rectangle rect : rectangles){
			Line line1 = new Line(rect.getLox(), rect.getLoy(), rect.getRbx(), rect.getLoy());
			horizontal.add(line1);
			Line line2 = new Line(rect.getLox(), rect.getRby(), rect.getRbx(), rect.getRby());
			horizontal.add(line2);
			Line line3 = new Line(rect.getLox(), rect.getLoy(), rect.getLox(), rect.getRby());
			vertical.add(line3);
			Line line4 = new Line(rect.getRbx(), rect.getLoy(), rect.getRbx(), rect.getRby());
			vertical.add(line4);			
		}
		return new LineCollection(horizontal, vertical);
		
	}
}
