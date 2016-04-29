package main;

import java.util.Comparator;

public class StructureForPQ {
	public StructureForPQ(Rectangle rechthoek, int status, double x){
		setRechthoek(rechthoek);
		setStatus(status);
		setxCoordinate(x);
	}
	
	double getxCoordinate() {
		return xCoordinate;
	}
	private void setxCoordinate(double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	
	private double xCoordinate;
	
	Rectangle getRechthoek() {
		return rechthoek;
	}
	private void setRechthoek(Rectangle rechthoek) {
		this.rechthoek = rechthoek;
	}
	
	private Rectangle rechthoek;
	
	int getStatus() {
		return status;
	}
	
	private void setStatus(int status) {
		this.status = status;
	}

	private int status;
}

class structComparator implements Comparator<StructureForPQ>
{
    public int compare(StructureForPQ s1, StructureForPQ s2)
    {
    	if (s1.getxCoordinate() < s2.getxCoordinate()) return -1;
        if (s1.getxCoordinate() > s2.getxCoordinate()) return 1;
        if (s1.getStatus() == 0) return -1;
        if (s2.getStatus() == 0) return 1;
        return 0;
        
    }
}
