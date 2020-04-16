package com.hupa.exp.servermng.entity.wsclient;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;


public class WsClientOutputDto extends BaseOutputDto {

    private String id;
    private String wsIp;
    private String wsPort;
    private String clientNumber;
    private Integer type;
    private boolean success;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
