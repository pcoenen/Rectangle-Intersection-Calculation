package utilities;

import java.util.Comparator;

public class StructureComparator implements Comparator<Structure>
{
    public int compare(Structure s1, Structure s2)
    {
    	double x1 = s1.getXValue();
    	double x2 = s2.getXValue();
    	if (x1 < x2) return -1;
        if (x1 > x2) return 1;
        return 0;
        
    }
}
