package test;

import generate_input.Input_generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

import main.Algorithm;
import main.Algorithm1;
import main.Algorithm2;
import main.Algorithm3;
import main.Coordinate;
import main.Rectangle;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;

public class DoublingRatioExperiment {

	public static void main(String[] args) throws JSAPException, IOException {
		JSAP jsap = new JSAP();
		JSAPResult config = parse(jsap, args);
		if (!config.success()) {
			// stop execution when the command line argument parsing fails
			System.err.println("Incorrect arguments. For help, use -h or --help");
			return;
		}
		
		int n = config.getInt("number");
		String outputPath = "./" + config.getString("output");
		String inputPath = "./" + config.getString("input");
		double sideSize = config.getDouble("side");
		if (sideSize <= 0 || sideSize > 1) sideSize = 1;
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(outputPath, "UTF-8");
		} catch (FileNotFoundException e) {
			File file = new File(outputPath);
			file.createNewFile();
			writer = new PrintWriter(outputPath, "UTF-8");
		}
		
		writer.println("N\tAlgoritme1\tAlgoritme2\tAlgoritme3");
		System.out.println("N\tAlgoritme1\tAlgoritme2\tAlgoritme3");
		long start_time;
		long end_time;
		Algorithm algorithm;
		HashSet<Rectangle> rectangles;
		double runTime1, runTime2, runTime3;
		for(int i=1; i <= n; i=i*2) {
			new Input_generator(i, 2, sideSize, inputPath);
			rectangles = getInputRectangles(inputPath);
			// Algoritme 1
			algorithm = new Algorithm1(rectangles);
			start_time = System.nanoTime();
			algorithm.run();
			end_time = System.nanoTime();
			runTime1 = (end_time - start_time)/(1e6);
			// Algoritme 2
			algorithm = new Algorithm2(rectangles);
			start_time = System.nanoTime();
			algorithm.run();
			end_time = System.nanoTime();
			runTime2 = (end_time - start_time)/(1e6);
			// Algoritme 2
			algorithm = new Algorithm3(rectangles);
			start_time = System.nanoTime();
			algorithm.run();
			end_time = System.nanoTime();
			runTime3 = (end_time - start_time)/(1e6);
			
			writer.println(i + "\t" + runTime1 + "\t" + runTime2 + "\t" + runTime3);
			System.out.println(i + "\t" + runTime1 + "\t" + runTime2 + "\t" + runTime3);
		}

        writer.close();
	}
	
	private static JSAPResult parse(JSAP jsap, String[] args) throws JSAPException {
		// command line parser
		FlaggedOption maxRect = new FlaggedOption("number") .setLongFlag("number") .setShortFlag('n') .setDefault("20000") .setStringParser(JSAP.INTEGER_PARSER);
		jsap.registerParameter(maxRect);
		
		FlaggedOption output = new FlaggedOption("output") .setLongFlag("output") .setShortFlag('o') .setDefault("doubling.txt");
		jsap.registerParameter(output);
		
		FlaggedOption input = new FlaggedOption("input") .setLongFlag("input") .setShortFlag('i') .setDefault("doubling_in.txt");
		jsap.registerParameter(input);
		
		FlaggedOption side = new FlaggedOption("side") .setLongFlag("side") .setShortFlag('s') .setDefault("1") .setStringParser(JSAP.DOUBLE_PARSER);
		jsap.registerParameter(side);
		
		JSAPResult config = jsap.parse(args);
		return config;
	}
	
	private static HashSet<Rectangle> getInputRectangles(String inputfile) throws IOException{
		File input = new File(inputfile);
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
