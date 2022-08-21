package spring.security.demo.bl.service.user.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.security.demo.bl.service.user.PasswordResetService;
import spring.security.demo.common.TokenGenerator;
import spring.security.demo.persistence.dao.user.PasswordResetDAO;
import spring.security.demo.persistence.dao.user.UserDAO;
import spring.security.demo.persistence.entity.PasswordReset;
import spring.security.demo.persistence.entity.User;
import spring.security.demo.web.form.PasswordResetMailForm;

/**
 * <h2>PasswordResetServiceImpl Class</h2>
 * <p>
 * Process for Displaying PasswordResetServiceImpl
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
@Service
@Transactional
public class PasswordResetServiceImpl implements PasswordResetService {
    /**
     * <h2>psw_token_length</h2>
     * <p>
     * psw_token_length
     * </p>
     */
    public static final int psw_token_length = 20;

    /**
     * <h2>psw_token_expired_minute</h2>
     * <p>
     * psw_token_expired_minute
     * </p>
     */
    public static final int psw_token_expired_minute = 3;

    /**
     * <h2>now</h2>
     * <p>
     * now
     * </p>
     */
    private Timestamp now = new Timestamp(new Date(System.currentTimeMillis()).getTime());

    /**
     * <h2>passwordResetDao</h2>
     * <p>
     * passwordResetDao
     * </p>
     */
    @Autowired
    PasswordResetDAO passwordResetDao;

    /**
     * <h2>userDao</h2>
     * <p>
     * userDao
     * </p>
     */
    @Autowired
    UserDAO userDao;

    /**
     * <h2>passwordEncoder</h2>
     * <p>
     * passwordEncoder
     * </p>
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * <h2>createResetToken</h2>
     * <p>
     * 
     * </p>
     * 
     * @param user_email
     * @return
     */
    @Override
    public PasswordResetMailForm createResetToken(String user_email) {
        if (isEmailExist(user_email)) {
            passwordResetDao.deleteTokenByEmail(user_email);
        }
        String token = new TokenGenerator(psw_token_length).getToken();
        Timestamp expired = new Timestamp(
                new Date(System.currentTimeMillis() + psw_token_expired_minute * 60 * 1000).getTime());
        System.out.println(expired);
        PasswordResetMailForm passwordResetForm = new PasswordResetMailForm();
        passwordResetForm.setUser_email(user_email);
        passwordResetForm.setToken(token);
        passwordResetForm.setCreated_at(now);
        passwordResetForm.setExpired_at(expired);
        this.passwordResetDao.createToken(this.getPswToken(passwordResetForm));
        return passwordResetForm;

    }

    /**
     * <h2>getPswToken</h2>
     * <p>
     * 
     * </p>
     *
     * @param passwordResetForm
     * @return
     * @return PasswordReset
     */
    private PasswordReset getPswToken(PasswordResetMailForm passwordResetForm) {
        PasswordReset pwToken = new PasswordReset();
        pwToken.setUser_email(passwordResetForm.getUser_email());
        pwToken.setToken(passwordResetForm.getToken());
        pwToken.setCreated_at(passwordResetForm.getCreated_at());
        pwToken.setExpired_at(passwordResetForm.getExpired_at());
        return pwToken;
    }

    /**
     * <h2>isEmailExist</h2>
     * <p>
     * 
     * </p>
     *
     * @param user_email
     * @return
     * @return boolean
     */
    private boolean isEmailExist(String user_email) {
        PasswordReset pwToken = this.passwordResetDao.getTokenDataByEmail(user_email);
        return pwToken != null;
    }

    /**
     * <h2>getDataByToken</h2>
     * <p>
     * 
     * </p>
     * 
     * @param token
     * @return
     */
    @Override
    public PasswordResetMailForm getDataByToken(String token) {
        try {
            PasswordResetMailForm passwordResetMailForm = new PasswordResetMailForm(
                    passwordResetDao.dbGetDataByToken(token));
            return passwordResetMailForm;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * <h2>doUpdatePassword</h2>
     * <p>
     * 
     * </p>
     * 
     * @param newPasswordResetForm
     */
    @Override
    public void doUpdatePassword(PasswordResetMailForm newPasswordResetForm) {
        newPasswordResetForm.setPassword(passwordEncoder.encode(newPasswordResetForm.getPassword()));
        User user = this.userDao.dbFindUserByAllEmail(newPasswordResetForm.getUser_email());
        user.setPassword(newPasswordResetForm.getPassword());
        this.userDao.dbUpdateUserPassword(user);

    }

    /**
     * <h2>doDeleteToken</h2>
     * <p>
     * 
     * </p>
     * 
     * @param token
     */
    @Override
    public void doDeleteToken(String token) {
        this.passwordResetDao.dbDeleteToken(token);

    }
}