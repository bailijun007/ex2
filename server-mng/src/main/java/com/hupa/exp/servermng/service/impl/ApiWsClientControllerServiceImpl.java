package com.hupa.exp.servermng.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IWsClientConfigDao;
import com.hupa.exp.daomysql.entity.po.expv2.WsClientConfigPo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.wsclient.WsClientInputDto;
import com.hupa.exp.servermng.entity.wsclient.WsClientListOutputDto;
import com.hupa.exp.servermng.entity.wsclient.WsClientOutputDto;
import com.hupa.exp.servermng.service.def.IApiWsClientControllerService;
import com.hupa.exp.util.http.HttpUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiWsClientControllerServiceImpl implements IApiWsClientControllerService {

    private Logger logger = LoggerFactory.getLogger(ApiWsClientControllerServiceImpl.class);

    @Autowired
    private IWsClientConfigDao iWsClientConfigDao;

    @Override
    public WsClientOutputDto createOrEdit(WsClientInputDto inputDto) throws BizException {
        WsClientOutputDto outputDto=new WsClientOutputDto();
        try{
            WsClientConfigPo wsClientConfigPo = new WsClientConfigPo();
            wsClientConfigPo.setId(inputDto.getId());
            wsClientConfigPo.setWsIp(inputDto.getWsIp());
            wsClientConfigPo.setWsPort(inputDto.getWsPort());
            wsClientConfigPo.setMtime(System.currentTimeMillis());
            wsClientConfigPo.setCtime(System.currentTimeMillis());
            Map<String,Object> param = new HashMap<>();
            StringBuilder builder = new StringBuilder();
            builder.append("http://");
            builder.append(wsClientConfigPo.getWsIp());
            builder.append(":");
            builder.append(wsClientConfigPo.getWsPort());
            builder.append("/http/ws/client/status/query");
            //String requestUrl ="http://"+configPo.getIp()+":"+configPo.getPort()+"/http/ws/client/status/query";
            // http://192.168.0.90:8081/http/ws/client/status/query
            String res = HttpUtil.getHttpClient().setUrl(builder.toString()).setData(param).httpGet();//{"clientNumber":"8"}
            if(res.contains("clientNumber")) {
                JSONObject jsonObject = JSONObject.parseObject(res);
                String str = jsonObject.getString("clientNumber");
                wsClientConfigPo.setClientNumber(Long.parseLong(str));
                outputDto.setSuccess(true);
                if(inputDto.getId()>0) {
                    iWsClientConfigDao.updateById(wsClientConfigPo);
                } else {
                    iWsClientConfigDao.insert(wsClientConfigPo);
                }
            }
            outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        }catch (Exception e){
            logger.info("ApiWsClientControllerServiceImpl Exception {}",e.getMessage());
            return outputDto;
        }
        return outputDto;
    }

    @Override
    public WsClientOutputDto getWsClientConfig(WsClientInputDto inputDto) throws BizException {
        WsClientConfigPo wsClientConfigPo =iWsClientConfigDao.selectPoById(inputDto.getId());
        WsClientOutputDto outputDto = new WsClientOutputDto();
        if(wsClientConfigPo!=null){
            outputDto.setId(String.valueOf(wsClientConfigPo.getId()));
            outputDto.setWsIp(wsClientConfigPo.getWsIp());
            outputDto.setWsPort(wsClientConfigPo.getWsPort());
            outputDto.setClientNumber(String.valueOf(wsClientConfigPo.getClientNumber()));
            outputDto.setType(wsClientConfigPo.getType());
            outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        }
        return outputDto;
    }


    @Override
    public WsClientListOutputDto getWsClientConfigPageData(WsClientInputDto inputDto) throws BizException {
        WsClientListOutputDto outputDto = new WsClientListOutputDto();
        IPage<WsClientConfigPo> pageData = iWsClientConfigDao.selectPageData(inputDto.getCurrentPage(),inputDto.getPageSize());
        if(pageData!=null && CollectionUtils.isNotEmpty(pageData.getRecords())){
            Map<String,Object> param = new HashMap<>();
            List<WsClientOutputDto> list = new ArrayList<>();
            WsClientOutputDto row = null;
            StringBuilder builder = null;

            for(WsClientConfigPo configPo : pageData.getRecords()) {
                row = new WsClientOutputDto();
                row.setId(String.valueOf(configPo.getId()));
                row.setWsIp(configPo.getWsIp());
                row.setWsPort(configPo.getWsPort());
                if(configPo.getClientNumber()!=null){
                    row.setClientNumber(String.valueOf(configPo.getClientNumber()));
                }
                try{
                    builder = new StringBuilder();
                    builder.append("http://");
                    builder.append(configPo.getWsIp());
                    builder.append(":");
                    builder.append(configPo.getWsPort());
                    builder.append("/http/ws/client/status/query");
                    String res = HttpUtil.getHttpClient().setUrl(builder.toString()).setData(param).httpGet();
                    if(res.contains("clientNumber")) {
                        JSONObject jsonObject = JSONObject.parseObject(res);
                        String str = jsonObject.getString("clientNumber");
                        row.setClientNumber(str);
                    }
                }catch (Exception e){
                    logger.info("ApiWsClientControllerServiceImpl Exception {}",e.getMessage());
                }
                list.add(row);
            }

            outputDto.setTotal(pageData.getTotal());
            outputDto.setRows(list);
        }
        outputDto.setSizePerPage(inputDto.getPageSize());
        return outputDto;
    }

    @Override
    public DeleteOutputDto delete(DeleteInputDto inputDto) throws BizException {
        DeleteOutputDto outputDto = new DeleteOutputDto();
        if(inputDto.getIds()!=null){
            String[] ids = inputDto.getIds().split(",");
            if(ids!=null && ids.length>0){
                for(String id:ids) {
                    iWsClientConfigDao.deleteById(Long.parseLong(id));
                }
            }
        }
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
