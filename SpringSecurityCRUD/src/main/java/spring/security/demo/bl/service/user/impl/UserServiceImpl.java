package spring.security.demo.bl.service.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.security.demo.bl.dto.CustomUserDetail;
import spring.security.demo.bl.service.user.UserService;
import spring.security.demo.persistence.dao.user.AuthorityDAO;
import spring.security.demo.persistence.dao.user.UserDAO;
import spring.security.demo.persistence.entity.Authority;
import spring.security.demo.persistence.entity.User;
import spring.security.demo.web.form.UserForm;

/**
 * <h2>UserServiceImpl Class</h2>
 * <p>
 * Process for Displaying UserServiceImpl
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    /**
     * <h2>userDAO</h2>
     * <p>
     * userDAO
     * </p>
     */
    @Autowired
    private UserDAO userDAO;

    /**
     * <h2>authorityDAO</h2>
     * <p>
     * authorityDAO
     * </p>
     */
    @Autowired
    private AuthorityDAO authorityDAO;

    /**
     * <h2>doGetUserByName</h2>
     * <p>
     * 
     * </p>
     * 
     * @param username
     * @return
     */
    @Override
    public User doGetUserByName(String username) {
        return this.userDAO.dbGetUserByName(username);
    }

    /**
     * <h2>doGetUserCount</h2>
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    @Override
    public long doGetUserCount() {
        return this.userDAO.dbGetUserCount();
    }

    /**
     * <h2>loadUserByUsername</h2>
     * <p>
     * 
     * </p>
     * 
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInfo = this.userDAO.dbGetUserByName(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("Invalid Username or Password!");
        }
        UserDetails user = new CustomUserDetail(userInfo.getUsername(), userInfo.getPassword(),
                userInfo.getAuthorities());
        return user;
    }

    /**
     * <h2>getUserList</h2>
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    @Override
    public List<User> getUserList() {
        return userDAO.getUserList();

    }

    /**
     * <h2>deleteUser</h2>
     * <p>
     * 
     * </p>
     * 
     * @param personId
     */
    @Override
    public void deleteUser(long personId) {
        Date deleteAt = new Date();
        User user = this.userDAO.getUser(personId);
        this.userDAO.dbDeleteUser(user, deleteAt);

    }

    /**
     * <h2>addUser</h2>
     * <p>
     * 
     * </p>
     * 
     * @param userForm
     */
    @Override
    public void addUser(UserForm userForm) {
        User user = new User(userForm);
        List<Authority> authorities = new ArrayList<Authority>();
        System.out.println(user.getAuthorities());
        this.userDAO.addPerson(user);
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
    public UserForm getUser(long personId) {
        User resultPost = this.userDAO.getUser(personId);
        UserForm resultPostform = resultPost != null ? new UserForm(resultPost) : null;
        return resultPostform;
    }

    /**
     * <h2>updateUser</h2>
     * <p>
     * 
     * </p>
     * 
     * @param userForm
     */
    @Override
    public void updateUser(UserForm userForm) {
        User update = this.userDAO.getUser(userForm.getId());
        update.setUsername(userForm.getUsername());
        System.out.println(update.getUsername());
        update.setPassword(userForm.getPassword());

        for (Authority authority : update.getAuthorities()) {
            if (userForm.getAuthority().getId() != authority.getId()) {
                update.setAuthorities(userForm.getAuthorities());
            }

        }
        System.out.println(update.getAuthorities());
        this.userDAO.updateUser(update);
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
        return userDAO.getAuthorityList();
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
    @Override
    public Authority getAuthorityById(Integer id) {
        return authorityDAO.getAuthorityById(id);

    }

    /**
     * <h2>doIsEmailExist</h2>
     * <p>
     * 
     * </p>
     * 
     * @param user_email
     * @return
     */
    @Override
    public boolean doIsEmailExist(String user_email) {
        // TODO Auto-generated method stub
        boolean result = false;
        User user = this.userDAO.dbFindUserByAllEmail(user_email);
        if (user != null) {
            result = true;
        }
        return result;
    }

}