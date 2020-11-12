package com.churway.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Item implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "goods_id")
    private Long goodsId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "survive_time")
    private Integer surviveTime;

    @Column(name = "start_price")
    private BigDecimal startPrice;

    @Column(name = "min_delta_price")
    private BigDecimal minDeltaPrice;

    @Column(name = "current_price")
    private BigDecimal currentPrice;

    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "create_time")
    private Date createTime;

    private Integer state;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return goods_id
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * @param goodsId
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * @return start_date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return survive_time
     */
    public Integer getSurviveTime() {
        return surviveTime;
    }

    /**
     * @param surviveTime
     */
    public void setSurviveTime(Integer surviveTime) {
        this.surviveTime = surviveTime;
    }

    /**
     * @return start_price
     */
    public BigDecimal getStartPrice() {
        return startPrice;
    }

    /**
     * @param startPrice
     */
    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    /**
     * @return min_delta_price
     */
    public BigDecimal getMinDeltaPrice() {
        return minDeltaPrice;
    }

    /**
     * @param minDeltaPrice
     */
    public void setMinDeltaPrice(BigDecimal minDeltaPrice) {
        this.minDeltaPrice = minDeltaPrice;
    }

    /**
     * @return current_price
     */
    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    /**
     * @param currentPrice
     */
    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     * @return buyer_id
     */
    public Long getBuyerId() {
        return buyerId;
    }

    /**
     * @param buyerId
     */
    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
    }
}