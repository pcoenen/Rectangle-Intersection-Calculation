package utilities;

import java.util.ArrayList;

public class LineCollection {
	
	public LineCollection(ArrayList<Line> horizontalLines, ArrayList<Line> verticalLines){
		setHorizontalLines(horizontalLines);
		setVerticalLines(verticalLines);
	}
	
	public ArrayList<Line> getHorizontalLines() {
		return horizontalLines;
	}
	private void setHorizontalLines(ArrayList<Line> horizontalLines) {
		this.horizontalLines = horizontalLines;
	}
	
	private ArrayList<Line> horizontalLines;
	
	public ArrayList<Line> getVerticalLines() {
		return verticalLines;
	}
	private void setVerticalLines(ArrayList<Line> verticalLines) {
		this.verticalLines = verticalLines;
	}
	
	private ArrayList<Line> verticalLines;
}
