package com.churway.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2020/11/13
 * @since 1.0.0
 */
@Document(indexName = "tradebank",type = "goods")
public class EsGoods implements Serializable {
    private Long id;

    @Field(type = FieldType.String,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String name;

    private Integer typeId;

    private String mainImg;

    private Long ownerId;

    private String ownerName;

    private Integer state;

    private Date createTime;

    @Field(type = FieldType.String,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "EsGoods{" +
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
