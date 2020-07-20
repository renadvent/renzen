package com.ren.renzen.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
public class Home_Controller {
	@RequestMapping()
	public String index() {
		return "index";
	}
}

