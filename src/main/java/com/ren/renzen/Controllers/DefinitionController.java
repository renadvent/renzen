package com.ren.renzen.Controllers;

import com.ren.renzen.Entities.Definition;
import com.ren.renzen.Repos.X_Definition_Repository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("/Definitions")
public class DefinitionController {

    private X_Definition_Repository mongoRepo;

    public DefinitionController(X_Definition_Repository mongoRepo){
        this.mongoRepo=mongoRepo;
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<Definition> getDefinitions(){
        return this.mongoRepo.findAll();
    }
}
