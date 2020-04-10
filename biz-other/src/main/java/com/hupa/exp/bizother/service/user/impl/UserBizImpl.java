package com.hupa.exp.bizother.service.user.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hp.sh.expv3.bb.extension.vo.BbAccountExtVo;
import com.hp.sh.expv3.bb.extension.vo.BbAccountVo;
import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.base.helper.security.SecurityPwdHelper;
import com.hupa.exp.bizother.entity.account.FundAccountBizBo;
import com.hupa.exp.bizother.entity.account.PcAccountBizBo;
import com.hupa.exp.bizother.entity.assets.AssetsBizBo;
import com.hupa.exp.bizother.entity.fundaccount.FundAccountMngBizBo;
import com.hupa.exp.bizother.entity.fundaccount.FundAccountMngListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserListBizBo;
import com.hupa.exp.bizother.exception.BizUserException;
import com.hupa.exp.bizother.service.account.def.IAccountBiz;
import com.hupa.exp.bizother.service.user.def.IUserBiz;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.daomysql.dao.expv2.def.*;
import com.hupa.exp.daomysql.entity.po.expv2.*;
import com.hupa.exp.util.convent.ConventObjectUtil;
import com.hupa.exp.util.math.DecimalUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserBizImpl implements IUserBiz {
    @Autowired
    private IExpUserDao iExpUserDao;
    @Autowired
    private IExpUserRoleDao iExpUserRoleDao;

    @Autowired
    private IAccountBiz iAccountBiz;

    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;

    @Autowired
    private IPcFeeDao pcFeeDao;

    @Autowired
    private IBbFeeDao bbFeeDao;

    @Autowired
    private IAssetDao iAssetDao;

    @Autowired
    private SecurityPwdHelper pwdHelper;

    @Autowired
    private IExpDicDao iExpDicDao;

    @Override
    public long createUser(ExpUserBizBo expUserBo) throws BizUserException, ValidateException {
        if(expUserBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpUserPo expUserPo = ConventObjectUtil.conventObject(expUserBo, ExpUserPo.class);
        String pwd = pwdHelper.getMd5Pwd("PwdMD5Key", expUserBo.getUserpwd());
        if(!StringUtils.isEmpty( expUserBo.getFundPwd()))
        {
            String fundPwd = pwdHelper.getMd5Pwd("FundPwdMD5Key", expUserBo.getFundPwd());
            expUserPo.setFundPwd(fundPwd);
        }

        long userId = 0;
        expUserPo.setUserpwd(pwd);//加密后的密码

        expUserPo.setMtime(System.currentTimeMillis());//修改时间
        //expUserPo.setUserType(0);
        expUserBo.setCtime(new Date().getTime());
        expUserPo.setFeeLevel(1);
        if(expUserBo.getUserType()!=0)
        {
            PcFeePo pcFeePo= pcFeeDao.selectPcFeeByLevel(1);
            //查吃单和挂单的手续费
            if(pcFeePo!=null)
            {
                BigDecimal mPcFee=pcFeePo.getMakerFee().divide(new BigDecimal("100"));
                BigDecimal tPcFee=pcFeePo.getTakerFee().divide(new BigDecimal("100"));

                ExpDicPo dicPo= iExpDicDao.selectDicByKey("PcFeeRedisKey");
                //给redis的不用%所以除100
                if(dicPo!=null)
                {
                    redisUtilDb0.hset(dicPo.getValue(),"m_"+expUserPo.getId(), DecimalUtil.trimZeroPlainString(mPcFee));
                    redisUtilDb0.hset(dicPo.getValue(),"t_"+expUserPo.getId(), DecimalUtil.trimZeroPlainString(tPcFee));
                }
                else
                {
                    redisUtilDb0.hset("pc_fee","m_"+expUserPo.getId(), DecimalUtil.trimZeroPlainString(mPcFee));
                    redisUtilDb0.hset("pc_fee","t_"+expUserPo.getId(), DecimalUtil.trimZeroPlainString(tPcFee));
                }
                //数据库里的还是要%的
                expUserPo.setMakerFee(mPcFee.multiply(new BigDecimal("100")));
                expUserPo.setTakerFee(tPcFee.multiply(new BigDecimal("100")));
            }

            BbFeePo bbFeePo = bbFeeDao.selectBbFeeByLevel(1);
            if(bbFeePo!=null){
                BigDecimal mBbFee=bbFeePo.getMakerFee().divide(new BigDecimal("100"));
                BigDecimal tBbFee=bbFeePo.getTakerFee().divide(new BigDecimal("100"));

                ExpDicPo expDicPo= iExpDicDao.selectDicByKey("BbFeeRedisKey");
                //给redis的不用%所以除100
                if(expDicPo!=null) {
                    redisUtilDb0.hset(expDicPo.getValue(),"m_"+expUserPo.getId(), DecimalUtil.trimZeroPlainString(mBbFee));
                    redisUtilDb0.hset(expDicPo.getValue(),"t_"+expUserPo.getId(), DecimalUtil.trimZeroPlainString(tBbFee));
                } else {
                    redisUtilDb0.hset("bb_fee","m_"+expUserPo.getId(), DecimalUtil.trimZeroPlainString(mBbFee));
                    redisUtilDb0.hset("bb_fee","t_"+expUserPo.getId(), DecimalUtil.trimZeroPlainString(tBbFee));
                }
                //数据库里的还是要%的
                expUserPo.setBbMakerFee(mBbFee.multiply(new BigDecimal("100")));
                expUserPo.setBbTakerFee(tBbFee.multiply(new BigDecimal("100")));
            }
        }

        userId = iExpUserDao.insert(expUserPo);
        //通过用户id删除角色
            if (expUserBo.getId() > 0) {
                iExpUserRoleDao.deleteUserRoleByUserId(expUserBo.getId());
            }
            if (expUserBo.getRoleList().size() > 0) {
                for (int i = 0; i < expUserBo.getRoleList().size(); i++) {
                    ExpUserRolePo expUserRolePo = new ExpUserRolePo();
                    expUserRolePo.setUserid(userId);
                    expUserRolePo.setRoleid(expUserBo.getRoleList().get(i));
                    iExpUserRoleDao.insert(expUserRolePo);
                }
        }
        return userId;
    }

    @Override
    public List<ExpUserBizBo> queryList() {
        List<ExpUserPo> userPos = iExpUserDao.selectList();
        List<ExpUserBizBo> userBos = new ArrayList<>();
        for (ExpUserPo po : userPos) {
            ExpUserBizBo bo = ConventObjectUtil.conventObject(po, ExpUserBizBo.class);
            userBos.add(bo);
        }
        return userBos;
    }

    @Override
    public ExpUserListBizBo queryListByUserType(long currentPage, long pageSize, Integer userType, String userName, Long id) {
        ExpUserListBizBo listBizBo = new ExpUserListBizBo();
        IPage<ExpUserPo> userPos = iExpUserDao.selectUserList(currentPage, pageSize, userType, userName,id);
        List<ExpUserBizBo> boList = new ArrayList<>();
        for (ExpUserPo po : userPos.getRecords()) {
            ExpUserBizBo bo = ConventObjectUtil.conventObject(po, ExpUserBizBo.class);
            boList.add(bo);
        }
        listBizBo.setRows(boList);
        listBizBo.setTotal(userPos.getTotal());
        return listBizBo;
    }

    @Override
    public long editById(ExpUserBizBo expUserBo) throws BizUserException, ValidateException {
        if(expUserBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);

        ExpUserPo changeBo = ConventObjectUtil.conventObject(expUserBo, ExpUserPo.class);

        ExpUserPo defaultPo = iExpUserDao.selectPoById(expUserBo.getId());

        //密码是之前的密码
        String newPwd= pwdHelper.getMd5Pwd("PwdMD5Key",changeBo.getUserpwd());
        if (!defaultPo.getUserpwd().equals(changeBo.getUserpwd())){ //不修改密码的时候密码不加密
            changeBo.setUserpwd(newPwd);//加密后的密码
        }

        String newFundPwd=pwdHelper.getMd5Pwd("FundPwdMD5Key",changeBo.getFundPwd());
        if (defaultPo.getFundPwd()==null || !defaultPo.getFundPwd().equals(changeBo.getFundPwd())) { //不修改密码的时候密码不加密
            changeBo.setFundPwd(newFundPwd);//加密后的密码
        }

        if (defaultPo.getUserType() == 0) {
            //通过用户id删除角色
            if (defaultPo.getId() > 0) {
                iExpUserRoleDao.deleteUserRoleByUserId(defaultPo.getId());
            }
            for (int i = 0; i < expUserBo.getRoleList().size(); i++) {
                ExpUserRolePo expUserRolePo = new ExpUserRolePo();
                expUserRolePo.setUserid(defaultPo.getId());
                expUserRolePo.setRoleid(expUserBo.getRoleList().get(i));
                iExpUserRoleDao.insert(expUserRolePo);
            }
        }
        changeBo.setMtime(System.currentTimeMillis());//修改时间
        return iExpUserDao.updateById(changeBo);
    }

    @Override
    public long enableById(ExpUserBizBo expUserBo) throws BizUserException, ValidateException {
        if(expUserBo==null)
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_EMPTY_ERROR);
        ExpUserPo expUserPo = ConventObjectUtil.conventObject(expUserBo, ExpUserPo.class);
        return iExpUserDao.updateById(expUserPo);
    }

    @Override
    public ExpUserBizBo queryById(long id) {
        ExpUserPo expUserPo = iExpUserDao.selectPoById(id);
        if(expUserPo==null)
            return null;
        ExpUserBizBo expUserBo = ConventObjectUtil.conventObject(expUserPo, ExpUserBizBo.class);
        return expUserBo;
    }

   /* @Override
    public FundAccountMngListBizBo queryFundAccountList(long currentPage, long pageSize, Integer userType, String userName, Long id) throws BizException {
        List<AssetPo> assetPos = iAssetDao.selectList();
        IPage<ExpUserPo> userPos = iExpUserDao.selectUserList(currentPage, pageSize, userType, userName,id);
        FundAccountMngListBizBo fundAccountListBizBo = new FundAccountMngListBizBo();
        fundAccountListBizBo.setTotal(userPos.getTotal());
        List<FundAccountMngBizBo> fundAccountBizBoList = new ArrayList<>();
        for (ExpUserPo userPo : userPos.getRecords()) {
            FundAccountMngBizBo fundAccountBizBo = new FundAccountMngBizBo();
            fundAccountBizBo.setId(userPo.getId());
            String name = !StringUtils.isEmpty(userPo.getPhone()) ? userPo.getPhone() : userPo.getEmail();
            fundAccountBizBo.setUserName(name);
            fundAccountBizBo.setRealName(userPo.getRealName());
            List<AssetsBizBo> assetsBizBos = new ArrayList<>();

            for (AssetPo pcPo : assetPos) {
               FundAccountBizBo fundAccount = iAccountBiz.getFundAccount(userPo.getId(), pcPo.getRealName());
                AssetsBizBo assetsBizBo = new AssetsBizBo();
                assetsBizBo.setAsset(pcPo.getRealName());
                //获取资金账户
                if (null != fundAccount) {
                    assetsBizBo.setFundAccountAvailable(fundAccount.getAvailable());
                    assetsBizBo.setFundAccountLock(fundAccount.getLock());
                    assetsBizBo.setFundAccountTotal(fundAccount.getTotal());
                } else {
                    assetsBizBo.setFundAccountAvailable(new BigDecimal("0"));
                    assetsBizBo.setFundAccountLock(new BigDecimal("0"));
                    assetsBizBo.setFundAccountTotal(new BigDecimal("0"));
                }
                //获取合约账户
                PcAccountBizBo pcBo = iAccountBiz.getPcAccount(userPo.getId(), pcPo.getRealName());
                if (null != pcBo) {
                    assetsBizBo.setPcAccountAvailable(pcBo.getAvailable());
                    assetsBizBo.setPcAccountTotal(pcBo.getTotal());
                    assetsBizBo.setPcOrderMargin(pcBo.getOrderMargin());
                    assetsBizBo.setPcPosMargin(pcBo.getPosMargin());
                } else {
                    assetsBizBo.setPcAccountAvailable(new BigDecimal("0"));
                    assetsBizBo.setPcAccountTotal(new BigDecimal("0"));
                    assetsBizBo.setPcOrderMargin(new BigDecimal("0"));
                    assetsBizBo.setPcPosMargin(new BigDecimal("0"));
                }
                //获取币币账户
                BbAccountVo bbAccountVo = iAccountBiz.getBBAccount(userPo.getId(), pcPo.getRealName());
                if(bbAccountVo!=null){
                    //assetsBizBo.setBbAccountTotal();
                    assetsBizBo.setBbAccountBalance(bbAccountVo.getBalance());
                    //assetsBizBo.setBbAccountFrozen();
                }else{
                    assetsBizBo.setBbAccountTotal(new BigDecimal("0"));
                    assetsBizBo.setBbAccountBalance(new BigDecimal("0"));
                    assetsBizBo.setBbAccountFrozen(new BigDecimal("0"));
                }

                assetsBizBos.add(assetsBizBo);
            }
            fundAccountBizBo.setAssets(assetsBizBos);
            fundAccountBizBoList.add(fundAccountBizBo);
        }
        fundAccountListBizBo.setRows(fundAccountBizBoList);
        return fundAccountListBizBo;
    }*/

    @Override
    public FundAccountMngListBizBo queryFundAccountListByParam(long currentPage, long pageSize, Integer userType, String userName, String asset, Long id) throws BizException {
        FundAccountMngListBizBo fundAccountListBizBo = new FundAccountMngListBizBo();
        //List<AssetPo> assetPos = iAssetDao.selectList();//获取所有交易对
        IPage<ExpUserPo> userPos = iExpUserDao.selectUserList(currentPage, pageSize, userType, userName,id); //按条件获取用户
        List<FundAccountMngBizBo> fundAccountBizBoList = new ArrayList<>();
        for (ExpUserPo userPo : userPos.getRecords()) {
            FundAccountMngBizBo fundAccountBizBo = new FundAccountMngBizBo();
            fundAccountBizBo.setId(userPo.getId());
            fundAccountBizBo.setUserName(!StringUtils.isEmpty(userPo.getPhone()) ? userPo.getPhone() : userPo.getEmail());
            fundAccountBizBo.setRealName(userPo.getRealName());
            List<AssetsBizBo> assetsBizBos = new ArrayList<>();
            //for (AssetPo pcPo : assetPos) {
                AssetsBizBo assetsBizBo = new AssetsBizBo();
                assetsBizBo.setAsset(asset);//pcPo.getRealName()
                // 获取资金账户
                FundAccountBizBo fundAccount = iAccountBiz.getFundAccount(userPo.getId(), asset);//pcPo.getRealName()
                if (fundAccount != null) {
                    assetsBizBo.setFundAccountAvailable(fundAccount.getAvailable()==null?BigDecimal.ZERO:fundAccount.getAvailable());//可用余额
                    assetsBizBo.setFundAccountLock(fundAccount.getLock()==null?BigDecimal.ZERO:fundAccount.getAvailable());//锁定额度
                    assetsBizBo.setFundAccountTotal(fundAccount.getTotal()==null?BigDecimal.ZERO:fundAccount.getTotal());//总金额
                } else {
                    iAccountBiz.createFundAccount(userPo.getId(), asset);//创建资金账户pcPo.getRealName()
                    assetsBizBo.setFundAccountAvailable(BigDecimal.ZERO);
                    assetsBizBo.setFundAccountLock(BigDecimal.ZERO);
                    assetsBizBo.setFundAccountTotal(BigDecimal.ZERO);
                }
                //获取合约账户
                PcAccountBizBo pcBo = iAccountBiz.getPcAccount(userPo.getId(), asset);//pcPo.getRealName()
                if (pcBo != null ) {
                    assetsBizBo.setPcAccountAvailable(pcBo.getAvailable()==null?BigDecimal.ZERO:pcBo.getAvailable());//可用余额
                    assetsBizBo.setPcAccountTotal(pcBo.getTotal()==null?BigDecimal.ZERO:pcBo.getTotal());//总金额
                    assetsBizBo.setPcOrderMargin(pcBo.getOrderMargin()==null?BigDecimal.ZERO:pcBo.getOrderMargin());//委托保证金
                    assetsBizBo.setPcPosMargin(pcBo.getPosMargin()==null?BigDecimal.ZERO:pcBo.getPosMargin());//仓位保证金
                } else {
                    iAccountBiz.createPcAccount(userPo.getId(), asset);//创建合约账户pcPo.getRealName()
                    assetsBizBo.setPcAccountAvailable(BigDecimal.ZERO);
                    assetsBizBo.setPcAccountTotal(BigDecimal.ZERO);
                    assetsBizBo.setPcOrderMargin(BigDecimal.ZERO);
                    assetsBizBo.setPcPosMargin(BigDecimal.ZERO);
                }
                //获取币币账户
                 BbAccountExtVo bbAccountVo = iAccountBiz.getBBAccount(userPo.getId(), asset);//pcPo.getRealName()
                if(bbAccountVo!=null){
                    assetsBizBo.setBbAccountTotal(bbAccountVo.getTotal()==null? BigDecimal.ZERO:bbAccountVo.getTotal());
                    assetsBizBo.setBbAccountBalance(bbAccountVo.getAvailable()==null? BigDecimal.ZERO:bbAccountVo.getAvailable());
                    assetsBizBo.setBbAccountFrozen(bbAccountVo.getLock()==null? BigDecimal.ZERO:bbAccountVo.getLock());
                }else{
                    iAccountBiz.createBBAccount(userPo.getId(), asset); //创建币币账户pcPo.getRealName()
                    assetsBizBo.setBbAccountTotal(BigDecimal.ZERO);
                    assetsBizBo.setBbAccountBalance(BigDecimal.ZERO);
                    assetsBizBo.setBbAccountFrozen(BigDecimal.ZERO);//new BigDecimal("0")
                }
                assetsBizBos.add(assetsBizBo);
            //}
            fundAccountBizBo.setAssets(assetsBizBos);
            fundAccountBizBoList.add(fundAccountBizBo);
        }
        fundAccountListBizBo.setTotal(userPos.getTotal());
        fundAccountListBizBo.setRows(fundAccountBizBoList);
        return fundAccountListBizBo;
    }

    @Override
    public FundAccountMngBizBo queryFundAccountById(long id) throws BizException {
        List<AssetPo> assetPos = iAssetDao.selectList();//所有交易对
        ExpUserPo userPo = iExpUserDao.selectPoById(id);
        if(userPo==null)
            return null;
        FundAccountMngBizBo fundAccountBizBo = new FundAccountMngBizBo();

        fundAccountBizBo.setId(userPo.getId());
        String userName = !StringUtils.isEmpty(userPo.getPhone()) ? userPo.getPhone() : userPo.getEmail();
        fundAccountBizBo.setUserName(userName);
        fundAccountBizBo.setRealName(userPo.getRealName());
        List<AssetsBizBo> assetsBizBos = new ArrayList<>();
        for (AssetPo pcPo : assetPos) {
            //获取资金账户
            FundAccountBizBo fundAccount = iAccountBiz.getFundAccount(userPo.getId(), pcPo.getRealName());
            AssetsBizBo assetsBizBo = new AssetsBizBo();
            assetsBizBo.setAsset(pcPo.getRealName());
            if (null != fundAccount) {
                assetsBizBo.setFundAccountAvailable(fundAccount.getAvailable());
                assetsBizBo.setFundAccountLock(fundAccount.getLock());
                assetsBizBo.setFundAccountTotal(fundAccount.getTotal());
            } else {
                assetsBizBo.setFundAccountAvailable(new BigDecimal("0"));
                assetsBizBo.setFundAccountLock(new BigDecimal("0"));
                assetsBizBo.setFundAccountTotal(new BigDecimal("0"));
            }
            //获取合约账户
            PcAccountBizBo pcBo = iAccountBiz.getPcAccount(userPo.getId(), pcPo.getRealName());
            if (null != pcBo) {
                assetsBizBo.setPcAccountAvailable(pcBo.getAvailable());
                assetsBizBo.setPcAccountTotal(pcBo.getTotal());
                assetsBizBo.setPcOrderMargin(pcBo.getOrderMargin());
                assetsBizBo.setPcPosMargin(pcBo.getPosMargin());
            } else {
                assetsBizBo.setPcAccountAvailable(new BigDecimal("0"));
                assetsBizBo.setPcAccountTotal(new BigDecimal("0"));
                assetsBizBo.setPcOrderMargin(new BigDecimal("0"));
                assetsBizBo.setPcPosMargin(new BigDecimal("0"));
            }
            //获取币币账户
            BbAccountVo bbAccountVo = iAccountBiz.getBBAccount(userPo.getId(), pcPo.getRealName());
            if(bbAccountVo!=null){
                assetsBizBo.setBbAccountTotal(BigDecimal.ZERO);
                assetsBizBo.setBbAccountBalance(bbAccountVo.getBalance());
                assetsBizBo.setBbAccountFrozen(BigDecimal.ZERO);
            }else{
                assetsBizBo.setBbAccountTotal(new BigDecimal("0"));
                assetsBizBo.setBbAccountBalance(new BigDecimal("0"));
                assetsBizBo.setBbAccountFrozen(new BigDecimal("0"));
            }

            assetsBizBos.add(assetsBizBo);
        }
        fundAccountBizBo.setAssets(assetsBizBos);
        return fundAccountBizBo;
    }

}
