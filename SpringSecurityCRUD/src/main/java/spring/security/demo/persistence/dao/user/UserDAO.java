package spring.security.demo.persistence.dao.user;

import java.util.Date;
import java.util.List;

import spring.security.demo.persistence.entity.Authority;
import spring.security.demo.persistence.entity.User;

/**
 * <h2>UserDAO Class</h2>
 * <p>
 * Process for Displaying UserDAO
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
public interface UserDAO {

    /**
     * <h2>dbSaveUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param user
     * @return void
     */
    public void dbSaveUser(User user);

    /**
     * <h2>dbGetUserByName</h2>
     * <p>
     * 
     * </p>
     *
     * @param username
     * @return
     * @return User
     */
    public User dbGetUserByName(String username);

    /**
     * <h2>dbGetUserCount</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return long
     */
    public long dbGetUserCount();

    /**
     * <h2>getUserList</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return List<User>
     */
    public List<User> getUserList();

    /**
     * <h2>deleteUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param personId
     * @return void
     */

    /**
     * <h2>addPerson</h2>
     * <p>
     * 
     * </p>
     *
     * @param user
     * @return void
     */
    public void addPerson(User user);

    /**
     * <h2>getUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param personId
     * @return
     * @return User
     */
    public User getUser(long personId);

    /**
     * <h2>updateUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param update
     * @return void
     */
    public void updateUser(User update);

    /**
     * <h2>getAuthorityList</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return List<Authority>
     */
    public List<Authority> getAuthorityList();

    /**
     * <h2>dbDeleteUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param user
     * @param deleteAt
     * @return void
     */
    public void dbDeleteUser(User user, Date deleteAt);

    /**
     * <h2>dbFindUserByAllEmail</h2>
     * <p>
     * 
     * </p>
     *
     * @param user_email
     * @return
     * @return User
     */
    public User dbFindUserByAllEmail(String user_email);

    /**
     * <h2>dbUpdateUserPassword</h2>
     * <p>
     * 
     * </p>
     *
     * @param user
     * @return void
     */
    public void dbUpdateUserPassword(User user);

}