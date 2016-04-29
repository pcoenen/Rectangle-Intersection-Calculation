package main;

import java.util.Comparator;

public class StructureForBST {
	public StructureForBST(Rectangle rechthoek, int status, double x){
		setRechthoek(rechthoek);
		setyCoordinate(x);
	}
	
	double getyCoordinate() {
		return yCoordinate;
	}
	private void setyCoordinate(double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	
	private double yCoordinate;
	
	Rectangle getRechthoek() {
		return rechthoek;
	}
	private void setRechthoek(Rectangle rechthoek) {
		this.rechthoek = rechthoek;
	}
	
	private Rectangle rechthoek;
}

class structComparatorBSTMin implements Comparator<StructureForBST>
{
    public int compare(StructureForBST s1, StructureForBST s2)
    {
    	if (s1.getyCoordinate() < s2.getyCoordinate()) return -1;
        if (s1.getyCoordinate() > s2.getyCoordinate()) return 1;
        return 0;
        
    }
}

class structComparatorBSTMax implements Comparator<StructureForBST>
{
    public int compare(StructureForBST s1, StructureForBST s2)
    {
    	if (s1.getyCoordinate() < s2.getyCoordinate()) return 1;
        if (s1.getyCoordinate() > s2.getyCoordinate()) return -1;
        return 0;
        
    }
}
