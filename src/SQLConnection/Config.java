package SQLConnection;

import java.util.ArrayList;

import org.hibernate.cfg.Configuration;

import DrawingApp.PnlDrawing;
import DrawingApp.Status;
import Login.User;
import Login.Workspace;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class Config {
	
	private Configuration cfg;
	
	public Config() {
		cfg = new Configuration().configure();
		cfg.addAnnotatedClass(Point.class);
		cfg.addAnnotatedClass(Line.class);
		cfg.addAnnotatedClass(Rectangle.class);
		cfg.addAnnotatedClass(Circle.class);
		cfg.addAnnotatedClass(Donut.class);
		cfg.addAnnotatedClass(Workspace.class);
		cfg.addAnnotatedClass(User.class);
		
		
	}
	
	public Configuration getCfg() {
		return cfg;
	}
	
	
	
	
}
