import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		String path = args[0];
		File input = new File("./"+path);
		BufferedReader reader = new BufferedReader(new FileReader(input));
		//Read file
	    String text = null;
	    while ((text = reader.readLine()) != null) {
	        System.out.println(text);
	    }
	    reader.close();
	} 

}
