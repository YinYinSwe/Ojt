package spring.security.demo.persistence.dao.user;

import spring.security.demo.persistence.entity.Authority;

/**
 * <h2>AuthorityDAO Class</h2>
 * <p>
 * Process for Displaying AuthorityDAO
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
public interface AuthorityDAO {

    /**
     * <h2>dbSaveAuthority</h2>
     * <p>
     * 
     * </p>
     *
     * @param authority
     * @return void
     */
    public void dbSaveAuthority(Authority authority);

    /**
     * <h2>dbGetAuthorityCount</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return long
     */
    public long dbGetAuthorityCount();

    /**
     * <h2>getAuthorityById</h2>
     * <p>
     * 
     * </p>
     *
     * @param id
     * @return
     * @return Authority
     */
    public Authority getAuthorityById(Integer id);
}