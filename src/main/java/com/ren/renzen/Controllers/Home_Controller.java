package com.ren.renzen.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home_Controller {

	@RequestMapping()
	public String index() {
		return "index";
	}

}

