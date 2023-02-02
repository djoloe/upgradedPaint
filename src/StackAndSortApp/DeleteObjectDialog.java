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

public class DeleteObjectDialog extends JDialog {

	private JLabel labelX;
	private JLabel labelSelectedX;
	private JLabel labelY;
	private JLabel labelSelectedY;
	private JLabel labelR;
	private JLabel labelSelectedR;
	private JButton confirmButton;
	private JButton cancelButton;
	private FrmDrawing deleteObject;
	
	public DeleteObjectDialog(FrmDrawing deleteObjectTransfer) {
		deleteObject = deleteObjectTransfer;
		
		setBounds(100, 100, 450, 450);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		labelX = new JLabel("X coordinate:");
		springLayout.putConstraint(SpringLayout.NORTH, labelX, 26, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, labelX, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(labelX);
		
		labelSelectedX = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, labelSelectedX, 0, SpringLayout.NORTH, labelX);
		springLayout.putConstraint(SpringLayout.WEST, labelSelectedX, 6, SpringLayout.EAST, labelX);
		getContentPane().add(labelSelectedX);
		
		labelY = new JLabel("Y coordinate:");
		springLayout.putConstraint(SpringLayout.NORTH, labelY, 12, SpringLayout.SOUTH, labelX);
		springLayout.putConstraint(SpringLayout.WEST, labelY, 0, SpringLayout.WEST, labelX);
		getContentPane().add(labelY);
		
		labelSelectedY = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, labelSelectedY, 0, SpringLayout.NORTH, labelY);
		springLayout.putConstraint(SpringLayout.WEST, labelSelectedY, 4, SpringLayout.EAST, labelY);
		getContentPane().add(labelSelectedY);
		
		labelR = new JLabel("R coordinate:");
		springLayout.putConstraint(SpringLayout.NORTH, labelR, 14, SpringLayout.SOUTH, labelY);
		springLayout.putConstraint(SpringLayout.WEST, labelR, 0, SpringLayout.WEST, labelX);
		getContentPane().add(labelR);
		
		labelSelectedR = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, labelSelectedR, 0, SpringLayout.NORTH, labelR);
		springLayout.putConstraint(SpringLayout.WEST, labelSelectedR, 0, SpringLayout.WEST, labelSelectedX);
		getContentPane().add(labelSelectedR);
		
		confirmButton = new JButton("Confirm");
		springLayout.putConstraint(SpringLayout.NORTH, confirmButton, 0, SpringLayout.NORTH, labelX);
		springLayout.putConstraint(SpringLayout.SOUTH, confirmButton, 0, SpringLayout.SOUTH, labelY);
		springLayout.putConstraint(SpringLayout.EAST, confirmButton, -61, SpringLayout.EAST, getContentPane());
		getContentPane().add(confirmButton);
		
		cancelButton = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, cancelButton, 14, SpringLayout.SOUTH, confirmButton);
		springLayout.putConstraint(SpringLayout.WEST, cancelButton, 0, SpringLayout.WEST, confirmButton);
		springLayout.putConstraint(SpringLayout.SOUTH, cancelButton, 54, SpringLayout.SOUTH, confirmButton);
		springLayout.putConstraint(SpringLayout.EAST, cancelButton, 0, SpringLayout.EAST, confirmButton);
		getContentPane().add(cancelButton);
		
		Circle cirle = deleteObjectTransfer.getSelectedCircle();
		setValuesAtLabels(cirle);
		
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteObjectTransfer.deleteElement();
				dispose();
				deleteObjectTransfer.refreshJList();
				deleteObjectTransfer.deleteButtonEnabled(false);
			}
		});
		
		
		
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				}
		});
		
		this.setVisible(true);
	}
	
	public void setValuesAtLabels(Circle obj) {
		Point p = obj.getCenter();
		
		
		labelSelectedX.setText(String.valueOf(p.getX()));
		labelSelectedY.setText(String.valueOf(p.getY()));
		labelSelectedR.setText(String.valueOf(obj.getRadius()));
	}
	

}
