package com.hupa.exp.servermng.entity.klineconfig;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

import java.util.List;

/**
 * Created by Administrator on 2020/3/18.
 */
public class KlineInfoInputDto extends BaseInputDto {

    private Long id;
    private String asset;
    private String symbol;
    private String interval;
    private Integer klineType;
    private List<RepairKlineOutputDto> rows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public List<RepairKlineOutputDto> getRows() {
        return rows;
    }

    public void setRows(List<RepairKlineOutputDto> rows) {
        this.rows = rows;
    }

    public Integer getKlineType() {
        return klineType;
    }

    public void setKlineType(Integer klineType) {
        this.klineType = klineType;
    }
}
