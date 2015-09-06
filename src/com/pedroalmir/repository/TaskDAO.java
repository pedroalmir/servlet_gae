/**
 * 
 */
package com.pedroalmir.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.pedroalmir.model.Task;

/**
 * The DAO should work as a translator worlds. Suppose one relational database. 
 * The DAO should seek to know the database and convert into objects to be used by the application. 
 * 
 * Similarly, should know how to pick them up, convert and send SQL statements to the database. 
 * This is how a DAO works.
 * 
 * Usually we have a DAO for each domain object system (Product, Customer, Purchase, etc..), 
 * or for each module, or set of closely related entities. Each DAO must have an interface that 
 * specifies the methods of data manipulation. Our codes work only with the interfaces of DAOs, 
 * ignoring the implementation used. This is a good practice, not only in terms of persistence, 
 * but in several other points of an application.
 * 
 * This class represents the task entity DAO.
 * 
 * @author Pedro Almir
 * 
 */
public class TaskDAO {
	
	/**
	 * List all tasks registered in the database.
	 * @return list with all tasks
	 */
	@SuppressWarnings("unchecked")
	public List<Task> listAll() {
		EntityManager em = EMFService.get().createEntityManager();
		/* Read the existing entries */
		Query q = em.createQuery("select t from Task t");
		List<Task> tasks = q.getResultList();
		return tasks;
	}

	/**
	 * Create and add new task
	 * 
	 * @param userId User identification
	 * @param summary Task summary
	 * @param description Task description
	 * @param url Task URL
	 * @param dueDate Task due date
	 */
	public void add(String userId, String summary, String description, String url, String dueDate) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Task todo = new Task(userId, summary, description, url, dueDate);
			em.persist(todo);
			em.close();
		}
	}
	
	/**
	 * Find all tasks by user identification
	 * 
	 * @param userID
	 * @return list with all user tasks
	 */
	@SuppressWarnings("unchecked")
	public List<Task> findAllByID(String userID) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Task t where t.author = :userId");
		q.setParameter("userId", userID);
		List<Task> tasks = q.getResultList();
		return tasks;
	}
	
	/**
	 * Remove a task
	 * 
	 * @param id
	 * 			task identification
	 */
	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Task todo = em.find(Task.class, id);
			em.remove(todo);
		} finally {
			em.close();
		}
	}
}
