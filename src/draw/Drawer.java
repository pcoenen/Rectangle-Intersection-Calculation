package draw;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JFrame;

import main.Rectangle;

public class Drawer {
	
	public Drawer(HashSet<Rectangle> rechthoeken, ArrayList<double[]> intersections) {
		JFrame frame = new JFrame("Rechthoeken");
		frame.add(new Draw(rechthoeken, intersections));
		frame.setSize(1000,1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
