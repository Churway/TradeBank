package com.churway.service;

import com.churway.dao.ItemMapper;
import com.churway.entity.Goods;
import com.churway.entity.Item;
import com.churway.entity.ReadyToActionJob;
import com.churway.model.ItemDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.quartz.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private Scheduler scheduler;

    public ItemMapper getItemMapper() {
        return itemMapper;
    }

    public void add(Item item) throws SchedulerException {
        Goods goods = new Goods();
        goods.setId(item.getGoodsId());
        goods.setState(2);
        goodsService.update(goods);
        itemMapper.insert(item);
        rabbitTemplate.convertAndSend("oneMinDelayQ", item);
        //定时开始拍卖
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
        JobDetail build = JobBuilder.newJob(ReadyToActionJob.class).withIdentity(item.getId().toString(), "action").usingJobData("id", item.getId()).build();
        SimpleTrigger trigger = TriggerBuilder.newTrigger().startAt(item.getStartDate()).withIdentity(item.getId().toString(), "action").withSchedule(SimpleScheduleBuilder.simpleSchedule()).build();
        scheduler.scheduleJob(build, trigger);
    }

    public void startAction(Long id) {
        Item item = itemMapper.selectByPrimaryKey(id);
        Goods goods = new Goods();
        goods.setId(item.getGoodsId());
        goods.setState(3);
        goodsService.update(goods);
        item.setState(2);
        itemMapper.updateByPrimaryKey(item);
        rabbitTemplate.convertAndSend("tenMinDelayQ", item);
    }

    public void stopAction(Long id) {
        Item item = itemMapper.selectByPrimaryKey(id);
        Goods goods = new Goods();
        goods.setId(item.getGoodsId());
        if (item.getBuyerId() == null) {
            item.setState(4);
            goods.setState(1);
        } else {
            item.setState(3);
            goods.setState(0);
        }
        goodsService.update(goods);
        itemMapper.updateByPrimaryKey(item);
    }

    public ItemDto findItemDto(Long id) {
        return itemMapper.selectItemDto(id);
    }

    public Item findItemById(Long id) {
        return itemMapper.selectByPrimaryKey(id);
    }

    public ItemDto findItemDtoByGoodsId(Long id) {
        return itemMapper.selectItemDtoByGoodsId(id);
    }

    public int update(Item item) {
        return itemMapper.updateByPrimaryKey(item);
    }

    public PageInfo<ItemDto> listItemDtoBuyer(Integer page, Integer size, Long buyerId) {
        PageHelper.startPage(page, size);
        List<ItemDto> list = itemMapper.selectItemDtoBuyer(buyerId);
        return new PageInfo<>(list);
    }

    public PageInfo<ItemDto> listItemDtoSeller(Integer page, Integer size, Long sellerId) {
        PageHelper.startPage(page, size);
        List<ItemDto> list = itemMapper.selectItemDtoSeller(sellerId);
        return new PageInfo<>(list);
    }
}