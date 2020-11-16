package com.churway.controller;

import com.churway.service.ItemService;
import com.churway.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/churway/Item")
public class ItemController extends BaseController {
	@Autowired
	private ItemService itemService;

	@ResponseBody
	@RequestMapping("/add")
	public String add(){
		System.out.println("----");
		return "success";
	}

}