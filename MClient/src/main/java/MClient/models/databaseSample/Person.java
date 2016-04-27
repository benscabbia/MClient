package MClient.models.databaseSample;

import javax.persistence.*;

/**
 * Created by Ben on 27/04/2016.
 */
@Entity
@Table(name="person")
public class Person {

    public Person() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PersonID")
    private long personId;
    @Column(name = "Firstname")
    private String firstName;

    @Column(name = "Surname")
    private String lastName;

    @Column(name = "Age")
    private int age;

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}