package SQLConnection;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;

import DrawingApp.MainFrame;
import DrawingApp.PnlDrawing;
import Login.LoadWorkspace;
import Login.MainDialog;
import Login.User;
import Login.Workspace;




public class SaveToBase {
		
		private ArrayList<Shape> shapes = new ArrayList<>();
		private Session session;
		private Workspace workspace;
		
		
		public SaveToBase(ArrayList<Shape> shapes, Workspace workspace) {
		
		this.workspace = workspace;
		Config cfg = new Config();
		Configuration configuration = cfg.getCfg();
		SessionFactory sf = configuration.buildSessionFactory();
		session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		
			
		for (Shape shape : shapes) {
			if(shape instanceof Point) {
				Point castedPoint = (Point) shape;
				savePoint(castedPoint);
			} else if (shape instanceof Line) {
				Line l = (Line) shape;
				saveLine(l);
			} else if (shape instanceof Donut) {
				Donut d  = (Donut) shape;
				saveDonut(d);
			} else if (shape instanceof Rectangle) {
				Rectangle r = (Rectangle) shape;
				saveRectangle(r);
			} else if (shape instanceof Circle) {
				Circle c = (Circle) shape;
				saveCircle(c);
			} 
		}
		
		
		
		tx.commit();
		}
		
		private void saveCircle(Circle c) {
			Point center = c.getCenter();
			int radius = c.getRadius();
			Circle circle = new Circle(center, radius, workspace);
			session.save(circle);
		}
		
		private void saveRectangle(Rectangle r) {
			Point upperleft = r.getUpperLeft();
			int width = r.getWidth();
			int height = r.getHeight();
			Rectangle rectangle = new Rectangle(upperleft, width, height, workspace);
			session.save(rectangle);				
		}
		
		private void saveDonut(Donut d) {
			Point center = d.getCenter();
			int radius = d.getRadius();
			int innerRadius = d.getInnerRadius();
			Donut donut = new Donut(center, radius, innerRadius, workspace);
			session.save(donut);
		}
		
		private void saveLine(Line l) {
			Point startPoint = l.getStartPoint();
			Point endPoint = l.getEndPoint();
			Line line = new Line(startPoint,endPoint,workspace);
			session.save(line);
		}
		
		private void savePoint(Point castedPoint) {
			int x = castedPoint.getX();
			int y = castedPoint.getY();
			Point p = new Point(x,y,workspace);
			session.save(p);
		}
}
