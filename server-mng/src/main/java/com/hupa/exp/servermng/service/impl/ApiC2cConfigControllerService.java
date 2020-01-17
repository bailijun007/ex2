package com.hupa.exp.servermng.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IC2cConfigDao;
import com.hupa.exp.daomysql.entity.po.expv2.C2cConfigPo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.c2config.C2cConfigInputDto;
import com.hupa.exp.servermng.entity.c2config.C2cConfigListInputDto;
import com.hupa.exp.servermng.entity.c2config.C2cConfigListOutputDto;
import com.hupa.exp.servermng.entity.c2config.C2cConfigOutputDto;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiC2cConfigControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2020/1/13.
 */
@Service
public class ApiC2cConfigControllerService  implements IApiC2cConfigControllerService {

   @Autowired
   private IC2cConfigDao c2cConfigDao;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private IExpOperationLogService logService;

    /**
     * 分页 查询C2C列表
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public C2cConfigListOutputDto findC2cConfigList(C2cConfigListInputDto inputDto) throws BizException {
        C2cConfigListOutputDto outputDto = new C2cConfigListOutputDto();
        IPage<C2cConfigPo> pageList = c2cConfigDao.selectC2cConfigList(inputDto.getAsset(),inputDto.getCurrentPage(),inputDto.getPageSize());
        List<C2cConfigOutputDto> c2cConfigLists = new ArrayList<>();
        for(C2cConfigPo c2cConfigPo: pageList.getRecords()) {
            C2cConfigOutputDto row = new C2cConfigOutputDto();
            row.setId(c2cConfigPo.getId());
            row.setAsset(c2cConfigPo.getAsset());
            row.setMinInMoney(c2cConfigPo.getMinInMoney());
            row.setMinOutMoney(c2cConfigPo.getMinOutMoney());
            row.setCtime(String.valueOf(c2cConfigPo.getCtime()));
            row.setMtime(String.valueOf(c2cConfigPo.getMtime()));
            //C2cConfigOutputDto bo=ConventObjectUtil.conventObject(po, C2cConfigOutputDto.class);
            c2cConfigLists.add(row);
        }
        outputDto.setTotal(pageList.getTotal());
        outputDto.setRows(c2cConfigLists);
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    /**
     * 新增一条记录
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public C2cConfigOutputDto createC2cConfig(C2cConfigInputDto inputDto) throws BizException {
        C2cConfigOutputDto outputDto = new C2cConfigOutputDto();
        if(inputDto != null){
            C2cConfigPo c2cConfigPo = new C2cConfigPo();
            c2cConfigPo.setAsset(inputDto.getAsset());
            c2cConfigPo.setMinInMoney(inputDto.getMinInMoney());
            c2cConfigPo.setMinOutMoney(inputDto.getMinOutMoney());
            c2cConfigPo.setCtime(System.currentTimeMillis());
            c2cConfigPo.setMtime(System.currentTimeMillis());
            c2cConfigDao.insert(c2cConfigPo);

            //添加操作日志
            ExpUserBizBo user= sessionHelper.getUserInfoBySession();
            logService.createOperationLog(user.getId(),user.getUserName(), "C2CConfig",
                    OperationType.Insert.toString(), JsonUtil.toJsonString(JsonUtil.toJsonString(c2cConfigPo)),JsonUtil.toJsonString(""));
        }
        return outputDto;
    }

    /**
     * c2cconfig
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public C2cConfigOutputDto editC2cConfig(C2cConfigInputDto inputDto) throws BizException {
        C2cConfigOutputDto outputDto = new C2cConfigOutputDto();
        if(inputDto != null){

            C2cConfigPo beforeBo = c2cConfigDao.selectPoById(inputDto.getId());
            C2cConfigPo c2cConfigPo = beforeBo;
            if(c2cConfigPo!=null){
                c2cConfigPo.setAsset(inputDto.getAsset());
                c2cConfigPo.setMinInMoney(inputDto.getMinInMoney());
                c2cConfigPo.setMinOutMoney(inputDto.getMinOutMoney());
                c2cConfigPo.setMtime(System.currentTimeMillis());
                c2cConfigDao.updateById(c2cConfigPo);
                ExpUserBizBo user= sessionHelper.getUserInfoBySession();
                logService.createOperationLog(user.getId(),user.getUserName(), "C2CConfig",
                        OperationType.Update.toString(), JsonUtil.toJsonString(beforeBo),JsonUtil.toJsonString(c2cConfigPo));
            }
        }
        return outputDto;
    }

    @Override
    public C2cConfigOutputDto getC2cConfigById(C2cConfigInputDto inputDto) throws BizException {
        C2cConfigOutputDto outputDto = new C2cConfigOutputDto();
        if(inputDto != null ){
            C2cConfigPo c2cConfigPo = c2cConfigDao.selectPoById(inputDto.getId());
            outputDto.setAsset(c2cConfigPo.getAsset());
            outputDto.setMinOutMoney(c2cConfigPo.getMinOutMoney());
            outputDto.setMinInMoney(c2cConfigPo.getMinInMoney());
            outputDto.setCtime(String.valueOf(c2cConfigPo.getCtime()));
            outputDto.setMtime(String.valueOf(c2cConfigPo.getMtime()));
        }
        return outputDto;
    }


    /**
     *  删除C2C配置，可批量删除
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public DeleteOutputDto deletesConfigList(DeleteInputDto inputDto) throws BizException {
        String[] ids=inputDto.getIds().split(",");
        for(String id:ids) {
            c2cConfigDao.deleteById(Long.parseLong(id));
        }
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

}
