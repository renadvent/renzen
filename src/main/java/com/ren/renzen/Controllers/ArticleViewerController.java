package com.ren.renzen.Controllers;

import com.ren.renzen.CommandObjects.ArticleInfoComponentCO;
import com.ren.renzen.CommandObjects.ArticleTabComponentCO;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class ArticleViewerController {


    //TODO update toModel
    @GetMapping(path = "/getArticleStreamComponentCO/{id}")
    public ArticleInfoComponentCO getArticleStreamComponentCO(@PathVariable ObjectId id) {
        return articleStreamCOAssembler.toModel(articleService.findBy_id(id));
    }

    //TODO update toModel
    @GetMapping(path = "/getArticleTabComponentCO/{id}")
    public ArticleTabComponentCO getArticleTabComponentCO(@PathVariable ObjectId id) {
        return articleTabCOAssembler.toModel(articleService.findBy_id(id));
    }


}
