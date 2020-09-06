package BackEndRewrite.Repositories;

import BackEndRewrite.DomainObjects.ProfileDO;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<ProfileDO, ObjectId> {
    Optional<ProfileDO> findByUsername(String username);
    Optional<ProfileDO> findById(ObjectId objectId);
    ProfileDO findBy_id(ObjectId _id);
    List<ProfileDO> findAllBy_id(List<ObjectId> objectIdList);
}
