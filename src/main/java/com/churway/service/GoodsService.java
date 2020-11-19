package com.churway.service;

import com.churway.dao.GoodsMapper;
import com.churway.entity.Goods;
import com.churway.model.EsGoods;
import com.churway.model.GoodsDto;
import com.churway.model.SysUser;
import com.churway.repository.EsGoodsRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private ImgService imgService;

	@Autowired
	EsGoodsRepository esGoodsRepository;

	public void add(EsGoods goods) {
		esGoodsRepository.save(goods);
	}

	public GoodsMapper getGoodsMapper() {
		return goodsMapper;
	}

	@Transactional
	public void add(GoodsDto goodsDto, SysUser sysUser, List<String> pathList) {
		Goods goods = new Goods();
		BeanUtils.copyProperties(goodsDto,goods);
		goods.setCreateTime(new Date());
		goods.setState(1);
		goods.setOwnerId(sysUser.getId());
		goods.setOwnerName(sysUser.getAccount());
		goodsMapper.insert(goods);
		imgService.add(pathList,goods.getId());
	}


	public List<Goods> selectAll() {
		return goodsMapper.selectAll();
	}

	public int delete(Long id) {
		Goods crt = new Goods();
		crt.setId(id);
		crt.setState(0);
		return goodsMapper.updateByPrimaryKeySelective(crt);
	}

    public PageInfo<Goods> showMyGoods(Integer page, Integer size, Date startDate, Date endDate,Long ownerId) {
		PageHelper.startPage(page,size);
		List<Goods> list = goodsMapper.selectByCreateDate(startDate, endDate, ownerId);
		PageInfo<Goods> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	public Goods findById(Long id) {
		return goodsMapper.selectByPrimaryKey(id);
	}

    public int update(Goods goods) {
		return goodsMapper.updateByPrimaryKeySelective(goods);
    }
}