package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import com.martiansoftware.jsap.*;

import draw.Drawer;

public class Main {
	// TODO: statische klasse?
	public void main(String[] args) throws IOException, JSAPException {
		JSAPResult config = this.parse(args);
				
		String path = args[0];
		File input = new File("./"+path);
		BufferedReader reader = new BufferedReader(new FileReader(input));
		//Read file
	    String algoritme_nummer = reader.readLine();
	    String n = reader.readLine();
	    //Lees de input rechthoeken
	    String text = null;
	    String[] array;
	    HashSet<Rectangle> rechthoeken = new HashSet<>();
	    while ((text = reader.readLine()) != null) {
	         array = text.split(" ");
	         rechthoeken.add(new Rectangle( Double.parseDouble(array[0]), Double.parseDouble(array[1]), Double.parseDouble(array[2]), Double.parseDouble(array[3])));	        
	    }
	    reader.close();
	    
	    Algorithm algorithm = new Algorithm1(rechthoeken);
	    
	    ArrayList<double[]> intersections = new ArrayList<>();
	    intersections = algorithm.run();
	    
	    new Drawer(rechthoeken, intersections);
	} 
	
	private JSAPResult parse(String[] args) throws JSAPException {
		// command line parser
		JSAP jsap = new JSAP(); 
		
		FlaggedOption inputFile = new FlaggedOption("input")
										.setShortFlag('i')
										.setLongFlag("input");
		jsap.registerParameter(inputFile);
		FlaggedOption outputFile = new FlaggedOption("output")
										.setShortFlag('o')
										.setLongFlag("output");
		jsap.registerParameter(outputFile);
		Switch draw = new Switch("draw") .setShortFlag('d') .setLongFlag("draw");
		jsap.registerParameter(draw);
		
		JSAPResult config = jsap.parse(args);
		return config;
	}

}
