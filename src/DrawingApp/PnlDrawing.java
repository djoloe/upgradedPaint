package DrawingApp;


import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.CompoundBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.html.HTMLDocument.Iterator;
import javax.swing.undo.UndoManager;
import javax.swing.Timer;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import DrawingApp.Status;

public class PnlDrawing extends JPanel{

	ArrayList<geometry.Shape> shapes = new ArrayList<geometry.Shape>();
	private String state;
	private Point p1, p2;
	private Frame f = new Frame();
	private Color selectedColor;
	private Color fillColor;
	private Shape selectedShape;
	private ShapeDetailsPanel detailsPanel;
	private MouseEvent lastEvent;
	private EditDialog editDialog;
	private Status status;
	private static int WIDTH = 800;
	private static int HEIGHT = 800;
	private ArrayList<Shape> newShapes;
	private ArrayList<Shape> redoList = new ArrayList<>();
	private Shape shapeToRedo;
	
	private Timer timer = new Timer(200, new ActionListener() {
	
	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				singleClick(lastEvent);
				timer.stop();
				
			}
		});
	
	public PnlDrawing() {
	
		
		this.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mousePressed(MouseEvent e) {
				if(status == null) {
					return;
				} 
				
		        
				detailsPanel =  MainFrame.Instance().getDetailsPanel();
				
				if (timer.isRunning()) {
					Shape shapeEdit = getSelectedItem(e.getX(), e.getY());
					if (shapeEdit != null) {
						EditDialog editDialog = new EditDialog(shapeEdit);
						editDialog.setVisible(true);	
					}
					
					timer.stop();
		        } else if(e.getButton() == MouseEvent.BUTTON1){
		        	lastEvent = e;
		            timer.restart();
		        } else if(e.getButton() == MouseEvent.BUTTON3){
		        	rightClick(e);
		        }
			}
		});
			
		
	}
	
	private void rightClick(MouseEvent e) {
		final JPopupMenu popupMenu =  new JPopupMenu();
		
		JMenuItem undo = new JMenuItem("Undo");
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				undo();
			}
		});
		popupMenu.add(undo);
		
		JMenuItem redo = new JMenuItem("Redo");
		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				redo();
			}
		});
		popupMenu.add(redo);
		
		this.setComponentPopupMenu(popupMenu);
		popupMenu.setVisible(true);
	}
	
	private void singleClick(MouseEvent e) {
		
	
		
		switch (status) {
		
		case Point:
			Point p = new Point(e.getX(), e.getY());
			p.setColor(selectedColor);
			shapes.add(p);
			repaint();
			detailsPanel.setValuePointAtPaint(p);
			break;
			
		case Line:
			if (p1 == null) {
				p1 = new Point(e.getX(), e.getY());
			} else {
				p2 = new Point(e.getX(), e.getY());
			}
			
			if (p1 != null && p2 != null) {				
				Line l = new Line(p1, p2);
				l.setColor(selectedColor);
				shapes.add(l);
				repaint();
				detailsPanel.setValueLineAtPaint(l);
				clearPoints();
			}
			break;
			
		case Circle:
			int radius;
			String textRadius;
			Point p3;
			try {
				p3 = new Point(e.getX(), e.getY());
				textRadius = JOptionPane.showInputDialog(f,"Enter radius","");
				if( textRadius != null || textRadius.length() > 0  ) {
					radius = Integer.parseInt(textRadius);
					Circle c = new Circle(p3,radius);
					c.setCenter(p3);
					c.setColor(selectedColor);
					c.setFillColor(fillColor);
					shapes.add(c);
					repaint();
					detailsPanel.setValueCircleAtPaint(c);
				}
			} catch (Exception e2) {
				break;
			}
			break;
			
		case Rectangle:
			int width = 0;
			int height = 0;
			try {
				Point p4 = new Point(e.getX(), e.getY());
				String textWidth = JOptionPane.showInputDialog(f,"Enter width");
				String textHeight = JOptionPane.showInputDialog(f,"Enter height");
				if(textWidth != null || textHeight != null) {
					width = Integer.parseInt(textWidth);
					height = Integer.parseInt(textHeight);
					Rectangle r = new Rectangle(p4, width, height);
					r.setColor(selectedColor);
					r.setFillColor(fillColor);
					shapes.add(r);
					detailsPanel.setValueRectAtPaint(r);
					repaint();
				}
				
			} catch (Exception e2) {
				break;
			}
			break;
			
		case Donut:
			
			Point p5 = new Point(e.getX(), e.getY());
			int inner = 0;
			int radius1 = 0;
			try {
				String textInner = JOptionPane.showInputDialog(f,"Enter inner");
				String textRadius1 = JOptionPane.showInputDialog(f,"Enter radius");
				if(textInner != null || textRadius1 != null) {
				inner = Integer.parseInt(textInner);
				radius1 = Integer.parseInt(textRadius1);
				Donut d = new Donut(p5, radius1, inner);
				d.setColor(selectedColor);
				shapes.add(d);
				repaint();
				detailsPanel.setValueDonutAtPaint(d);
				}
				
			} catch (Exception e2) {
				break;
			}
			break;
		case Select:
			selectedShape = getSelectedItem(e.getX(), e.getY());
			detailsPanel =  MainFrame.Instance().getDetailsPanel();
			detailsPanel.whichShapeToPopulate(selectedShape);
			
			break;
			
		}
		
	}
	
	
	public void saveWithFileChooser() {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			try {
				File file = chooser.getSelectedFile();
				FileOutputStream opf = new FileOutputStream(file);
				ObjectOutputStream oop = new ObjectOutputStream(opf);
				oop.writeObject(shapes);
				oop.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void openWithFileChooser() {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            try
            {
             File file = chooser.getSelectedFile();
        	FileInputStream fis = new FileInputStream(file);
   			ObjectInputStream ois = new ObjectInputStream(fis);
   			newShapes = (ArrayList<Shape>) ois.readObject();
            
            }
            catch(Exception io)
            {
                io.printStackTrace();
            }   
        }
	
	}
	
	public void undo() {
		if(shapes.size() > 0) {
			int index = shapes.size() - 1;
			shapeToRedo = shapes.get(index);
			addToRedoList();
			shapes.remove(index);
		}
	}

	public void redo() {
		if(redoList.size() > 0) {
		int redoIndex = redoList.size() - 1;
		shapes.add(redoList.get(redoIndex));
		redoList.remove(redoIndex);
		}
	}
	
	private void addToRedoList() {
		redoList.add(shapeToRedo);
	}
	

	public void clearPanel() {
		shapes.clear();
		this.repaint();
	}
	
	public void repaintPanel() {
		this.shapes = newShapes;
		for (Shape shape : shapes) {
		
		}
		this.repaint();
	}
	
	public void removeObject(Shape shape) {
		shapes.remove(shape);
		for (Shape shape1 : shapes) {
			
		}
	}
	
	public Shape getSelectedItem(int x, int y) {
		Shape selectedItem = null;
		
		for (Shape shape : shapes) {
			if(shape.contains(x, y)) {
				selectedItem = shape;
			}
		}
		return selectedItem;
	}
	
	
	
	public void setSelectedColor(Color color) {
		this.selectedColor = color;
	}
	
	public void setFillColor(Color color) {
		this.fillColor = color;
	}
	
	@Override
	 public void paintComponent(Graphics g) {
		 Graphics2D g2d = (Graphics2D) g;
		 
		 for (Shape shape : shapes) {
			 shape.draw(g);
		}
		 
		 MainFrame.Instance().refreshScreen();
		 
	 }
	 
	 public void setStatus(Status status) {
		 clearPoints();
		 this.status = status;
	 }
	 
	 public String getState() {
		 return state;
	 }
	 private void clearPoints() {
		 p1 = null;
		 p2 = null;
	 }
	 
	public Shape getSelectedShape() {
		return selectedShape;
	}
	
	public ArrayList<Shape> getShapeList(){
		return shapes;
	}
	
	
	
	
	
}



