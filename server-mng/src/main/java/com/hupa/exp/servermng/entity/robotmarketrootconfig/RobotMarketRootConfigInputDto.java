package com.hupa.exp.servermng.entity.robotmarketrootconfig;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

/**
 * Created by Administrator on 2020/2/15.
 */
public class RobotMarketRootConfigInputDto extends BaseInputDto {

    private Long id;
    private Integer pageNo;
    private Integer pageSize;
    private Integer expAreaType;
    private String createOrderUrl;
    private String cancelOrderUrl;
    private String queryOrderUrl;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public String getCreateOrderUrl() {
        return createOrderUrl;
    }

    public void setCreateOrderUrl(String createOrderUrl) {
        this.createOrderUrl = createOrderUrl;
    }

    public String getCancelOrderUrl() {
        return cancelOrderUrl;
    }

    public void setCancelOrderUrl(String cancelOrderUrl) {
        this.cancelOrderUrl = cancelOrderUrl;
    }

    public String getQueryOrderUrl() {
        return queryOrderUrl;
    }

    public void setQueryOrderUrl(String queryOrderUrl) {
        this.queryOrderUrl = queryOrderUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getExpAreaType() {
        return expAreaType;
    }

    public void setExpAreaType(Integer expAreaType) {
        this.expAreaType = expAreaType;
    }
}
