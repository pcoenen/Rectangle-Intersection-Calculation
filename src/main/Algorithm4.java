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

public class Algorithm4 extends Algorithm {

	public Algorithm4(HashSet<Rectangle> rechthoeken) {
		super(rechthoeken);
	}
	
	ArrayList<double[]> intersections = new ArrayList<>();

	public ArrayList<double[]> run(){
		LineCollection lines =  createLinesOfRectangles(getRechthoeken());
		ArrayList<Structure> structures = createS(lines);
		recursive(structures, 0, structures.size()-1);
		
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
			List<Structure> structures1 = structures.subList(0, pivot-1);
			ArrayList<Line> start1 = new ArrayList<>();
			ArrayList<Line> eind1 = new ArrayList<>();
			ArrayList<Line> verticaal1 = new ArrayList<>();
			List<Structure> structures2 = structures.subList(pivot, structures.size()-1);
			ArrayList<Line> start2 = new ArrayList<>();
			ArrayList<Line> eind2 = new ArrayList<>();
			ArrayList<Line> verticaal2 = new ArrayList<>();
			//conquer
			recursive(structures1, start1, eind1, verticaal1);
			recursive(structures2, start2, eind2, verticaal2);
			//merge
			double min1 = structures1.get(0).getLine().getStartPoint().getX();
			double max1 = structures1.get(structures1.size()-1).getLine().getEndPoint().getX();
			double min2 = structures2.get(0).getLine().getStartPoint().getX();
			double max2 = structures2.get(structures2.size()-2).getLine().getEndPoint().getX();
			for(Line line : start1){
				if(line.getEndPoint().getX() >= max2){
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
				if(line.getEndPoint().getX() <= min1){
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
	
	private void LSI(){
		LineCollection lines = createLinesOfRectangles(getRechthoeken());		
		ArrayList<Structure> S = createS(lines);
		ArrayList<Point> left = new ArrayList<>();
		ArrayList<Point> right = new ArrayList<>();
		ArrayList<double[]> vertical = new ArrayList<>();
		Linsect(S, left, right, vertical);
		
	}
	
	private void Linsect(List<Structure> list, ArrayList<Point> left, ArrayList<Point> right, ArrayList<double[]> vertical){
		if(list.size() == 1){
			Structure structure = list.get(0);
			switch (structure.getType()) {
	        	case LINKS_PUNT:
	        		left.add((Point) structure.getObject());
	        		break;
	        	case RECHTS_PUNT:
	        		right.add((Point) structure.getObject());
	        		break;
	        	case VERTICAAL_LIJNSTUK:
	        		Line line = (Line) structure.getObject();
	        		double[] value = {line.getStartPoint().getY(), line.getEndPoint().getY()};
	        		vertical.add(value);
	        		break;
			}
		} else {
			int pivot = list.size()/2;
			ArrayList<Point> left1 = new ArrayList<>();
			ArrayList<Point> right1 = new ArrayList<>();
			ArrayList<double[]> vertical1 = new ArrayList<>();
			Linsect(list.subList(0, pivot), left1, right1, vertical);
			ArrayList<Point> left2 = new ArrayList<>();
			ArrayList<Point> right2 = new ArrayList<>();
			ArrayList<double[]> vertical2 = new ArrayList<>();
			Linsect(list.subList(pivot, list.size()-1), left2, right2, vertical2);
		}
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
	
	private ArrayList<Structure> createS(LineCollection lines){
		ArrayList<Structure> S = new ArrayList<>();
		for(Line line : lines.getVerticalLines()){
			S.add(new Structure(line, Type.VERTICAAL_LIJNSTUK));
		}
		for(Line line : lines.getHorizontalLines()){
			S.add(new Structure(line.getStartPoint(), Type.LINKS_PUNT));
			S.add(new Structure(line.getEndPoint(), Type.RECHTS_PUNT));
		}
		S.sort(new StructureComparator());
		return S;
	}
}
