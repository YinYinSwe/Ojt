package spring.security.demo.bl.service.user;

import java.util.List;

import javax.validation.Valid;

import spring.security.demo.persistence.entity.Authority;
import spring.security.demo.persistence.entity.User;
import spring.security.demo.web.form.UserForm;

/**
 * <h2>UserService Class</h2>
 * <p>
 * Process for Displaying UserService
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
public interface UserService {

    /**
     * <h2>doGetUserByName</h2>
     * <p>
     * 
     * </p>
     *
     * @param username
     * @return
     * @return User
     */
    public User doGetUserByName(String username);

    /**
     * <h2>doGetUserCount</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return long
     */
    public long doGetUserCount();

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
    public void deleteUser(long personId);

    /**
     * <h2>addUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param userForm
     * @return void
     */
    public void addUser(UserForm userForm);

    /**
     * <h2>getUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param personId
     * @return
     * @return UserForm
     */
    public UserForm getUser(long personId);

    /**
     * <h2>updateUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param userForm
     * @return void
     */
    public void updateUser(@Valid UserForm userForm);

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

    /**
     * <h2>doIsEmailExist</h2>
     * <p>
     * 
     * </p>
     *
     * @param user_email
     * @return
     * @return boolean
     */
    public boolean doIsEmailExist(String user_email);

}