package com.churway.controller;

import com.churway.service.GoodsService;
import com.churway.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/churway/Goods")
public class GoodsController extends BaseController {
	@Autowired
	private GoodsService goodsService;

}