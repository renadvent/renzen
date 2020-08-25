package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import com.ren.renzen.Repos.Article_Repository;
import com.ren.renzen.Repos.Community_Repository;
import com.ren.renzen.Repos.User_Repository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin("*")
public class SiteController {
    final User_Repository user_repository;
    final Community_Repository community_repository;
    final Article_Repository article_repository;

    public SiteController(User_Repository user_repository, Community_Repository community_repository, Article_Repository article_repository) {
        this.user_repository = user_repository;
        this.community_repository = community_repository;
        this.article_repository = article_repository;
    }

    @RequestMapping()
    public String index(){
        return "index";
    }

    @RequestMapping(path="/register")
    @ResponseBody
    public ProfileTabComponentCO Register(@RequestBody UserNamePassword payload){
        return null;
    }

    static class UserNamePassword{
        String username;
        String password;

        //public UserNamePassword(){};
        public UserNamePassword(String username,String password){
            this.username=username;
            this.password=password;
        }
    }

}
