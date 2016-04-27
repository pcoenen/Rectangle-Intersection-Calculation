package draw;

import java.util.HashSet;

import javax.swing.JFrame;

import main.Rectangle;

public class Test {

	public static void main(String[] args) {
		//maken rechthoekset
		HashSet<Rectangle> set = new HashSet<>();
		set.add(new Rectangle(00, 00, 0.2, 0.2));
		//teken shit
		/**JFrame frame = new JFrame("Rechthoeken");
		frame.add(new Draw(set));
		frame.setSize(1000,1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);**/
	}

}
