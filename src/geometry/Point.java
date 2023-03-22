package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import Login.User;
import Login.Workspace;



@Entity
@Table
public class Point extends Shape {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idpoint;
	@Column(name="x")
	private int x;
	@Column(name="y")
	private int y;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "id_workspace")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Workspace workspace;
	
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
	
	public Point(int x, int y, Workspace workspace) {
		this(x,y);
		this.workspace = workspace;
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
