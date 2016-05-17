package draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JPanel;

import main.Coordinate;
import main.Rectangle;

@SuppressWarnings("serial")
public class Draw extends JPanel {
	
	public Draw(HashSet<Rectangle> rechthoeken, HashSet<Coordinate> punten){
		setRechthoeken(rechthoeken);
		setPunten(punten);
	}
	
	private HashSet<Coordinate> punten;
	
	private HashSet<Rectangle> rechthoeken;

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		HashSet<Rectangle> rechthoeken = getRechthoeken();
		for(Rectangle rechthoek : rechthoeken){
			int[] coordinaten = getCoordinates(rechthoek);
			g2d.drawRect(coordinaten[0], coordinaten[1], coordinaten[2], coordinaten[3]);
		}
		g2d.setColor(Color.RED);
		HashSet<Coordinate> punten = getPunten();
		for(Coordinate punt: punten){
			int[] coordinatenSnijpunten = getCoordinates(punt);
			g2d.fillRect(coordinatenSnijpunten[0], coordinatenSnijpunten[1], coordinatenSnijpunten[2], coordinatenSnijpunten[3]);
		}
	}
	
	private int[] getCoordinates(Coordinate punt){
		int[] coordinaten = new int[4];
		coordinaten[0] = pasSchaalaan(punt.getX())-3;
		coordinaten[1] = pasSchaalaan(punt.getY())-3;
		coordinaten[2] = 6;
		coordinaten[3] = 6;		
		return coordinaten;
	}
	
	private int[] getCoordinates(Rectangle rechthoek){
		int[] coordinaten = new int[4];
		coordinaten[0] = pasSchaalaan(rechthoek.getLo().getX());
		coordinaten[1] = pasSchaalaan(rechthoek.getLo().getY());
		coordinaten[2] = pasSchaalaan(rechthoek.getRb().getX()) - pasSchaalaan(rechthoek.getLo().getX());
		coordinaten[3] = pasSchaalaan(rechthoek.getRb().getY()) - pasSchaalaan(rechthoek.getLo().getY());		
		return coordinaten;
		
		
	}
	
	private int pasSchaalaan(double punt){
		return (int) (punt*850);
	}

	public HashSet<Coordinate> getPunten() {
		return punten;
	}

	public void setPunten(HashSet<Coordinate> punten) {
		this.punten = punten;
	}

	public HashSet<Rectangle> getRechthoeken() {
		return rechthoeken;
	}

	public void setRechthoeken(HashSet<Rectangle> rechthoeken) {
		this.rechthoeken = rechthoeken;
	}
}