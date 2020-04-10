package com.hupa.exp.servermng.entity.candle;

import com.hupa.exp.common.entity.dto.output.BaseOutputDto;
import com.hupa.exp.servermng.entity.klineconfig.CandleBo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PcCandleStatisticsOutputDto extends BaseOutputDto {

    private Map<Integer, PcCandleData> candleStatisticsData;

    private Map<String, Map<String,Map<Integer, PcCandleData>>> PcCandleStatisticsData;

    public static class PcCandleData{
        private String assetSymbol;
        private List<String> timeList;
        private List<Integer> countList;
        private List<String> colorList;

        private List<CandleBo> dataLists = new ArrayList<CandleBo>();
        private List<String> volumeLists = new ArrayList<String>();
        private List<String> timeLists = new ArrayList<String>();

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

        public List<String> getColorList() {
            return colorList;
        }

        public void setColorList(List<String> colorList) {
            this.colorList = colorList;
        }

        public List<CandleBo> getDataLists() {
            return dataLists;
        }

        public void setDataLists(List<CandleBo> dataLists) {
            this.dataLists = dataLists;
        }

        public List<String> getVolumeLists() {
            return volumeLists;
        }

        public void setVolumeLists(List<String> volumeLists) {
            this.volumeLists = volumeLists;
        }

        public List<String> getTimeLists() {
            return timeLists;
        }

        public void setTimeLists(List<String> timeLists) {
            this.timeLists = timeLists;
        }
    }

    public Map<Integer, PcCandleData> getCandleStatisticsData() {
        return candleStatisticsData;
    }

    public void setCandleStatisticsData(Map<Integer, PcCandleData> candleStatisticsData) {
        this.candleStatisticsData = candleStatisticsData;
    }

    public Map<String, Map<String, Map<Integer, PcCandleData>>> getPcCandleStatisticsData() {
        return PcCandleStatisticsData;
    }

    public void setPcCandleStatisticsData(Map<String, Map<String, Map<Integer, PcCandleData>>> pcCandleStatisticsData) {
        PcCandleStatisticsData = pcCandleStatisticsData;
    }



}
