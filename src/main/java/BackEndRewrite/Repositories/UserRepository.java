package BackEndRewrite.Repositories;

import BackEndRewrite.DomainObjects.UserDO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDO,String> {
    UserDO findByUsername(String username);
}
