package geometry;

import java.awt.Color;
import java.awt.Graphics;

import DrawingApp.PnlDrawing;


public abstract class Shape implements Moveable, Comparable{
	
	protected boolean selected;
	protected Color edgeColor;
	protected Color fillColor;
	public Shape() {
	
	}

	public Shape(boolean selected) {
		this.selected = selected;
	}
	
	
	
	public abstract boolean contains(int x, int y);
	
	public abstract void draw(Graphics g);

	public boolean isSelected() {
		return selected;
	}
	
	public void setEdgecolor(Color color) {
		this.edgeColor  = color;
	}
	
	public void setFillColor(Color color) {
		this.fillColor  = color;
	}
	
	public Color getColor() {
		return edgeColor;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	

	
	
	
	

	
}
