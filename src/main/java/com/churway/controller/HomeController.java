package com.churway.controller;

import com.churway.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends BaseController {

	@RequestMapping("/")
	public String home() {
		return "tradebank/home";
	}

	@RequestMapping("/openWork/{work}")
	public String openWork(@PathVariable("work") String work) {
		return "tradebank/" + work;
	}

	@RequestMapping({"/success","/info"})
	public String success() {
		return "tradebank/success";
	}

}