package com.california.mams.orm.model;

import java.util.Date;

public class MamsMenu {
    private Integer id;

    private String kid;

    private String type;

    private String p1Kid;

    private String name;

    private String uri;

    private Integer weight;

    private Date createTime;

    private Date updateTime;

    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid == null ? null : kid.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getP1Kid() {
        return p1Kid;
    }

    public void setP1Kid(String p1Kid) {
        this.p1Kid = p1Kid == null ? null : p1Kid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    @Override
    public String toString() {
        return "MamsMenu{" +
                "id=" + id +
                ", kid='" + kid + '\'' +
                ", type='" + type + '\'' +
                ", p1Kid='" + p1Kid + '\'' +
                ", name='" + name + '\'' +
                ", uri='" + uri + '\'' +
                ", weight=" + weight +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", comment='" + comment + '\'' +
                '}';
    }
}