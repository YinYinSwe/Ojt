package ojt.student.bl.service.person;

import java.util.List;

import ojt.student.persistence.entity.Person;
import ojt.student.web.form.PersonForm;

public interface PersonService {

    public List<Person> getPersonList();

    /**
     * <h2>addStudent</h2>
     * <p>
     * Adding new Student
     * </p>
     *
     * @param studentForm
     */
    public void addPerson(PersonForm personForm);

    /**
     * <h2>deleteStudent</h2>
     * <p>
     * Deleting a Student
     * </p>
     *
     * @param studentId
     * @return void
     */
    public void deletePerson(Integer personId);

    /**
     * <h2>getStudent</h2>
     * <p>
     * 
     * </p>
     *
     * @param empid
     * @return
     * @return Student
     */
    public PersonForm getPerson(int empid);

    /**
     * <h2>updateStudent</h2>
     * <p>
     * 
     * </p>
     * 
     * @param studentForm
     */
    public void updatePerson(PersonForm person);

}