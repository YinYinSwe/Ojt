package spring.security.demo.web.controller.user;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.security.demo.bl.service.user.PasswordResetService;
import spring.security.demo.bl.service.user.UserService;
import spring.security.demo.web.form.PasswordResetForm;
import spring.security.demo.web.form.PasswordResetMailForm;

/**
 * <h2>PasswordFormController Class</h2>
 * <p>
 * Process for Displaying PasswordFormController
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
@Controller
public class PasswordFormController {
    /**
     * <h2>mailSender</h2>
     * <p>
     * mailSender
     * </p>
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * <h2>userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;

    /**
     * <h2>messageSource</h2>
     * <p>
     * messageSource
     * </p>
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * <h2>passwordResetService</h2>
     * <p>
     * passwordResetService
     * </p>
     */
    @Autowired
    private PasswordResetService passwordResetService;

    /**
     * <h2>email</h2>
     * <p>
     * 
     * </p>
     *
     * @param request
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/forgot_password", method = RequestMethod.GET)
    public ModelAndView email(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("emailSend");
        model.addObject("emailForm", new PasswordResetMailForm());
        model.setViewName("emailSend");
        return model;
    }

    /**
     * <h2>sendEmailToClient</h2>
     * <p>
     * 
     * </p>
     *
     * @param passwordResetMailForm
     * @param result
     * @param request
     * @param response
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public ModelAndView sendEmailToClient(
            @Valid @ModelAttribute("emailForm") PasswordResetMailForm passwordResetMailForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {

        if (result.hasErrors()) {
            ModelAndView model = new ModelAndView("emailSend");
            model.addObject("errorMsg", messageSource.getMessage("M_SC_0007", null, null));
            return model;
        }
        if (!userService.doIsEmailExist(passwordResetMailForm.getUser_email())) {
            ModelAndView model = new ModelAndView("emailSend");
            model.addObject("errorMsg", "Invalid email address!");
            return model;
        }
        passwordResetMailForm = this.passwordResetService.createResetToken(passwordResetMailForm.getUser_email());
        String url = getBaseUrl(request) + request.getServletPath() + "/" + passwordResetMailForm.getToken();
        this.sendMail(url, passwordResetMailForm);
        ModelAndView newModel = new ModelAndView("sendMailSuccess");
        newModel.addObject("msg", "Password Reset link has been sent!");
        return newModel;

    }

    /**
     * <h2>sendMail</h2>
     * <p>
     * 
     * </p>
     *
     * @param url
     * @param passwordResetMailForm
     * @return void
     */
    private void sendMail(String url, PasswordResetMailForm passwordResetMailForm) {
        String sender = "htetn4494@gmail.com";
        String subject = "Reset Your Password";
        String body = "Reset your password from following url : \n";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(passwordResetMailForm.getUser_email());
        email.setFrom(sender);
        email.setSubject(subject);
        email.setText(body + url);
        mailSender.send(email);

    }

    /**
     * <h2>showResetPassword</h2>
     * <p>
     * 
     * </p>
     *
     * @param token
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/sendEmail/{token}", method = RequestMethod.GET)
    public ModelAndView showResetPassword(@PathVariable String token) {
        ModelAndView mv = new ModelAndView("invalidMail");
        PasswordResetMailForm passwordResetForm = passwordResetService.getDataByToken(token);
        if (passwordResetForm == null) {
            mv.addObject("errorMsg", "Invalid Token! Please check your token url!");
            return mv;
        }
        if (isTokenExpired(passwordResetForm.getExpired_at())) {
            mv.addObject("errorMsg", "Token has been expired!");
            return mv;
        }
        PasswordResetForm passwordChangeResetForm = new PasswordResetForm();
        passwordChangeResetForm.setToken(token);
        mv.setViewName("passwordReset");
        mv.addObject("passwordResetForm", passwordChangeResetForm);
        return mv;
    }

    /**
     * <h2>isTokenExpired</h2>
     * <p>
     * 
     * </p>
     *
     * @param expired_at
     * @return
     * @return boolean
     */
    private boolean isTokenExpired(Timestamp expired_at) {
        Timestamp now = new Timestamp(new Date().getTime());
        return now.after(expired_at);
    }

    /**
     * <h2>resetPassword</h2>
     * <p>
     * 
     * </p>
     *
     * @param passwordResetForm
     * @param result
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/password/reset", method = RequestMethod.POST)
    public ModelAndView resetPassword(@Valid @ModelAttribute("passwordResetForm") PasswordResetForm passwordResetForm,
            BindingResult result) {

//        if (result.hasErrors()) {
//            return new ModelAndView("passwordReset");
//        }
        String userEmail = passwordResetService.getDataByToken(passwordResetForm.getToken()).getUser_email();
        PasswordResetMailForm newPasswordResetForm = new PasswordResetMailForm();
        newPasswordResetForm.setUser_email(userEmail);
        newPasswordResetForm.setPassword(passwordResetForm.getPassword());
        this.passwordResetService.doUpdatePassword(newPasswordResetForm);
        this.passwordResetService.doDeleteToken(passwordResetForm.getToken());
        ModelAndView mv = new ModelAndView("sendMailSuccess");
        mv.addObject("msg", "Password has been changed!");
        return mv;

    }

    /**
     * <h2>getBaseUrl</h2>
     * <p>
     * 
     * </p>
     *
     * @param request
     * @return
     * @return String
     */
    private String getBaseUrl(HttpServletRequest request) {
        String url = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 0) {
            url = url + ":" + request.getServerPort();
        }
        url = url + request.getContextPath();
        return url;
    }
}