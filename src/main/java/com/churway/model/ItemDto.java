package com.churway.model;

import com.churway.entity.Goods;

import java.math.BigDecimal;
import java.util.Date;

public class ItemDto{

    private Long id;


    private Long goodsId;


    private Date startDate;

    private Date endDate;


    private Integer surviveTime;


    private BigDecimal startPrice;


    private BigDecimal minDeltaPrice;


    private BigDecimal currentPrice;


    private Long sellerId;


    private Long buyerId;


    private Date createTime;

    //0:被删除的 1:竞拍尚未开始 2:竞拍进行中 3:竞拍成交 4:竞拍流拍
    private Integer state;

    private Goods goods;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getSurviveTime() {
        return surviveTime;
    }

    public void setSurviveTime(Integer surviveTime) {
        this.surviveTime = surviveTime;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public BigDecimal getMinDeltaPrice() {
        return minDeltaPrice;
    }

    public void setMinDeltaPrice(BigDecimal minDeltaPrice) {
        this.minDeltaPrice = minDeltaPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}