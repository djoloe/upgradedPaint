package Login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



import DrawingApp.MainFrame;
import SQLConnection.ReadFromBase;
import geometry.Line;
import geometry.Point;
import geometry.Shape;

import javax.swing.SpringLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class LoadWorkspace extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton buttonNew;
	private DefaultListModel<Workspace> workspaces = new DefaultListModel<>();
	private User loggedUser;
	private Session session;
	private Transaction tx;
	private JButton buttonLoad;
	private JButton buttonDelete; 
	private JList<Workspace> workspacesList;
	private static LoadWorkspace dialog;
	private String workSpaceName;
	private Workspace workspace;
	private ArrayList<Shape> shapes = new ArrayList<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new LoadWorkspace();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static LoadWorkspace Instance() {
		if(dialog == null) {
			dialog = new LoadWorkspace();
		}
		return dialog;
		
	}
	
	/**
	 * Create the dialog.
	 */
	private LoadWorkspace() {
		this.setVisible(true);
		loggedUser = MainDialog.Instance().getUser();
		setBounds(200, 150, 418, 389);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		workspacesList = new JList<Workspace>();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, workspacesList, 10, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, workspacesList, 24, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, workspacesList, 297, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, workspacesList, 232, SpringLayout.WEST, contentPanel);
		contentPanel.add(workspacesList);
		workspacesList.setVisible(true);
		
		
		{
			buttonNew = new JButton("New");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, buttonNew, 27, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, buttonNew, 80, SpringLayout.EAST, workspacesList);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, buttonNew, -273, SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, buttonNew, -12, SpringLayout.EAST, contentPanel);
			contentPanel.add(buttonNew);
			buttonNew.setActionCommand("OK");
			getRootPane().setDefaultButton(buttonNew);
			buttonNew.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					MainFrame.Instance();
					MainFrame.Instance().setVisibleFrame();
					dialog.dispose();
					
				}
			});
		}	
		{
			JButton cancelButton = new JButton("Cancel");
			sl_contentPanel.putConstraint(SpringLayout.WEST, cancelButton, 0, SpringLayout.WEST, buttonNew);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, cancelButton, -92, SpringLayout.SOUTH, contentPanel);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();				
				}
			});
			
			buttonLoad = new JButton("Load");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, cancelButton, 75, SpringLayout.SOUTH, buttonLoad);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, buttonLoad, 22, SpringLayout.SOUTH, buttonNew);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, buttonLoad, -209, SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, buttonLoad, 0, SpringLayout.WEST, buttonNew);
			sl_contentPanel.putConstraint(SpringLayout.EAST, buttonLoad, -13, SpringLayout.EAST, contentPanel);
			contentPanel.add(buttonLoad);
			buttonLoad.setEnabled(false);
			buttonLoad.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					int id = 0;
					try {
						
						id = MainDialog.Instance().getWorkspaceID();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						ReadFromBase read = new ReadFromBase(id);
						shapes = read.getShapes();
						MainFrame.Instance().paintShapesFromBase(shapes);
						MainFrame.Instance().setVisibleFrame();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					
					
				}

				
			});
			
			buttonDelete = new JButton("Delete");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, buttonDelete, 18, SpringLayout.SOUTH, buttonLoad);
			sl_contentPanel.putConstraint(SpringLayout.WEST, buttonDelete, 0, SpringLayout.WEST, buttonNew);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, buttonDelete, -15, SpringLayout.NORTH, cancelButton);
			buttonDelete.setActionCommand("Cancel");
			contentPanel.add(buttonDelete);
			buttonDelete.setEnabled(false);
			buttonDelete.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						removeWorkspace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
		}
	}
	
	
	
	private void addShape(Shape shape) {
		shapes.add(shape);
	}
	public void showWindow() {
		dialog.setVisible(true);
		enableButton();
	}
	
	private void setUpConfig() {
		Configuration cfg = new Configuration().configure();
		cfg.addAnnotatedClass(User.class);
		cfg.addAnnotatedClass(Workspace.class);
		SessionFactory sf = cfg.buildSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();
		
	}
	
	public void enableButton() {
		if(!(workspaces.size() < 0)) {
			buttonLoad.setEnabled(true);
			buttonDelete.setEnabled(true);
			}
	}
	
	public void addtoJList(Workspace workspaceToModel) {
		workspaces.addElement(workspaceToModel);
		workspacesList.setModel(workspaces);
	}
	
	public void makeWorkspace() {
		Frame f = new Frame();
		workSpaceName = JOptionPane.showInputDialog(f, "Enter workspace name: ");
		workspace = new Workspace(workSpaceName, loggedUser);
		loggedUser.setWorkspace(workspace);
		addtoJList(workspace);
	}
	
	public Workspace getWorkspace() {
		return workspace;
	}
	
	public String getWorkspaceStringFromJlist() {
		Workspace workspaceForLoad = (Workspace) workspacesList.getSelectedValue();
		String workspaceStringLoad = workspaceForLoad.toString();
		return workspaceStringLoad;
	}
	
	private void removeWorkspace() throws SQLException {
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/paint", "root", "adde22432");
		String sql = "delete from paint.workspace where idWorkspace = " + MainDialog.Instance().getWorkspaceID();
		PreparedStatement preparedStmt = conn.prepareStatement(sql);
		preparedStmt.execute();
		Workspace workspaceForDelete = (Workspace) workspacesList.getSelectedValue();
		workspaces.removeElement(workspaceForDelete);
		}
	
	private ResultSet openConnection(String sql) throws SQLException {
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/paint", "root", "adde22432");
		Statement stm = conn.createStatement();
	    ResultSet rst;
	    rst = stm.executeQuery(sql);
		return rst;
	}
	
	public String getWorkspaceNameFromJdialog() {
		return workSpaceName;
	}

	
	}
	

