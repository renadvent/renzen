package com.ren.renzen.Services;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.azure.storage.common.sas.SasProtocol;
import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.DomainObjects.DiscussionDO;
import com.ren.renzen.Repositories.ArticleRepository;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.DiscussionService;
import com.ren.renzen.additional.KEYS;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    final ArticleRepository articleRepository;
    final DiscussionService discussionService;

    public ArticleServiceImpl(ArticleRepository articleRepository, DiscussionService discussionService) {
        this.articleRepository = articleRepository;
        this.discussionService = discussionService;


;

    }

    @Override
    public ArticleDO save(ArticleDO articleDO) {
        DiscussionDO discussionDO = new DiscussionDO();
        discussionService.save(discussionDO);
        articleDO.setDiscussionID(discussionDO.get_id());
        return articleRepository.save(articleDO);
    }

    @Override
    public ArticleDO findBy_id(ObjectId Id) {

        //return articleRepository.findBy_id(Id).stream().map(Optional::get)

        Optional<ArticleDO> articleDOOptional = articleRepository.findBy_id(Id);

        if (articleDOOptional.isPresent()) {
            return articleDOOptional.get();
        } else {
            throw new ResourceNotFoundException("Article Not Found");
        }
    }






    @Override
    public List<ArticleDO> findBy_idIn(List<ObjectId> objectIdList) {

        //for now skips missing objects if deleted

//        return articleRepository.findBy_idIn(objectIdList).stream()
//                .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

        return articleRepository.findBy_idIn(objectIdList);
    }

    @Override
    public List<ArticleDO> findAll() {
        return (articleRepository.findAll());
    }

    /**
     * gets first 10 results
     *
     * @return
     */
    @Override
    public List<ArticleDO> findAllPage() {
        var paging = PageRequest.of(0, 10, Sort.by("_id").descending());
        return articleRepository.findAll(paging).getContent();
    }

}
