package Login;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.NotFound;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService.Work;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import antlr.collections.List;

@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int iduser;
	@Column
	private String username;
	@Column
	private int password;
	@Column
	private ArrayList<Workspace> workspaces = new ArrayList<>();
	
	public User() {
		
	}
	
	public User(String username, int password) {
		this.username = username;
		this.password = password;
	}
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getPassword() {
		return password;
	}
	
	public void setPassword(int password) {
		this.password = password;
	}
	
	public void setWorkspace(Workspace workspace) {
		workspaces.add(workspace);
	}
	
	
	public Workspace getWorkspace(Workspace workspace) {
		return workspace;
	}
	
	
	public int getIduser() {
		return iduser;
	}
	
	
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
}
