package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.candle.PcCandleStatisticsInputDto;
import com.hupa.exp.servermng.entity.candle.PcCandleStatisticsOutputDto;

public interface IApiPcCandleControllerService {


    PcCandleStatisticsOutputDto getPcCandleStatisticsData(PcCandleStatisticsInputDto inputDto) throws BizException;

    PcCandleStatisticsOutputDto getBbCandleStatisticsData(PcCandleStatisticsInputDto inputDto) throws BizException;





}
