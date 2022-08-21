package spring.security.demo.config;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import spring.security.demo.persistence.dao.user.AuthorityDAO;
import spring.security.demo.persistence.dao.user.UserDAO;
import spring.security.demo.persistence.entity.Authority;
import spring.security.demo.persistence.entity.User;

/**
 * <h2>DeploymentListener Class</h2>
 * <p>
 * Process for Displaying DeploymentListener
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
@Component
public class DeploymentListener {

    /**
     * <h2>passwordEncoder</h2>
     * <p>
     * passwordEncoder
     * </p>
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * <h2>authorityDAO</h2>
     * <p>
     * authorityDAO
     * </p>
     */
    @Autowired
    private AuthorityDAO authorityDAO;

    /**
     * <h2>userDAO</h2>
     * <p>
     * userDAO
     * </p>
     */
    @Autowired
    private UserDAO userDAO;

    /**
     * <h2>addInitialData</h2>
     * <p>
     * 
     * </p>
     *
     * @return void
     */
    @PostConstruct
    public void addInitialData() {

        // adding default data
        if (this.authorityDAO.dbGetAuthorityCount() <= 0 && this.userDAO.dbGetUserCount() <= 0) {
            List<Authority> adminAuthorities = new ArrayList<Authority>();
            Authority adminAuthority = new Authority(null, "ROLE_ADMIN");
            this.authorityDAO.dbSaveAuthority(adminAuthority);
            adminAuthorities.add(adminAuthority);
            User admin = new User(null, "admin", passwordEncoder.encode("123"), "admin1999@gmail.com", null,
                    adminAuthorities);
            this.userDAO.dbSaveUser(admin);

            List<Authority> userAuthorities = new ArrayList<Authority>();
            Authority userAuthority = new Authority(null, "ROLE_USER");
            this.authorityDAO.dbSaveAuthority(userAuthority);
            userAuthorities.add(userAuthority);
            User user = new User(null, "user", passwordEncoder.encode("123"), "user1999@gmail.com", null,
                    userAuthorities);
            this.userDAO.dbSaveUser(user);
        }
    }
}