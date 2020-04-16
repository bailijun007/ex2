package com.hupa.exp.servermng.entity.klineconfig;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.util.List;

public class KlineConfigInputDto extends BaseInputDto {
    private long id;
    private String asset;
    private String symbol;
    private Integer klineType;
    private String klineInterval;
    private Long statTime;
    private Long endTime;
    private boolean status;
    private Integer type;
    private List<RepairKlineOutputDto> rows;
    private String expName;

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getStatTime() {
        return statTime;
    }

    public void setStatTime(Long statTime) {
        this.statTime = statTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getKlineInterval() {
        return klineInterval;
    }

    public void setKlineInterval(String klineInterval) {
        this.klineInterval = klineInterval;
    }

    public Integer getKlineType() {
        return klineType;
    }

    public void setKlineType(Integer klineType) {
        this.klineType = klineType;
    }

    public List<RepairKlineOutputDto> getRows() {
        return rows;
    }

    public void setRows(List<RepairKlineOutputDto> rows) {
        this.rows = rows;
    }

    public String getExpName() {
        return expName;
    }

    public void setExpName(String expName) {
        this.expName = expName;
    }
}
