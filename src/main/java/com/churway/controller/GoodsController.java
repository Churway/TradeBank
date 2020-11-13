package com.churway.controller;

import com.churway.model.GoodsDto;
import com.churway.model.SysUser;
import com.churway.service.GoodsService;
import com.churway.service.ImgService;
import com.churway.utils.BaseController;
import com.churway.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/churway/Goods")
public class GoodsController extends BaseController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ImgService imgService;

    @Value("${image.parent.path}")
    String parentPath;

    @ResponseBody
    @RequestMapping("/add")
    public String add(GoodsDto goodsDto, HttpServletRequest request) {
        List<MultipartFile> imgs = goodsDto.getImgs();
        List<String> pathList = new ArrayList<>();
        try {
            for (MultipartFile i : imgs) {
                String path = UUID.randomUUID() + "-" + i.getOriginalFilename();
                i.transferTo(new File(parentPath + path));
                pathList.add(path);
            }
            goodsDto.setMainImg(pathList.get(0));
        } catch (IOException e) {
            return "Error when transfer images,please try later.";
        }
        String token = super.getCookieVal(request, "token");
        SysUser sysUser = JwtUtils.getObject(token, SysUser.class);
        goodsService.add(goodsDto, sysUser);
        imgService.add(pathList, goodsDto.getId());
        return "success";
    }

}