package com.churway.controller;

import com.churway.entity.Type;
import com.churway.service.TypeService;
import com.churway.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/churway/Type")
public class TypeController extends BaseController {
	@Autowired
	private TypeService typeService;

	@ResponseBody
	@RequestMapping("/getTypes")
	public List<Type> getTypes(){
		return typeService.listType();
	}

}