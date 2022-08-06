package ojt.student.persistence.dao.student;

import java.util.List;
import ojt.student.persistence.entity.Person;

/**
 * <h2> PersonDao Class</h2>
 * <p>
 * Process for Displaying PersonDao
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
public interface PersonDao {
    public List<Person> getPersonList();

    /**
     * <h2>addStudent</h2>
     * <p>
     * Adding new Student
     * </p>
     *
     * @param student
     * @return void
     */
    public void addPerson(Person person);

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
     * @param studentId
     * @return
     * @return Student
     */
    public Person getPerson(int personId);

    /**
     * <h2>updateStudent</h2>
     * <p>
     * 
     * </p>
     *
     * @param student
     * @return
     * @return Student
     */
    public void updatePerson(Person person);
}