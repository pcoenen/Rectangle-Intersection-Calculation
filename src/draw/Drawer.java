package draw;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JFrame;

import main.Coordinate;
import main.Rectangle;

public class Drawer {
	
	public Drawer(HashSet<Rectangle> rechthoeken, HashSet<Coordinate> intersections) {
		JFrame frame = new JFrame("Rechthoeken");
		frame.add(new Draw(rechthoeken, intersections));
		frame.setSize(1000,1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
