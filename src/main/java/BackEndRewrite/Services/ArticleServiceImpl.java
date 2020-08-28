package BackEndRewrite.Services;

import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.ArticleComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.Services.Interfaces.ArticleService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Override
    public Optional<ArticleDO> getArticleDOList() {
        return Optional.empty();
    }

    @Override
    public Optional<Set<ArticleDO>> findArticleDOsByCommunityID(String communityId) {
        return Optional.empty();
    }

    @Override
    public Optional<ArticleDO> findArticleDOByID(String Id) {
        return Optional.empty();
    }

    @Override
    public Optional<ArticleComponentCO> findArticleComponentCOByID(String Id) {
        return Optional.empty();
    }
}
