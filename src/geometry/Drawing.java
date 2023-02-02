package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1200,1000);
		Drawing drawing = new Drawing();
		frame.add(drawing);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
	
	public void paint(Graphics g) {
		Point p1 = new Point(100,100);
		Point p2 = new Point(200,200);
		Line l1 = new Line(p1,p2);
		Rectangle r1 = new Rectangle(p1,50,50);
		Circle c1 = new Circle(p2,30);
		Donut d1 = new Donut(p1,30,15);
		
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		shapes.add(p1);
		shapes.add(l1);
		shapes.add(r1);
		shapes.add(c1);
		shapes.add(d1);
		
		/*for(Shape s: shapes) {
			s.draw(g);
		}*/
		
		for(Shape s: shapes) {
			s.moveBy(10, 0);
			s.draw(g);
		}
		
	}
	
	public Drawing() {
		
	}

}
