package cnss.dao;
import java.util.List;  
import java.util.ArrayList;  
import org.hibernate.Query;  
import org.hibernate.Session;
import cnss.util.*;
import cnss.model.pojo.Urls;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;  

@ManagedBean(name ="UrlDao")  
@ViewScoped
public class UrlDao {

	 
	
	
	    private Urls url;  
	    private Urls newurl;  
	    private List < Urls > DaoAllUrls;  
	    private List < Urls > DaoSearchUrlList;  
	    
	    
		


		//Session session;  
	    public List < Urls >AllUrls()  
	    {  
	        Session session = HibernateUtil.getSessionFactory().openSession();  
	        try  
	        {  
	            session.beginTransaction();  
	            DaoAllUrls = session.createCriteria(Urls.class).list();  
	            int count = DaoAllUrls.size();  
	            // FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "List Size", Integer.toString(count));//Debugging Purpose  
	            //RequestContext.getCurrentInstance().showMessageInDialog(message1);  
	            session.getTransaction().commit();  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	            session.getTransaction().rollback();  
	        }  
	        session.close();  
	        return DaoAllUrls;  
	    } 
	    
	    
	    public Integer getId()  
	    {  
	        Session session = HibernateUtil.getSessionFactory().openSession();  
	        String hql = "select * url.idURL from url ";  
	        Query query = session.createQuery(hql);  
	        List < Integer > results = query.list();  
	        Integer userId = 1;  
	        if (results.get(0) != null)  
	        {  
	            userId = results.get(0) + 1;  
	        }  
	        session.flush();  
	        session.close();  
	        return userId;  
	    }  
	    public List < Urls > SearchByRecordNo(String name)  
	    {  
	        Session session = HibernateUtil.getSessionFactory().openSession();  
	        List < Urls > daoSearchList = new ArrayList < > ();  
	        try  
	        {  
	            session.beginTransaction();  
	            Query qu = session.createQuery("From url U where U.name =:name"); //User is the entity not the table  
	            qu.setParameter("recordNo", name);  
	            daoSearchList = qu.list();  
	            int count = daoSearchList.size();  
	            session.getTransaction().commit();  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	            session.getTransaction().rollback();  
	        }  
	        finally  
	        {  
	            session.close();  
	        }  
	        return daoSearchList;  
	    }  
	    public void add(Urls newurl)  
	    {  
	        Session session = HibernateUtil.getSessionFactory().openSession();  
	        try  
	        {  
	            String Id = Integer.toString(newurl.getID());  
	            // begin a transaction  
	            session.beginTransaction();  
	            session.merge(newurl);  
	            session.flush();  
	            System.out.println("NewUrl saved, id: " +  
	                newurl.getID());  
	            session.getTransaction().commit();  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	            session.getTransaction().rollback();  
	        }  
	        session.close();  
	    }  
	    public void delete(Urls url)  
	    {  
	        Session session = HibernateUtil.getSessionFactory().openSession();  
	        try  
	        {  
	            String name = url.getName();  
	            session.beginTransaction();  
	            session.delete(url);  
	            session.getTransaction().commit();  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	            session.getTransaction().rollback();  
	        }  
	        session.close();  
	    }  
	    public void update(Urls url)  
	    {  
	        Session session = HibernateUtil.getSessionFactory().openSession();  
	        try  
	        {  
	            session.beginTransaction();  
	            session.update(url);  
	            session.flush();  
	            session.getTransaction().commit();  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	            session.getTransaction().rollback();  
	        }  
	        session.close();  
	    }  
	}

	
	

