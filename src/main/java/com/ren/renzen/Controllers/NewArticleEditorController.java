package com.ren.renzen.Controllers;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.ren.renzen.Converters.*;
import com.ren.renzen.ModelAssemblers.*;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.UserService;
import com.ren.renzen.Services.MapValidationErrorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.ren.renzen.Controllers.CONTROLLER_PATHS.Article.OPEN_ARTICLE_DRAFT_FROM_APP;

@Controller
public class NewArticleEditorController {


    //services
    final UserService userService;
    final ArticleService articleService;
    final CommunityService communityService;

    //converters
    final ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO;
    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;
    final ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;
    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
    final CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;
    final CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

    //assemblers
    final ArticleTabCOAssembler articleTabCOAssembler;
    final ProfileStreamCOAssembler profileStreamCOAssembler;
    final ProfileTabCOAssembler profileTabCOAssembler;
    final CommunityTabCOAssembler communityTabCOAssembler;
    final CommunityStreamCOAssembler communityStreamCOAssembler;
    final ArticleStreamCOAssembler articleStreamCOAssembler;
    //ERROR MAP
    final MapValidationErrorService mapValidationErrorService;
    //AZURE
    BlobServiceClient blobServiceClient;
    BlobContainerClient containerClient;

    public NewArticleEditorController(UserService userService, ArticleService articleService, CommunityService communityService, ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleTabCOAssembler articleTabCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler, ProfileTabCOAssembler profileTabCOAssembler, CommunityTabCOAssembler communityTabCOAssembler, CommunityStreamCOAssembler communityStreamCOAssembler, ArticleStreamCOAssembler articleStreamCOAssembler, MapValidationErrorService mapValidationErrorService) {
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

//    @RequestMapping(NEW_CREATE_ARTICLE)
//    public String newCreateArticle(
////            @RequestBody NewCreateArticlePayload payload,
//                                   @RequestParam String image,
//                                   @RequestParam String token,
//                                    @RequestParam String link,
////                                   BindingResult result, Principal principal,
//                                   Model model) {
//        //might not need principal
//
//        model.addAttribute("source","createNewArticle");
//        model.addAttribute("token",token);
//        model.addAttribute("image",image);
//        model.addAttribute("link",link);
//
//        return "index";

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

//    }

//    @RequestMapping(OPEN_ARTICLE_DRAFT_FROM_APP)
//    public String openDraftFromApp(
//            @RequestParam String articleID,
//            @RequestParam String token,
//            Model model
//    ){
//        model.addAttribute("articleID",articleID);
//        model.addAttribute("token",token);
//
//        return "index";
//
//    }

}
