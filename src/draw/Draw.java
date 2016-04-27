package draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Rectangle;

@SuppressWarnings("serial")
public class Draw extends JPanel {
	
	public Draw(HashSet<Rectangle> rechthoeken, ArrayList<double[]> punten){
		setRechthoeken(rechthoeken);
		setPunten(punten);
	}
	
	private ArrayList<double[]> punten;
	
	private HashSet<Rectangle> rechthoeken;

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		HashSet<Rectangle> rechthoeken = getRechthoeken();
		for(Rectangle rechthoek : rechthoeken){
			int[] coordinaten = getCoordinates(rechthoek);
			System.out.println(Arrays.toString(coordinaten));
			g2d.drawRect(coordinaten[0], coordinaten[1], coordinaten[2], coordinaten[3]);
		}
		g2d.setColor(Color.GREEN);
		g2d.setColor(Color.RED);
		ArrayList<double[]> punten = getPunten();
		for(double[] punt: punten){
			int[] coordinatenSnijpunten = getCoordinates(punt);
			System.out.println(Arrays.toString(coordinatenSnijpunten));
			g2d.fillRect(coordinatenSnijpunten[0], coordinatenSnijpunten[1], coordinatenSnijpunten[2], coordinatenSnijpunten[3]);
		}
	}
	
	private int[] getCoordinates(double[] punt){
		int[] coordinaten = new int[4];
		coordinaten[0] = pasSchaalaan(punt[0])-3;
		coordinaten[1] = pasSchaalaan(punt[1])-3;
		coordinaten[2] = 6;
		coordinaten[3] = 6;		
		return coordinaten;
	}
	
	private int[] getCoordinates(Rectangle rechthoek){
		int[] coordinaten = new int[4];
		coordinaten[0] = pasSchaalaan(rechthoek.getLox());
		coordinaten[1] = pasSchaalaan(rechthoek.getLoy());
		coordinaten[2] = pasSchaalaan(rechthoek.getRbx()) - pasSchaalaan(rechthoek.getLox());
		coordinaten[3] = pasSchaalaan(rechthoek.getRby()) - pasSchaalaan(rechthoek.getLoy());		
		return coordinaten;
		
		
	}
	
	private int pasSchaalaan(double punt){
		return (int) (punt*1000);
	}

	public ArrayList<double[]> getPunten() {
		return punten;
	}

	public void setPunten(ArrayList<double[]> punten) {
		this.punten = punten;
	}

	public HashSet<Rectangle> getRechthoeken() {
		return rechthoeken;
	}

	public void setRechthoeken(HashSet<Rectangle> rechthoeken) {
		this.rechthoeken = rechthoeken;
	}
}