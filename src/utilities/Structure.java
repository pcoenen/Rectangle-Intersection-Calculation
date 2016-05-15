package utilities;

public class Structure {
	public Structure(Line line, Type type){
		setObject(line);
		setType(type);;
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
