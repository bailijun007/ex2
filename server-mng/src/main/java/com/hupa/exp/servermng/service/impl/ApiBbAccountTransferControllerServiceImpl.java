package com.hupa.exp.servermng.service.impl;

import com.gitee.hupadev.base.api.PageResult;
import com.hp.sh.expv3.bb.extension.api.BbAccountRecordExtApi;
import com.hp.sh.expv3.bb.extension.vo.BbAccountRecordVo;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.bbtransfer.BbTransferInfo;
import com.hupa.exp.servermng.entity.bbtransfer.BbTransferListInputDto;
import com.hupa.exp.servermng.entity.bbtransfer.BbTransferListOutputDto;
import com.hupa.exp.servermng.service.def.IApiBbAccountTransferControllerService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/2/15.
 */
@Service
public class ApiBbAccountTransferControllerServiceImpl implements IApiBbAccountTransferControllerService {

    private static Logger logger = LoggerFactory.getLogger(ApiBbAccountTransferControllerServiceImpl.class);

    @Autowired
    private BbAccountRecordExtApi bbAccountRecordExtApi;

    /**
     * 查询用币币户划转历史记录
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public BbTransferListOutputDto getAllBbAccountTransferList(BbTransferListInputDto inputDto) throws BizException{
        //返回对象
        BbTransferListOutputDto outputDto = new BbTransferListOutputDto();
        try{
            // 调用第三方接口：查询币币划转历史记录
            PageResult<BbAccountRecordVo> pageResult = bbAccountRecordExtApi.queryHistory(
                    inputDto.getAccountId()==null || inputDto.getAccountId()==0?null:inputDto.getAccountId(),
                    StringUtils.isNotBlank(inputDto.getAsset())?inputDto.getAsset():null,
                    inputDto.getPageSize(), inputDto.getCurrentPage()!=0?(int)inputDto.getCurrentPage():1);

            if(pageResult!=null){
                //遍历赋值
                List<BbTransferInfo> list = new ArrayList<BbTransferInfo>();
                if(CollectionUtils.isNotEmpty(pageResult.getList())){
                    for (BbAccountRecordVo bbAccountRecordVo : pageResult.getList()) {
                        BbTransferInfo bbTransferInfo = new BbTransferInfo();
                        bbTransferInfo.setId(bbAccountRecordVo.getId());
                        bbTransferInfo.setUserId(bbAccountRecordVo.getUserId());
                        bbTransferInfo.setAsset(bbAccountRecordVo.getAsset());
                        bbTransferInfo.setSn(bbAccountRecordVo.getSn());
                        bbTransferInfo.setType(bbAccountRecordVo.getType());
                        bbTransferInfo.setAmount(bbAccountRecordVo.getAmount());
                        bbTransferInfo.setRemark(bbAccountRecordVo.getRemark());
                        bbTransferInfo.setBalance(bbAccountRecordVo.getBalance());
                        bbTransferInfo.setTradeNo(bbAccountRecordVo.getTradeNo());
                        bbTransferInfo.setTradeType(bbAccountRecordVo.getTradeType());
                        bbTransferInfo.setSerialNo(bbAccountRecordVo.getSerialNo());
                        bbTransferInfo.setAssociatedId(bbAccountRecordVo.getAssociatedId());
                        bbTransferInfo.setTxId(bbAccountRecordVo.getTxId());
                        bbTransferInfo.setRequestId(bbAccountRecordVo.getRequestId());
                        bbTransferInfo.setModified(bbAccountRecordVo.getModified());
                        bbTransferInfo.setCreated(bbAccountRecordVo.getCreated());
                       /**
                        bbTransferInfo.setCreated(bbAccountRecordVo.getCreated()==null ? null : String.valueOf(bbAccountRecordVo.getCreated()));
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        transferInfoOutputDto.setCreated(bbAccountRecordVo.getCreated()==null ? null : sdf.format(bbAccountRecordVo.getCreated()));*/
                        list.add(bbTransferInfo);
                    }
                }
                outputDto.setTotal(pageResult.getRowTotal());
                outputDto.setTotalCount(pageResult.getRowTotal());
                outputDto.setRows(list);
            }
            outputDto.setSizePerPage(inputDto.getPageSize());
            outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        }catch(Exception e){
            logger.info("ApiBbAccountTransferControllerServiceImpl getAllBbAccountTransferList exception: " + e.getMessage());
        }
        return outputDto;
    }


}
