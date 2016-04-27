package generate_input;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Input_generator {

	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		int n = 10;
		String path = "./"+"input.txt";
		PrintWriter writer = new PrintWriter(path, "UTF-8");
		writer.println("1");
		writer.println(n);
		int i = 0;
		while(i<n){
			double[] rect = randomRect();
			writer.println(rect[0]+" "+rect[1]+" "+rect[2]+" "+rect[3]);
			i++;			
		}
		writer.close();
		
	}
	
	private static double[] randomRect(){
		double[] coordinates = new double[4];
		double min = 0;
		double max = 1;
		Random r = new Random();
		coordinates[0] = min + (max - min) * r.nextDouble();
		coordinates[1] = min + (max - min) * r.nextDouble();
		min = coordinates[0];
		coordinates[2] = min + (max - min) * r.nextDouble();
		min = coordinates[1];
		coordinates[3] = min + (max - min) * r.nextDouble();
		return coordinates;		
	}

}
