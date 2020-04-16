package com.hupa.exp.servermng.entity.wsclient;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;


public class WsClientInputDto extends BaseInputDto {

    private Long id;
    private String wsIp;
    private String wsPort;
    private String clientNumber;
    private Integer type;
    private Integer currentPage;
    private Integer pageSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getWsIp() {
        return wsIp;
    }

    public void setWsIp(String wsIp) {
        this.wsIp = wsIp;
    }

    public String getWsPort() {
        return wsPort;
    }

    public void setWsPort(String wsPort) {
        this.wsPort = wsPort;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
