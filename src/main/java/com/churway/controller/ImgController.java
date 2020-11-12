package com.churway.controller;

import com.churway.service.ImgService;
import com.churway.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/churway/Img")
public class ImgController extends BaseController {
	@Autowired
	private ImgService imgService;

}