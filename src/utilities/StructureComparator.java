package utilities;

import java.util.Comparator;

public class StructureComparator implements Comparator<Structure>
{
    public int compare(Structure s1, Structure s2)
    {
    	double x1 = 0;
    	double x2 = 0;
    	if(s1.getType() == Type.START_PUNT){
    		Line line = s1.getLine();
    		x1 = line.getStartPoint().getX();
    	}
    	if(s1.getType() == Type.EIND_PUNT){
    		Line line = s1.getLine();
    		x1 = line.getEndPoint().getX();
    	}
    	if(s1.getType() == Type.VERTICAAL_LIJNSTUK){
    		Line line = s1.getLine();
    		x1 = line.getStartPoint().getX();
    	}
    	if(s2.getType() == Type.START_PUNT){
    		Line line = s2.getLine();
    		x2 = line.getStartPoint().getX();
    	}
    	if(s2.getType() == Type.EIND_PUNT){
    		Line line = s2.getLine();
    		x2 = line.getEndPoint().getX();
    	}
    	if(s2.getType() == Type.VERTICAAL_LIJNSTUK){
    		Line line = s2.getLine();
    		x2 = line.getStartPoint().getX();
    	}
    	if (x1 < x2) return -1;
        if (x1 > x2) return 1;
        return 0;
        
    }
}
