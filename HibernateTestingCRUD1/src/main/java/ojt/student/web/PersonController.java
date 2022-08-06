package ojt.student.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.google.protobuf.TextFormat.ParseException;
import ojt.student.bl.service.person.PersonService;
import ojt.student.persistence.entity.Person;
import ojt.student.web.excel.PersonDataExcelExport;
import ojt.student.web.excel.PersonDataExcelImport;
import ojt.student.web.form.PersonForm;

/**
 * <h2>PersonController Class</h2>
 * <p>
 * Process for Displaying PersonController
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

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
    @RequestMapping(value = "/personList")
    public ModelAndView getStudentList(ModelAndView model) throws IOException {
        List<Person> PersonList = personService.getPersonList();
        model.addObject("PersonList", PersonList);
        model.setViewName("personList");
        return model;
    }

    /**
     * <h2>newPerson</h2>
     * <p>
     * 
     * </p>
     *
     * @param model
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/createPerson", method = RequestMethod.GET)
    public ModelAndView newPerson(ModelAndView model) {
        Person person = new Person();
        ModelAndView createPerson = new ModelAndView("createPerson");
        createPerson.addObject("rollBackPersonForm", person);
        createPerson.setViewName("createPerson");
        return createPerson;
    }

    /**
     * <h2>createStudentConfirm</h2>
     * <p>
     * 
     * </p>
     *
     * @param personForm
     * @param result
     * @param request
     * @param response
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "insertPerson", method = RequestMethod.POST)
    public ModelAndView createStudentConfirm(@ModelAttribute("rollBackPersonForm") @Valid PersonForm personForm,
            BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        this.personService.addPerson(personForm);
        ModelAndView createStudentView = new ModelAndView("redirect:/personList");

        return createStudentView;
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
    @RequestMapping(value = "/deletePerson", method = RequestMethod.GET)
    public ModelAndView deleteStudent(HttpServletRequest request) {
        int personId = Integer.parseInt(request.getParameter("id"));
        personService.deletePerson(personId);
        return new ModelAndView("redirect:/personList");
    }

    /**
     * <h2>updateUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param personForm
     * @param result
     * @param request
     * @param response
     * @return
     * @throws ParseException
     * @throws FileNotFoundException
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/editPerson", params = "update", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("finalConfirmUserForm") @Valid PersonForm personForm,
            BindingResult result, HttpServletRequest request, HttpServletResponse response)
            throws ParseException, FileNotFoundException, IOException {

        this.personService.updatePerson(personForm);
        ModelAndView updateUserView = new ModelAndView("redirect:/personList");
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
    @RequestMapping(value = "/updatePerson", method = RequestMethod.GET)
    public ModelAndView editStudent(@RequestParam("id") Integer personId, HttpServletRequest request) {
        PersonForm person = personService.getPerson(personId);
        ModelAndView model = new ModelAndView("updatePerson");
        model.addObject("person", person);
        model.setViewName("updatePerson");
        return model;
    }

    /**
     * <h2>exportToExcel</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @GetMapping("/downloadExcel")
    public ModelAndView exportToExcel() {
        ModelAndView mav = new ModelAndView();
        mav.setView(new PersonDataExcelExport());
        // read data from DB
        List<Person> list = personService.getPersonList();
        // send to excelImpl class
        mav.addObject("list1", list);
        return mav;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String importFromExcel(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        PersonDataExcelImport excelImporter = new PersonDataExcelImport();
        List<Person> listStudent = excelImporter.excelImport(file);

        for (Person person : listStudent) {
            PersonForm personForm = new PersonForm(person);
            System.out.println(personForm.getCountry());
            personService.addPerson(personForm);
        }

        return "Import SuccessFully";
    }
    
}