package geometry;

import java.awt.Graphics;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;



@Entity
@Table
public class Donut extends Circle {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private int iddonut;
	@Column
	private int innerRadius;
	
	
	public Donut() {
	
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center,radius);
		this.innerRadius = innerRadius;

	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center,radius,innerRadius);
		this.selected = selected;
		
	}
	
	public double area() {
		return super.area() - innerRadius*innerRadius*Math.PI;
	}
	
	public boolean contains(int x, int y) {
		return getCenter().distance(new Point(x,y)) <= getRadius() &&
				getCenter().distance(new Point(x,y)) >= innerRadius;
	}
	
	public boolean contains(Point p) {
		return contains(p.getX(), p.getY());
	}
	
	@Override
	public String toString() {
		return super.toString() + ", inner radius: " + innerRadius;
 	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Donut) {
			Donut temp = (Donut)obj;
			if(temp.getCenter().equals(super.getCenter()) &&
					temp.getRadius() == super.getRadius()
					&& temp.getInnerRadius() == innerRadius) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawOval(getCenter().getX()-innerRadius,
				getCenter().getY()-innerRadius, 
				innerRadius*2, innerRadius*2);
		g.drawOval(getCenter().getX()-radius,
				getCenter().getY()-radius, 
				radius*2, radius*2);
		
	
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
}
