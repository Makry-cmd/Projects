   package com.example.dao;

   import com.example.model.User;
   import org.hibernate.Session;
   import org.hibernate.SessionFactory;
   import org.hibernate.Transaction;
   import org.hibernate.cfg.Configuration;
   import java.time.LocalDateTime;
   
   import java.util.List;

   public class UserDaoImpl implements UserDao {
       private SessionFactory sessionFactory;

       public UserDaoImpl() {
           sessionFactory = new Configuration().configure().buildSessionFactory();
       }

       @Override
       public void createUser(User user) {
           Transaction transaction = null;
           try (Session session = sessionFactory.openSession()) {
               transaction = session.beginTransaction();
               user.setCreatedAt(LocalDateTime.now());
               session.save(user);
               transaction.commit();
           } catch (Exception e) {
               if (transaction != null) transaction.rollback();
               e.printStackTrace();
           }
       }

       @Override
       public User getUser(Long id) {
           try (Session session = sessionFactory.openSession()) {
               return session.get(User.class, id);
           }
       }

       @Override
       public List<User> getAllUsers() {
           try (Session session = sessionFactory.openSession()) {
               return session.createQuery("from User", User.class).list();
           }
       }

       @Override
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

       @Override
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
   }
   