package com.churway.dao;

import com.churway.entity.Item;
import com.churway.model.ItemDto;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ItemMapper extends Mapper<Item> {
    ItemDto selectItemDto(Long id);

    ItemDto selectItemDtoByGoodsId(Long id);

    List<ItemDto> selectItemDtoBuyer(Long buyerId);

    List<ItemDto> selectItemDtoSeller(Long sellerId);
}