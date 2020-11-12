package com.churway.controller;

import com.churway.service.TypeService;
import com.churway.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/churway/Type")
public class TypeController extends BaseController {
	@Autowired
	private TypeService typeService;

}