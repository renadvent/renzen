package BackEndRewrite.Repositories;

import BackEndRewrite.DomainObjects.ArticleDO;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<ArticleDO,String> {
}
