package SQLConnection;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DrawingApp.PnlDrawing;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class ReadFromBase {
	
	private Statement stm;
	private ArrayList<Shape> shapeList = new ArrayList<>();
	private int id;
	
	public ReadFromBase(int id) throws SQLException, ClassNotFoundException, IOException {
		
		this.id = id;
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/paint", "root", "adde22432");
	    stm = conn.createStatement(); 
		readPoints();
	    readLine();
	    readRectangle();
	    readCircleAndDonut();
	}
	
	
	private void readPoints() throws SQLException {
		String sql = "Select * From paint.point where id_workspace = "  + id;
	    ResultSet rst;
	    rst = stm.executeQuery(sql);
	   
	    while (rst.next()) {
	        Point point = new Point(rst.getInt("x"),rst.getInt("y"));
	        Shape shape = (Shape) point;
	        shapeList.add(shape);
	    }
	}
	
	private void readLine() throws SQLException, ClassNotFoundException, IOException {
		String sql = "Select * From paint.line where id_workspace = " + id;
	    ResultSet rst;
	    rst = stm.executeQuery(sql);
	   
	    while (rst.next()) {
	    	byte[] endPoint = rst.getBytes("endPoint");
	    	byte[] startPoint = rst.getBytes("startPoint");
	    	Line line = castDoublePoint(endPoint, startPoint);
	    	Shape shape = (Shape) line;
	        shapeList.add(shape);
	    }
	   
	}
	
	private void readRectangle() throws SQLException, ClassNotFoundException, IOException {
		String sql = "Select * From paint.rectangle where id_workspace = " + id;
	    ResultSet rst;
	    rst = stm.executeQuery(sql);
	   
	    while (rst.next()) {
	    	byte[] upperLeft = rst.getBytes("upperLeft");
	    	Point upperLeftPoint = castSinglePoint(upperLeft);
	        Rectangle rectangle = new Rectangle(upperLeftPoint, rst.getInt("width"), rst.getInt("height"));
	        Shape shape = (Shape) rectangle;
	        shapeList.add(shape);
	    }
	}
	
	
	private void readCircleAndDonut() throws SQLException, ClassNotFoundException, IOException {
		String sql = "Select * From paint.circle where id_workspace = " + id;
	    ResultSet rst;
	    rst = stm.executeQuery(sql);
	   
	    while (rst.next()) {
	    	byte[] center = rst.getBytes("center");
	    	Point centerDonut = castSinglePoint(center);
	    	if(rst.getInt("innerRadius") != 0) {
	        Donut donut = new Donut(centerDonut, rst.getInt("radius"), rst.getInt("innerRadius"));
	        Shape shape = (Shape) donut;
	        shapeList.add(shape);
	        } else {
	        	Circle circle = new Circle(centerDonut, rst.getInt("radius"));
	        	Shape shape = (Shape) circle;
	        	shapeList.add(shape);
	        }
	       
	    }
	}
	
	
	private Line castDoublePoint(byte[] b1, byte[] b2) throws IOException, ClassNotFoundException {
		  ByteArrayInputStream baip1 = new ByteArrayInputStream(b1);
		  ByteArrayInputStream baip2 = new ByteArrayInputStream(b2);
	      ObjectInputStream ois1 = new ObjectInputStream(baip1);
	      ObjectInputStream ois2 = new ObjectInputStream(baip2);
	      Point endPoint = (Point) ois1.readObject();
	      Point startPoint = (Point) ois2.readObject();
	      Line line = new Line(startPoint, endPoint);
	      return line;
	}
	
	private Point castSinglePoint(byte[] b1) throws IOException, ClassNotFoundException {
		  ByteArrayInputStream baip1 = new ByteArrayInputStream(b1);
	      ObjectInputStream ois1 = new ObjectInputStream(baip1);
	      Point changeablePoint = (Point) ois1.readObject();
	      return changeablePoint;
	}
	
	public ArrayList<Shape> getShapesFromDB(){
		return shapeList;
	}
	
	
}
	
	
	    



