package MClient.models.databaseSample;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Ben on 20/05/2016.
 */
@Document(collection = "people")
public class MongoPerson {
    @Id
    private String id;

    private int systemNumber;
    private String gender;
    private String firstName;
    private String lastName;
    private int age;
    private String job;

    public MongoPerson() {}

    public MongoPerson(int systemNumber, String gender, String firstName, String lastName, int age, String job) {

        this.systemNumber = systemNumber;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.job = job;
    }

    public String getId() {
        return id;
    }

//    public void setId(String id) {
//        this.id = id;
//    }

    public int getSystemNumber() {
        return systemNumber;
    }

    public void setSystemNumber(int systemNumber) {
        this.systemNumber = systemNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "MongoPerson{" +
                "id='" + id + '\'' +
                ", gender='" + gender + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                '}';
    }
}
