package main;

import java.awt.Color;

import graph.ScatterGraphInterface;

public class ScatterGraphAdaptation implements ScatterGraphInterface {

	private double[][] values;

	public ScatterGraphAdaptation(double values[][]) {
		this.values = values;
	}

	public double getY(int item) {
		return values[item][0];
	}

	public double getX(int item) {
		return values[item][1];
	}

	public String getName(int item) {
		return "Name" + item;
	}

	public Color getTextColor(int item) {
		return Color.red;
	}

	public int getNumberOfItems() {
		return values.length;
	}

	public int getStyle(int item) {
		return ScatterGraphInterface.NONE;
	}
}
