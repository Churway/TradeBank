package com.churway.controller;

import com.churway.entity.Item;
import com.churway.model.ItemDto;
import com.churway.service.ItemService;
import com.churway.utils.BaseController;
import com.github.pagehelper.PageInfo;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/churway/Item")
public class ItemController extends BaseController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/add")
    public String add(Item item, HttpServletRequest request) {
        if (item.getStartDate().getTime() < new Date().getTime() + 60000)
            return "tradebank/error";
        item.setSellerId(super.getUser(request).getId());
        item.setCreateTime(new Date());
        item.setCurrentPrice(item.getStartPrice());
        item.setState(1);
        item.setEndDate(new Date(item.getStartDate().getTime()+item.getSurviveTime()*60000));
        try {
            itemService.add(item);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "tradebank/error";
        }
        return "tradebank/success";
    }
    @ResponseBody
    @RequestMapping("/listBuyer")
    public PageInfo<ItemDto> listBuyer(Integer page, Integer size,HttpServletRequest request) {
        return itemService.listItemDtoBuyer(page,size,super.getUser(request).getId());
    }

    @ResponseBody
    @RequestMapping("/listSeller")
    public PageInfo<ItemDto> listSeller(Integer page, Integer size,HttpServletRequest request) {
        return itemService.listItemDtoSeller(page,size,super.getUser(request).getId());
    }
}