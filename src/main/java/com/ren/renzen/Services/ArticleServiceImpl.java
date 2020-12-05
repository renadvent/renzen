package com.ren.renzen.Services;

import com.ren.renzen.ResourceObjects.DomainObjects.ArticleDO;
import com.ren.renzen.Repositories.ArticleRepository;
import com.ren.renzen.Services.Interfaces.ArticleService;
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

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<ArticleDO> findAllByCreatorIDAndWorkName(ObjectId creator, String workName) {
        return articleRepository.findAllByCreatorIDAndWorkName(creator, workName);
    }

    @Override
    public ArticleDO save(ArticleDO articleDO) {
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

    @Override
    public List<ArticleDO> findAllPage() {
        return null;
    }

    /**
     * gets first 10 results
     *
     * @return
     */
    @Override
    public List<ArticleDO> findAllPage(int page) {
        var paging = PageRequest.of(page, 1, Sort.by("_id").descending());
        return articleRepository.findAll(paging).getContent();
    }


    @Override
    public List<ArticleDO> findByIsDraft(boolean published, int page) {


        return articleRepository.findByIsDraft(published,PageRequest.of(page, 1, Sort.by("_id").descending()));
    }



    @Override
    public Boolean deleteArticle(ObjectId id) {

        articleRepository.deleteById(id);

        //articleRepository.delete();

        return true;
    }



}
