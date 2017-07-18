package com.testproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.testproject.data.User;
import com.testproject.utill.InputHelper;

public class UserDAOImpl implements UserDAO {

	/**
	 * Simple DAO implementation for adding new user datas to database
	 */

	@Override
	public void createUser(User user) {

		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MiniAdminApp");
		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		user.getUserName();
		user.getUserPassword();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();
		emFactory.close();
		System.out.println("New User Added !");

	}

	/**
	 * Simple DAO implementation for deleting existing user from database based
	 * on its ID
	 */
	@Override
	public void deleteUser(int id) {

		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MiniAdminApp");
		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		User newUser = entityManager.find(User.class, id);
		entityManager.remove(newUser);
		entityManager.getTransaction().commit();
		entityManager.close();
		emFactory.close();
		System.out.println("User: " + newUser.getUserName() + " is deleted.");

	}

	/**
	 * Simple DAO implementation for searching database for specific user based
	 * on its user name
	 */
	@Override
	public User findUser(String userName) {

		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MiniAdminApp");
		EntityManager entityManager = emFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.userName = :userName");
			query.setParameter("userName", userName);
			User user = (User) query.getSingleResult();
			return user;

		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Simple DAO implementation that allows admin to change user password based
	 * on user id
	 */
	@Override
	public void updateUserPassword(int id) {
		
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MiniAdminApp");
		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		User user = entityManager.find(User.class, id);
		String newPassword = InputHelper
				.inputHelp("Old password is: " + user.getUserPassword() + "\nInsert new password: ");
		user.setUserPassword(newPassword);
		entityManager.getTransaction().commit();
		System.out.println("Password updated. New password is: " + newPassword);
		entityManager.close();
		emFactory.close();
	}

	/**
	 * Simple DAO implementation that displays all users from database
	 */
	@Override
	public void displayAllUsers() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("MiniAdminApp");
		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("SELECT u FROM User u");
		@SuppressWarnings("unchecked")
		List<User> list = query.getResultList();
		list.forEach(element -> System.out.println(element.toString()));
	}
}
