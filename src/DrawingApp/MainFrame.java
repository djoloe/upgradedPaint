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
	
public class MainFrame {
	
	private static MainFrame mainFrame;
	private JFrame mainFrm;
	private String[] choices = { "Point","Line", "Circle","Rectangle", "Donut", "Select"};
	private JComboBox<String> comboBox;
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
		
		comboBox = new JComboBox<String>(choices);
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 0, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 0, SpringLayout.WEST, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, 31, SpringLayout.NORTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 200, SpringLayout.WEST, mainFrm.getContentPane());
		comboBoxRenderer = new DefaultListCellRenderer();
		comboBoxRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		comboBox.setRenderer(comboBoxRenderer);
		mainFrm.getContentPane().add(comboBox);
		
		
		
		pnlDrawing = new PnlDrawing();
		springLayout.putConstraint(SpringLayout.NORTH, pnlDrawing, 0, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, pnlDrawing, 0, SpringLayout.WEST, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, pnlDrawing, 66, SpringLayout.SOUTH, mainFrm.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pnlDrawing, 650, SpringLayout.WEST, mainFrm.getContentPane());
		mainFrm.getContentPane().add(pnlDrawing);
		
		
		
		pnlDrawing.setBounds(0, 0, 66, 650);
		pnlDrawing.setVisible(true);
		pnlDrawing.setState(choices[0]);
		mainFrm.setVisible(true);
		
		
		comboBox.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	if (e.getActionCommand() == "comboBoxChanged") {
	        		item = (String) comboBox.getSelectedItem();
	        		pnlDrawing.setState(item);
	        	}
	        }
	    });
	
		
	}
	
	public void refreshScreen() {
		mainFrm.repaint(); 
	}
	
}
