package com.hupa.exp.bizother.entity;

import java.util.List;

public class ExpMenuTreeBizBo {


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer sort;


    public List<ExpMenuTreeBizBo> getChildren() {
        return children;
    }

    public void setChildren(List<ExpMenuTreeBizBo> children) {
        this.children = children;
    }
    public Integer getMenulevel() {
        return menulevel;
    }

    public void setMenulevel(Integer menulevel) {
        this.menulevel = menulevel;
    }
    public String text;

    public long id;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl;
    }

    public  boolean enable;

public  String menuurl;

    public Integer menulevel;

    public List<ExpMenuTreeBizBo> children;
}
