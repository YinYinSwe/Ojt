package spring.security.demo.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.security.demo.persistence.entity.Authority;
import spring.security.demo.persistence.entity.User;

/**
 * <h2>UserForm Class</h2>
 * <p>
 * Process for Displaying UserForm
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
    /**
     * <h2>id</h2>
     * <p>
     * id
     * </p>
     */
    private long id;

    /**
     * <h2>username</h2>
     * <p>
     * username
     * </p>
     */
    @NotEmpty
    private String username;

    /**
     * <h2>password</h2>
     * <p>
     * password
     * </p>
     */
    @NotEmpty
    private String password;

    /**
     * <h2>authorities</h2>
     * <p>
     * authorities
     * </p>
     */
    private List<Authority> authorities = new ArrayList<Authority>();

    /**
     * <h2>authority</h2>
     * <p>
     * authority
     * </p>
     */
    private Authority authority;

    /**
     * <h2>deleteAt</h2>
     * <p>
     * deleteAt
     * </p>
     */
    private Date deleteAt;
    /**
     * <h2>email</h2>
     * <p>
     * email
     * </p>
     */
    private String email;

    /**
     * <h2>Constructor for UserForm</h2>
     * <p>
     * Constructor for UserForm
     * </p>
     * 
     * @param user
     */

    public UserForm(User user) {
        super();
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.authorities = user.getAuthorities();
        this.deleteAt = user.getDeleteAt();

    }

}