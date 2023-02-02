package geometry;

import java.awt.Graphics;

public class Line extends Shape {

	private Point startPoint;
	private Point endPoint;
	
	public Line() {
	}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		this.selected = selected;
	}
	
	
	public double length() {
		return startPoint.distance(endPoint);
	}
	
	public boolean contains(int x, int y) {
		return length() - (startPoint.distance(new Point(x,y))
			+ endPoint.distance(new Point(x,y))) <= 2;
	}
	
	@Override
	public String toString() {
		return startPoint.toString() + " --> " + endPoint.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Line) {
			Line temp = (Line)obj;
			if(temp.getStartPoint().equals(startPoint) && 
					temp.getEndPoint().equals(endPoint)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(edgeColor);
		g.drawLine(startPoint.getX(), startPoint.getY(),
				endPoint.getX(), endPoint.getY());
	
	}
	
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		//startPoint.setX(startPoint.getX()+byX));
		endPoint.moveBy(byX, byY);
	}

	public void moveTo(int x, int y) {
		
	}
	
	public int compareTo(Object o) {
		if(o instanceof Line) {
			Line temp = (Line)o;
			return ((int)(this.length() - temp.length()));
		}
		return 0;
	}
	
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	
	public Point getEndPoint() {
		return endPoint;
	}

	

	
}
