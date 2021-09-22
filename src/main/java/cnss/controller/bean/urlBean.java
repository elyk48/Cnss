package cnss.controller.bean;


import java.io.Serializable;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import cnss.model.pojo.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import cnss.dao.UrlDao;
import java.io.Serializable;  
import javax.faces.context.FacesContext;  
import javax.faces.application.FacesMessage;  

@ManagedBean(name ="urlBean")  
@ViewScoped
public class urlBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	  private List < Urls > urlsList;  
	    private List < Urls > searchList;  
	    private List < Urls > searchBynameList;  
	    UrlDao urlDao = new UrlDao();  
	    Urls url = new Urls();  
	    Urls newurl = new Urls();  
	    
	    
	    public Urls getNewurl() {
			return this.newurl;
		}

		public void setNewurl(Urls newurl) {
			this.newurl = newurl;
		}

		public List < Urls > getUrls()  
	    {  
	        urlsList = urlDao. AllUrls() ;  
	        int count = urlsList.size();  
	        return urlsList;  
	    }  
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	private String link;
	private String status;
	public   ArrayList<Urls> Urlz =  new ArrayList<Urls>() ;


	  
	public urlBean() {
		List<String> list = this.Data();
	
		for (String line : list) {
		
			adddUrl(line);
		}
		
		
		settingStatus();
		
	  
	}

	public  ArrayList<Urls> getUrlz() {
		return this.Urlz;
	}
	
	 public void setUrlz(ArrayList<Urls> urlz) {
		Urlz = urlz;
	}

	public String adddUrl(String url) {		 
	      Urls ur = new Urls(url);
	      
	      Urlz.add(ur);
	      return null;
	   }
	
	
	 
	 public List<String> Data() {
		 
		 List<String> allLines = new ArrayList();
		 try {
				allLines = Files.readAllLines(Paths.get("/Users/ely/Desktop/urls.txt"));
				for (String line : allLines) {
					System.out.println(line);
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		 
		 return allLines;
	 }
	
	 public static boolean Status(String url, int timeout) {
		    url = url.replaceFirst("https", "http"); 
		   
		    
		    try {
		    	
		        HttpURLConnection connection = (HttpURLConnection) new URL(url)
		                .openConnection();
		        
		        connection.setConnectTimeout(timeout);
		              connection.setReadTimeout(timeout);
		        connection.setRequestMethod("HEAD");
		        
		        int responseCode = connection.getResponseCode();
			    System.out.println(responseCode);
		        if (!(200<=responseCode && responseCode <=299) ) {
		        	
		            return false;
		        }
		    } catch (IOException exception) {
		        return false;
		    }
	
		    return true;
		  
		}
	 
	 
	 public void settingStatus() {
		 boolean test =true;
		 for(Urls u: Urlz) {
			 
			 test=Status(u.getLink(),0);
			if(!test) {
				u.setStatus("Down");
				
			}else
				u.setStatus("UP");
		 }
		 
	 }
	
	 
	 public void test() {
		 boolean b;
  
		 b= Status("https://evax.tn",5000);
		 System.out.println(b);
		
	 }
	
	
	
	 
	 
	 public void addUrl()  
	    {  
	        String name = newurl.getName();
	        String link = newurl.getLink();
	        Integer urlId = 0;  
	       
	        
	        String Id = Integer.toString(newurl.getID());  
	        newurl.setName(name);  
	        newurl.setLink(link);
	        urlDao.add(newurl);  
	        System.out.println("Url successfully saved.");  
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Url successfully saved.");  
	       
	        newurl = new Urls();  
	    } 
	
	    
	    public void deleteUrl(Urls url)  
	    {  
	        String Name = url.getName();  
	        //FacesMessage message3= new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Item",contactName);  
	        // RequestContext.getCurrentInstance().showMessageInDialog(message3);  
	        urlDao.delete(url);  
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "url deleted successfully");  
	     
	    }  
	    
	    public Urls getUrl()  
	    {  
	        return url;  
	    }  
	    public void setUrl(Urls url)  
	    {  
	        this.url = url;  
	    }  
	   
	    public List < Urls > getUrlsList()  
	    {  
	        return urlsList;  
	    }  
	    public void setUrslList(List < Urls > urlsList)  
	    {  
	        this.urlsList = urlsList;  
	    }  
	    public List < Urls> getSearchList()  
	    {  
	        return searchList;  
	    }  
	    public void setSearchList(List < Urls > searchList)  
	    {  
	        this.searchList = searchList;  
	    }  
	
}

