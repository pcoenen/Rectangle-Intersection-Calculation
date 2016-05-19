package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

import org.junit.Test;

import generate_input.Input_generator;
import main.Algorithm;
import main.Algorithm1;
import main.Algorithm2;
import main.Algorithm3;
import main.Coordinate;
import main.Rectangle;

public class Testcase {
	
	private static final int ArrayList = 0;

	@Test
	public void algo1AlwaysSameOutput() throws IOException {
		HashSet<Rectangle> rechthoeken = getInputRectangles();
		Algorithm algoritme1 = new Algorithm1(rechthoeken);
		HashSet<Coordinate> uitvoer1 = algoritme1.run();
		HashSet<Coordinate> uitvoer2 = algoritme1.run();
		assertEquals("Algoritme 1 geeft een uitvoer met twee keer een verschillende lengte"
				, uitvoer1.size(), uitvoer2.size());
		assertFalse("Algoritme 1 geeft verschillende uitvoer.", !uitvoer1.equals(uitvoer2));
	}

	
	@Test
	public void algo2AlwaysSameOutput() throws IOException {
		HashSet<Rectangle> rechthoeken = getInputRectangles();
		Algorithm algoritme2 = new Algorithm2(rechthoeken);
		HashSet<Coordinate> uitvoer1 = algoritme2.run();
		HashSet<Coordinate> uitvoer2 = algoritme2.run();
		assertEquals("Algoritme 2 geeft een uitvoer met twee keer een verschillende lengte"
				, uitvoer1.size(), uitvoer2.size());
		assertFalse("Algoritme 2 geeft verschillende uitvoer.", !uitvoer1.equals(uitvoer2));
	}
	
	@Test
	public void algo3AlwaysSameOutput() throws IOException {
		HashSet<Rectangle> rechthoeken = getInputRectangles();
		Algorithm algoritme31 = new Algorithm3(rechthoeken);
		Algorithm algoritme32 = new Algorithm3(rechthoeken);
		HashSet<Coordinate> uitvoer1 = algoritme31.run();
		HashSet<Coordinate> uitvoer2 = algoritme32.run();
		assertEquals("Algoritme 3 geeft een uitvoer met twee keer een verschillende lengte"
				, uitvoer1.size(), uitvoer2.size());
		assertFalse("Algoritme 3 geeft verschillende uitvoer.", !uitvoer1.equals(uitvoer2));
	}

	@Test
	public void sameOutputTest() throws IOException {
		HashSet<Rectangle> rechthoeken = getInputRectangles();
		Algorithm algoritme1 = new Algorithm1(rechthoeken);
		Algorithm algoritme2 = new Algorithm2(rechthoeken);
		Algorithm algoritme3 = new Algorithm3(rechthoeken);
		HashSet<Coordinate> uitvoer1 = algoritme1.run();
		HashSet<Coordinate> uitvoer2 = algoritme2.run();
		HashSet<Coordinate> uitvoer3 = algoritme3.run();
		assertEquals("Algo 1 & 2 hebben een uitvoer met twee keer een verschillende lengte"
				, uitvoer1.size(), uitvoer2.size());
		assertFalse("Algoritme 1 en 2 hebben een verschillende uitvoer.", !uitvoer1.equals(uitvoer2));
		assertEquals("Algo 2 & 3 hebben een uitvoer met twee keer een verschillende lengte"
				, uitvoer2.size(), uitvoer3.size());
		assertFalse("Algoritme 2 en 3 hebben een verschillende uitvoer.", ! uitvoer2.equals(uitvoer3));
	}

	
	public HashSet<Rectangle> getInputRectangles() throws IOException{
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
