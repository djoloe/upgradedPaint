package StackAndSortApp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CreateObjectDialog extends JDialog {
	private JLabel labelX;
	private JTextField fieldX;
	private JLabel labelY;
	private JTextField fieldY;
	private JLabel labelR;
	private JTextField fieldR;
	private JButton createButton;
	private JButton exitButton;
	private JLabel errorForX;
	private JLabel errorForY;
	private JLabel errorForR;
	private FrmDrawing createDialog;
	

	public CreateObjectDialog(FrmDrawing createDialogTransfer) {
		
		createDialog = createDialogTransfer;
		setBounds(100, 100, 450, 450);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		labelX = new JLabel("X coordinate:");
		springLayout.putConstraint(SpringLayout.NORTH, labelX, 23, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, labelX, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(labelX);
		
		fieldX = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, fieldX, -3, SpringLayout.NORTH, labelX);
		springLayout.putConstraint(SpringLayout.WEST, fieldX, 6, SpringLayout.EAST, labelX);
		springLayout.putConstraint(SpringLayout.EAST, fieldX, 54, SpringLayout.EAST, labelX);
		getContentPane().add(fieldX);
		fieldX.setColumns(10);
		
		labelY = new JLabel("Y coordinate:");
		springLayout.putConstraint(SpringLayout.SOUTH, labelY, -314, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, labelY, 0, SpringLayout.EAST, labelX);
		getContentPane().add(labelY);
		
		fieldY = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, fieldY, -3, SpringLayout.NORTH, labelY);
		springLayout.putConstraint(SpringLayout.WEST, fieldY, 6, SpringLayout.EAST, labelY);
		getContentPane().add(fieldY);
		fieldY.setColumns(10);
		
		labelR = new JLabel("R coordinate:");
		springLayout.putConstraint(SpringLayout.NORTH, labelR, 32, SpringLayout.SOUTH, labelY);
		springLayout.putConstraint(SpringLayout.WEST, labelR, 0, SpringLayout.WEST, labelX);
		getContentPane().add(labelR);
		
		fieldR = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, fieldR, -3, SpringLayout.NORTH, labelR);
		springLayout.putConstraint(SpringLayout.WEST, fieldR, 5, SpringLayout.EAST, labelR);
		springLayout.putConstraint(SpringLayout.EAST, fieldR, 0, SpringLayout.EAST, fieldX);
		getContentPane().add(fieldR);
		fieldR.setColumns(10);
		
		createButton = new JButton("Create");
		springLayout.putConstraint(SpringLayout.NORTH, createButton, 23, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, createButton, -56, SpringLayout.EAST, getContentPane());
		getContentPane().add(createButton);
		
		exitButton = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, exitButton, 81, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, exitButton, -283, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, exitButton, -56, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, fieldY, -185, SpringLayout.WEST, exitButton);
		springLayout.putConstraint(SpringLayout.SOUTH, createButton, -6, SpringLayout.NORTH, exitButton);
		getContentPane().add(exitButton);
		
		errorForX = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, errorForX, 3, SpringLayout.SOUTH, fieldX);
		springLayout.putConstraint(SpringLayout.WEST, errorForX, 0, SpringLayout.WEST, labelX);
		springLayout.putConstraint(SpringLayout.EAST, errorForX, 6, SpringLayout.EAST, fieldX);
		getContentPane().add(errorForX);
		
		errorForY = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, errorForY, 6, SpringLayout.SOUTH, labelY);
		springLayout.putConstraint(SpringLayout.WEST, errorForY, 0, SpringLayout.WEST, labelX);
		getContentPane().add(errorForY);
		
		errorForR = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, errorForR, 6, SpringLayout.SOUTH, labelR);
		springLayout.putConstraint(SpringLayout.WEST, errorForR, 0, SpringLayout.WEST, labelX);
		getContentPane().add(errorForR);
		
		this.setVisible(true);
		
		createButton.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent ae){
				   
			      int valueOfX = populateAndVerify(fieldX.getText(), errorForX);
			      int valueOfY = populateAndVerify(fieldY.getText(), errorForY);
			      int valueOfR = populateAndVerify(fieldR.getText(), errorForR);
			      if(valueOfX < 0 || valueOfY < 0 || valueOfR <0) {
			    	  return;
			      }
			      Point tacka = new Point(valueOfX, valueOfY);
			      Circle obj = new Circle(tacka,  valueOfR);
			      createDialog.addElement(obj);
			      dispose();
			      createDialog.enableSortButton();
			      
			   }
			});
		
			
			
			exitButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	dispose();
		       }
		    });
			
			
			
		}
		
	
	
	
	
	
	public Integer populateAndVerify(String inputValue, JLabel errorLabel) {
		int value = -1;
		try {
			value = Integer.parseInt(inputValue);
			errorLabel.setText("");
		} catch (Exception e) {
			errorLabel.setText("The value isn't int type!");	
		}
		
		return value;
	}

}
