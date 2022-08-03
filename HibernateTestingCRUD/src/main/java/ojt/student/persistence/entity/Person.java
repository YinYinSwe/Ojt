package ojt.student.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import ojt.student.web.form.PersonForm;

/**
 * <h2> Person Class</h2>
 * <p>
 * Process for Displaying Person
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
@Entity
@Table(name="PERSON")
public class Person {
    private int id;
    private String name;
    private String country;

    /**
     * <h2> Constructor for Person </h2>
     * <p>
     * Constructor for Person
     * </p>
     */
    public Person() {
        super();
    }

    /**
     * <h2> Constructor for Person </h2>
     * <p>
     * Constructor for Person
     * </p>
     * @param id
     * @param name
     * @param country
     */
    public Person(int id, String name, String country) {
        super();
        this.id = id;
        this.name = name;
        this.country = country;
    }
    /**
     * <h2> getId</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return int
     */
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /**
     * <h2> setId</h2>
     * <p>
     * 
     * </p>
     *
     * @param id
     * @return void
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * <h2> getName</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return String
     */
    @Column(name="name")
    public String getName() {
        return name;
    }

    /**
     * <h2> setName</h2>
     * <p>
     * 
     * </p>
     *
     * @param name
     * @return void
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * <h2> getCountry</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return String
     */
    @Column(name="country")
    public String getCountry() {
        return country;
    }

    /**
     * <h2> setCountry</h2>
     * <p>
     * 
     * </p>
     *
     * @param country
     * @return void
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * <h2> toString </h2>
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    @Override
    public String toString(){
        return "id="+id+", name="+name+", country="+country;
    }
    /**
     * <h2> Constructor for Person </h2>
     * <p>
     * Constructor for Person
     * </p>
     * @param personForm
     */
    public Person(PersonForm personForm) {
        this.id = personForm.getId();
        this.name = personForm.getName();
        this.country=  personForm.getCountry();
       
    }
}
