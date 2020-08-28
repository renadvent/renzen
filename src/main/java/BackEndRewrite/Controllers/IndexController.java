package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.TabComponentCOs.CommunityTabComponentCO;
import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.Converters.CommunityDO_to_CommunityTabComponentCO;
import BackEndRewrite.Converters.ProfileDO_to_ProfileTabComponentCO;
import BackEndRewrite.DomainObjects.CommunityDO;
import BackEndRewrite.DomainObjects.DiscussionDO;
import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.Repositories.ArticleRepository;
import BackEndRewrite.Repositories.CommunityRepository;
import BackEndRewrite.Repositories.DiscussionRepository;
import BackEndRewrite.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@CrossOrigin("*")
public class IndexController {
    /**
     * returns the index home page
     * @return
     */
    @RequestMapping()
    public String index(){
        return "index";
    }




}
