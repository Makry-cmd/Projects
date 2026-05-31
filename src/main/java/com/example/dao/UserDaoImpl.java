package com.example.dao;

import com.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImpl {
	
    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createUser(User user) {
        Transaction transaction = null;
		
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
			User existingUser = session.createQuery("FROM User WHERE email = :email", User.class)
                                   .setParameter("email", user.getEmail())
                                   .uniqueResult();
		
			if (existingUser != null) {
				throw new IllegalArgumentException("Пользователь с таким email уже существует.");
			}						
            session.save(user);
            transaction.commit();
        }catch(IllegalArgumentException e){
			e.printStackTrace();
		}
			catch (Exception e) {
			e.printStackTrace();
            if (transaction != null) transaction.rollback();
            
        }
    }

    public User getUser(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteUser(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }
}



   