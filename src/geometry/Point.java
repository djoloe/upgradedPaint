package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape{

	private int x;
	private int y;
	
	public Point() {
		
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, boolean selected) {
		this(x,y);
		this.selected = selected;
	}
	
	
	
	public double distance(int x, int y) {
		int dx = this.x - x;
		int dy = this.y - y;
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	public double distance(Point p) {
		int dx = this.x - p.getX();
		int dy = this.y - p.getY();
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	public boolean contains(int x, int y) {
		return this.distance(new Point(x,y)) <= 2;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(x - 2, y, x + 2, y);
		g.drawLine(x, y - 2, x, y + 2);
		
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Point) {
			Point temp = (Point)obj;
			if(temp.getX() == x && temp.getY()==y) {
				return true;
			}
		}
		return false;
	}
	
	public void moveBy(int byX, int byY) {
		x += byX;
		//x = x + byX;
		y += byY;
		//y = y + byY;
	}

	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	
	public int compareTo(Object o) {
		if(o instanceof Point) {
			Point temp = (Point)o;
			return ((int)(this.distance(0, 0) - temp.distance(0, 0)));
		}
		return 0;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}

	

	
	
}
