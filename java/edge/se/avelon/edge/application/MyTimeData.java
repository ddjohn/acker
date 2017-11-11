package se.avelon.edge.application;

public class MyTimeData {
	public String name;
	public int type;
	public int value;
	
	public MyTimeData(String name, int type, int value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}
	
	public String toString() {
		return name;
	}
}
