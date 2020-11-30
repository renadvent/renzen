package com.ren.renzen.Controllers;

import com.microsoft.rest.v2.annotations.GET;
import com.ren.renzen.ModelAssemblers.ArticleStreamCOAssembler;
import com.ren.renzen.ModelAssemblers.ArticleTabCOAssembler;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.ren.renzen.Controllers.CONTROLLER_PATHS.Article.GET_ARTICLE_STREAM_COMPONENTCO;
import static com.ren.renzen.Controllers.CONTROLLER_PATHS.Article.GET_ARTICLE_TAB_COMPONENTCO;

@RestController
public class ArticleViewerController {

    //SERVICES
    final UserService userService;
    final ArticleService articleService;

    //ASSEMBLERS
    final ArticleStreamCOAssembler articleStreamCOAssembler;
    final ArticleTabCOAssembler articleTabCOAssembler;

    public ArticleViewerController(UserService userService, ArticleService articleService, ArticleStreamCOAssembler articleStreamCOAssembler, ArticleTabCOAssembler articleTabCOAssembler) {
        this.userService = userService;
        this.articleService = articleService;
        this.articleStreamCOAssembler = articleStreamCOAssembler;
        this.articleTabCOAssembler = articleTabCOAssembler;
    }

    @GetMapping(GET_ARTICLE_STREAM_COMPONENTCO)
    public ResponseEntity<?> getArticleStreamComponentCO(@PathVariable ObjectId id, Principal principal) {




        var articleDO = articleService.findBy_id(id);

//        if (!userService.findByUsername(principal.getName()).equals(articleDO.getCreatorName())) {
        if (principal==null || !principal.getName().equals(articleDO.getCreatorName())) {
            //GET PUBLIC VERSION
            return ResponseEntity.ok(articleStreamCOAssembler.assembleDomainToPublicModelView(articleDO));
        } else {
            //GET FULL VERSION
            return ResponseEntity.ok(articleStreamCOAssembler.assembleDomainToFullModelView(articleDO));
        }
    }

    //TODO update toModel
    @GetMapping(GET_ARTICLE_TAB_COMPONENTCO)
    public ResponseEntity<?> getArticleTabComponentCO(@PathVariable ObjectId id, Principal principal) {
        var articleDO = articleService.findBy_id(id);

        //if (!userService.findByUsername(principal.getName()).equals(articleDO.getCreatorName())) {
        if (principal==null || !principal.getName().equals(articleDO.getCreatorName())) {
            //GET PUBLIC VERSION
            return ResponseEntity.ok(articleTabCOAssembler.assembleDomainToPublicModelView(articleDO));
        } else {
            //GET FULL VERSION
            return ResponseEntity.ok(articleTabCOAssembler.assembleDomainToFullModelView(articleDO));
        }
    }
}
