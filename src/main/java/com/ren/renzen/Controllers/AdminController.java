package com.ren.renzen.Controllers;

import com.ren.renzen.CommandObjects.ArticleInfoComponentCO;
import com.ren.renzen.CommandObjects.CommunityInfoComponentCO;
import com.ren.renzen.DomainObjects.ArticleDO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {

    @GetMapping("/getAdminArticles")
    public ResponseEntity<CollectionModel<?>> getAllArticles() {

        List<ArticleInfoComponentCO> returnList = new ArrayList<>();
        for (ArticleDO articleDO : articleService.findAll()) {
            returnList.add(articleDO_to_articleStreamComponentCO.convert(articleDO));
        }
        return ResponseEntity.ok(CollectionModel.wrap(returnList));
    }

    @GetMapping("/getAdminCommunities")
    public CollectionModel<CommunityInfoComponentCO> getAllCommunities(Principal principal) {
        return (communityStreamCOAssembler.toCollectionModel(communityService.findAll(principal.getName())));
    }

}
