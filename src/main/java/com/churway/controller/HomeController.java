package com.churway.controller;

import com.churway.utils.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController extends BaseController {
	@Value("${image.visit.preUrl}")
	String visitPreUrl;

	@RequestMapping("/")
	public String home() {
		return "tradebank/home";
	}

	@RequestMapping("/openWork/{work}")
	public String openWork(@PathVariable("work") String work, HttpServletRequest request) {
		if("scan".equals(work)||"mygoods".equals(work))
			request.setAttribute("preUrl",visitPreUrl);
		return "tradebank/" + work;
	}

	@RequestMapping({"/success","/info"})
	public String success() {
		return "tradebank/success";
	}

	@RequestMapping({"/setAction"})
	public String setAction(Long goodsId) {
		return "tradebank/setAction";
	}
}