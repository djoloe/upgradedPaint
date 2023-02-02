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

import geometry.Shape;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
	
public class MainFrame {
	
	private static MainFrame mainFrame;
	private JFrame mainFrm;
	private String[] choices = { "New","Open", "Save","Save as >", "Print > "};
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
	private JLabel labelState;
	private JLabel labelSelection;
	private JLabel labelXState;
	private JLabel labelYState;
	private JLabel labelRadiusState;
	
	public static MainFrame Instance() {
		if (mainFrame == null) {
			mainFrame = new MainFrame();
		}
		return mainFrame;
	}
	
	private MainFrame() {
		
		mainFrm = new JFrame();
		mainFrm.setBounds(100, 100, 650, 450);
		mainFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		mainFrm.getContentPane().setLayout(springLayout);
		
		
		
		
		pnlDrawing = new PnlDrawing();
		springLayout.putConstraint(SpringLayout.NORTH, pnlDrawing, 45, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, pnlDrawing, 0, SpringLayout.WEST, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, pnlDrawing, -22, SpringLayout.SOUTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pnlDrawing, 636, SpringLayout.WEST, mainFrm.getContentPane());
		mainFrm.getContentPane().add(pnlDrawing);
		
		
		
		pnlDrawing.setBounds(0, 0, 66, 650);
		pnlDrawing.setVisible(true);
		pnlDrawing.setState(choices[0]);
		
		menuBar = new JMenuBar();
		springLayout.putConstraint(SpringLayout.NORTH, menuBar, 0, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, menuBar, 10, SpringLayout.WEST, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, menuBar, -6, SpringLayout.NORTH, pnlDrawing);
		mainFrm.getContentPane().add(menuBar);
		
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
		
		labelState = new JLabel("State:");
		springLayout.putConstraint(SpringLayout.NORTH, labelState, 6, SpringLayout.SOUTH, pnlDrawing);
		springLayout.putConstraint(SpringLayout.WEST, labelState, 0, SpringLayout.WEST, pnlDrawing);
		mainFrm.getContentPane().add(labelState);
		
		labelSelection = new JLabel("");
		springLayout.putConstraint(SpringLayout.WEST, labelSelection, 6, SpringLayout.EAST, labelState);
		springLayout.putConstraint(SpringLayout.SOUTH, labelSelection, 0, SpringLayout.SOUTH, labelState);
		mainFrm.getContentPane().add(labelSelection);
		
		labelXState = new JLabel("X:");
		springLayout.putConstraint(SpringLayout.WEST, labelXState, 72, SpringLayout.EAST, labelSelection);
		springLayout.putConstraint(SpringLayout.SOUTH, labelXState, 0, SpringLayout.SOUTH, labelState);
		mainFrm.getContentPane().add(labelXState);
		
		labelYState = new JLabel("Y:");
		springLayout.putConstraint(SpringLayout.WEST, labelYState, 57, SpringLayout.EAST, labelXState);
		springLayout.putConstraint(SpringLayout.SOUTH, labelYState, 0, SpringLayout.SOUTH, labelState);
		mainFrm.getContentPane().add(labelYState);
		
		labelRadiusState = new JLabel("Radius:");
		springLayout.putConstraint(SpringLayout.WEST, labelRadiusState, 47, SpringLayout.EAST, labelYState);
		springLayout.putConstraint(SpringLayout.SOUTH, labelRadiusState, 0, SpringLayout.SOUTH, labelState);
		mainFrm.getContentPane().add(labelRadiusState);
		
<<<<<<< Updated upstream
		JMenuItem newMenuItem = new JMenuItem("New");
		mnNewMenu.add(newMenuItem);
		
		JMenuItem openMenuItem = new JMenuItem("Open");
		mnNewMenu.add(openMenuItem);
		
		JMenuItem saveMenuItem = new JMenuItem("Save");
		mnNewMenu.add(saveMenuItem);
		
		JMenu saveAsMenuItem = new JMenu("Save as >");
		mnNewMenu.add(saveAsMenuItem);
		
		JMenu printMenuItem = new JMenu("Print >");
		mnNewMenu.add(printMenuItem);
=======
		JButton buttonPoint = new JButton("Point");
		buttonPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setState("Point");
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, buttonPoint, 0, SpringLayout.NORTH, menuBar);
		springLayout.putConstraint(SpringLayout.WEST, buttonPoint, 59, SpringLayout.EAST, menuBar);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonPoint, -6, SpringLayout.NORTH, pnlDrawing);
		mainFrm.getContentPane().add(buttonPoint);
		
		JButton buttonLine = new JButton("Line");
		buttonLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setState("Line");
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, buttonLine, 0, SpringLayout.NORTH, menuBar);
		springLayout.putConstraint(SpringLayout.WEST, buttonLine, 6, SpringLayout.EAST, buttonPoint);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonLine, -6, SpringLayout.NORTH, pnlDrawing);
		mainFrm.getContentPane().add(buttonLine);
		
		JButton buttonRectangle = new JButton("Rectangle");
		buttonRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setState("Rectangle");
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, buttonRectangle, 0, SpringLayout.NORTH, menuBar);
		springLayout.putConstraint(SpringLayout.WEST, buttonRectangle, 6, SpringLayout.EAST, buttonLine);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonRectangle, -6, SpringLayout.NORTH, pnlDrawing);
		mainFrm.getContentPane().add(buttonRectangle);
		
		JButton buttonCircle = new JButton("Circle");
		buttonCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setState("Circle");
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, buttonCircle, 0, SpringLayout.NORTH, menuBar);
		springLayout.putConstraint(SpringLayout.WEST, buttonCircle, 6, SpringLayout.EAST, buttonRectangle);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonCircle, -6, SpringLayout.NORTH, pnlDrawing);
		mainFrm.getContentPane().add(buttonCircle);
		
		JButton buttonDonut = new JButton("Donut");
		buttonDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.setState("Donut");
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, buttonDonut, 0, SpringLayout.NORTH, menuBar);
		springLayout.putConstraint(SpringLayout.WEST, buttonDonut, 6, SpringLayout.EAST, buttonCircle);
		springLayout.putConstraint(SpringLayout.SOUTH, buttonDonut, -6, SpringLayout.NORTH, pnlDrawing);
		mainFrm.getContentPane().add(buttonDonut);
>>>>>>> Stashed changes
		mainFrm.setVisible(true);
	
		
	}
	
	public void refreshScreen() {
		mainFrm.repaint(); 
	}
}
