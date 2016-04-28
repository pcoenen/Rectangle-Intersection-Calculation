package main;
import generate_input.Input_generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
		
		if (config.contains("random")) {
			if (config.contains("algorithm")) {
				new Input_generator(config.getInt("random"), config.getInt("algorithm"), config.getString("input"));
			} else {
				System.err.println("Please specify an algorithm if you want to use random input.");
				return;
			}
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
	    
	    //select algorithm
	    Algorithm algorithm = null;
        switch (algoritme_nummer) {
        	case "1":
        		algorithm = new Algorithm1(rechthoeken);
        		break;
            default: writeAlgoNotExists(config.getString("output"));
            	break;
        }
        
        if(algorithm != null){	    
	        ArrayList<double[]> intersections = new ArrayList<>();
	        long start_time = System.nanoTime();
		    intersections = algorithm.run();
	        long end_time = System.nanoTime();
	        double run_time_miliseconds = (end_time - start_time)/10^6;
	        writeOutput(intersections, run_time_miliseconds, "./"+config.getString("output"));
	        System.out.println("Algoritme voltooid in " + run_time_miliseconds + " ms.");
		    if (config.getBoolean("draw")) {
		    	new Drawer(rechthoeken, intersections);
		    }
        }
	} 
	
	private static JSAPResult parse(JSAP jsap, String[] args) throws JSAPException {
		// command line parser
		
		FlaggedOption input = new FlaggedOption("input") .setShortFlag('i') .setLongFlag("input") .setDefault("invoerrechthoeken.txt");
		input.setHelp("The input file.");
		jsap.registerParameter(input);
		
		FlaggedOption output = new FlaggedOption("output") .setDefault("uitvoerrechthoeken.txt") .setShortFlag('o') .setLongFlag("output");
		output.setHelp("The output file.");
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
		
		FlaggedOption algorithm = new FlaggedOption("algorithm") .setShortFlag('a') .setLongFlag("algorithm") .setStringParser(JSAP.INTEGER_PARSER);
		algorithm.setHelp("The algorithm that must be used when a random input file is generated.");
		jsap.registerParameter(algorithm);
		
		JSAPResult config = jsap.parse(args);
		return config;
	}
	
	private static void writeOutput(ArrayList<double[]> intersections, double time, String outputPath) throws IOException {
		PrintWriter writer;
		try {
			writer = new PrintWriter(outputPath, "UTF-8");
		} catch (FileNotFoundException e) {
			File file = new File(outputPath);
			file.createNewFile();
			writer = new PrintWriter(outputPath, "UTF-8");
		}
		
		for(double[] intersec : intersections){
			writer.println(intersec[0]+" "+intersec[1]);
		}
		writer.println("");
		writer.println(time + " miliseconden");
		writer.close();
	}
	
	private static void writeAlgoNotExists(String outputPath) throws IOException {
		PrintWriter writer;
		try {
			writer = new PrintWriter(outputPath, "UTF-8");
		} catch (FileNotFoundException e) {
			File file = new File(outputPath);
			file.createNewFile();
			writer = new PrintWriter(outputPath, "UTF-8");
		}			
		writer.println("Dit algortime werd niet geïmplementeerd");
		writer.close();
	}

}
