package com.ren.renzen.Controllers;


import com.ren.renzen.Entities.X_Page;
import com.ren.renzen.Repos.X_Page_Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class Page_Controller {

    X_Page_Repository mongoRep;

    Page_Controller(X_Page_Repository mongoRep){
        this.mongoRep=mongoRep;
    }

    @PostMapping()
    public String createPage(@RequestBody X_Page page){
        X_Page x = this.mongoRep.save(page);
        return "New Page "+x.getPageName();
    }
}
