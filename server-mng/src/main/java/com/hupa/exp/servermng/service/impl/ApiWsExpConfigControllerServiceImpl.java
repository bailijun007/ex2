package com.hupa.exp.servermng.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.IPcWsExpConfigDao;
import com.hupa.exp.daomysql.entity.po.expv2.PcWsExpConfigPo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.wsconfig.WsExpConfigInputDto;
import com.hupa.exp.servermng.entity.wsconfig.WsExpConfigListOutputDto;
import com.hupa.exp.servermng.entity.wsconfig.WsExpConfigOutputDto;
import com.hupa.exp.servermng.service.def.IApiWsExpConfigControllerService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiWsExpConfigControllerServiceImpl implements IApiWsExpConfigControllerService {

    @Autowired
    private IPcWsExpConfigDao iPcWsExpConfigDao;

    @Override
    public WsExpConfigOutputDto createOrEdit(WsExpConfigInputDto inputDto) throws BizException {
        PcWsExpConfigPo pcWsExpConfigPo = new PcWsExpConfigPo();
        pcWsExpConfigPo.setId(inputDto.getId());
        pcWsExpConfigPo.setKey(inputDto.getKey());
        pcWsExpConfigPo.setValue(inputDto.getValue());
        pcWsExpConfigPo.setRemark(inputDto.getRemark());
        if(inputDto.getId()>0) {
            iPcWsExpConfigDao.updateById(pcWsExpConfigPo);
        } else {
            iPcWsExpConfigDao.insert(pcWsExpConfigPo);
        }
        WsExpConfigOutputDto outputDto=new WsExpConfigOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public WsExpConfigOutputDto getWsExpConfig(WsExpConfigInputDto inputDto) throws BizException {
        PcWsExpConfigPo pcWsExpConfigPo =iPcWsExpConfigDao.selectPoById(inputDto.getId());
        WsExpConfigOutputDto outputDto = new WsExpConfigOutputDto();
        if(pcWsExpConfigPo!=null){
            outputDto.setId(String.valueOf(pcWsExpConfigPo.getId()));
            outputDto.setKey(pcWsExpConfigPo.getKey());
            outputDto.setValue(pcWsExpConfigPo.getValue());
            outputDto.setRemark(pcWsExpConfigPo.getRemark());
        }
        return outputDto;
    }

    @Override
    public WsExpConfigListOutputDto getWsExpConfigPageData(WsExpConfigInputDto inputDto) throws BizException {
        WsExpConfigListOutputDto outputDto = new WsExpConfigListOutputDto();
        IPage<PcWsExpConfigPo> pageData = iPcWsExpConfigDao.selectPageData(inputDto.getCurrentPage(),inputDto.getPageSize());
        List<WsExpConfigOutputDto> list = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(pageData.getRecords())){
            WsExpConfigOutputDto row = null;
            for(PcWsExpConfigPo configPo : pageData.getRecords()) {
                row = new WsExpConfigOutputDto();
                row.setId(String.valueOf(configPo.getId()));
                row.setKey(configPo.getKey());
                row.setValue(configPo.getValue());
                row.setRemark(configPo.getRemark());
                list.add(row);
            }
            outputDto.setTotal(pageData.getTotal());
        }
        outputDto.setRows(list);
        outputDto.setSizePerPage(inputDto.getPageSize());
        return outputDto;
    }


    @Override
    public DeleteOutputDto delete(DeleteInputDto inputDto) throws BizException {
        DeleteOutputDto outputDto=new DeleteOutputDto();
        if(inputDto.getIds()!=null){
            String[] ids = inputDto.getIds().split(",");
            if(ids!=null && ids.length>0){
                for(String id:ids) {
                    iPcWsExpConfigDao.deleteById(Long.parseLong(id));
                }
            }
        }
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }


}
