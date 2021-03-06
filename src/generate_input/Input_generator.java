package generate_input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Input_generator {

	public Input_generator(int n, int algoritme, double maxLengthSide, String inputFile) throws IOException {
		String path = "./" + inputFile;
		PrintWriter writer;
		try {
			writer = new PrintWriter(path, "UTF-8");
		} catch (FileNotFoundException e) {
			File file = new File(path);
			file.createNewFile();
			writer = new PrintWriter(path, "UTF-8");
		}
		writer.println(algoritme);
		writer.println(n);
		int i = 0;
		while(i<n){
			double[] rect = randomRect(maxLengthSide);
			writer.println(rect[0]+" "+rect[1]+" "+rect[2]+" "+rect[3]);
			i++;			
		}
		writer.close();
	}
	
	private static double[] randomRect(double maxLengthSide){
		double[] coordinates = new double[4];
		double min = 0;
		double max = 1;
		Random r = new Random();
		coordinates[0] = min + (max - min) * r.nextDouble();
		coordinates[1] = min + (max - min) * r.nextDouble();
		min = coordinates[0];
		coordinates[2] = min + Math.min(maxLengthSide, (max - min)) * r.nextDouble();
		min = coordinates[1];
		coordinates[3] = min + Math.min(maxLengthSide, (max - min)) * r.nextDouble();
		return coordinates;		
	}

}
