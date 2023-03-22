package DrawingApp;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JColorChooser;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class EditPanel extends JFrame{
	
	private JPanel contentPane;
	private JFrame editFrame;
	private JLabel labelX;
	private JLabel labelY;
	private JLabel labelWidth;
	private JLabel labelHeight;
	private JLabel labelRadius;
	private JLabel labelInner;
	private JLabel labelX2;
	private JLabel labelY2;
	private JLabel labelClassName;
	private JButton exitButton;
	private JButton editButton;
	private JFrame f = new JFrame();
	private JButton deleteButton;
	private String name;
	private Shape shape;
	
	
	public EditPanel(Shape selectedShape, PnlDrawing pnlDrawing) {
		shape = selectedShape;
		
		name =  selectedShape.getClass().getSimpleName();
		editFrame = new JFrame();
		editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editFrame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		editFrame.setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		labelClassName = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelClassName, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelClassName, 160, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, labelClassName, 33, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelClassName, -175, SpringLayout.EAST, contentPane);
		contentPane.add(labelClassName);
		
		labelX = new JLabel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelX, 54, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelX, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, labelX, 68, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelX, 70, SpringLayout.WEST, contentPane);
		contentPane.add(labelX);
		
		labelY = new JLabel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelY, 6, SpringLayout.SOUTH, labelX);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelY, 0, SpringLayout.WEST, labelX);
		contentPane.add(labelY);
		
		labelWidth = new JLabel("width:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelWidth, 6, SpringLayout.SOUTH, labelY);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelWidth, 0, SpringLayout.WEST, labelX);
		contentPane.add(labelWidth);
		
		labelHeight = new JLabel("height:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelHeight, 6, SpringLayout.SOUTH, labelWidth);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelHeight, 0, SpringLayout.WEST, labelX);
		contentPane.add(labelHeight);
		
		labelRadius = new JLabel("radius:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelRadius, 6, SpringLayout.SOUTH, labelHeight);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelRadius, 0, SpringLayout.WEST, labelX);
		contentPane.add(labelRadius);
		
		labelInner = new JLabel("inner r:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelInner, 6, SpringLayout.SOUTH, labelRadius);
		sl_contentPane.putConstraint(SpringLayout.EAST, labelInner, 0, SpringLayout.EAST, labelHeight);
		contentPane.add(labelInner);
		
		labelX2 = new JLabel("x2:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelX2, 0, SpringLayout.NORTH, labelX);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelX2, 65, SpringLayout.EAST, labelX);
		contentPane.add(labelX2);
		
		labelY2 = new JLabel("y2:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, labelY2, 0, SpringLayout.NORTH, labelY);
		sl_contentPane.putConstraint(SpringLayout.WEST, labelY2, 0, SpringLayout.WEST, labelX2);
		contentPane.add(labelY2);
		
		editButton = new JButton("Edit");
		sl_contentPane.putConstraint(SpringLayout.NORTH, editButton, 5, SpringLayout.NORTH, labelX);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, editButton, 0, SpringLayout.SOUTH, labelY);
		sl_contentPane.putConstraint(SpringLayout.EAST, editButton, -26, SpringLayout.EAST, contentPane);
		contentPane.add(editButton);
		
		exitButton = new JButton("Exit");
		sl_contentPane.putConstraint(SpringLayout.NORTH, exitButton, -4, SpringLayout.NORTH, labelHeight);
		sl_contentPane.putConstraint(SpringLayout.WEST, exitButton, 0, SpringLayout.WEST, editButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, exitButton, -26, SpringLayout.EAST, contentPane);
		contentPane.add(exitButton);
		
		deleteButton = new JButton("Delete");
		sl_contentPane.putConstraint(SpringLayout.NORTH, deleteButton, -4, SpringLayout.NORTH, labelInner);
		sl_contentPane.putConstraint(SpringLayout.WEST, deleteButton, 0, SpringLayout.WEST, editButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, deleteButton, 20, SpringLayout.EAST, editButton);
		contentPane.add(deleteButton);
		
		editFrame.setVisible(true);
		
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editFrame.repaint();
				editFrame.dispose();
				
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (name) {
				case "Point":
					int x = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter x:"));
					int y = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter y:")); 
//					Color newPointColor = pnlDrawing.popUpColorChooser();
					Point p = (Point) shape;
//					p.setEdgecolor(newPointColor);
					p.setX(x);
					p.setY(y);
					
					break;
				case "Line":
					int x1 = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter x of first point:"));
					int y1 = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter y of first point:")); 
					int x2 = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter x of second point:"));
					int y2 = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter y of second point:")); 
					
					if(x1 == 0 || y2 == 0) {
						break;
					}
					Line l = (Line) shape;
//					Color newLineColor = pnlDrawing.popUpColorChooser();
					Point tempStart = l.getStartPoint();
					tempStart.setX(x1);
					tempStart.setY(y1);
					Point tempEnd = l.getEndPoint();
					tempEnd.setX(x2);
					tempEnd.setY(y2);
//					l.setEdgecolor(newLineColor);
					l.setStartPoint(tempStart);
					l.setEndPoint(tempEnd);
					
					break;
				case "Circle":
					try {
						int circleX = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter x of center:"));
						int circleY = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter y of center:"));
						int circleR = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter radius:"));
//						Color newCircleEdgeColor = pnlDrawing.popUpColorChooser();
//						Color newCircleFillcolor = pnlDrawing.popUpColorChooser();
						Circle c = (Circle) shape;
						Point centerP = c.getCenter();
//						c.setEdgecolor(newCircleEdgeColor);
//						c.setFillColor(newCircleFillcolor);
						centerP.setX(circleX);
						centerP.setY(circleY);
						try {
							c.setRadius(circleR) ;
						} catch (Exception e1) {
							System.out.println("mora biti broj veci od 1");
						}	
					} catch (Exception e2) {
						System.out.println("Cancel button or wrong number input!");
					}
					
					break;
				case "Rectangle":
					try {
						int rectX = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter x of upper left:"));
						int rectY = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter y of upper left:"));
						int rectWidth = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter width:"));
						int rectHeight = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter height:"));
						Rectangle r = (Rectangle) shape;
//						Color newRectEdgeColor = pnlDrawing.popUpColorChooser();
//						Color newRectFillcolor = pnlDrawing.popUpColorChooser();
						Point upperLeft = r.getUpperLeft();
//						r.setEdgecolor(newRectEdgeColor);
//						r.setEdgecolor(newRectFillcolor);
						upperLeft.setX(rectX);
						upperLeft.setY(rectY);
						r.setWidth(rectWidth);
						r.setHeight(rectHeight);
					} catch (Exception e2) {
						System.out.println("Cancel button or wrong number input!");
					}
				break;
				case "Donut":
					try {
						int donutX = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter x of center:"));
						int donutY = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter y of center:"));
						int donutInner = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter inner radius:"));
						int donutR = Integer.parseInt( JOptionPane.showInputDialog(f,"Enter radius:"));
						Donut d = (Donut) shape;
						Point centerDonut = d.getCenter();
//						Color newDonutColor = pnlDrawing.popUpColorChooser();
						centerDonut.setX(donutX);
						centerDonut.setY(donutY);
//						d.setEdgecolor(newDonutColor);
						try {
							d.setRadius(donutR);
						} catch (Exception e1) {
							System.out.println("mora biti broj veci od 1");
						}
						d.setInnerRadius(donutInner);
					} catch (Exception e2) {
						System.out.println("Cancel button or wrong number input!");
					}
					break;
				}
				
				pnlDrawing.repaint();
				
			}
		});
		
		
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 int result = JOptionPane.showConfirmDialog(f,"Wanna delete object?", "Notifaction",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){
			            	pnlDrawing.removeObject(selectedShape);
							pnlDrawing.repaint();
							editFrame.dispose();
			            }else if (result == JOptionPane.NO_OPTION){
			            	
							editFrame.dispose();
			            }
				
			}
		});
		
		switch (name) {
		case "Point":
			setValuesforPoint();		
			break;
		case "Line":
			setValuesForLine();
			break;
		case "Rectangle":
			setValuesForRect();
			break;
		case "Circle":
			setValuesForCircle();
			break;
		case "Donut":
			setValuesForDonut();
			break;
		default:
			break;
		}
		
		
	}
	
	private void setValuesforPoint() {
		Point p = (Point) shape;
		labelClassName.setText(name);
		String textX = Integer.toString(p.getX());
		String textY = Integer.toString(p.getY());
		labelX.setText("x:" + textX);
		labelY.setText("y:" + textY);
		labelWidth.setVisible(false);
		labelHeight.setVisible(false);
		labelX2.setVisible(false);
		labelY2.setVisible(false);
		labelRadius.setVisible(false);
		labelInner.setVisible(false);
	}
	
	private void setValuesForLine() {
		Line l = (Line) shape;
		labelClassName.setText(name);
		Point startPoint = l.getStartPoint();
		Point endPoint = l.getEndPoint();
		String textX = Integer.toString(startPoint.getX());
		String textY = Integer.toString(startPoint.getY());
		String textX2 = Integer.toString(endPoint.getX());
		String textY2 = Integer.toString(endPoint.getY());
		labelX.setText("x:" + textX);
		labelY.setText("y:" + textY);
		labelX2.setText("x:" + textX2);
		labelY2.setText("y:" + textY2);
		labelWidth.setVisible(false);
		labelHeight.setVisible(false);
		labelRadius.setVisible(false);
		labelInner.setVisible(false);
	}
	
	private void setValuesForRect() {
		Rectangle r = (Rectangle) shape;
		labelClassName.setText(name);
		Point p = r.getUpperLeft();
		String textX = Integer.toString(p.getX());
		String textY = Integer.toString(p.getY());
		labelX.setText("x:" + textX);
		labelY.setText("y:" + textY);
		String textWidth = Integer.toString(r.getWidth());
		String textHeight = Integer.toString(r.getHeight());
		labelWidth.setText("width:"  + textWidth);
		labelHeight.setText("height:" + textHeight);
		labelX2.setVisible(false);
		labelY2.setVisible(false);
		labelRadius.setVisible(false);
		labelInner.setVisible(false);
	}
	
	private void setValuesForCircle() {
		Circle c = (Circle) shape;
		labelClassName.setText(name);
		Point p = c.getCenter();
		String textX = Integer.toString(p.getX());
		String textY = Integer.toString(p.getY());
		labelX.setText("x:" + textX);
		labelY.setText("y:" + textY);
		String textRadius = Integer.toString(c.getRadius());
		labelRadius.setText("radius:" + textRadius);
		labelWidth.setVisible(false);
		labelHeight.setVisible(false);
		labelX2.setVisible(false);
		labelY2.setVisible(false);
		labelInner.setVisible(false);
	}
	
	private void setValuesForDonut() {
		Donut d = (Donut) shape;
		labelClassName.setText(name);
		Point p = d.getCenter();
		String textX = Integer.toString(p.getX());
		String textY = Integer.toString(p.getY());
		labelX.setText("x:" + textX);
		labelY.setText("y:" + textY);
		String textRadius = Integer.toString(d.getRadius());
		labelRadius.setText("radius:" + textRadius);
		String textInner = Integer.toString(d.getInnerRadius());
		labelInner.setText("inner:" + textInner);
		labelWidth.setVisible(false);
		labelHeight.setVisible(false);
		labelX2.setVisible(false);
		labelY2.setVisible(false);
	}
	
	
}
