package main;
import java.util.HashSet;


public abstract class Algorithm {
	public Algorithm(HashSet<Rectangle> rechthoeken) {
		setRechthoeken(rechthoeken);
	}
	
	public abstract HashSet<Coordinate> run();
	
	
	protected HashSet<Rectangle> getRechthoeken() {
		return rechthoeken;
	}

	protected void setRechthoeken(HashSet<Rectangle> rechthoeken) {
		this.rechthoeken = rechthoeken;
	}

	private HashSet<Rectangle> rechthoeken;
}
