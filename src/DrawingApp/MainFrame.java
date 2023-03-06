package DrawingApp;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.PageAttributes.OriginType;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import javax.imageio.ImageIO;
import javax.lang.model.util.Elements.Origin;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.filechooser.FileNameExtensionFilter;

import DrawingApp.PnlDrawing;
import SQLConnection.Mapping;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.SystemColor;
	
public class MainFrame {
	
	private static MainFrame mainFrame;
	private JFrame mainFrm;
	private PnlDrawing pnlDrawing;
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenuItem newMenuItem;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem;
	private JMenu saveAsMenuItem;
	private JPanel bottomArea;
	private ShapeDetailsPanel detailsPanel;
	private JButton buttonSelect;
	private JMenuItem printMenuItem;
	private JMenuItem saveToMySQLMenuItem;
	
	
	public static MainFrame Instance() {
		if (mainFrame == null) {
			mainFrame = new MainFrame();
		}
		return mainFrame;
	}
	
	
	private MainFrame() {
		
		mainFrm = new JFrame();
		mainFrm.setBounds(100, 100, 950, 549);
		mainFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		mainFrm.getContentPane().setLayout(springLayout);
		
		
		pnlDrawing = new PnlDrawing();
		springLayout.putConstraint(SpringLayout.SOUTH, pnlDrawing, -124, SpringLayout.SOUTH, mainFrm.getContentPane());
		pnlDrawing.setBackground(SystemColor.activeCaption);
		springLayout.putConstraint(SpringLayout.WEST, pnlDrawing, 0, SpringLayout.WEST, mainFrm.getContentPane());
		mainFrm.getContentPane().add(pnlDrawing);
		
		pnlDrawing.setBounds(0, 0, 66, 650);
		pnlDrawing.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlDrawing.setVisible(true);
		
		
		menuBar = new JMenuBar();
		springLayout.putConstraint(SpringLayout.NORTH, pnlDrawing, 6, SpringLayout.SOUTH, menuBar);
		springLayout.putConstraint(SpringLayout.NORTH, menuBar, 0, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, menuBar, -450, SpringLayout.SOUTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, menuBar, 10, SpringLayout.WEST, mainFrm.getContentPane());
		menuBar.setPreferredSize(new Dimension(30,10));
		mainFrm.getContentPane().add(menuBar);
		
		
		JButton buttonPoint = new JButton("Point");
		springLayout.putConstraint(SpringLayout.NORTH, buttonPoint, 10, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, buttonPoint, -6, SpringLayout.NORTH, pnlDrawing);
		springLayout.putConstraint(SpringLayout.EAST, buttonPoint, -768, SpringLayout.EAST, mainFrm.getContentPane());
		buttonPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setStatus(Status.Point);
			}
		});
		mainFrm.getContentPane().add(buttonPoint);
		
		JButton buttonLine = new JButton("Line");
		springLayout.putConstraint(SpringLayout.NORTH, buttonLine, 0, SpringLayout.NORTH, buttonPoint);
		springLayout.putConstraint(SpringLayout.WEST, buttonLine, 6, SpringLayout.EAST, buttonPoint);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonLine, -6, SpringLayout.NORTH, pnlDrawing);
		buttonLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setStatus(Status.Line);
			}
		});
		mainFrm.getContentPane().add(buttonLine);
		
		JButton buttonRectangle = new JButton("Rectangle");
		springLayout.putConstraint(SpringLayout.NORTH, buttonRectangle, 0, SpringLayout.NORTH, buttonPoint);
		springLayout.putConstraint(SpringLayout.WEST, buttonRectangle, 6, SpringLayout.EAST, buttonLine);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonRectangle, -6, SpringLayout.NORTH, pnlDrawing);
		springLayout.putConstraint(SpringLayout.EAST, buttonRectangle, -593, SpringLayout.EAST, mainFrm.getContentPane());
		buttonRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setStatus(Status.Rectangle);
			}
		});
		mainFrm.getContentPane().add(buttonRectangle);
		
		JButton buttonCircle = new JButton("Circle");
		springLayout.putConstraint(SpringLayout.NORTH, buttonCircle, 10, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, buttonCircle, 6, SpringLayout.EAST, buttonRectangle);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonCircle, -6, SpringLayout.NORTH, pnlDrawing);
		buttonCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setStatus(Status.Circle);
			}
		});
		mainFrm.getContentPane().add(buttonCircle);
		
		JButton buttonDonut = new JButton("Donut");
		springLayout.putConstraint(SpringLayout.NORTH, buttonDonut, 10, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, buttonDonut, 6, SpringLayout.EAST, buttonCircle);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonDonut, -6, SpringLayout.NORTH, pnlDrawing);
		buttonDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setStatus(Status.Donut);
			}
		});
		mainFrm.getContentPane().add(buttonDonut);
		
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		springLayout.putConstraint(SpringLayout.NORTH, menuFile, 0, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, menuFile, 144, SpringLayout.EAST, menuBar);
		
		newMenuItem = new JMenuItem("New");
		newMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.clearPanel();
			}
		});
		menuFile.add(newMenuItem);
		
		openMenuItem = new JMenuItem("Open");
		menuFile.add(openMenuItem);
		openMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.openWithFileChooser();
				pnlDrawing.repaintPanel();
			}
		});
		
		saveMenuItem = new JMenuItem("Save");
		menuFile.add(saveMenuItem);
		saveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.saveWithFileChooser();
			}
		});
		
		saveAsMenuItem = new JMenu("Save as >");
		menuFile.add(saveAsMenuItem);
		
		printMenuItem = new JMenuItem("Save as png");
		printMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.saveImage();
			}
		});
		menuFile.add(printMenuItem);
		
		saveToMySQLMenuItem = new JMenuItem("Save to mySQL");
		menuFile.add(saveToMySQLMenuItem);
		saveToMySQLMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Mapping mapping = new Mapping(pnlDrawing.getShapeList());
			}
		});
		
		bottomArea = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, bottomArea, 1, SpringLayout.SOUTH, pnlDrawing);
		springLayout.putConstraint(SpringLayout.SOUTH, bottomArea, 0, SpringLayout.SOUTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pnlDrawing, 0, SpringLayout.EAST, bottomArea);
		springLayout.putConstraint(SpringLayout.WEST, bottomArea, 0, SpringLayout.WEST, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, bottomArea, 0, SpringLayout.EAST, mainFrm.getContentPane());
		bottomArea.setBackground(SystemColor.activeCaption);
		mainFrm.getContentPane().add(bottomArea);
		
		
		String state = pnlDrawing.getState();
		detailsPanel = new ShapeDetailsPanel(bottomArea);
		
		
		
		JButton buttonColor = new JButton("Color");
		springLayout.putConstraint(SpringLayout.NORTH, buttonColor, 10, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, buttonColor, 99, SpringLayout.EAST, buttonDonut);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonColor, -6, SpringLayout.NORTH, pnlDrawing);
		springLayout.putConstraint(SpringLayout.EAST, buttonColor, -271, SpringLayout.EAST, mainFrm.getContentPane());
		buttonColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Color color = popUpColorChooser();
				 pnlDrawing.setSelectedColor(color); 
			}
		});
		mainFrm.getContentPane().add(buttonColor);
		
		JButton buttonFill = new JButton("Fill");
		springLayout.putConstraint(SpringLayout.NORTH, buttonFill, 10, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, buttonFill, 6, SpringLayout.EAST, buttonColor);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonFill, -6, SpringLayout.NORTH, pnlDrawing);
		mainFrm.getContentPane().add(buttonFill);
		buttonFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Color fillColor = popUpColorChooser();
				pnlDrawing.setFillColor(fillColor);
				 
			}
		});
		
		buttonSelect = new JButton("Select");
		springLayout.putConstraint(SpringLayout.NORTH, buttonSelect, 0, SpringLayout.NORTH, buttonPoint);
		springLayout.putConstraint(SpringLayout.WEST, buttonSelect, 6, SpringLayout.EAST, buttonFill);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonSelect, -6, SpringLayout.NORTH, pnlDrawing);
		springLayout.putConstraint(SpringLayout.EAST, buttonSelect, -121, SpringLayout.EAST, mainFrm.getContentPane());
		mainFrm.getContentPane().add(buttonSelect);
		buttonSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setStatus(Status.Select);
			}
		});
		
		JButton buttonDelete = new JButton("Delete");
		springLayout.putConstraint(SpringLayout.NORTH, buttonDelete, 0, SpringLayout.NORTH, buttonPoint);
		springLayout.putConstraint(SpringLayout.WEST, buttonDelete, 6, SpringLayout.EAST, buttonSelect);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonDelete, -6, SpringLayout.NORTH, pnlDrawing);
		mainFrm.getContentPane().add(buttonDelete);
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Shape shape = pnlDrawing.getSelectedShape();
				if(shape != null && pnlDrawing.getShapeList().size() > 0) {
					DeleteDialog deleteDialog = new DeleteDialog(shape, pnlDrawing.getShapeList());
					deleteDialog.setVisible(true);
				}
				
			}
		});
		
		mainFrm.setVisible(true);
		
		
	}
	
	public Color popUpColorChooser() {
			
			Color initialColor = Color.black;
			Color color = JColorChooser.showDialog(pnlDrawing, "select color", initialColor);
			return color;
		}

	public void refreshScreen() {
		mainFrm.repaint(); 
	}
	
	public ShapeDetailsPanel getDetailsPanel() {
		return detailsPanel;
	}
	
	public JPanel getBottomAreaPanel() {
		return bottomArea;
	}
}
