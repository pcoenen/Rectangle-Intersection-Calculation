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
//		ArrayList<double[]> uitvoer1 = algoritme1.run();
//		ArrayList<double[]> uitvoer2 = algoritme1.run();
//		uitvoer1.sort(new doubleComparator());
//		uitvoer2.sort(new doubleComparator());
//		assertEquals("Algoritme 1 geeft een uitvoer met twee keer een verschillende lengte"
//				, uitvoer1.size(), uitvoer2.size());
//		for(int i = 0; i < uitvoer1.size(); i++){
//			assertFalse("Algoritme 1 geeft verschillende uitvoer.", !Arrays.equals(uitvoer1.get(i), uitvoer2.get(i)));
//		}
		HashSet<Coordinate> uitvoer1 = algoritme1.run();
		HashSet<Coordinate> uitvoer2 = algoritme1.run();
		assertTrue("Algoritme 1 geeft verschillende uitvoer.", uitvoer1.equals(uitvoer2));
	}
	
	@Test
	public void algo1Doubles() throws IOException {
		HashSet<Rectangle> rechthoeken = getInputRectangles();
		Algorithm algoritme = new Algorithm1(rechthoeken);
		ArrayList<double[]> uitvoer = algoritme.run();
		uitvoer.sort(new doubleComparator());
		Comparator<double[]> comp = new doubleComparator();
		for(int i = 1; i < uitvoer.size(); i++){
			if(comp.compare(uitvoer.get(i-1), uitvoer.get(i)) == 0){
				assertFalse("Algoritme 1 geeft dubbele waardes terug", true);
			}
		}
	}
	
	@Test
	public void algo2AlwaysSameOutput() throws IOException {
		HashSet<Rectangle> rechthoeken = getInputRectangles();
		Algorithm algoritme2 = new Algorithm2(rechthoeken);
		ArrayList<double[]> uitvoer1 = algoritme2.run();
		ArrayList<double[]> uitvoer2 = algoritme2.run();
		uitvoer1.sort(new doubleComparator());
		uitvoer2.sort(new doubleComparator());
		assertEquals("Algoritme 2 geeft een uitvoer met twee keer een verschillende lengte"
				, uitvoer1.size(), uitvoer2.size());
		for(int i = 0; i < uitvoer1.size(); i++){
			assertFalse("Algoritme 2 geeft verschillende uitvoer.", !Arrays.equals(uitvoer1.get(i), uitvoer2.get(i)));
		}
	}
	
	@Test
	public void algo2Doubles() throws IOException {
		HashSet<Rectangle> rechthoeken = getInputRectangles();
		Algorithm algoritme = new Algorithm2(rechthoeken);
		ArrayList<double[]> uitvoer = algoritme.run();
		uitvoer.sort(new doubleComparator());
		Comparator<double[]> comp = new doubleComparator();
		for(int i = 1; i < uitvoer.size(); i++){
			if(comp.compare(uitvoer.get(i-1), uitvoer.get(i)) == 0){
				assertFalse("Algoritme 2 geeft dubbele waardes terug", true);
			}
		}
	}
	
	@Test
	public void algo3AlwaysSameOutput() throws IOException {
		HashSet<Rectangle> rechthoeken = getInputRectangles();
		Algorithm algoritme31 = new Algorithm3(rechthoeken);
		Algorithm algoritme32 = new Algorithm3(rechthoeken);
		ArrayList<double[]> uitvoer1 = algoritme31.run();
		ArrayList<double[]> uitvoer2 = algoritme32.run();
		uitvoer1.sort(new doubleComparator());
		uitvoer2.sort(new doubleComparator());
		assertEquals("Algoritme 3 geeft een uitvoer met twee keer een verschillende lengte"
				, uitvoer1.size(), uitvoer2.size());
		for(int i = 0; i < uitvoer1.size(); i++){
			assertFalse("Algoritme 3 geeft verschillende uitvoer.", !Arrays.equals(uitvoer1.get(i), uitvoer2.get(i)));
		}
	}
	
	@Test
	public void algo3Doubles() throws IOException {
		HashSet<Rectangle> rechthoeken = getInputRectangles();
		Algorithm algoritme = new Algorithm3(rechthoeken);
		ArrayList<double[]> uitvoer = algoritme.run();
		uitvoer.sort(new doubleComparator());
		Comparator<double[]> comp = new doubleComparator();
		for(int i = 1; i < uitvoer.size(); i++){
			if(comp.compare(uitvoer.get(i-1), uitvoer.get(i)) == 0){
				assertFalse("Algoritme 3 geeft dubbele waardes terug", true);
			}
		}
	}

	@Test
	public void sameOutputTest() throws IOException {
		HashSet<Rectangle> rechthoeken = getInputRectangles();
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
		assertEquals("Algo 1 & 2 hebben een uitvoer met twee keer een verschillende lengte"
				, uitvoer1.size(), uitvoer2.size());
		for(int i = 0; i < uitvoer1.size(); i++){
			assertFalse("Algo 1 & 2 hebben verschillende uitvoer.", !Arrays.equals(uitvoer1.get(i), uitvoer2.get(i)));
		}
		assertEquals("Algo 2 & 3 hebben een uitvoer met twee keer een verschillende lengte"
				, uitvoer2.size(), uitvoer3.size());
		for(int i = 0; i < uitvoer1.size(); i++){
			assertFalse("Algo 2 & 3 hebben verschillende uitvoer.", !Arrays.equals(uitvoer2.get(i), uitvoer3.get(i)));
		}
	}
	
	private void dubbleFilter(ArrayList<double[]> uitvoer){
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

}
