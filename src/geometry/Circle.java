package geometry;

import java.awt.Graphics;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import Login.Workspace;


@Entity
@Table
public class Circle extends Shape {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idcircle;
	
	@Column
	protected int radius;
	
	@Column
	private Point center;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "id_workspace")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Workspace workspace;
	
	public Circle() {
		
	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Circle(Point center, int radius, Workspace workspace) {
		this(center,radius);
		this.workspace = workspace;
	}
	
	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		this.selected = selected;
		
	}

	public double area() {
		return radius * radius * Math.PI;
	}

	public double circumference() {
		return radius * 2 * Math.PI;
	}

	public boolean contains(int x, int y) {
		return center.distance(new Point(x, y)) <= radius;
	}

	public boolean contains(Point p) {
		return contains(p.getX(), p.getY());
	}

	@Override
	public String toString() {
		return "Center: " + center + ", radius: " + radius;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle temp = (Circle) obj;
			if (temp.getCenter().equals(center) && temp.getRadius() == radius) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
		g.setColor(fillColor);
		g.fillOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
	}

	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);

	}

	public void moveTo(int x, int y) {
		center.moveTo(x, y);

	}
	
	public int compareTo(Object o) {
		if(o instanceof Circle) {
			Circle temp = (Circle)o;
			return ((int)(this.area() - temp.area()));
		}
		return 0;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) throws Exception {
		if(radius < 0) {
			throw new Exception("Negativan poluprecnik");
		}else {
			this.radius = radius;
		}
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	

}
