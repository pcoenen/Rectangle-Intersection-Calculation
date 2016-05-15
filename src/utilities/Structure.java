package utilities;

public class Structure {
	public Structure(Line line, Type type){
		setObject(line);
		setType(type);;
	}
	
	public double getXValue(){
		switch (this.getType()) {
	    	case START_PUNT:
	    		Line line = this.getLine();
	    		return line.getStartPoint().getX();
	    	case EIND_PUNT:
	    		return this.getLine().getEndPoint().getX();
	    	case VERTICAAL_LIJNSTUK:
	    		return this.getLine().getStartPoint().getX();
		}
		return (Double) null;
	}
	
	public Line getLine() {
		return line;
	}
	private void setObject(Line line) {
		this.line = line;
	}
	
	private Line line;
	
	public Type getType() {
		return type;
	}
	private void setType(Type type) {
		this.type = type;
	}
	
	private Type type;

}
