package com.ren.renzen.Services;

import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.DomainObjects.DiscussionDO;
import com.ren.renzen.Repositories.ArticleRepository;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.DiscussionService;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    final ArticleRepository articleRepository;
    final DiscussionService discussionService;

    public ArticleServiceImpl(ArticleRepository articleRepository, DiscussionService discussionService) {
        this.articleRepository = articleRepository;
        this.discussionService = discussionService;
    }

    @Override
    public ArticleDO save(ArticleDO articleDO) {
        DiscussionDO discussionDO = new DiscussionDO();
        discussionService.save(discussionDO);
        articleDO.setDiscussionID(discussionDO.get_id());
        return articleRepository.save(articleDO);
    }

    @Override
    public Iterable<ArticleDO> getArticleDOList() {
        return articleRepository.findAll();
    }

    @Override
    public Iterable<ArticleDO> findArticleDOsByCommunityID(ObjectId communityId) {
        return articleRepository.findArticleDOSByCommunityID(communityId);
    }

    @Override
    public ArticleDO findBy_id(ObjectId Id) {

        Optional<ArticleDO> articleDOOptional = articleRepository.findBy_id(Id);

        if (articleDOOptional.isPresent()){
            return articleDOOptional.get();
        }else{
            throw new ResourceNotFoundException("Article Not Found");
        }
    }

    @Override
    public List<ArticleDO> findBy_idIn(List<ObjectId> objectIdList) {
        return articleRepository.findBy_idIn(objectIdList);
    }

    @Override
    public void delete(ArticleDO articleDO) {
        articleRepository.delete(articleDO);
    }

    @Override
    public Iterable<ArticleDO> findAll() {
        return articleRepository.findAll();
    }


    /**
     * gets first 10 results
     * @return
     */
    @Override
    public List<ArticleDO> findAllPage() {
        var paging = PageRequest.of(0,10, Sort.by("_id").descending());
        return articleRepository.findAll(paging).getContent();
    }

    @Override
    public List<ArticleDO> findAllByCommunityIDAndTopic(ObjectId communityID, String topic){
        return articleRepository.findAllByCommunityIDAndTopic(communityID,topic);
    }
}