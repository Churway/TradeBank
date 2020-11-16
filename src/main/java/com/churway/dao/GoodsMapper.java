package com.churway.dao;

import com.churway.entity.Goods;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface GoodsMapper extends Mapper<Goods> {
    List<Goods> selectByCreateDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("ownerId") Long ownerId);
}