package com.hupa.exp.bizother.service.contract.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.contract.PcContractBizBo;
import com.hupa.exp.bizother.entity.contract.PcContractListBizBo;
import com.hupa.exp.bizother.service.contract.def.IPcContractBiz;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IExpDicDao;
import com.hupa.exp.daomysql.dao.expv2.def.IPcContractDao;
import com.hupa.exp.daomysql.entity.po.expv2.ExpDicPo;
import com.hupa.exp.daomysql.entity.po.expv2.PcContractPo;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PcContractBizImpl implements IPcContractBiz
{
    @Autowired
    private IExpDicDao dicDao;

    @Autowired
    private IPcContractDao iPcContractDao;

    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;

    @Override
    public PcContractBizBo get(String pair) {

        PcContractPo po = iPcContractDao.selectOnePo("",pair);
        if (po == null)
            return null;

        PcContractBizBo bizBo = ConventObjectUtil.conventObject(po, PcContractBizBo.class);

        return bizBo;
    }


    @Override
    public long createPcContract(PcContractBizBo bo) throws ValidateException {
        if(bo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        PcContractPo po= ConventObjectUtil.conventObject(bo,PcContractPo.class);
        long id= iPcContractDao.insert(po);
        ExpDicPo dicPo= dicDao.selectDicByKey("ContractRedisKey");
        if(dicPo!=null)
        {
            redisUtilDb0.hset(dicPo.getValue(),po.getSymbol(), JSON.toJSONString(po));
        }
        ExpDicPo dicPoDisplay= dicDao.selectDicByKey("DisplayNameRedisKey");
        if(dicPoDisplay!=null)
        {
            redisUtilDb0.hset(dicPoDisplay.getValue(),po.getDisplayName(), JSON.toJSONString(po));
        }
        return id;
    }

    @Override
    public long editContract(PcContractBizBo bo) throws ValidateException {
        if(bo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        PcContractPo po= ConventObjectUtil.conventObject(bo,PcContractPo.class);
        PcContractPo oldPo=  iPcContractDao.selectPoById(bo.getId());
        po.setCtime(oldPo.getCtime());
        long id= iPcContractDao.updateById(po);
        List<PcContractPo> pcContractPos= iPcContractDao.selectPos();
        //list 转 map
//        Map<String, PcContractPo> pairMap = pcContractPos.stream().collect(Collectors.toMap(PcContractPo::getPair, a -> a,(k1, k2)->k1));
//        Map<String, PcContractPo> displayMap = pcContractPos.stream().collect(Collectors.toMap(PcContractPo::getDisplayName, a -> a,(k1, k2)->k1));
        ExpDicPo dicPo= dicDao.selectDicByKey("ContractRedisKey");
        if(dicPo!=null)
        {
            //redisUtilDb0.del(dicPo.getValue());
            //redisUtilDb0.hmset(dicPo.getValue(),pairMap);
            redisUtilDb0.hdel(dicPo.getValue(),oldPo.getSymbol());
            redisUtilDb0.hset(dicPo.getValue(),po.getSymbol(),po);
        }
        ExpDicPo dicPoDisplay= dicDao.selectDicByKey("DisplayNameRedisKey");
        if(dicPoDisplay!=null)
        {
            //redisUtilDb0.del(dicPoDisplay.getValue());
            //redisUtilDb0.hmset(dicPoDisplay.getValue(),displayMap);
            redisUtilDb0.hdel(dicPoDisplay.getValue(),oldPo.getDisplayName());
            redisUtilDb0.hset(dicPoDisplay.getValue(),po.getDisplayName(),po);
        }
        return id;
    }

    @Override
    public PcContractBizBo getContractById(long id) {

        PcContractPo po = iPcContractDao.selectPoById(id);
        if (po == null)
            return null;

        PcContractBizBo bo = ConventObjectUtil.conventObject(po, PcContractBizBo.class);
        return bo;
    }

    @Override
    public PcContractListBizBo selectPosPageByParam(String pair, long currentPage, long pageSize) {
        IPage<PcContractPo> poList = iPcContractDao.selectPosPageByParam("",pair, currentPage, pageSize);
        PcContractListBizBo listBizBo = new PcContractListBizBo();
        List<PcContractBizBo> boList = new ArrayList<>();
        for (PcContractPo po : poList.getRecords()) {
            PcContractBizBo bo = ConventObjectUtil.conventObject(po, PcContractBizBo.class);
            boList.add(bo);
        }
        listBizBo.setRows(boList);
        listBizBo.setTotal(poList.getTotal());
        return listBizBo;
    }

    @Override
    public List<String> selectAllSymbol() {
        List<PcContractPo> listPo= iPcContractDao.selectPosByStatus(1);
        List<String> symbolList=new ArrayList<>();
        for(PcContractPo po:listPo)
        {
            symbolList.add(po.getSymbol());
        }
        return symbolList;
    }

    @Override
    public boolean checkHasContract(String pair) {
        return iPcContractDao.checkHasContract("",pair);
    }

}
