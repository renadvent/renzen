package BackEndRewrite.Services;

import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.Repositories.UserRepository;
import BackEndRewrite.Services.Interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Accesses User Repository
 */

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    private final MongoTemplate mongoTemplate;
    final MongoOperations mongoOperations;

    public UserServiceImpl(UserRepository userRepository, MongoTemplate mongoTemplate, MongoOperations mongoOperations) {
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
        this.mongoOperations = mongoOperations;
    }
    //final MongoClients mongoClients;


    @Override
    public ProfileDO save(ProfileDO profileDO) {
        return userRepository.save(profileDO);
    }

    @Override
    public Iterable<ProfileDO> getProfileDOList() {
        return userRepository.findAll();
    }

    @Override
    public ProfileDO findProfileDOById(ObjectId id) {

        //Optional<ProfileDO> profileDOOptional = userRepository.findById(id);

        //Optional<ProfileDO> p = userRepository.findById(id);

        //MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "Profiles");
        //Query query = new Query(Criteria.where("Id").is(id.toString()));
        //ProfileDO profileDOtest = mongoTemplate.findOne(query, ProfileDO.class);

//        System.out.println(mongoOperations.findOne(query, ProfileDO.class));
//
//        System.out.println(mongoOperations.findById(query, ProfileDO.class,"Profiles"));

        return userRepository.findBy_id(id);
        //return mongoOperations.findById(query, ProfileDO.class,"Profiles");

        //MongoCollection<Document> coll = mongoClients.create().getDatabase("renzen").getCollection("Profiles");

        //return mongoOperations.findOne(query, ProfileDO.class);

        //return profileDOtest;

//        if (profileDOOptional.isEmpty()){
//            throw new ResourceNotFoundException("id not found");
//        }else {
//            return profileDOOptional.get();
//        }
    }

    @Override
    public ProfileDO findProfileDOByName(String profileName) {

        Optional<ProfileDO> profileDOOptional = userRepository.findByUsername(profileName);

        if (profileDOOptional.isEmpty()){
            throw new ResourceNotFoundException("id not found");
        }else {
            return profileDOOptional.get();
        }

    }

    /**
     * used by register controller
     * @param name
     * @return
     */
    @Override
    public boolean checkIfUsernameTaken(String name) {

        if (userRepository.findByUsername(name).isPresent()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ProfileDO findProfileDOByNameAndPassword(String name, String password) {


        Optional<ProfileDO> profileDOOptional = userRepository.findByUsername(name);

        if (profileDOOptional.isPresent()){
            if (profileDOOptional.get().getPassword() == password){
                return profileDOOptional.get();
            }else{
                throw new RuntimeException("username/password not found");
            }
        }else{
            throw new RuntimeException("username/password not found");
        }
    }

    @Override
    public ProfileDO saveAndReturnProfileDO(ProfileDO profileDO) {
        return userRepository.save(profileDO);
    }

}
