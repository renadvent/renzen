package BackEndRewrite.Services;

import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.ArticleComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.DomainObjects.DiscussionDO;
import BackEndRewrite.Repositories.ArticleRepository;
import BackEndRewrite.Services.Interfaces.ArticleService;
import BackEndRewrite.Services.Interfaces.DiscussionService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ArticleServiceImpl implements ArticleService {

    final ArticleRepository articleRepository;
    final DiscussionService discussionService;

    public ArticleServiceImpl(ArticleRepository articleRepository, DiscussionService discussionService) {
        this.articleRepository = articleRepository;
        this.discussionService = discussionService;
    }

    /**
     * creates a discussion for article, and saves what it is passed
     * @param articleDO
     * @return
     */
    @Override
    public ArticleDO save(ArticleDO articleDO) {

        DiscussionDO discussionDO = new DiscussionDO();
        discussionService.save(discussionDO);
        articleDO.setDiscussionID(discussionDO.getId());
        return articleRepository.save(articleDO);

    }

    @Override
    public ArticleDO getArticleDOList() {
        return null;
    }

    @Override
    public Set<ArticleDO> findArticleDOsByCommunityID(String communityId) {
        return null;
    }

    @Override
    public ArticleDO findArticleDOByID(String Id) {
        return null;
    }

    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }
}
