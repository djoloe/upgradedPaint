package DrawingApp;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

import javax.swing.JLabel;

public class BottomPanel {
	
	private static JPanel panel;
	private JLabel labelState;
	private JLabel labelX1;
	private JLabel labelX2;
	private JLabel labelY1;
	private JLabel labelY2;
	private JLabel labelWidth;
	private JLabel labelHeight;
	private JLabel labelRadius;
	private JLabel labelInner;
	private JLabel labelStateCurrent;
	private JTextField fieldX1;
	private JTextField fieldX2;
	private JTextField fieldY1;
	private JTextField fieldY2;
	private JTextField fieldHeight;
	private JTextField fieldWidth;
	private JTextField fieldRadius;
	private JTextField fieldInner;
	private Shape selectedShape;
	private String statusObject;
	
	public BottomPanel(JPanel panel){
		this.panel = panel;
		panel.setVisible(true);
		panel.setLayout(null);
		
		labelState = new JLabel("State:");
		labelState.setBounds(0, 0, 50, 30);
		panel.add(labelState);
		
		labelStateCurrent = new JLabel();
		labelStateCurrent.setBounds(40,5,70,20);
		panel.add(labelStateCurrent);
		
		labelX1 = new JLabel("X:");
		labelX1.setBounds(120,5,20,20);
		labelX1.setVisible(false);
		panel.add(labelX1);
		
		fieldX1 = new JTextField();
		fieldX1.setBounds(140, 7, 30, 20);
		fieldX1.setVisible(false);
		panel.add(fieldX1);
		
		labelX2 = new JLabel("X:");
		labelX2.setBounds(120, 30, 20, 20);
		labelX2.setVisible(false);
		panel.add(labelX2);
		
		fieldX2 = new JTextField();
		fieldX2.setBounds(140, 30, 30, 20);
		fieldX2.setVisible(false);
		panel.add(fieldX2);
		
		labelY1 = new JLabel("Y:");
		labelY1.setBounds(180,5,20,20);
		labelY1.setVisible(false);
		panel.add(labelY1);
		
		fieldY1 = new JTextField();
		fieldY1.setBounds(200, 7, 30, 20);
		fieldY1.setVisible(false);
		panel.add(fieldY1);
		
		labelY2 = new JLabel("Y:");
		labelY2.setBounds(180, 30, 20, 20);
		labelY2.setVisible(false);
		panel.add(labelY2);
		
		fieldY2 = new JTextField();
		fieldY2.setBounds(200, 30, 30, 20);
		fieldY2.setVisible(false);
		panel.add(fieldY2);
	
		labelWidth = new JLabel("Width:");
		labelWidth.setBounds(240, 7, 40, 20);
		labelWidth.setVisible(false);
		panel.add(labelWidth);
		
		fieldWidth = new JTextField();
		fieldWidth.setBounds(290, 7, 30, 20);
		fieldWidth.setVisible(false);
		panel.add(fieldWidth);
		
		labelHeight = new JLabel("Height:");
		labelHeight.setBounds(240, 30, 45, 20);
		labelHeight.setVisible(false);
		panel.add(labelHeight);
		
		fieldHeight = new JTextField();
		fieldHeight.setBounds(290, 30, 30, 20);
		fieldHeight.setVisible(false);
		panel.add(fieldHeight);
		
		labelRadius = new JLabel("Radius:");
		labelRadius.setBounds(330, 5, 45, 20);
		labelRadius.setVisible(false);
		panel.add(labelRadius);
		
		fieldRadius = new JTextField();
		fieldRadius.setBounds(380, 5, 30, 20);
		fieldRadius.setVisible(false);
		panel.add(fieldRadius);
		
		labelInner = new JLabel("Inner:");
		labelInner.setBounds(330, 30, 40, 20);
		labelInner.setVisible(false);
		panel.add(labelInner);
		
		fieldInner = new JTextField();
		fieldInner.setBounds(380, 30, 30, 20);
		fieldInner.setVisible(false);
		panel.add(fieldInner);
		
		
	}
	
	
	
	
	public void setStatusName(String statusObject) {
		this.statusObject = statusObject;
	}
	
	public void setShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}
	
	
	
	
	
	
	public void setValuePointAtPaint(Point p) {
		hideAllComponents();
		labelX1.setVisible(true);
		labelY1.setVisible(true);
		fieldX1.setVisible(true);
		fieldY1.setVisible(true);
		fieldX1.setText(Integer.toString(p.getX()));
		fieldY1.setText(Integer.toString(p.getY()));
		labelStateCurrent.setText("Point");
		
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
		labelStateCurrent.setText("Line");
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
		labelStateCurrent.setText("Rectangle");
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
		labelStateCurrent.setText("Circle");
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
		labelStateCurrent.setText("Donut");
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
	
	
	
}

