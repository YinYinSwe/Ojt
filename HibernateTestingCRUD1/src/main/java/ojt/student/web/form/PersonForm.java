package ojt.student.web.form;

import javax.validation.constraints.NotEmpty;
import ojt.student.persistence.entity.Person;

/**
 * <h2> PersonForm Class</h2>
 * <p>
 * Process for Displaying PersonForm
 * </p>
 * 
 * @author Yin Yin Swe
 *
 */
public class PersonForm {
    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String country;

    /**
     * <h2> getId</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return int
     */
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
     * <h2> Constructor for PersonForm </h2>
     * <p>
     * Constructor for PersonForm
     * </p>
     */
    public PersonForm() {
        super();
    }

    /**
     * <h2> Constructor for PersonForm </h2>
     * <p>
     * Constructor for PersonForm
     * </p>
     * @param person
     */
    public PersonForm(Person person) {
        super();
        this.id = person.getId();
        this.name = person.getName();
        this.country = person.getCountry();

    }

}