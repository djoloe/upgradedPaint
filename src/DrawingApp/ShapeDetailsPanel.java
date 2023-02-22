package DrawingApp;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.w3c.dom.css.Rect;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import javax.swing.JTextField;

public class ShapeDetailsPanel extends JPanel{

	private JPanel panel;
	private JTextField fieldX1;
	private JTextField fieldX2;
	private JTextField fieldY1;
	private JTextField fieldY2;
	private JTextField fieldWidth;
	private JTextField fieldHeight;
	private JTextField fieldRadius;
	private JTextField fieldInner;
	private JLabel labelX1;
	private JLabel labelX2;
	private JLabel labelY1;
	private JLabel labelY2;
	private JLabel labelWidth;
	private JLabel labelHeight;
	private JLabel labelRadius;
	private JLabel labelInner;
	private JLabel labelClass;
	private Status status;
	private Point p;
	private Line l;
	private Circle c;
	private Rectangle r;
	private Donut d;
	private Shape shape;
	
	
	public ShapeDetailsPanel(JPanel panel) {
		this.panel = panel;
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		labelClass = new JLabel("");
		sl_panel.putConstraint(SpringLayout.NORTH, labelClass, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, labelClass, 179, SpringLayout.WEST, panel);
		panel.add(labelClass);
		
		labelX1 = new JLabel("X:");
		sl_panel.putConstraint(SpringLayout.NORTH, labelX1, 48, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, labelX1, 10, SpringLayout.WEST, panel);
		labelX1.setVisible(false);
		panel.add(labelX1);
		
		labelX2 = new JLabel("X:");
		sl_panel.putConstraint(SpringLayout.NORTH, labelX2, 20, SpringLayout.SOUTH, labelX1);
		sl_panel.putConstraint(SpringLayout.WEST, labelX2, 0, SpringLayout.WEST, labelX1);
		labelX2.setVisible(false);
		panel.add(labelX2);
		
		fieldX1 = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, fieldX1, 45, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, fieldX1, 6, SpringLayout.EAST, labelX1);
		sl_panel.putConstraint(SpringLayout.EAST, fieldX1, 40, SpringLayout.EAST, labelX1);
		fieldX1.setVisible(false);
		panel.add(fieldX1);
		fieldX1.setColumns(10);
		
