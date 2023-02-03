package DrawingApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import DrawingApp.PnlDrawing;
import geometry.Shape;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;
	
public class MainFrame {
	
	private static MainFrame mainFrame;
	private JFrame mainFrm;
	private PnlDrawing pnlDrawing;
	private String item;
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenuItem newMenuItem;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem;
	private JMenu saveAsMenuItem;
	private JMenu printMenuItem;
	private JPanel bottomPanel;
	
	
	public static MainFrame Instance() {
		if (mainFrame == null) {
			mainFrame = new MainFrame();
		}
		return mainFrame;
	}
	
	public MainFrame() {
		
		mainFrm = new JFrame();
		mainFrm.setBounds(100, 100, 650, 467);
		mainFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		mainFrm.getContentPane().setLayout(springLayout);
		
		
		pnlDrawing = new PnlDrawing();
		springLayout.putConstraint(SpringLayout.WEST, pnlDrawing, 0, SpringLayout.WEST, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, pnlDrawing, -66, SpringLayout.SOUTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pnlDrawing, 636, SpringLayout.WEST, mainFrm.getContentPane());
		mainFrm.getContentPane().add(pnlDrawing);
		
		pnlDrawing.setBounds(0, 0, 66, 650);
		pnlDrawing.setVisible(true);
		
		

		
		
		
		menuBar = new JMenuBar();
		springLayout.putConstraint(SpringLayout.NORTH, pnlDrawing, 6, SpringLayout.SOUTH, menuBar);
		springLayout.putConstraint(SpringLayout.NORTH, menuBar, 0, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, menuBar, -374, SpringLayout.SOUTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, menuBar, 10, SpringLayout.WEST, mainFrm.getContentPane());
		mainFrm.getContentPane().add(menuBar);
		
		
		
		JButton buttonPoint = new JButton("Point");
		springLayout.putConstraint(SpringLayout.NORTH, buttonPoint, 0, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, buttonPoint, -6, SpringLayout.NORTH, pnlDrawing);
		buttonPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setState("Point");
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, buttonPoint, 59, SpringLayout.EAST, menuBar);
		mainFrm.getContentPane().add(buttonPoint);
		
		JButton buttonLine = new JButton("Line");
		springLayout.putConstraint(SpringLayout.NORTH, buttonLine, 0, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, buttonLine, -6, SpringLayout.NORTH, pnlDrawing);
		buttonLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setState("Line");
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, buttonLine, 6, SpringLayout.EAST, buttonPoint);
		mainFrm.getContentPane().add(buttonLine);
		
		JButton buttonRectangle = new JButton("Rectangle");
		springLayout.putConstraint(SpringLayout.NORTH, buttonRectangle, 0, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, buttonRectangle, -6, SpringLayout.NORTH, pnlDrawing);
		buttonRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setState("Rectangle");
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, buttonRectangle, 6, SpringLayout.EAST, buttonLine);
		mainFrm.getContentPane().add(buttonRectangle);
		
		JButton buttonCircle = new JButton("Circle");
		springLayout.putConstraint(SpringLayout.NORTH, buttonCircle, 0, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, buttonCircle, -6, SpringLayout.NORTH, pnlDrawing);
		buttonCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setState("Circle");
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, buttonCircle, 6, SpringLayout.EAST, buttonRectangle);
		mainFrm.getContentPane().add(buttonCircle);
		
		JButton buttonDonut = new JButton("Donut");
		springLayout.putConstraint(SpringLayout.NORTH, buttonDonut, 0, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, buttonDonut, -6, SpringLayout.NORTH, pnlDrawing);
		buttonDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setState("Donut");
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, buttonDonut, 6, SpringLayout.EAST, buttonCircle);
		mainFrm.getContentPane().add(buttonDonut);
		
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		springLayout.putConstraint(SpringLayout.NORTH, menuFile, 0, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, menuFile, 144, SpringLayout.EAST, menuBar);
		
		newMenuItem = new JMenuItem("New");
		menuFile.add(newMenuItem);
		
		openMenuItem = new JMenuItem("Open");
		menuFile.add(openMenuItem);
		
		saveMenuItem = new JMenuItem("Save");
		menuFile.add(saveMenuItem);
		
		saveAsMenuItem = new JMenu("Save as >");
		menuFile.add(saveAsMenuItem);
		
		printMenuItem = new JMenu("Print >");
		menuFile.add(printMenuItem);
		
		bottomPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, bottomPanel, 6, SpringLayout.SOUTH, pnlDrawing);
		springLayout.putConstraint(SpringLayout.WEST, bottomPanel, 0, SpringLayout.WEST, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, bottomPanel, -10, SpringLayout.SOUTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, bottomPanel, 636, SpringLayout.WEST, mainFrm.getContentPane());
		mainFrm.getContentPane().add(bottomPanel);
		
		BottomPanel instancePanel = new BottomPanel(bottomPanel);
		mainFrm.setVisible(true);
		
		
	}
	
	public void refreshScreen() {
		mainFrm.repaint(); 
	}
}
