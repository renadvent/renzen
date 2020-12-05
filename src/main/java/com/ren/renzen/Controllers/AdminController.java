package com.ren.renzen.Controllers;

import com.ren.renzen.Converters.ArticleConverter;
import com.ren.renzen.ResourceObjects.CommandObjects.ArticleDTOs;
import com.ren.renzen.ResourceObjects.CommandObjects.CommunityDTOs;
import com.ren.renzen.ResourceObjects.DomainObjects.ArticleDO;
import com.ren.renzen.ModelAssemblers.CommunityStreamCOAssembler;
import com.ren.renzen.ModelAssemblers.ProfileStreamCOAssembler;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.ren.renzen.Controllers.CONTROLLER_PATHS.Admin.*;

@RestController
public class AdminController {

    final ArticleService articleService;
    final CommunityService communityService;
    final UserService userService;

    final ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;

    final CommunityStreamCOAssembler communityStreamCOAssembler;
    final ProfileStreamCOAssembler profileStreamCOAssembler;

    public AdminController(ArticleService articleService, CommunityService communityService, UserService userService, ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, CommunityStreamCOAssembler communityStreamCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler) {
        this.articleService = articleService;
        this.communityService = communityService;
        this.userService = userService;
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
        this.communityStreamCOAssembler = communityStreamCOAssembler;
        this.profileStreamCOAssembler = profileStreamCOAssembler;
    }
    //final CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

    //@PostMapping(path="/deleteArticle")

    @GetMapping(GET_ADMIN_ARTICLES)
    public ResponseEntity<CollectionModel<?>> getAllArticles() {

        List<ArticleDTOs.ArticleInfoComponentCO> returnList = new ArrayList<>();
        for (ArticleDO articleDO : articleService.findAll()) {
            returnList.add(articleDO_to_articleStreamComponentCO.convertDomainToFullView(articleDO));
        }
        return ResponseEntity.ok(CollectionModel.wrap(returnList));
    }

    @GetMapping(GET_ADMIN_COMMUNITIES)
    public CollectionModel<CommunityDTOs.CommunityInfoComponentCO> getAllCommunities(Principal principal) {
        return (communityStreamCOAssembler.assembleDomainToFullModelViewCollection(communityService.findAll(principal.getName())));
    }

    @GetMapping(GET_ADMIN_PROFILES)
    public ResponseEntity<CollectionModel<?>> getAllProfiles() {
        return ResponseEntity
                .ok(profileStreamCOAssembler.assembleDomainToFullModelViewCollection(userService.findAll()));
    }

}
