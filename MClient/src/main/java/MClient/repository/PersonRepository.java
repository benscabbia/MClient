package MClient.repository;

import MClient.models.databaseSample.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Ben on 27/04/2016.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAll();
    Person findByAge(int age);

    Person findByLastName(String lastName);
}
