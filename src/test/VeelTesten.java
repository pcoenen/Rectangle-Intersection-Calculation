package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

import draw.Drawer;
import generate_input.Input_generator;
import main.Algorithm;
import main.Algorithm1;
import main.Algorithm2;
import main.Algorithm3;
import main.Rectangle;
public class VeelTesten {
	
	public static void main(String[] args) throws IOException{
		HashSet<Rectangle> rechthoeken;
		for(int i = 1; i <= 50000000; i++){
			if(i%10000 == 0){
				System.out.println("Test " + i);
			}
			new Input_generator(10, 2, 1,"invoerrechthoeken.txt");
			rechthoeken = getInputRectangles();
			Algorithm algoritme1 = new Algorithm1(rechthoeken);
			Algorithm algoritme2 = new Algorithm2(rechthoeken);
			Algorithm algoritme3 = new Algorithm3(rechthoeken);
			ArrayList<double[]> uitvoer1 = algoritme1.run();
			ArrayList<double[]> uitvoer2 = algoritme2.run();
			ArrayList<double[]> uitvoer3 = algoritme3.run();
			uitvoer1.sort(new doubleComparator());
			uitvoer2.sort(new doubleComparator());
			uitvoer3.sort(new doubleComparator());
			dubbleFilter(uitvoer3);
			if(uitvoer1.size() != uitvoer2.size()){
				//				new Drawer(rechthoeken, uitvoer1);
//				new Drawer(rechthoeken, uitvoer2);
				System.out.println("Lengte van output ��n en twee verschillen");
				break;
			}			
			for(int j = 0; j < uitvoer1.size(); j++){
				if(!Arrays.equals(uitvoer1.get(j), uitvoer2.get(j))){
					//					new Drawer(rechthoeken, uitvoer1);
//					new Drawer(rechthoeken, uitvoer2);
					System.out.println("Output ��n en twee verschillen");
					return;
				}
			}
			if(uitvoer3.size() != uitvoer2.size()){
				//				new Drawer(rechthoeken, uitvoer2);
//				new Drawer(rechthoeken, uitvoer3);
				System.out.println("Lengte van output twee en drie verschillen");
				break;
			}
			for(int j = 0; j < uitvoer2.size(); j++){
				if(!Arrays.equals(uitvoer3.get(j), uitvoer2.get(j))){
					//					new Drawer(rechthoeken, uitvoer2);
//					new Drawer(rechthoeken, uitvoer3);
					System.out.println("Output twee en drie verschillen");
					return;
				}
			}
		}
	}
	
	private static void dubbleFilter(ArrayList<double[]> uitvoer){
		ArrayList<Integer> buitengooien = new ArrayList<>();
		Comparator<double[]> comp = new doubleComparator();
		for(int i = 1; i < uitvoer.size(); i++){
			if(comp.compare(uitvoer.get(i-1), uitvoer.get(i)) == 0){
				buitengooien.add(i-1);
			}
		}
		for(int i=buitengooien.size()-1; i >= 0; i--){
			uitvoer.remove((int) buitengooien.get(i));
		}
	}
	
	private static HashSet<Rectangle> getInputRectangles() throws IOException{
		String path = "invoerrechthoeken.txt";
		File input = new File("./"+path);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(input));
		} catch (FileNotFoundException e) {
			System.err.println("Input file does not exists!");
		}
		
		//Read file
	    String algoritme_nummer = reader.readLine();
		int n = Integer.parseInt(reader.readLine());
	    //Lees de input rechthoeken
	    String text = null;
	    String[] array;
	    HashSet<Rectangle> rechthoeken = new HashSet<>();
	    int i = 0;
	    while (i < n && (text = reader.readLine()) != null) {
	         array = text.split(" ");
	         rechthoeken.add(new Rectangle( Double.parseDouble(array[0]), Double.parseDouble(array[1]), Double.parseDouble(array[2]), Double.parseDouble(array[3])));	
	         i++;
	    }
	    reader.close();
	    return rechthoeken;
	}

}

class doubleComparator implements Comparator<double[]>
{
    public int compare(double[] d1, double[] d2)
    {
    	if(d1[0] < d2[0]) return -1;
    	if(d1[0] > d2[0]) return 1;
    	if(d1[1] < d2[1]) return -1;
    	if(d1[1] > d2[1]) return 1;
        return 0;
        
    }
}
