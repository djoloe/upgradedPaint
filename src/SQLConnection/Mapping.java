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
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import DrawingApp.PnlDrawing;

public class Mapping {
		
		private ArrayList<Shape> shapes = new ArrayList<>();
		private Session session;
		
		public Mapping(ArrayList<Shape> shapes) {
			
		
		
		Config cfg = new Config();
		Configuration configuration = cfg.getCfg();
		SessionFactory sf = configuration.buildSessionFactory();
		session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		
		for (Shape shape : shapes) {
			if(shape instanceof Point) {
				Point p = (Point) shape;
				session.save(p);
			} else if (shape instanceof Line) {
				Line l = (Line) shape;
				session.save(l);
			} else if (shape instanceof Donut) {
				Donut d  = (Donut) shape;
				session.save(d);
			} else if (shape instanceof Rectangle) {
				Rectangle r = (Rectangle) shape;
				session.save(r);
			} else if (shape instanceof Circle) {
				Circle c = (Circle) shape;
				session.save(c);
			} 
		}
		
		tx.commit();
		
		}
		
		
}
