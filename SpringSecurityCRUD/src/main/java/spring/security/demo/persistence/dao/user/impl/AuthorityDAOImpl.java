package spring.security.demo.persistence.dao.user.impl;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.security.demo.persistence.dao.user.AuthorityDAO;
import spring.security.demo.persistence.entity.Authority;

/**
 * <h2>AuthorityDAOImpl Class</h2>
 * <p>
 * Process for Displaying AuthorityDAOImpl
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
@Transactional
@Repository
public class AuthorityDAOImpl implements AuthorityDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * <h2>dbSaveAuthority</h2>
     * <p>
     * 
     * </p>
     * 
     * @param authority
     */
    @Override
    public void dbSaveAuthority(Authority authority) {
        this.sessionFactory.getCurrentSession().save(authority);
    }

    /**
     * <h2>dbGetAuthorityCount</h2>
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public long dbGetAuthorityCount() {
        Query query = this.sessionFactory.getCurrentSession().createQuery("SELECT COUNT(a) FROM Authority a");
        return (long) query.getSingleResult();
    }

    /**
     * <h2>getAuthorityById</h2>
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Authority getAuthorityById(Integer id) {
//        System.out.println(id);
        Query query = this.sessionFactory.getCurrentSession().createQuery("SELECT a FROM Authority a WHERE a.id = :id");
        query.setParameter("id", id);
        return (Authority) query.uniqueResult();
    }
}