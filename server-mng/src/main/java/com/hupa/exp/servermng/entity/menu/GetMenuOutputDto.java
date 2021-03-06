package com.hupa.exp.servermng.entity.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class GetMenuOutputDto extends BaseOutputDto {
    private Long id;
    @JsonProperty("menu_name")
    private String menuname;
    private Integer parentmenuid;
    @JsonProperty("menu_url")
    private String menuurl;
    private Integer sort;
    private boolean enable;
    private String icon;
    private Long ctime;
    private Long mtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public Integer getParentmenuid() {
        return parentmenuid;
    }

    public void setParentmenuid(Integer parentmenuid) {
        this.parentmenuid = parentmenuid;
    }

    public String getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getMtime() {
        return mtime;
    }

    public void setMtime(Long mtime) {
        this.mtime = mtime;
    }
}
