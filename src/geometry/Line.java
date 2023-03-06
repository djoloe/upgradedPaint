package geometry;

import java.awt.Graphics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table
public class Line extends Shape {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idline;
	@Column 
	private Point startPoint;
	@Column
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
		double lenght = Math.abs(length() - (startPoint.distance(new Point(x,y))
				+ endPoint.distance(new Point(x,y))));
		return lenght <= 2;
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
		g.setColor(color);
		g.drawLine(startPoint.getX(), startPoint.getY(),
				endPoint.getX(), endPoint.getY());
	
	}
	
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
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
