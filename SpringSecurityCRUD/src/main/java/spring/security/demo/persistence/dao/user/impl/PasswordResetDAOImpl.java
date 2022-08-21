package spring.security.demo.persistence.dao.user.impl;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.security.demo.persistence.dao.user.PasswordResetDAO;
import spring.security.demo.persistence.entity.PasswordReset;

/**
 * <h2>PasswordResetDAOImpl Class</h2>
 * <p>
 * Process for Displaying PasswordResetDAOImpl
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
@Transactional
@Repository
public class PasswordResetDAOImpl implements PasswordResetDAO {
    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * <h2>createToken</h2>
     * <p>
     * 
     * </p>
     * 
     * @param pswToken
     */
    @Override
    public void createToken(PasswordReset pswToken) {
        this.sessionFactory.getCurrentSession().save(pswToken);

    }

    /**
     * <h2>getTokenDataByEmail</h2>
     * <p>
     * 
     * </p>
     * 
     * @param user_email
     * @return
     */
    @Override
    public PasswordReset getTokenDataByEmail(String user_email) {
        String userHqlQuery = "SELECT pw FROM PasswordReset pw WHERE pw.user_email = :email";
        Query queryUserByEmail = this.sessionFactory.getCurrentSession().createQuery(userHqlQuery);
        queryUserByEmail.setParameter("email", user_email);
        PasswordReset passwordReset = (PasswordReset) queryUserByEmail.uniqueResult();
        return passwordReset;
    }

    /**
     * <h2>deleteTokenByEmail</h2>
     * <p>
     * 
     * </p>
     * 
     * @param user_email
     */
    @Override
    public void deleteTokenByEmail(String user_email) {
        String userHqlQuery = "DELETE FROM PasswordReset pw WHERE pw.user_email = :email";
        Query queryUserByEmail = this.sessionFactory.getCurrentSession().createQuery(userHqlQuery);
        queryUserByEmail.setParameter("email", user_email);
        queryUserByEmail.executeUpdate();

    }

    /**
     * <h2>dbGetDataByToken</h2>
     * <p>
     * 
     * </p>
     * 
     * @param token
     * @return
     */
    @Override
    public PasswordReset dbGetDataByToken(String token) {
        String pswHqlQuery = "SELECT pw FROM  PasswordReset pw WHERE pw.token = :token";
        Query queryDataByToken = this.sessionFactory.getCurrentSession().createQuery(pswHqlQuery);
        queryDataByToken.setParameter("token", token);
        PasswordReset passwordReset = (PasswordReset) queryDataByToken.uniqueResult();
        return passwordReset;
    }

    /**
     * <h2>dbDeleteToken</h2>
     * <p>
     * 
     * </p>
     * 
     * @param token
     */
    @Override
    public void dbDeleteToken(String token) {
        PasswordReset passwordReset = this.sessionFactory.getCurrentSession().load(PasswordReset.class, token);
        this.sessionFactory.getCurrentSession().delete(passwordReset);

    }

}