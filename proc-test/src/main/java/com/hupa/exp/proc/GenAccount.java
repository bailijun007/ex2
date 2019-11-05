package com.hupa.exp.proc;

import com.hupa.exp.account.def.Account4ServerDef;
import com.hupa.exp.account.def.fund.FundAccount4MngDef;
import com.hupa.exp.account.def.fund.FundAccount4ServerDef;
import com.hupa.exp.account.util.token.Account4ServerTokenUtil;
import com.hupa.exp.account.util.token.fund.FundAccount4MngTokenUtil;
import com.hupa.exp.account.util.token.fund.FundAccount4ServerTokenUtil;
import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.base.exception.account.AccountException;
import com.hupa.exp.base.exception.account.AccountTransferException;
import com.hupa.exp.base.exception.account.FundAccountException;
import com.hupa.exp.base.helper.security.SecurityPwdHelper;
import com.hupa.exp.bizuser.exception.BizUserException;
import com.hupa.exp.bizuser.service.def.IUserApiKeyBiz;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IAssetDao;
import com.hupa.exp.daomysql.dao.expv2.def.IExpUserDao;
import com.hupa.exp.daomysql.entity.po.expv2.AssetPo;
import com.hupa.exp.daomysql.entity.po.expv2.ExpUserPo;
import com.hupa.exp.help.IdCardGenerator;
import com.hupa.exp.help.RandomValueUtil;
import com.hupa.exp.pc.margin.def.account.PcAccount4ServerDef;
import com.hupa.exp.pc.margin.util.token.PcAccount4ServerTokenUtil;
import com.hupa.exp.util.math.DecimalUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Component
public class GenAccount {

//    @Reference
//    private AccountIdDef accountIdDef;
    @Reference
    private Account4ServerDef account4ServerDef;
    @Reference
    private FundAccount4MngDef fundAccount4MngDef;
    @Reference
    private FundAccount4ServerDef fundAccount4ServerDef;
    @Reference
    private PcAccount4ServerDef pcAccount4ServerDef;

    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;

    @Autowired
    private IExpUserDao iExpUserDao;

    @Autowired
    private IAssetDao iAssetDao;

    @Autowired
    private IUserApiKeyBiz iUserApiKeyBiz;

    @Autowired
    private SecurityPwdHelper securityPwdHelper;

    @PostConstruct
    private void stat()
    {

        List<AssetPo> assetPoList= iAssetDao.selectActiveList();
        IdCardGenerator g = new IdCardGenerator();
        for(int i=0;i<1000;i++)
        {
            ExpUserPo user=new ExpUserPo();
            String phone= RandomValueUtil.getTelephone();
            Long id=account4ServerDef.newAccountId();
            user.setId(id);
            user.setAreaCode("86");
            user.setPhone(phone);
            String pwd=securityPwdHelper.getMd5Pwd("PwdMD5Key","e10adc3949ba59abbe56e057f20f883e");
            String fundPwd= securityPwdHelper.getMd5Pwd("FundPwdMD5Key","e10adc3949ba59abbe56e057f20f883e");
            user.setUserpwd(pwd);
            user.setFundPwd(fundPwd);
            user.setPwdLevel(1);
            user.setFeeLevel(1);
            user.setMakerFee(new BigDecimal("0.02"));
            user.setTakerFee(new BigDecimal("0.075"));
            redisUtilDb0.hset("pc_fee","m_"+id, DecimalUtil.trimZeroPlainString(new BigDecimal("0.02")));
            redisUtilDb0.hset("pc_fee","t_"+id, DecimalUtil.trimZeroPlainString(new BigDecimal("0.075")));
            user.setStatus(1);
            user.setUserType(2);
            user.setRealName(RandomValueUtil.getChineseName());
            user.setIdNum(g.generate());
            user.setIdType(1);
            user.setCtime(System.currentTimeMillis());
            user.setMtime(System.currentTimeMillis());
            //创建账号
            iExpUserDao.insert(user);
            try {
                iUserApiKeyBiz.createApiKey(id,"",2);
            } catch (BizUserException e) {
                e.printStackTrace();
            }
            try {
                //创建账户
                account4ServerDef.createAccount(String.valueOf(id),id, Account4ServerTokenUtil.genToken4CreateAccount(String.valueOf(id),id));
                for(AssetPo po:assetPoList)
                {
                    //创建资金账号
                    fundAccount4ServerDef.createFundAccount(String.valueOf(id),id,po.getRealName(),
                            FundAccount4ServerTokenUtil.genToken4CreateFundAccount(String.valueOf(id),id,po.getRealName()));
                    //资金账号加钱
                    fundAccount4MngDef.addAvailableByManager(String.valueOf(id),id,po.getRealName(),new BigDecimal(10000000),
                            FundAccount4MngTokenUtil.genToken4AddAvailableByManager(String.valueOf(id),id, po.getRealName(), new BigDecimal(10000000)
                            ));
                    //创建合约账号
                    pcAccount4ServerDef.createPcAccount(String.valueOf(id),id,po.getRealName(),
                            PcAccount4ServerTokenUtil.genToken4CreatePcAccount(String.valueOf(id),id,po.getRealName()));
                    //资金划转
                    fundAccount4ServerDef.transfer2PcAccount(String.valueOf(id),
                            id,
                            po.getRealName(),
                            new BigDecimal(1000000),
                            FundAccount4ServerTokenUtil.genToken4Transfer2PcAccount(String.valueOf(id),id, po.getRealName(),new BigDecimal(1000000)));
                }
            } catch (AccountException e) {
                e.printStackTrace();
            } catch (FundAccountException e) {
                e.printStackTrace();
            } catch (AccountTransferException e) {
                e.printStackTrace();
            } catch (com.hupa.exp.base.exception.pc.PcAccountException e) {
                e.printStackTrace();
            }

        }
    }


}
