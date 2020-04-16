package com.hupa.exp.servermng.entity.candle;

import com.hupa.exp.common.entity.dto.input.BaseInputDto;

public class PcCandleStatisticsInputDto extends BaseInputDto {

    private String asset;
    private String symbol;
    private String interval;
    private String year;
    private String month;
    private String day;
    private String klineType;
    private String type;

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getKlineType() {
        return klineType;
    }

    public void setKlineType(String klineType) {
        this.klineType = klineType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
