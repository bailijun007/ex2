package com.hupa.exp.servermng.entity.candle;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.util.List;
import java.util.Map;

public class PcCandleStatisticsOutputDto extends BaseOutputDto {

    private Map<String, Map<String,Map<Integer, PcCandleData>>> PcCandleStatisticsData;

    public Map<String, Map<String, Map<Integer, PcCandleData>>> getPcCandleStatisticsData() {
        return PcCandleStatisticsData;
    }

    public void setPcCandleStatisticsData(Map<String, Map<String, Map<Integer, PcCandleData>>> pcCandleStatisticsData) {
        PcCandleStatisticsData = pcCandleStatisticsData;
    }

    public static class PcCandleData{
    private String assetSymbol;
    private List<String> timeList;
    private List<Integer> countList;
    private List<String> colorList;

        public List<String> getColorList() {
            return colorList;
        }

        public void setColorList(List<String> colorList) {
            this.colorList = colorList;
        }

        public String getAssetSymbol() {
        return assetSymbol;
    }

    public void setAssetSymbol(String assetSymbol) {
        this.assetSymbol = assetSymbol;
    }

    public List<String> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<String> timeList) {
        this.timeList = timeList;
    }

    public List<Integer> getCountList() {
        return countList;
    }

    public void setCountList(List<Integer> countList) {
        this.countList = countList;
    }
    }
}
