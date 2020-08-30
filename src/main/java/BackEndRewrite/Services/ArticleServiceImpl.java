package BackEndRewrite.Services;

import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.DomainObjects.DiscussionDO;
import BackEndRewrite.Repositories.ArticleRepository;
import BackEndRewrite.Services.Interfaces.ArticleService;
import BackEndRewrite.Services.Interfaces.DiscussionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

        //broken

        DiscussionDO discussionDO = new DiscussionDO();
        discussionService.save(discussionDO);
        articleDO.setDiscussionID(discussionDO.getId());
        return articleRepository.save(articleDO);

    }

    @Override
    public Iterable<ArticleDO> getArticleDOList() {
        return articleRepository.findAll();
    }

    @Override
    public Iterable<ArticleDO> findArticleDOsByCommunityID(String communityId) {
        return articleRepository.findArticleDOSByCommunityID(communityId);
    }

    @Override
    public ArticleDO findArticleDOByID(String Id) {

        Optional<ArticleDO> articleDOOptional = articleRepository.findById(Id);

        if (articleDOOptional.isPresent()){
            return articleDOOptional.get();
        }else{
            throw new RuntimeException("Article Not Found");
        }
    }

    @Override
    public void delete(ArticleDO articleDO) {
        articleRepository.delete(articleDO);
    }
}
