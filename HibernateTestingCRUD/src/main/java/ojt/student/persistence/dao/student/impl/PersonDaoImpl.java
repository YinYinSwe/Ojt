package ojt.student.persistence.dao.student.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ojt.student.persistence.dao.student.PersonDao;
import ojt.student.persistence.entity.Person;

/**
 * <h2>PersonDaoImpl Class</h2>
 * <p>
 * Process for Displaying PersonDaoImpl
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
@Repository
@Transactional
public class PersonDaoImpl implements PersonDao {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * <h2>getStudentList</h2>
     * <p>
     * Getting all students
     * </p>
     *
     * @return List<Student>
     */
    @SuppressWarnings("unchecked")
    public List<Person> getPersonList() {
        return sessionFactory.getCurrentSession().createQuery("from Person").list();
    }

    /**
     * <h2>addPerson</h2>
     * <p>
     * 
     * </p>
     * 
     * @param person
     */
    public void addPerson(Person person) {
        sessionFactory.getCurrentSession().saveOrUpdate(person);
    }

    /**
     * <h2>deletePerson</h2>
     * <p>
     * 
     * </p>
     * 
     * @param personId
     */
    @Override
    public void deletePerson(Integer personId) {
        Person person = (Person) sessionFactory.getCurrentSession().load(Person.class, personId);
        if (null != person) {
            this.sessionFactory.getCurrentSession().delete(person);
        }
    }

    /**
     * <h2>updatePerson</h2>
     * <p>
     * 
     * </p>
     * 
     * @param person
     */
    public void updatePerson(Person person) {
        this.sessionFactory.getCurrentSession().update(person);
    }

    /**
     * <h2>getPerson</h2>
     * <p>
     * 
     * </p>
     * 
     * @param personId
     * @return
     */
    @Override
    public Person getPerson(int personId) {
        System.out.println(personId);
        String studentHqlQuery = "SELECT s FROM Person s where s.id = :id";
        Query queryStudentById = this.sessionFactory.getCurrentSession().createQuery(studentHqlQuery);
        queryStudentById.setParameter("id", personId);
        Person resultStudent = (Person) queryStudentById.uniqueResult();
        return resultStudent;
    }
}