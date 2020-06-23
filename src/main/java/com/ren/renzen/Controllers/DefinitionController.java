package com.ren.renzen.Controllers;

import com.ren.renzen.Entities.Definition;
import com.ren.renzen.Repos.X_Definition_Repository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter @Setter
@RestController
@RequestMapping("/Definitions")
public class DefinitionController {

    private X_Definition_Repository mongoRepo;

    public DefinitionController(X_Definition_Repository mongoRepo){
        this.mongoRepo=mongoRepo;
    }

    //, produces = MediaType.APPLICATION_JSON_VALUE
    @GetMapping()
    public List<Definition> getDefinitions(){
        return this.mongoRepo.findAll();
    }

    @PostMapping()
    public String createDefinition(@RequestBody Definition definition){

        System.out.println(definition.getId());
        System.out.println(definition.getDefinition());

        Definition x = mongoRepo.save(definition);


        return ("Hello from Def: "+x.getDefinition());

    }
}
