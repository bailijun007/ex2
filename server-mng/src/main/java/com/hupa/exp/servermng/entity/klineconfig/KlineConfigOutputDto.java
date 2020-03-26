package com.hupa.exp.servermng.entity.klineconfig;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class KlineConfigOutputDto extends BaseOutputDto {
    private long id;

    private boolean bn;

    private String msg;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isBn() {
        return bn;
    }

    public void setBn(boolean bn) {
        this.bn = bn;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
