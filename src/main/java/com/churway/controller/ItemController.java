package com.churway.controller;

import com.churway.service.ItemService;
import com.churway.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/churway/Item")
public class ItemController extends BaseController {
	@Autowired
	private ItemService itemService;

}