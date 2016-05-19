package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import draw.Drawer;
import generate_input.Input_generator;
import main.Algorithm;
import main.Algorithm1;
import main.Algorithm2;
import main.Algorithm3;
import main.Coordinate;
import main.Rectangle;
public class VeelTesten {
	
	public static void main(String[] args) throws IOException{
		int N = 1000000;
		if (args.length == 1) {
			try {
				N = Integer.parseInt(args [0]);
			} catch (NumberFormatException e) {
				N = 100000;
			}
		}
		HashSet<Rectangle> rechthoeken;
		System.out.println("Begonnen met testen.");
		System.out.println("Test 1");
		for(int i = 1; i <= N; i++){
			if(i%100000 == 0){
				System.out.println("Test " + i);
			}
			new Input_generator(10, 3, 1,"invoerrechthoeken.txt");
			rechthoeken = getInputRectangles();
			Algorithm algoritme1 = new Algorithm1(rechthoeken);
			Algorithm algoritme2 = new Algorithm2(rechthoeken);
			Algorithm algoritme3 = new Algorithm3(rechthoeken);
			HashSet<Coordinate> uitvoer1 = algoritme1.run();
			HashSet<Coordinate> uitvoer2 = algoritme2.run();
			HashSet<Coordinate> uitvoer3 = algoritme3.run();
			if(! uitvoer1.equals(uitvoer2)) {
				new Drawer(rechthoeken, uitvoer1);
				new Drawer(rechthoeken, uitvoer2);
				System.err.println("Output één en twee verschillen");
				return;
			}
			if(! uitvoer2.equals(uitvoer3)) {
				new Drawer(rechthoeken, uitvoer2);
				new Drawer(rechthoeken, uitvoer3);
				System.err.println("Output twee en drie verschillen");
				return;
			}
		}
		System.out.println("Testen succesvol beïndig");
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
	    reader.readLine();
		int n = Integer.parseInt(reader.readLine());
	    //Lees de input rechthoeken
	    String text = null;
	    String[] array;
	    HashSet<Rectangle> rechthoeken = new HashSet<>();
	    int i = 0;
	    while (i < n && (text = reader.readLine()) != null) {
	         array = text.split(" ");
	         Coordinate lo = new Coordinate(Double.parseDouble(array[0]), Double.parseDouble(array[1]));
	         Coordinate rb = new Coordinate(Double.parseDouble(array[2]), Double.parseDouble(array[3]));
	         rechthoeken.add(new Rectangle(lo, rb));
	         i++;
	    }
	    reader.close();
	    return rechthoeken;
	}

}