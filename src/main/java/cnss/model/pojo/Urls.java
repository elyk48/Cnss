package cnss.model.pojo;
import java.io.Serializable;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import static javax.persistence.GenerationType.IDENTITY;  
import javax.persistence.Id;  
import javax.persistence.Table;  
import javax.persistence.Temporal;  
import javax.persistence.TemporalType;
@ManagedBean(name ="Urls")  
@ViewScoped
@Entity
@Table(name ="url", catalog = "urls") 
public class Urls implements  java.io.Serializable {
	@Id
	@Column(name="idURL")
     private int ID;
	 @Column(name = "url_path")
	private String link;
	 @Column(name = "Status")
	private String status;
	 @Column(name = "name")
	private String name;
	 
	 @Id @GeneratedValue(strategy = IDENTITY)  
	  @Column(name = "idURL")  
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	@Column(name = "url_path")  
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Column(name = "Status")  
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "name")  
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Urls() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Urls(int iD, String link, String status, String name) {
		super();
		ID = iD;
		this.link = link;
		this.status = status;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Urls [ID=" + ID + ", link=" + link + ", status=" + status + ", name=" + name + "]";
	}
	 
	
public Urls(String link) {
		
		this.link = link;
		this.status="Default";
	}

	
    	


	
	
	
}
