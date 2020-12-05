package com.ren.renzen.Controllers;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.ren.renzen.Converters.ArticleConverter;
import com.ren.renzen.Converters.CommunityConverter;
import com.ren.renzen.Converters.ProfileConverter;
import com.ren.renzen.ModelAssemblers.ArticleAssembler;
import com.ren.renzen.ModelAssemblers.CommunityAssembler;
import com.ren.renzen.ModelAssemblers.ProfileAssembler;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.UserService;
import com.ren.renzen.Services.MapValidationErrorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.ren.renzen.Controllers.Constants.CONTROLLER_PATHS.Article.OPEN_ARTICLE_DRAFT_FROM_APP;

@Controller
public class OpenFromAppController {


    //services
    final UserService userService;
    final ArticleService articleService;
    final CommunityService communityService;

    //converters
    final ArticleConverter.ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO;
    final ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;
    final ProfileConverter.ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;
    final ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
    final CommunityConverter.CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;
    final CommunityConverter.CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

    //assemblers
    final ArticleAssembler.ArticleTabCOAssembler articleTabCOAssembler;
    final ProfileAssembler.ProfileStreamCOAssembler profileStreamCOAssembler;
    final ProfileAssembler.ProfileTabCOAssembler profileTabCOAssembler;
    final CommunityAssembler.CommunityTabCOAssembler communityTabCOAssembler;
    final CommunityAssembler.CommunityStreamCOAssembler communityStreamCOAssembler;
    final ArticleAssembler.ArticleStreamCOAssembler articleStreamCOAssembler;
    //ERROR MAP
    final MapValidationErrorService mapValidationErrorService;
    //AZURE
    BlobServiceClient blobServiceClient;
    BlobContainerClient containerClient;

    public OpenFromAppController(UserService userService, ArticleService articleService, CommunityService communityService, ArticleConverter.ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileConverter.ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityConverter.CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityConverter.CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleAssembler.ArticleTabCOAssembler articleTabCOAssembler, ProfileAssembler.ProfileStreamCOAssembler profileStreamCOAssembler, ProfileAssembler.ProfileTabCOAssembler profileTabCOAssembler, CommunityAssembler.CommunityTabCOAssembler communityTabCOAssembler, CommunityAssembler.CommunityStreamCOAssembler communityStreamCOAssembler, ArticleAssembler.ArticleStreamCOAssembler articleStreamCOAssembler, MapValidationErrorService mapValidationErrorService) {
        this.userService = userService;
        this.articleService = articleService;
        this.communityService = communityService;
        this.articleDO_to_articleTabComponentCO = articleDO_to_articleTabComponentCO;
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
        this.profileDO_to_profileTabComponentCO = profileDO_to_profileTabComponentCO;
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
        this.communityDO_to_communityTabComponentCO = communityDO_to_communityTabComponentCO;
        this.communityDO_to_communityStreamComponentCO = communityDO_to_communityStreamComponentCO;
        this.articleTabCOAssembler = articleTabCOAssembler;
        this.profileStreamCOAssembler = profileStreamCOAssembler;
        this.profileTabCOAssembler = profileTabCOAssembler;
        this.communityTabCOAssembler = communityTabCOAssembler;
        this.communityStreamCOAssembler = communityStreamCOAssembler;
        this.articleStreamCOAssembler = articleStreamCOAssembler;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @RequestMapping(OPEN_ARTICLE_DRAFT_FROM_APP)
    public String openDraftFromApp(
            @RequestParam String articleID,
            @RequestParam String token,
//            BindingResult result, Principal principal,
            Model model
    ) {
        model.addAttribute("source", "createNewArticle");
        model.addAttribute("articleID", articleID);
        model.addAttribute("token", token);

        return "index";

    }
}