		fieldX2 = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, fieldX2, 14, SpringLayout.SOUTH, fieldX1);
		sl_panel.putConstraint(SpringLayout.WEST, fieldX2, 6, SpringLayout.EAST, labelX2);
		sl_panel.putConstraint(SpringLayout.EAST, fieldX2, 40, SpringLayout.EAST, labelX2);
		fieldX2.setVisible(false);
		fieldX2.setColumns(10);
		panel.add(fieldX2);
		
		
		labelY1 = new JLabel("Y:");
		sl_panel.putConstraint(SpringLayout.WEST, labelY1, 18, SpringLayout.EAST, fieldX1);
		sl_panel.putConstraint(SpringLayout.SOUTH, labelY1, 0, SpringLayout.SOUTH, labelX1);
		labelY1.setVisible(false);
		panel.add(labelY1);
		
		labelY2 = new JLabel("Y:");
		sl_panel.putConstraint(SpringLayout.NORTH, labelY2, 0, SpringLayout.NORTH, labelX2);
		sl_panel.putConstraint(SpringLayout.WEST, labelY2, 0, SpringLayout.WEST, labelY1);
		labelY2.setVisible(false);
		panel.add(labelY2);
		
		fieldY1 = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, fieldY1, 45, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, fieldY1, 3, SpringLayout.EAST, labelY1);
		sl_panel.putConstraint(SpringLayout.EAST, fieldY1, 37, SpringLayout.EAST, labelY1);
		fieldY1.setVisible(false);
		panel.add(fieldY1);
		fieldY1.setColumns(10);
		
		
		fieldY2 = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, fieldY2, -3, SpringLayout.NORTH, labelX2);
		sl_panel.putConstraint(SpringLayout.WEST, fieldY2, 3, SpringLayout.EAST, labelY2);
		sl_panel.putConstraint(SpringLayout.EAST, fieldY2, 0, SpringLayout.EAST, fieldY1);
		fieldY2.setVisible(false);
		fieldY2.setColumns(10);
		panel.add(fieldY2);
		
		
		labelWidth = new JLabel("Width:");
		sl_panel.putConstraint(SpringLayout.NORTH, labelWidth, 0, SpringLayout.NORTH, labelX1);
		sl_panel.putConstraint(SpringLayout.WEST, labelWidth, 19, SpringLayout.EAST, fieldY1);
		labelWidth.setVisible(false);
		panel.add(labelWidth);
		
		labelHeight = new JLabel("Height:");
		sl_panel.putConstraint(SpringLayout.WEST, labelHeight, 0, SpringLayout.WEST, labelWidth);
		sl_panel.putConstraint(SpringLayout.SOUTH, labelHeight, 0, SpringLayout.SOUTH, labelX2);
		labelHeight.setVisible(false);
		panel.add(labelHeight);
		
		fieldWidth = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, fieldWidth, -3, SpringLayout.NORTH, labelX1);
		sl_panel.putConstraint(SpringLayout.WEST, fieldWidth, 9, SpringLayout.EAST, labelWidth);
		fieldWidth.setVisible(false);
		panel.add(fieldWidth);
		fieldWidth.setColumns(10);
		
		
		fieldHeight = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, fieldHeight, 14, SpringLayout.SOUTH, fieldWidth);
		sl_panel.putConstraint(SpringLayout.EAST, fieldWidth, 0, SpringLayout.EAST, fieldHeight);
		sl_panel.putConstraint(SpringLayout.WEST, fieldHeight, 6, SpringLayout.EAST, labelHeight);
		sl_panel.putConstraint(SpringLayout.EAST, fieldHeight, 40, SpringLayout.EAST, labelHeight);
		fieldHeight.setVisible(false);
		fieldHeight.setColumns(10);
		panel.add(fieldHeight);
		
		
		labelRadius = new JLabel("Radius:");
		sl_panel.putConstraint(SpringLayout.NORTH, labelRadius, 0, SpringLayout.NORTH, labelX1);
		sl_panel.putConstraint(SpringLayout.WEST, labelRadius, 22, SpringLayout.EAST, fieldWidth);
		labelRadius.setVisible(false);
		panel.add(labelRadius);
		
		labelInner = new JLabel("Inner:");
		sl_panel.putConstraint(SpringLayout.WEST, labelInner, 0, SpringLayout.WEST, labelRadius);
		sl_panel.putConstraint(SpringLayout.SOUTH, labelInner, 0, SpringLayout.SOUTH, labelX2);
		labelInner.setVisible(false);
		panel.add(labelInner);
		
		fieldRadius = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, fieldRadius, 45, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, fieldRadius, 6, SpringLayout.EAST, labelRadius);
		sl_panel.putConstraint(SpringLayout.EAST, fieldRadius, 40, SpringLayout.EAST, labelRadius);
		fieldRadius.setVisible(false);
		panel.add(fieldRadius);
		fieldRadius.setColumns(10);
		
		
		fieldInner = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, fieldInner, 14, SpringLayout.SOUTH, fieldRadius);
		sl_panel.putConstraint(SpringLayout.WEST, fieldInner, 13, SpringLayout.EAST, labelInner);
		sl_panel.putConstraint(SpringLayout.EAST, fieldInner, 2, SpringLayout.EAST, fieldRadius);
		fieldInner.setVisible(false);
		fieldInner.setColumns(10);
		panel.add(fieldInner);
		
	}
	
	
	
	public ShapeDetailsPanel(JPanel contentPanel, Shape shape) {
		this(contentPanel);
		whichShapeToPopulate(shape);
		this.shape = shape;
	}



	public void setValuePointAtPaint(Point p) {
		hideAllComponents();
		labelX1.setVisible(true);
		labelY1.setVisible(true);
		fieldX1.setVisible(true);
		fieldY1.setVisible(true);
		fieldX1.setText(Integer.toString(p.getX()));
		fieldY1.setText(Integer.toString(p.getY()));
		labelClass.setText("Point");
		
	}
	
	public void setValueLineAtPaint(Line l) {
		hideAllComponents();
		labelX1.setVisible(true);
		labelY1.setVisible(true);
		labelX2.setVisible(true);
		labelY2.setVisible(true);
		fieldX1.setVisible(true);
		fieldY1.setVisible(true);
		fieldX2.setVisible(true);
		fieldY2.setVisible(true);
		fieldX1.setText(Integer.toString(l.getStartPoint().getX()));
		fieldY1.setText(Integer.toString(l.getStartPoint().getY()));
		fieldX2.setText(Integer.toString(l.getEndPoint().getX()));
		fieldY2.setText(Integer.toString(l.getEndPoint().getY()));
		labelClass.setText("Line");
	}
	
	public void setValueRectAtPaint(Rectangle r) {
		hideAllComponents();
		labelX1.setVisible(true);
		labelY1.setVisible(true);
		fieldX1.setVisible(true);
		fieldY1.setVisible(true);
		labelWidth.setVisible(true);
		labelHeight.setVisible(true);
		fieldWidth.setVisible(true);
		fieldHeight.setVisible(true);
		fieldX1.setText(Integer.toString(r.getUpperLeft().getX()));
		fieldY1.setText(Integer.toString(r.getUpperLeft().getY()));
		fieldWidth.setText(Integer.toString(r.getWidth()));
		fieldHeight.setText(Integer.toString(r.getHeight()));
		labelClass.setText("Rectangle");
	}
	
	public void setValueCircleAtPaint(Circle c) {
		hideAllComponents();
		labelX1.setVisible(true);
		labelY1.setVisible(true);
		fieldX1.setVisible(true);
		fieldY1.setVisible(true);
		fieldX1.setText(Integer.toString(c.getCenter().getX()));
		fieldY1.setText(Integer.toString(c.getCenter().getY()));
		labelRadius.setVisible(true);
		fieldRadius.setText(Integer.toString(c.getRadius()));
		fieldRadius.setVisible(true);
		labelClass.setText("Circle");
	}
	
	public void setValueDonutAtPaint(Donut d) {
		hideAllComponents();
		labelX1.setVisible(true);
		labelY1.setVisible(true);
		fieldX1.setVisible(true);
		fieldY1.setVisible(true);
		fieldX1.setText(Integer.toString(d.getCenter().getX()));
		fieldY1.setText(Integer.toString(d.getCenter().getY()));
		labelRadius.setVisible(true);
		labelInner.setVisible(true);
		fieldRadius.setVisible(true);
		fieldInner.setVisible(true);
		fieldRadius.setText(Integer.toString(d.getRadius()));
		fieldInner.setText(Integer.toString(d.getInnerRadius()));
		labelClass.setText("Donut");
	}
	
	public void hideAllComponents() {
		labelX1.setVisible(false);
		labelX2.setVisible(false);
		labelY1.setVisible(false);
		labelY2.setVisible(false);
		labelWidth.setVisible(false);
		labelHeight.setVisible(false);
		labelRadius.setVisible(false);
		labelInner.setVisible(false);
		fieldX1.setVisible(false);
		fieldX2.setVisible(false);
		fieldY1.setVisible(false);
		fieldY2.setVisible(false);
		fieldHeight.setVisible(false);
		fieldWidth.setVisible(false);
		fieldRadius.setVisible(false);
		fieldInner.setVisible(false);
	}
	
	
	
	private void whichShape(Shape shape) {
		if(shape instanceof Point) {
			p = (Point) shape;
			setStatus(Status.Point);
		} else if (shape instanceof Line) {
			l = (Line) shape;
			setStatus(Status.Line);
		} else if (shape instanceof Donut) {
			d = (Donut) shape;
			setStatus(Status.Donut);
		} else if (shape instanceof Rectangle) {
			r = (Rectangle) shape;
			setStatus(Status.Rectangle);
		} else if (shape instanceof Circle) {
			c = (Circle) shape;
			setStatus(Status.Circle);
		} 
	}
	
	public void whichShapeToPopulate(Shape shape) {
		whichShape(shape);
		switch (status) {
		case Point:
			setValuePointAtPaint(p);
			break;
		case Line:
			setValueLineAtPaint(l);
			break;
		case Rectangle:
			setValueRectAtPaint(r);
			break;
		case Circle:
			setValueCircleAtPaint(c);
			break;
		case Donut:
			setValueDonutAtPaint(d);
			break;
		}
		
	}
	
	public void setNewValues() throws NumberFormatException, Exception {
		whichShape(shape);
		switch (status) {
		case Point: 
			p.setX(Integer.parseInt(fieldX1.getText()));
			p.setY(Integer.parseInt(fieldY1.getText()));
			break;
		case Line:
			Point startPoint = new Point(Integer.parseInt(fieldX1.getText()), Integer.parseInt(fieldY1.getText()));
			l.setStartPoint(startPoint);
			Point endPoint = new Point(Integer.parseInt(fieldX2.getText()), Integer.parseInt(fieldY2.getText()));
			l.setEndPoint(endPoint);
			break;
		case Circle:
			Point center = new Point(Integer.parseInt(fieldX1.getText()), Integer.parseInt(fieldY1.getText()));
			c.setCenter(center);
			c.setRadius(Integer.parseInt(fieldRadius.getText()));
			break;
		case Rectangle:
			Point upperLeft = new Point(Integer.parseInt(fieldX1.getText()), Integer.parseInt(fieldY1.getText()));
			r.setUpperLeft(upperLeft);
			r.setWidth(Integer.parseInt(fieldWidth.getText()));
			r.setHeight(Integer.parseInt(fieldInner.getText()));
			break;
		case Donut:
			Point centerDonut = new Point(Integer.parseInt(fieldX1.getText()), Integer.parseInt(fieldY1.getText()));
			d.setCenter(centerDonut);
			d.setRadius(Integer.parseInt(fieldRadius.getText()));
			d.setInnerRadius(Integer.parseInt(fieldInner.getText()));
			break;
		}
	
	}
	
	private void setStatus(Status status) {
		this.status = status;
		
	}
}
