package DrawingApp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Shape;

public class EditDialog extends JDialog {

	private JPanel contentPanel;
	private ShapeDetailsPanel detailsPanel;
	public EditDialog(Shape shape) {
		
		setBounds(300,250, 450, 300);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnNewButton = new JButton("Cancel");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						detailsPanel.enterNewValues(shape);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					dispose();
				}
			});
			buttonPane.add(btnNewButton);
			
			
		}
		
		detailsPanel = new ShapeDetailsPanel(contentPanel, shape);
		
	}
	
	

}
