package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import com.martiansoftware.jsap.*;

import draw.Drawer;

public class Main {
	public static void main(String[] args) throws IOException, JSAPException {
		JSAP jsap = new JSAP();
		JSAPResult config = parse(jsap, args);
		if (!config.success()) {
			// stop execution when the command line argument parsing fails
			System.err.println("Incorrect arguments. For help, use -h or --help");
			return;
		}
		if (config.getBoolean("help")) {
			System.out.println("Help page");
			System.out.println();
			System.out.println(jsap.getHelp());
			return;
		}
				
		String path = config.getString("input");
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
	
	private static JSAPResult parse(JSAP jsap, String[] args) throws JSAPException {
		// command line parser
		
		FlaggedOption input = new FlaggedOption("input") .setShortFlag('i') .setLongFlag("input") .setDefault("invoerrechthoeken.txt");
		input.setHelp("The input file, 'invoerrechthoeken.txt' if omitted.");
		jsap.registerParameter(input);
		
		FlaggedOption output = new FlaggedOption("output") .setDefault("uitvoerrechthoeken.txt") .setShortFlag('o') .setLongFlag("output");
		output.setHelp("The output file, 'uitvoerrechthoeken.txt' if omitted.");
		jsap.registerParameter(output);
		
		Switch draw = new Switch("draw") .setShortFlag('d') .setLongFlag("draw");
		draw.setHelp("Request visual output.");
		jsap.registerParameter(draw);
		
		Switch help = new Switch("help") .setShortFlag('h') .setLongFlag("help");
		help.setHelp("Open the help page.");
		jsap.registerParameter(help);
		
		FlaggedOption random = new FlaggedOption("random") .setShortFlag('r') .setLongFlag("random") .setStringParser(JSAP.INTEGER_PARSER);
		random.setHelp("Use a random rectangle generator instead of an input file. Specify the number of rectangles.");
		jsap.registerParameter(random);
		
		JSAPResult config = jsap.parse(args);
		return config;
	}

}
