package spring.security.demo.persistence.dao.user.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.security.demo.persistence.dao.user.UserDAO;
import spring.security.demo.persistence.entity.Authority;
import spring.security.demo.persistence.entity.User;

/**
 * <h2>UserDAOImpl Class</h2>
 * <p>
 * Process for Displaying UserDAOImpl
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
@Transactional
@Repository
public class UserDAOImpl implements UserDAO {
    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * <h2>dbSaveUser</h2>
     * <p>
     * 
     * </p>
     * 
     * @param user
     */
    @Override
    public void dbSaveUser(User user) {
        this.sessionFactory.getCurrentSession().save(user);
    }

    /**
     * <h2>dbGetUserByName</h2>
     * <p>
     * 
     * </p>
     * 
     * @param username
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public User dbGetUserByName(String username) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u WHERE u.username = :username");
        query.setParameter("username", username);
        return (User) query.uniqueResult();
    }

    /**
     * <h2>dbGetUserCount</h2>
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public long dbGetUserCount() {
        Query query = this.sessionFactory.getCurrentSession().createQuery("SELECT COUNT(u) FROM User u");
        return (long) query.getSingleResult();
    }

    /**
     * <h2>getUserList</h2>
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public List<User> getUserList() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();

    }

    /**
     * <h2>deleteUser</h2>
     * <p>
     * 
     * </p>
     * 
     * @param personId
     */

    /**
     * <h2>addPerson</h2>
     * <p>
     * 
     * </p>
     * 
     * @param user
     */
    @Override
    public void addPerson(User user) {
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        sessionFactory.getCurrentSession().save(user);
    }

    /**
     * <h2>getUser</h2>
     * <p>
     * 
     * </p>
     * 
     * @param personId
     * @return
     */
    @Override
    public User getUser(long personId) {
        System.out.println(personId);
        String studentHqlQuery = "SELECT u FROM User u where u.id = :id";
        Query queryStudentById = this.sessionFactory.getCurrentSession().createQuery(studentHqlQuery);
        queryStudentById.setParameter("id", personId);
        User resultStudent = (User) queryStudentById.uniqueResult();
        return resultStudent;
    }

    /**
     * <h2>updateUser</h2>
     * <p>
     * 
     * </p>
     * 
     * @param update
     */
    @Override
    public void updateUser(User update) {
        this.sessionFactory.getCurrentSession().update(update);

    }

    /**
     * <h2>getAuthorityList</h2>
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    @Override
    public List<Authority> getAuthorityList() {
        return sessionFactory.getCurrentSession().createQuery("from Authority").list();
    }

    /**
     * <h2>dbDeleteUser</h2>
     * <p>
     * 
     * </p>
     * 
     * @param user
     * @param deleteAt
     */
    @Override
    public void dbDeleteUser(User user, Date deleteAt) {
        user.setDeleteAt(deleteAt);
        this.sessionFactory.getCurrentSession().update(user);

    }

    /**
     * <h2>dbFindUserByAllEmail</h2>
     * <p>
     * 
     * </p>
     * 
     * @param email
     * @return
     */
    @Override
    public User dbFindUserByAllEmail(String email) {
        String userQuery = "SELECT u FROM User u WHERE u.email = :email";
        Query query = this.sessionFactory.getCurrentSession().createQuery(userQuery);
        query.setParameter("email", email);
        User user = (User) query.uniqueResult();
        return user;
    }

    /**
     * <h2>dbUpdateUserPassword</h2>
     * <p>
     * 
     * </p>
     * 
     * @param userForm
     */
    @Override
    public void dbUpdateUserPassword(User userForm) {
        User user = this.sessionFactory.getCurrentSession().load(User.class, userForm.getId());
        if (user != null) {
            user.setPassword(userForm.getPassword());
            this.sessionFactory.getCurrentSession().update(user);
        }

    }

}