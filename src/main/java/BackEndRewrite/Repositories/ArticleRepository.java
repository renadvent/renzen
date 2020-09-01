package BackEndRewrite.Repositories;

import BackEndRewrite.DomainObjects.ArticleDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleDO,String> {
    Iterable<ArticleDO> findArticleDOSByCommunityID(String id);

}
