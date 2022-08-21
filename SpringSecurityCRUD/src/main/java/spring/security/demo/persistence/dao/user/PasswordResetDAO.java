package spring.security.demo.persistence.dao.user;

import spring.security.demo.persistence.entity.PasswordReset;

/**
 * <h2>PasswordResetDAO Class</h2>
 * <p>
 * Process for Displaying PasswordResetDAO
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
public interface PasswordResetDAO {

    /**
     * <h2>createToken</h2>
     * <p>
     * 
     * </p>
     *
     * @param pswToken
     * @return void
     */
    void createToken(PasswordReset pswToken);

    /**
     * <h2>getTokenDataByEmail</h2>
     * <p>
     * 
     * </p>
     *
     * @param user_email
     * @return
     * @return PasswordReset
     */
    PasswordReset getTokenDataByEmail(String user_email);

    /**
     * <h2>deleteTokenByEmail</h2>
     * <p>
     * 
     * </p>
     *
     * @param user_email
     * @return void
     */
    void deleteTokenByEmail(String user_email);

    /**
     * <h2>dbGetDataByToken</h2>
     * <p>
     * 
     * </p>
     *
     * @param token
     * @return
     * @return PasswordReset
     */
    PasswordReset dbGetDataByToken(String token);

    /**
     * <h2>dbDeleteToken</h2>
     * <p>
     * 
     * </p>
     *
     * @param token
     * @return void
     */
    void dbDeleteToken(String token);

}