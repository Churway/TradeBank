package com.churway.service;

import com.churway.dao.GoodsMapper;
import com.churway.entity.Goods;
import com.churway.model.GoodsDto;
import com.churway.model.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Service
public class GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;

	public GoodsMapper getGoodsMapper() {
		return goodsMapper;
	}

	public int add(GoodsDto goodsDto, SysUser sysUser) {
		Goods goods = new Goods();
		BeanUtils.copyProperties(goodsDto,goods);
		goods.setCreateTime(new Date());
		goods.setState(1);
		goods.setOwnerId(sysUser.getId());
		goods.setOwnerName(sysUser.getAccount());
		return goodsMapper.insert(goods);
	}
}