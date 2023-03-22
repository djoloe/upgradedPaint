package Login;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import geometry.Shape;



@Entity
@Table
public class Workspace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int idWorkspace;

	@Column
	private String workspaceName;
	
	


	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	
	public Workspace() {
		
	}
	
	public Workspace(String workspaceName) {
		this.workspaceName = workspaceName;
	}
	
	public Workspace(String workspaceName, User user) {
		this.workspaceName = workspaceName;
		this.user = user;
	}
	

	public int getIdWorkspace() {
		return idWorkspace;
	}

	public void setIdWorkspace(int idWorkspace) {
		this.idWorkspace = idWorkspace;
	}
	
	public String getWorkspaceName() {
		return workspaceName;
	}

	public void setWorkspaceName(String workspaceName) {
		this.workspaceName = workspaceName;
	}
	
	@Override
	public String toString(){
		return workspaceName;
	}
	
	
	
	
}
