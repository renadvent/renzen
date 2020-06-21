package com.ren.renzen.Controllers;

import com.ren.renzen.Entities.Employee;
import com.ren.renzen.Repos.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("/mongo")
public class PostController {

    private EmployeeRepository mongoRepo;

    public PostController(EmployeeRepository mongoRepo){
        this.mongoRepo=mongoRepo;
    }

    @RequestMapping(method=RequestMethod.GET)
    public List<Employee> getNotes(){

        return this.mongoRepo.findAll();

    }

    @RequestMapping(method = RequestMethod.POST)
    public String testPost(@RequestBody Employee employee){

        Employee x = mongoRepo.save(employee);

        x.getFirstName();

        return ("Hello from Post: "+x.getFirstName());
    }


}
