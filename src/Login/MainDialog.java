package Login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import geometry.Point;
import geometry.Shape;

import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class MainDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField fieldUsername;
	private User newUser;
	private Session session;
	private Transaction tx;
	private JPasswordField passwordField;
	private String password;
	private static final int SALT = 1562425;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MainDialog dialog = new MainDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public MainDialog() {
		setTitle("Login");
		setBounds(500, 250, 436, 306);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		fieldUsername = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, fieldUsername, 55, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, fieldUsername, 119, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, fieldUsername, 74, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, fieldUsername, -154, SpringLayout.EAST, contentPanel);
		contentPanel.add(fieldUsername);
		fieldUsername.setColumns(10);
		
		JLabel labelUsername = new JLabel("Enter username:");
		sl_contentPanel.putConstraint(SpringLayout.WEST, labelUsername, 148, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, labelUsername, -6, SpringLayout.NORTH, fieldUsername);
		contentPanel.add(labelUsername);
		
		JLabel labelPassword = new JLabel("Enter password:");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, labelPassword, 14, SpringLayout.SOUTH, fieldUsername);
		sl_contentPanel.putConstraint(SpringLayout.WEST, labelPassword, 0, SpringLayout.WEST, labelUsername);
		contentPanel.add(labelPassword);
		
		
		JLabel labelWarningUsername = new JLabel("User already in database!");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, labelWarningUsername, 0, SpringLayout.NORTH, fieldUsername);
		sl_contentPanel.putConstraint(SpringLayout.WEST, labelWarningUsername, 6, SpringLayout.EAST, fieldUsername);
		contentPanel.add(labelWarningUsername);
		labelWarningUsername.setVisible(false);
		
		passwordField = new JPasswordField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, passwordField, 9, SpringLayout.SOUTH, labelPassword);
		sl_contentPanel.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, fieldUsername);
		sl_contentPanel.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, fieldUsername);
		contentPanel.add(passwordField);
		
		JLabel labelWarningPassword = new JLabel("Wrong password!");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, labelWarningPassword, 0, SpringLayout.NORTH, passwordField);
		sl_contentPanel.putConstraint(SpringLayout.WEST, labelWarningPassword, 5, SpringLayout.EAST, passwordField);
		contentPanel.add(labelWarningPassword);
		labelWarningPassword.setVisible(false);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
					setUpConfig();
					labelWarningPassword.setVisible(false);
					try {
							String loginTestUser = readUserForLogin();
							if(loginTestUser == null  || !loginTestUser.equals(fieldUsername.getText())) {
								saveUser();
								dispose();
							} else if (checkPassword(loginTestUser) == true){
								labelWarningUsername.setVisible(true);
								System.out.println("Password ok!");
								dispose();
							} else {
								labelWarningPassword.setVisible(true);
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
			}
		}
	}
	
	private void setUpConfig() {
		Configuration cfg = new Configuration().configure().addAnnotatedClass(User.class);
		SessionFactory sf = cfg.buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();
		
	}
	
	private void saveUser() {
		String username = fieldUsername.getText();
		int hashPassword = passwordField.getText().hashCode();
		int finalPassword = hashPassword + SALT;
		newUser = new User(username, finalPassword);
		session.save(newUser);
		tx.commit();
	}
	
	private String readUserForLogin() throws SQLException {
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/paint", "root", "adde22432");
		Statement stm = conn.createStatement();
		String sql = "Select * From paint.user";
	    ResultSet rst;
	    rst = stm.executeQuery(sql);
	    String checkUsername = null;
	   
	    while (rst.next()) {
	    	checkUsername = rst.getString("username");
	    }
	    return checkUsername;
	}
	
	private boolean checkPassword(String loginTestUser) throws SQLException {
		
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/paint", "root", "adde22432");
		Statement stm = conn.createStatement();
		String sql = "Select * From paint.user";
	    ResultSet rst;
	    rst = stm.executeQuery(sql);
	    boolean flag = false;
	    while (rst.next()) {
	    	if(rst.getString("username").equals(loginTestUser)) {
	    		if((passwordField.getText().hashCode() + SALT) == rst.getInt("password")) {
	    				flag = true;
	    			}
	    		}
	    	}
		return flag;
	}
	
	
	
	
}
