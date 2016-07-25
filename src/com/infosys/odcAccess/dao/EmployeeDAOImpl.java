package com.infosys.odcAccess.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.odcAccess.dto.Employee;

@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {
	
	 private SessionFactory sessionFactory;
	 
	    public EmployeeDAOImpl(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }
	
    @Override 	    
	public void add(Employee item) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(item);
		  } catch (HibernateException e) {
			  e.printStackTrace();
			  session.getTransaction().rollback();
		}
			session.getTransaction().commit();
	}
	
    @Override 	
	public void update(Employee item) {
		
		Session session = sessionFactory.getCurrentSession();
		try {
			System.out.println("IN Update");
			session.beginTransaction();
			session.saveOrUpdate(item);
			} catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
		session.getTransaction().commit();
	}
	
	@Override 
	public Employee get(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Employee item=null;
		try {
			System.out.println("IN GetIteam");
			session.beginTransaction();
		    item = (Employee) session.get(Employee.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return item;
	}
	
	@Override 
	public List<Employee> get(String idOrName, int firstPage, int lastPage) {
		Session session = sessionFactory.getCurrentSession();
		Employee item=null;
		List<Employee> employees = null;
		try {
			System.out.println("In getEmployee(String idOrName)");
			session.beginTransaction();
		    //item = (EmployeeMap) session.get(EmployeeMap.class, id);
			Query query = session.createQuery("from Employee where ID=:idOrName "
					+ "or FIRSTNAME||MIDDLENAME||LASTNAME like '%:idOrName%' ");
			
			query.setParameter("idOrName", idOrName);
			if (firstPage != -1) query.setFirstResult(firstPage);  
			if (lastPage != -1) query.setMaxResults(lastPage); 
		    employees =query.setParameter("idOrName", idOrName).list();
		    
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();	
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return employees;
	}
	
	@Override 
	public void delete(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Employee item = (Employee) session.get(Employee.class, id);
		if(null != item) {
			session.delete(item);
		}
		session.getTransaction().commit();
		//return item;
	}

	@Override 
	public List<Employee> list(int firstPage, int lastPage) {
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Employee> items = null;
		try {
			System.out.println("IN LIST");
			
			Query query = session.createQuery("from Employee");
			if (firstPage != -1) query.setFirstResult(firstPage);  
			if (lastPage != -1) query.setMaxResults(lastPage); 
			items = (List<Employee>)query.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return items;
	}

}
