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
	
public class MainFrame {
	
	private static MainFrame mainFrame;
	private JFrame mainFrm;
	private String[] choices = { "New","Open", "Save","Save as >", "Print > "};
	private PnlDrawing pnlDrawing;
	private String item;
	private JPanel panel;
	private DefaultListCellRenderer comboBoxRenderer;
	
	
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
		comboBoxRenderer = new DefaultListCellRenderer();
		comboBoxRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		
		
		
		pnlDrawing = new PnlDrawing();
		springLayout.putConstraint(SpringLayout.NORTH, pnlDrawing, 45, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, pnlDrawing, 0, SpringLayout.WEST, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, pnlDrawing, 0, SpringLayout.SOUTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pnlDrawing, 636, SpringLayout.WEST, mainFrm.getContentPane());
		mainFrm.getContentPane().add(pnlDrawing);
		
		
		
		pnlDrawing.setBounds(0, 0, 66, 650);
		pnlDrawing.setVisible(true);
		pnlDrawing.setState(choices[0]);
		
		JMenuBar menuBar = new JMenuBar();
		springLayout.putConstraint(SpringLayout.NORTH, menuBar, 0, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, menuBar, 10, SpringLayout.WEST, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, menuBar, -6, SpringLayout.NORTH, pnlDrawing);
		mainFrm.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		springLayout.putConstraint(SpringLayout.NORTH, mnNewMenu, 0, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, mnNewMenu, 144, SpringLayout.EAST, menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Open");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("Save as >");
		mnNewMenu.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Print >");
		mnNewMenu.add(mnNewMenu_2);
		mainFrm.setVisible(true);
	
		
	}
	
	public void refreshScreen() {
		mainFrm.repaint(); 
	}
}
