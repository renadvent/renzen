package BackEndRewrite.Repositories;

import BackEndRewrite.DomainObjects.ArticleDO;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleDO, ObjectId> {
    Optional<ArticleDO> findBy_id(ObjectId id);
    Iterable<ArticleDO> findArticleDOSByCommunityID(ObjectId objectId);

    List<ArticleDO> findAllByCommunityIDAndTopic(ObjectId communityID, String Topic);
}
