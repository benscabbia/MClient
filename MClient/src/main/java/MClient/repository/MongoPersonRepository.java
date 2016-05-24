package MClient.repository;

import MClient.models.databaseSample.MongoPerson;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Ben on 20/05/2016.
 */
public interface MongoPersonRepository extends MongoRepository<MongoPerson, String> {

    List<MongoPerson> findAll();

    public MongoPerson findByFirstName(String firstName);
    public MongoPerson findBySystemNumber(int systemNumber);

    //public MongoPerson findOne(BasicQuery query);
}
