package DrawingApp;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.text.html.HTMLDocument.Iterator;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class PnlDrawing extends JPanel{

	ArrayList<geometry.Shape> shapes = new ArrayList<geometry.Shape>();
	private String state;
	private Point p1, p2;
	private Frame f = new Frame();
	private String shapeName;
	private PnlDrawing pnlDrawing;
	private Shape selectedShape;
	
	
	public PnlDrawing() {
	
		pnlDrawing = this;
		
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(state == null) {
					System.out.println("Select object to draw!");
					return;
				}
				BottomPanel bottomPanel = BottomPanel.Instance();
				switch (state) {
				case "Select":
					selectedShape = getSelectedShape(e.getX(), e.getY());
					if (selectedShape == null) {
						break;
					}
					
					EditPanel editPanel = new EditPanel(selectedShape,pnlDrawing);
					editPanel.setSize(650, 450);
					
					break;					
				case "Point":
					Point p = new Point(e.getX(), e.getY());
					Color colorPoint = popUpColorChooser();
					p.setEdgecolor(colorPoint);	
					shapes.add(p);
					repaint();
					bottomPanel.setValuePointAtPaint(p, state);
					break;
					
				case "Line":
					if (p1 == null) {
						p1 = new Point(e.getX(), e.getY());
					} else {
						p2 = new Point(e.getX(), e.getY());
					}
					
					if (p1 != null && p2 != null) {
						Color colorLine = popUpColorChooser();
						Line l = new Line(p1, p2);
						l.setEdgecolor(colorLine);	
						shapes.add(l);
						repaint();
						bottomPanel.setValueLineAtPaint(l, state);
						clearPoints();
					}
					break;
					
				case "Circle":
					int radius;
					String textRadius;
					Point p3;
					try {
						Color circleEdgeColor = popUpColorChooser();
						Color circleFillColor = popUpColorChooser();
						p3 = new Point(e.getX(), e.getY());
						textRadius = JOptionPane.showInputDialog(f,"Enter radius","");
						if( textRadius != null || textRadius.length() > 0  ) {
							radius = Integer.parseInt(textRadius);
							Circle c = new Circle(p3,radius);
							c.setCenter(p3);
							c.setEdgecolor(circleEdgeColor);
							c.setFillColor(circleFillColor);
							shapes.add(c);
							repaint();
							bottomPanel.setValueCircleAtPaint(c, state);
							
						}
					} catch (Exception e2) {
						System.out.println("Invalid input or you pressed cancel button!");
						break;
					}
					break;
					
				case "Rectangle":
					int width = 0;
					int height = 0;
					try {
						Color colorEdgeRect = popUpColorChooser();
						Color colorFillRect = popUpColorChooser();
						Point p4 = new Point(e.getX(), e.getY());
						String textWidth = JOptionPane.showInputDialog(f,"Enter width");
						String textHeight = JOptionPane.showInputDialog(f,"Enter height");
						if(textWidth != null || textHeight != null) {
							width = Integer.parseInt(textWidth);
							height = Integer.parseInt(textHeight);
							Rectangle r = new Rectangle(p4, width, height);
							r.setEdgecolor(colorEdgeRect);
							r.setFillColor(colorFillRect);
							shapes.add(r);
							bottomPanel.setValueRectAtPaint(r, state);
							repaint();
						}
						
					} catch (Exception e2) {
						System.out.println("Invalid input or you pressed cancel button!");
						break;
					}
					break;
					
				case "Donut":
					
					Point p5 = new Point(e.getX(), e.getY());
					int inner = 0;
					int radius1 = 0;
					try {
						Color colorDonut = popUpColorChooser();
						String textInner = JOptionPane.showInputDialog(f,"Enter inner");
						String textRadius1 = JOptionPane.showInputDialog(f,"Enter radius");
						if(textInner != null || textRadius1 != null) {
						inner = Integer.parseInt(textInner);
						radius1 = Integer.parseInt(textRadius1);
						Donut d = new Donut(p5, radius1, inner);
						d.setEdgecolor(colorDonut);
						shapes.add(d);
						repaint();
						bottomPanel.setValueDonutAtPaint(d, state);
						break;
						}
						
					} catch (Exception e2) {
						System.out.println("Invalid input is 0 or you pressed cancel button!");
						break;
					}
				
					
				}
			}
			});
				
		
		
	}
	 
	public Color popUpColorChooser() {
		
		Color initialColor = Color.black;
		Color color = JColorChooser.showDialog(pnlDrawing, "select color", initialColor );
		return color;
	}

	
	public void removeObject(Shape shape) {
		shapes.remove(shape);
		for (Shape shape1 : shapes) {
			
		}
	}
	
	private Shape getSelectedShape(int x, int y) {
		Shape selectedItem = null;
		
		for (Shape shape : shapes) {
			if(shape.contains(x, y)) {
				selectedItem = shape;
			}
		}
		return selectedItem;
	}
	 
	
	
	
	 public void paintComponent(Graphics g) {
		 Graphics2D g2d = (Graphics2D) g;
		 
		 for (Shape shape : shapes) {
			 shape.draw(g);
		}
		 
		 MainFrame.Instance().refreshScreen();
		 
	 }
	 
	 public void setState(String state) {
		 clearPoints();
		 this.state = state;
	 }
	 
	 private void clearPoints() {
		 p1 = null;
		 p2 = null;
	 }
}

