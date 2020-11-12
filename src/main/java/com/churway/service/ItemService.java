package com.churway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.churway.entity.Item;
import com.churway.dao.ItemMapper;

@Transactional
@Service
public class ItemService {
	@Autowired
	private ItemMapper itemMapper;

	public ItemMapper getItemMapper() {
		return itemMapper;
	}

}