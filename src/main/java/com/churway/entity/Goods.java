package com.churway.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class Goods implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String name;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "main_img")
    private String mainImg;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "owner_name")
    private String ownerName;

    //0:不可用 1:未设置竞拍 2:等待竞拍 3:正在竞拍
    private Integer state;

    @Column(name = "create_time")
    private Date createTime;

    private String description;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return type_id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * @param typeId
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * @return main_img
     */
    public String getMainImg() {
        return mainImg;
    }

    /**
     * @param mainImg
     */
    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    /**
     * @return owner_id
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * @return owner_name
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * @param ownerName
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", mainImg='" + mainImg + '\'' +
                ", ownerId=" + ownerId +
                ", ownerName='" + ownerName + '\'' +
                ", state=" + state +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                '}';
    }
}