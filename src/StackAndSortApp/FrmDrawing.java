package StackAndSortApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Taskbar.State;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import geometry.Circle;
import geometry.Point;

public class FrmDrawing extends JFrame implements Comparator<Circle>{

	private JPanel contentPane;
	private ArrayList<Circle> circleList = new ArrayList<Circle>();
	private JList<Circle> circleDisplay = new JList<Circle>();
	private DefaultListModel listModel = new DefaultListModel();
	private JButton deleteButton = new JButton("Delete object");
	private JButton sortButton = new JButton("Sort objects");
	private static FrmDrawing mainFrm;
	private JButton addButton = new JButton("Add obj");
	
	
	
	public static void main(String[] args) {
		mainFrm = new FrmDrawing();
		mainFrm.setVisible(true);
	}
		
		
			
		
		
	public FrmDrawing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		circleDisplay = new JList();
		sl_contentPane.putConstraint(SpringLayout.NORTH, circleDisplay, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, circleDisplay, 32, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, circleDisplay, 350, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, circleDisplay, 229, SpringLayout.WEST, contentPane);
		contentPane.add(circleDisplay);
		
		addButton = new JButton("Add obj");
		sl_contentPane.putConstraint(SpringLayout.NORTH, addButton, 15, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, addButton, 48, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, addButton, -26, SpringLayout.EAST, contentPane);
		contentPane.add(addButton);
		
		deleteButton = new JButton("Delete obj");
		sl_contentPane.putConstraint(SpringLayout.NORTH, deleteButton, 30, SpringLayout.SOUTH, addButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, deleteButton, 0, SpringLayout.WEST, addButton);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, deleteButton, -290, SpringLayout.SOUTH, contentPane);
		contentPane.add(deleteButton);
		
		sortButton = new JButton("Sort obj");
		sl_contentPane.putConstraint(SpringLayout.NORTH, sortButton, 21, SpringLayout.SOUTH, deleteButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, sortButton, 0, SpringLayout.WEST, addButton);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, sortButton, -230, SpringLayout.SOUTH, contentPane);
		contentPane.add(sortButton);
		
		
	   
	    addButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	CreateObjectDialog dijalogProba = new CreateObjectDialog(mainFrm);
	        
	       }
	     });
	    
		circleDisplay.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(circleDisplay.getModel().getSize() > 0) {
					deleteButton.setEnabled(true);
				}
				
			}
		}); 
	    
	   
	    deleteButton.setEnabled(false);
	    	deleteButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        
		        DeleteObjectDialog deleteDialog = new DeleteObjectDialog(mainFrm);
	        }
	     });
    
	  
	    sortButton.setEnabled(false);
    	sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		 
				ArrayList<Circle> sortedList = sortCircles(Collections.list(listModel.elements()));
				listModel.clear();
				
				for (Circle circle : sortedList) {
					addElement(circle);
				}
			}
		});
}
	
	public ArrayList<Circle> sortCircles(ArrayList<Circle> circles) {
		
		Collections.sort(circles, Collections.reverseOrder());
		return circles;
	}
	
	
	public void addElement(Circle circle) {
		listModel.addElement(circle);
		circleDisplay.setModel(listModel);
		
	}

	
	public void deleteElement() {
			listModel.remove(circleDisplay.getSelectedIndex());		
	}
	
	public Circle getSelectedCircle() {
		return circleDisplay.getSelectedValue();
	}
   
	
	
	public void refreshJList() {
		circleDisplay.setModel(listModel);
	}
	
	public void enableSortButton() {
		if(listModel.size() >= 2) {
			sortButton.setEnabled(true);
		}
	}
	
	public void deleteButtonEnabled(boolean enabled) {
		deleteButton.setEnabled(enabled);
	}

	public int compare(Circle o1, Circle o2) {
		int radius1 = o1.getRadius();
		int radius2 = o2.getRadius();	
		return 0;
	}



}