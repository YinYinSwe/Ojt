package ojt.student.web.form;

import javax.validation.constraints.NotEmpty;
import ojt.student.persistence.entity.Person;

public class PersonForm {
    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public PersonForm() {
        super();
    }

    public PersonForm(Person person) {
        super();
        this.id = person.getId();
        this.name = person.getName();
        this.country = person.getCountry();

    }

}
