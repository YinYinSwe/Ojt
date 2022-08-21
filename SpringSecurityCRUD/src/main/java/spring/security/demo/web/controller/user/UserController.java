package spring.security.demo.web.controller.user;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.protobuf.TextFormat.ParseException;

import spring.security.demo.bl.service.user.UserService;
import spring.security.demo.persistence.entity.Authority;
import spring.security.demo.persistence.entity.User;
import spring.security.demo.web.form.UserForm;

/**
 * <h2>UserController Class</h2>
 * <p>
 * Process for Displaying UserController
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
@Controller
public class UserController {
    @Autowired
    private JavaMailSender mailSender;
    /**
     * <h2>passwordEncoder</h2>
     * <p>
     * passwordEncoder
     * </p>
     */

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
     * <h2>getStudentList</h2>
     * <p>
     * 
     * </p>
     *
     * @param model
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = { "/home", "/userList" }, method = RequestMethod.GET)
    public ModelAndView getStudentList(ModelAndView model) throws IOException {
        List<User> userList = userService.getUserList();
        model.addObject("PersonList", userList);
        model.setViewName("userList");
        return model;
    }

    /**
     * <h2>newUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param model
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public ModelAndView newUser(ModelAndView model) {
        UserForm user = new UserForm();
        ModelAndView createUser = new ModelAndView("createUser");
        List<Authority> authoList = userService.getAuthorityList();
        createUser.addObject("AuthorityList", authoList);
        createUser.addObject("rollBackUserForm", user);
        createUser.setViewName("createUser");
        return createUser;
    }

    /**
     * <h2>createUserConfirm</h2>
     * <p>
     * 
     * </p>
     *
     * @param userForm
     * @param result
     * @param request
     * @param response
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public ModelAndView createUserConfirm(@ModelAttribute("rollBackUserForm") @Valid UserForm userForm,
            BindingResult result, HttpServletRequest request, HttpServletResponse response) {

        List<Authority> authoList = userService.getAuthorityList();

        if (result.hasErrors()) {
            ModelAndView createUser = new ModelAndView("createUser");
            createUser.addObject("AuthorityList", authoList);
            createUser.addObject("errorMsg", messageSource.getMessage("M_SC_0007", null, null));
            return createUser;
        }

        Authority authority = userService.getAuthorityById(userForm.getAuthority().getId());
        System.out.println(authority.getId());
        System.out.println(authority.getName());
        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(authority);
        userForm.setAuthorities(authorities);
        userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
        this.userService.addUser(userForm);
        ModelAndView createUserView = new ModelAndView("redirect:/userList");
        return createUserView;

    }

    /**
     * <h2>updateUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param userForm
     * @param result
     * @param request
     * @param response
     * @return
     * @throws ParseException
     * @throws FileNotFoundException
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/editUser", params = "update", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("editedPersonForm") @Valid UserForm userForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response)
            throws ParseException, FileNotFoundException, IOException {
        Authority authority = userService.getAuthorityById(userForm.getAuthority().getId());
        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(authority);
        userForm.setAuthorities(authorities);
        this.userService.updateUser(userForm);
        ModelAndView updateUserView = new ModelAndView("redirect:/userList");
        return updateUserView;
    }

    /**
     * <h2>editStudent</h2>
     * <p>
     * 
     * </p>
     *
     * @param personId
     * @param request
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public ModelAndView editStudent(@RequestParam("id") long personId, HttpServletRequest request) {
        UserForm person = userService.getUser(personId);
        ModelAndView model = new ModelAndView("updateUser");
        List<Authority> authoList = userService.getAuthorityList();
        for (Authority authority : person.getAuthorities()) {
            person.setAuthority(authority);
        }
        model.addObject("AuthorityList", authoList);
        model.addObject("editedPersonForm", person);
        model.setViewName("updateUser");
        return model;
    }

    /**
     * <h2>deleteStudent</h2>
     * <p>
     * 
     * </p>
     *
     * @param request
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public ModelAndView deleteStudent(HttpServletRequest request) {
        long personId = Long.parseLong(request.getParameter("id"));
        userService.deleteUser(personId);
        return new ModelAndView("redirect:/userList");
    }

    /**
     * <h2>userPage</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return String
     */
    @RequestMapping(value = "/user")
    public String userPage() {
        return "user";
    }

    /**
     * <h2>adminPage</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return String
     */
    @RequestMapping(value = "/admin")
    public String adminPage() {
        return "admin";
    }

    /**
     * <h2>error</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return String
     */
    @RequestMapping(value = "/error")
    public String error() {
        return "access-denied";
    }
}