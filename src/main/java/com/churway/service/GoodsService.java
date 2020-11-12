package com.churway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.churway.entity.Goods;
import com.churway.dao.GoodsMapper;

@Transactional
@Service
public class GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;

	public GoodsMapper getGoodsMapper() {
		return goodsMapper;
	}

}