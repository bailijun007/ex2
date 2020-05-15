package com.hupa.exp.servermng.entity.robotmarketrootconfig;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;
import com.hupa.exp.common.entity.dto.output.BasePageOutputDto;


/**
 * Created by Administrator on 2020/2/15.
 */
public class RobotMarketRootConfigOutputDto extends BaseOutputDto {

    private long id;

    private String createOrderUrl;

    private String cancelOrderUrl;

    private String queryOrderUrl;

    private Long ctime;

    private Long mtime;

    private Integer expAreaType;

    public RobotMarketRootConfigOutputDto() {
    }

    @Override
    public String toString() {
        return "RobotMarketRootConfigOutputDto{" +
                "id=" + id +
                ", createOrderUrl='" + createOrderUrl + '\'' +
                ", cancelOrderUrl='" + cancelOrderUrl + '\'' +
                ", queryOrderUrl='" + queryOrderUrl + '\'' +
                ", ctime=" + ctime +
                ", mtime=" + mtime +
                ", expAreaType=" + expAreaType +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Integer getExpAreaType() {
        return expAreaType;
    }

    public void setExpAreaType(Integer expAreaType) {
        this.expAreaType = expAreaType;
    }
}
