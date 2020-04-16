package com.hupa.exp.servermng.service.impl;


import com.hp.sh.expv3.fund.user.api.ZwIdGeneratorApi;
import com.hp.sh.expv3.fund.wallet.vo.request.FundAddRequest;
import com.hp.sh.expv3.fund.wallet.vo.request.FundCutRequest;
import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.base.dic.expv2.DbKeyDic;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.account.BbFeeBizBo;
import com.hupa.exp.bizother.entity.account.PcFeeBizBo;
import com.hupa.exp.bizother.entity.dic.ExpDicBizBo;
import com.hupa.exp.bizother.entity.fundaccount.FundAccountMngBizBo;
import com.hupa.exp.bizother.entity.fundaccount.FundAccountMngListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserRoleBizBo;
import com.hupa.exp.bizother.service.account.def.IAccountBiz;
import com.hupa.exp.bizother.service.account.def.IBbFeeBiz;
import com.hupa.exp.bizother.service.account.def.IPcFeeBiz;
import com.hupa.exp.bizother.service.dic.def.IDicService;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.bizother.service.user.def.IUserBiz;
import com.hupa.exp.bizother.service.user.def.IUserRoleService;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IAssetDao;
import com.hupa.exp.daomysql.dao.expv2.def.IExpUserDao;
import com.hupa.exp.daomysql.entity.po.expv2.AssetPo;
import com.hupa.exp.daomysql.entity.po.expv2.ExpUserPo;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.user.*;
import com.hupa.exp.servermng.enums.LoginExceptionCode;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.exception.LoginException;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiUserControllerService;
import com.hupa.exp.servermng.validate.UserValidateImpl;
import com.hupa.exp.util.convent.ConventObjectUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApiUserControllerServiceImpl implements IApiUserControllerService {

    private Logger logger = LoggerFactory.getLogger(ApiUserControllerServiceImpl.class);

    @Autowired
    private IUserBiz iUserBiz;
    @Autowired
    private IUserRoleService iUserRoleService;
    @Autowired
    private UserValidateImpl userValidate;

    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;

    @Autowired
    private IDicService dicService;

    @Autowired
    private IPcFeeBiz iPcFeeBiz;

    @Autowired
    private IBbFeeBiz iBbFeeBiz;

    @Autowired
    private IExpUserDao iExpUserDao;

    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private IAssetDao iAssetDao;

    @Autowired
    private IAccountBiz accountBiz;

    //获取主键-- 使用雪花算法
    @Autowired
    private ZwIdGeneratorApi zwIdGeneratorApi;


    private static final String userPwd="1a3e73de0f8de7ae613b439492300d030f64fd488eb3fd1d1adc657d04f416bc";



    /**
     * 创建或修改用户信息
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public UserOutputDto createUser(UserInputDto inputDto) throws BizException {
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        UserOutputDto outputDto = new UserOutputDto();
        ExpUserBizBo expUserBo = null;
        ExpUserBizBo beforeBo = iUserBiz.queryById(inputDto.getId());
        if (inputDto.getId() != null && inputDto.getId() > 0) {
            //如果没查询到数据，不能修改
            if(beforeBo ==null)
                throw new MngException(MngExceptionCode.EMAIL_EXIST_ERROR_MNG);
            expUserBo = beforeBo;
            //判断手机号是否已经注册过
            ExpUserPo userPhone=iExpUserDao.getUserInfoByPhone(inputDto.getPhone());
            //传进来的手机号不等于当前用户的手机号码但是找到的数据   表示已存在该手机号码
            if(beforeBo!=null && userPhone!=null && !beforeBo.getPhone().equals(userPhone.getPhone()))
                throw new MngException(MngExceptionCode.PHONE_EXIST_ERROR_MNG);
            //判断邮箱是否已经注册过
            ExpUserPo userEmail=iExpUserDao.selectUserInfoByEmail(inputDto.getEmail());
            //传进来的邮箱不等于当前用户的手机号码但是找到的数据   表示已存在该邮箱
            if(beforeBo!=null && userEmail!=null && !beforeBo.getEmail().equals(userEmail.getEmail()))
                throw new MngException(MngExceptionCode.EMAIL_EXIST_ERROR_MNG);

        expUserBo.setAreaCode(inputDto.getAreaCode());
        expUserBo.setEmail(StringUtils.isEmpty(inputDto.getEmail())?null:inputDto.getEmail());
        expUserBo.setPhone(StringUtils.isEmpty(inputDto.getPhone())?null:inputDto.getPhone());
        expUserBo.setRoleList(inputDto.getRoleList());
        expUserBo.setPwdLevel(1);
        expUserBo.setUserType(inputDto.getUserType());
        expUserBo.setStatus(inputDto.getStatus());
        expUserBo.setIdNum(inputDto.getIdNum());
        expUserBo.setRealName(inputDto.getRealName());
        expUserBo.setNationality(inputDto.getNationality());
        expUserBo.setUserpwd(inputDto.getPassword());
        expUserBo.setFundPwd(inputDto.getFundPwd());
        iUserBiz.editById(expUserBo);//入库
        //记日志
        logService.createOperationLog(user.getId(), user.getUserName(), OperationModule.User.toString(),
                OperationType.Update.toString(), JsonUtil.toJsonString(beforeBo),
                JsonUtil.toJsonString(expUserBo));
        } else {
            //验证参数
            userValidate.validate(inputDto);
            expUserBo = new ExpUserBizBo();
            expUserBo.setAreaCode(inputDto.getAreaCode());
            expUserBo.setEmail(StringUtils.isEmpty(inputDto.getEmail())?null:inputDto.getEmail());
            expUserBo.setPhone(StringUtils.isEmpty(inputDto.getPhone())?null:inputDto.getPhone());
            expUserBo.setRoleList(inputDto.getRoleList());
            expUserBo.setPwdLevel(1);
            expUserBo.setUserType(inputDto.getUserType());
            expUserBo.setStatus(inputDto.getStatus());
            expUserBo.setIdNum(inputDto.getIdNum());
            expUserBo.setRealName(inputDto.getRealName());
            expUserBo.setNationality(inputDto.getNationality());
            expUserBo.setUserpwd(inputDto.getPassword());
            expUserBo.setFundPwd(inputDto.getFundPwd());

            //用户名不让改  只有创建的时候可以有一个
            expUserBo.setUserName(inputDto.getUserName());
            //生成随机数---主键
            //Integer number = new Random().nextInt(900000) + 100000;
            //expUserBo.setId(Long.parseLong(System.currentTimeMillis() + String.valueOf(number)));
            expUserBo.setId(zwIdGeneratorApi.getNextId());
            iUserBiz.createUser(expUserBo);
            //记日志
            logService.createOperationLog(user.getId(), user.getUserName(), OperationModule.User.toString(),
                    OperationType.Insert.toString(), JsonUtil.toJsonString(expUserBo),
                    "");
            if (inputDto.getUserType() != 0) {
                try {
                    accountBiz.createFundAccount(expUserBo.getId(), "BTC");
                } catch (Exception e) {
                    throw new MngException(MngExceptionCode.DUBBO_SERVER_ERROR);
                }
            }
        }
        outputDto.setId(String.valueOf(expUserBo.getId()));
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }


    @Override
    public UserListOutputDto queryList(UserListInputDto inputDto) throws BizException {
        if (new SessionHelper().getToken() == null)
            throw new LoginException(LoginExceptionCode.TOKEN_NULL_ERROR);
        UserListOutputDto outputDto = new UserListOutputDto();
        ExpUserListBizBo listBizBo = iUserBiz.queryListByUserType(inputDto.getCurrentPage(), inputDto.getPageSize(),
                0, inputDto.getUserName(), inputDto.getId());
        List<UserListOutputPage> pageList = new ArrayList<>();
        if(listBizBo!=null && listBizBo.getRows()!=null && listBizBo.getRows().size()>0){
            UserListOutputPage user = null;
            for (ExpUserBizBo bo : listBizBo.getRows()) {
                user = new UserListOutputPage();
                user.setId(String.valueOf(bo.getId()));
                user.setUserName(bo.getUserName());
                user.setUserpwd(bo.getUserpwd());
                user.setUserType(String.valueOf(bo.getUserType()));
                user.setUserLevel(String.valueOf(bo.getUserLevel()));
                user.setPwdLevel(String.valueOf(bo.getPwdLevel()));
                user.setSmsVerify(String.valueOf(bo.getSmsVerify()));
                user.setEmailVerify(String.valueOf(bo.getEmailVerify()));
                user.setGoogleVerify(String.valueOf(bo.getGoogleVerify()));
                user.setAreaCode(bo.getAreaCode());
                user.setStatus(String.valueOf(bo.getStatus()));
                user.setCtime(String.valueOf(bo.getCtime()));
                user.setMtime(String.valueOf(bo.getMtime()));
                user.setEmail(bo.getEmail());
                user.setPhone(bo.getPhone());
                user.setReferrerId(bo.getReferrerId());
                user.setReferrerCode(bo.getReferrerCode());
                user.setNationality(bo.getNationality());
                user.setRealName(bo.getRealName());
                user.setIdNum(bo.getIdNum());
                user.setFundPwd(bo.getFundPwd());
                user.setSecretKey(bo.getSecretKey());
                user.setFeeLevel(String.valueOf(bo.getFeeLevel()));
                user.setBbFeeLevel(String.valueOf(bo.getBbFeeLevel()));
                user.setIdType(String.valueOf(bo.getIdType()));
                user.setSurname(bo.getSurname());
                user.setRealName(String.valueOf(bo.getId()));
                user.setLoginTime(String.valueOf(bo.getLoginTime()));
                user.setLoginIp(bo.getLoginIp());
                List<String> roleList = new ArrayList<>();
                for (Integer roleId : bo.getRoleList()) {
                    roleList.add(String.valueOf(roleId));
                }
                user.setRoleList(roleList);
            }
        }
        outputDto.setRows(pageList);
        outputDto.setTotal(listBizBo.getTotal());
        outputDto.setSizePerPage(inputDto.getPageSize());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    /**
     * 查询用户信息---修改
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public UserOutputDto queryUserInfoById(UserInputDto inputDto) throws BizException {
        ExpUserBizBo expUserBo = iUserBiz.queryById(inputDto.getId());
        UserOutputDto outputDto = new UserOutputDto();
        if(expUserBo!=null){
            outputDto.setId(String.valueOf(expUserBo.getId()));
            outputDto.setUserName(expUserBo.getUserName());
            outputDto.setUserPwd(expUserBo.getUserpwd());
            outputDto.setUserType(String.valueOf(expUserBo.getUserType()));
            outputDto.setUserLevel(String.valueOf(expUserBo.getUserLevel()));
            outputDto.setPwdLevel(String.valueOf(expUserBo.getPwdLevel()));
            outputDto.setSmsVerify(String.valueOf(expUserBo.getSmsVerify()));
            outputDto.setEmailVerify(String.valueOf(expUserBo.getEmailVerify()));
            outputDto.setGoogleVerify(String.valueOf(expUserBo.getGoogleVerify()));
            outputDto.setAreaCode(expUserBo.getAreaCode());
            outputDto.setStatus(String.valueOf(expUserBo.getStatus()));
            outputDto.setCtime(String.valueOf(expUserBo.getCtime()));
            outputDto.setMtime(String.valueOf(expUserBo.getMtime()));
            outputDto.setEmail(expUserBo.getEmail());
            outputDto.setPhone(expUserBo.getPhone());
            outputDto.setReferrerId(expUserBo.getReferrerId());
            outputDto.setReferrerCode(expUserBo.getReferrerCode());
            outputDto.setNationality(expUserBo.getNationality());
            outputDto.setRealName(expUserBo.getRealName());
            outputDto.setIdNum(expUserBo.getIdNum());
            outputDto.setFundPwd(expUserBo.getFundPwd());
            outputDto.setSecretKey(expUserBo.getSecretKey());
            outputDto.setFeeLevel(String.valueOf(expUserBo.getFeeLevel()));
            outputDto.setBbFeeLevel(String.valueOf(expUserBo.getBbFeeLevel()));
            outputDto.setIdType(String.valueOf(expUserBo.getIdType()));
            outputDto.setSurname(expUserBo.getSurname());
            outputDto.setLoginTime(String.valueOf(expUserBo.getLoginTime()));
            outputDto.setLoginIp(expUserBo.getLoginIp());
        }
        List<String> roleList = new ArrayList<>();
        List<ExpUserRoleBizBo> expUserRoleList = iUserRoleService.queryPosByUserId(inputDto.getId());
        if(expUserRoleList!=null && expUserRoleList.size()>0){
            for (int i = 0; i < expUserRoleList.size(); i++) {
                roleList.add(String.valueOf(expUserRoleList.get(i).getRoleid()));
            }
        }
        outputDto.setRoleList(roleList);
        return outputDto;
    }

/*    @Override
    public FundAccountListOutputDto queryFundAccountList(FundAccountListInputDto inputDto) throws BizException {
        if (inputDto.getPageSize() > 100)
            inputDto.setPageSize(100);
        FundAccountListOutputDto outputDto = new FundAccountListOutputDto();
        FundAccountMngListBizBo listBizBo = iUserBiz.queryFundAccountList(inputDto.getCurrentPage(), inputDto.getPageSize(), 1, inputDto.getUserName(), inputDto.getId());
        List<FundAccountListOutputPage> pageList = new ArrayList<>();
        for (FundAccountMngBizBo bo : listBizBo.getRows()) {
            FundAccountListOutputPage account = new FundAccountListOutputPage();
            account.setAssets(bo.getAssets());
            account.setId(String.valueOf(bo.getId()));
            account.setRealName(bo.getRealName());
            account.setUserName(bo.getUserName());
            pageList.add(account);
        }
        outputDto.setRows(pageList);
        outputDto.setTotal(listBizBo.getTotal());
        outputDto.setSizePerPage(inputDto.getCurrentPage());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }*/

    /**
     * 获取账户资金列表
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public FundAccountListOutputDto queryFundAccountListByParam(FundAccountListInputDto inputDto) throws BizException {
        if (inputDto.getPageSize() > 100)
            inputDto.setPageSize(100);
        FundAccountListOutputDto outputDto = new FundAccountListOutputDto();
        FundAccountMngListBizBo listBizBo = iUserBiz.queryFundAccountListByParam(inputDto.getCurrentPage(), inputDto.getPageSize(), inputDto.getUserType(), inputDto.getUserName(),inputDto.getAsset(),inputDto.getId());
        List<FundAccountListOutputPage> pageList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(listBizBo.getRows())){
            FundAccountListOutputPage account = null;
            for (FundAccountMngBizBo bo : listBizBo.getRows()) {
                account = new FundAccountListOutputPage();
                account.setAssets(bo.getAssets());
                account.setId(String.valueOf(bo.getId()));
                account.setRealName(bo.getRealName());
                account.setUserName(bo.getUserName());
                pageList.add(account);
            }
        }
        outputDto.setRows(pageList);
        outputDto.setTotal(listBizBo.getTotal());
        outputDto.setSizePerPage(inputDto.getCurrentPage());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public EnableUserOutputDto enableUser(EnableUserInputDto inputDto) throws BizException {
        String[] idsArr = inputDto.getIds().split(",");
        long[] ids = Arrays.asList(idsArr).stream().mapToLong(Long::parseLong).toArray();
        ExpUserBizBo bizBo = null;
        for (long id : ids) {
            bizBo = iUserBiz.queryById(id);
            bizBo.setStatus(inputDto.getStatus());
            iUserBiz.enableById(bizBo);
        }
        EnableUserOutputDto outputDto = new EnableUserOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }


    @Override
    public CreateAccountOutputDto createAccount(CreateAccountInputDto inputDto) throws MngException {
        List<ExpUserBizBo> bizBos = iUserBiz.queryList();
        List<AssetPo> assetPos=iAssetDao.selectActiveList();
        for (ExpUserBizBo bo : bizBos) {
            if (bo.getUserType() != 0) {
                assetPos.forEach(assetPo -> {
                    try {
                        accountBiz.createFundAccount(bo.getId(),assetPo.getRealName());//创建资金账户
                        accountBiz.createPcAccount(bo.getId(),assetPo.getRealName());//创建合约账户
                        accountBiz.createBBAccount(bo.getId(),assetPo.getRealName());//创建币币账户
                    } catch (Exception e) {
                        logger.info("ApiUserControllerServiceImpl createAccount Exception:"+e.getMessage());
                    }
                });
            }
        }
        return null;
    }

    @Override
    public CreateAccountOutputDto batchCreateAccount(CreateAccountInputDto inputDto) throws BizException {
        ExpUserPo expUserPo = null;
        String strName = "测试号";
        int number = 0;
        ExpUserPo userPo =  iExpUserDao.selectUser(3,"@exp.com");
        if(userPo.getEmail()!=null){
            try {
                String[] array = userPo.getEmail().split("@");
                number = Integer.parseInt(array[0]);//把字符串强制转换为数字
            } catch (Exception e) {
            }
        }
        for (int i= number+1;i< number+inputDto.getNumber()+1; i++){
            expUserPo = new ExpUserPo();
            expUserPo.setId(zwIdGeneratorApi.getNextId());
            expUserPo.setUserName(strName+String.valueOf(i));
            expUserPo.setUserLevel(1);
            expUserPo.setUserpwd(userPwd);
            expUserPo.setPwdLevel(1);
            expUserPo.setFeeLevel(1);
            expUserPo.setBbFeeLevel(1);
            expUserPo.setEmail(String.valueOf(i)+"@exp.com");
            expUserPo.setPhone(UUID.randomUUID().toString());
            expUserPo.setAreaCode("86");
            expUserPo.setUserType(3); //批量创建的用户
            expUserPo.setStatus(1);
            expUserPo.setReferrerCode(StringUtils.EMPTY);
            expUserPo.setReferrerId(null);
            expUserPo.setSmsVerify(0);
            expUserPo.setEmailVerify(0);
            expUserPo.setGoogleVerify(0);
            expUserPo.setNationality(StringUtils.EMPTY);
            expUserPo.setRealName(StringUtils.EMPTY);
            expUserPo.setIdNum(StringUtils.EMPTY);
            expUserPo.setCtime(System.currentTimeMillis());
            expUserPo.setMtime(System.currentTimeMillis());
            expUserPo.setFundPwd(userPwd);
            expUserPo.setSecretKey(StringUtils.EMPTY);
            expUserPo.setIdType(null);
            expUserPo.setSurname(StringUtils.EMPTY);
            expUserPo.setName(StringUtils.EMPTY);
            expUserPo.setMakerFee(null);
            expUserPo.setTakerFee(null);
            expUserPo.setBbMakerFee(null);
            expUserPo.setBbTakerFee(null);
            iExpUserDao.insert(expUserPo);
        }
        CreateAccountOutputDto outputDto = new CreateAccountOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public GenFeeOutputDto genFee(GenFeeInputDto inputDto) throws BizException {
        List<ExpUserBizBo> bizBos = iUserBiz.queryList();
        ExpDicBizBo dicBizBo = dicService.queryDicByKey(DbKeyDic.PcFeeRedisKey);
        String redisKey = dicBizBo.getValue();
        List<PcFeeBizBo> pcFeeBizBoList = iPcFeeBiz.getAllPcFee();
        Map<Integer, PcFeeBizBo> feeMap = pcFeeBizBoList.stream().collect(Collectors.toMap(PcFeeBizBo::getTier, a -> a, (k1, k2) -> k1));
        BigDecimal redisTakerFee = new BigDecimal("0");
        BigDecimal redisMakerFee = new BigDecimal("0");
        BigDecimal dbTakerFee = new BigDecimal("0");
        BigDecimal dbMakerFee = new BigDecimal("0");

        ExpDicBizBo dicBbBizBo = dicService.queryDicByKey("BbFeeRedisKey");
        String redisBbKey = dicBbBizBo.getValue();
        List<BbFeeBizBo> bbFeeBizBoList = iBbFeeBiz.getAllBbFee();
        Map<Integer, BbFeeBizBo> bBFeeMap = bbFeeBizBoList.stream().collect(Collectors.toMap(BbFeeBizBo::getTier, a -> a, (k1, k2) -> k1));
        BigDecimal redisTakerBbFee = new BigDecimal("0");
        BigDecimal redisMakerBbFee = new BigDecimal("0");
        BigDecimal dbTakerBbFee = new BigDecimal("0");
        BigDecimal dbMakerBbFee = new BigDecimal("0");

        for (ExpUserBizBo userBizBo : bizBos) {

            if (userBizBo.getUserType() != 0) {

                //默认一级pc合约
                if (userBizBo.getFeeLevel() == null) {
                    redisTakerFee = feeMap.get(1).getTakerFee().divide(new BigDecimal("100"));
                    redisMakerFee = feeMap.get(1).getMakerFee().divide(new BigDecimal("100"));
                    dbTakerFee = feeMap.get(1).getTakerFee();
                    dbMakerFee = feeMap.get(1).getMakerFee();
                    userBizBo.setFeeLevel(feeMap.get(1).getTier());
                } else {
                    redisTakerFee = feeMap.get(userBizBo.getFeeLevel()).getTakerFee().divide(new BigDecimal("100"));
                    redisMakerFee = feeMap.get(userBizBo.getFeeLevel()).getMakerFee().divide(new BigDecimal("100"));
                    dbTakerFee = feeMap.get(userBizBo.getFeeLevel()).getTakerFee();
                    dbMakerFee = feeMap.get(userBizBo.getFeeLevel()).getMakerFee();
                    userBizBo.setFeeLevel(feeMap.get(userBizBo.getFeeLevel()).getTier());
                }
                //默认一级bb交易
                if (userBizBo.getBbFeeLevel() == null) {
                    redisTakerBbFee = bBFeeMap.get(1).getTakerFee().divide(new BigDecimal("100"));
                    redisMakerBbFee = bBFeeMap.get(1).getMakerFee().divide(new BigDecimal("100"));
                    dbTakerBbFee = bBFeeMap.get(1).getTakerFee();
                    dbMakerBbFee = bBFeeMap.get(1).getMakerFee();
                    userBizBo.setBbFeeLevel(bBFeeMap.get(1).getTier());
                } else {
                    redisTakerBbFee = bBFeeMap.get(userBizBo.getBbFeeLevel()).getTakerFee().divide(new BigDecimal("100"));
                    redisMakerBbFee = bBFeeMap.get(userBizBo.getBbFeeLevel()).getMakerFee().divide(new BigDecimal("100"));
                    dbTakerBbFee = bBFeeMap.get(userBizBo.getBbFeeLevel()).getTakerFee();
                    dbMakerBbFee = bBFeeMap.get(userBizBo.getBbFeeLevel()).getMakerFee();
                    userBizBo.setBbFeeLevel(bBFeeMap.get(userBizBo.getBbFeeLevel()).getTier());
                }

                if (!StringUtils.isEmpty(userBizBo.getReferrerId())) {
                    userBizBo.setTakerFee(dbTakerFee.multiply(new BigDecimal("0.95")));
                    userBizBo.setMakerFee(dbMakerFee.multiply(new BigDecimal("0.95")));
                    redisUtilDb0.hset(redisKey, "t_" + userBizBo.getId(),
                            redisTakerFee.multiply(new BigDecimal("0.95")));
                    redisUtilDb0.hset(redisKey, "m_" + userBizBo.getId(),
                            redisMakerFee.multiply(new BigDecimal("0.95")));

                    userBizBo.setBbTakerFee(dbTakerBbFee.multiply(new BigDecimal("0.95")));
                    userBizBo.setBbMakerFee(dbMakerBbFee.multiply(new BigDecimal("0.95")));
                    redisUtilDb0.hset(redisBbKey, "t_" + userBizBo.getId(), redisTakerBbFee.multiply(new BigDecimal("0.95")));
                    redisUtilDb0.hset(redisBbKey, "m_" + userBizBo.getId(), redisMakerBbFee.multiply(new BigDecimal("0.95")));
                } else {
                    userBizBo.setTakerFee(dbTakerFee);
                    userBizBo.setMakerFee(dbMakerFee);
                    redisUtilDb0.hset(redisKey, "t_" + userBizBo.getId(), redisTakerFee);
                    redisUtilDb0.hset(redisKey, "m_" + userBizBo.getId(), redisMakerFee);

                    userBizBo.setBbTakerFee(dbTakerBbFee);
                    userBizBo.setBbMakerFee(dbMakerBbFee);
                    redisUtilDb0.hset(redisBbKey, "t_" + userBizBo.getId(), redisTakerBbFee);
                    redisUtilDb0.hset(redisBbKey, "m_" + userBizBo.getId(), redisMakerBbFee);
                }
                ExpUserPo userPo= ConventObjectUtil.conventObject(userBizBo,ExpUserPo.class);
                iExpUserDao.updateById(userPo);
            }
        }
        return null;
    }

    @Override
    public CheckExistUserOutputDto checkExistUser(CheckExistUserInputDto inputDto) throws BizException {
        CheckExistUserOutputDto outputDto = new CheckExistUserOutputDto();
        outputDto.setHasUser(false);
        if (iExpUserDao.selectUserByAccount(inputDto.getAccount()) != null)
            outputDto.setHasUser(true);
        return outputDto;
    }

    @Override
    public DeleteOutputDto deleteUser(DeleteInputDto inputDto) throws BizException {
        String[] ids=inputDto.getIds().split(",");
        for(String id:ids)
        {
            iExpUserDao.deleteById(Long.parseLong(id));
        }
        ExpUserBizBo user=sessionHelper.getUserInfoBySession();
        logService.createOperationLog(user.getId(),user.getUserName(),
                OperationModule.User.toString(), OperationType.Delete.toString(),
                inputDto.getIds(),"");
        DeleteOutputDto outputDto=new DeleteOutputDto();
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public FundAccountInfoOutputDto queryFundAccountById(FundAccountInfoInputDto inputDto) throws BizException {
        FundAccountMngBizBo bo = iUserBiz.queryFundAccountById(inputDto.getId());
        FundAccountInfoOutputDto outputDto = new FundAccountInfoOutputDto();
        outputDto.setAssets(bo.getAssets());
        outputDto.setId(String.valueOf(bo.getId()));
        outputDto.setRealName(bo.getRealName());
        outputDto.setUserName(bo.getUserName());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public EditFundAccountOutputDto editFundAccount(EditFundAccountInputDto inputDto) throws BizException {

        return null;
    }

    /**
     * 给资金账户加钱和扣钱
     * @param inputDto
     * @return
     * @throws BizException
     */
    @Override
    public EditFundAccountOutputDto editFundAccountOneAsset(EditFundAccountInputDto inputDto) throws BizException {
        String fundStr = "";
        try {
            fundStr = URLDecoder.decode(inputDto.getFunds(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_VALUE_ERROR);
        }
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        FundAccountMngBizBo beforeBo = iUserBiz.queryFundAccountById(inputDto.getId());
        String[] assetArr = fundStr.split("[|]");
        if (assetArr.length > 0) {
           try {
                String asset = assetArr[0];
                //创建资产账户
               accountBiz.createFundAccount(inputDto.getId(),asset);
                BigDecimal delta = new BigDecimal(assetArr[1]);
               //生成随机数---主键
               String tradeNo = String.valueOf(zwIdGeneratorApi.getNextId());
               if(inputDto.getType()!=null && inputDto.getType()==1){
                //if(delta.compareTo(new BigDecimal("0")) == 1) {
                    //加钱
                    FundAddRequest fundRequest = new FundAddRequest();
                    fundRequest.setUserId(inputDto.getId());// 用户ID
                    fundRequest.setAsset(asset); //资产
                    fundRequest.setAmount(delta); //本笔金额
                    fundRequest.setTradeType(3);//订单类型：1-充值，2-消费，3-后台充值，4-后台扣款 5-撤单  6-下注 7-下级返点 8-彩票中奖
                    fundRequest.setRemark("资产账户加钱");//备注
                    fundRequest.setTradeNo(tradeNo); //调用方支付单号
                    accountBiz.addFundAccount(fundRequest,inputDto.getId(),asset);
               // }else if(delta.compareTo(new BigDecimal("0"))== -1){
                }else if(inputDto.getType()!=null && inputDto.getType()==0){
                    //扣钱
                    FundCutRequest fundRequest = new FundCutRequest();
                    fundRequest.setUserId(inputDto.getId()); // 用户ID
                    fundRequest.setAsset(asset); //资产
                    fundRequest.setAmount(delta); //本笔金额
                    fundRequest.setTradeType(4);//订单类型：1-充值，2-消费，3-后台充值，4-后台扣款 5-撤单  6-下注 7-下级返点 8-彩票中奖
                    fundRequest.setRemark("资产账户扣钱"); //备注
                    fundRequest.setTradeNo(tradeNo);//调用方支付单号
                    accountBiz.cutFundAccount(fundRequest, inputDto.getId(),asset);
                }
                FundAccountMngBizBo afterBo = iUserBiz.queryFundAccountById(inputDto.getId());
                //记日志
                logService.createOperationLog(user.getId(), user.getUserName(), OperationModule.User.toString(),
                        OperationType.Insert.toString(), JsonUtil.toJsonString(beforeBo),
                        JsonUtil.toJsonString(afterBo));
            } catch (Exception e) {
               throw new MngException(MngExceptionCode.DUBBO_SERVER_ERROR);
           }
        }
        EditFundAccountOutputDto outputDto = new EditFundAccountOutputDto();
        return outputDto;
    }


    @Override
    public UserListOutputDto queryListByUserType(UserListInputDto inputDto) throws BizException {
        ExpUserListBizBo listBizBo = iUserBiz.queryListByUserType(inputDto.getCurrentPage(), inputDto.getPageSize(),
                inputDto.getUserType(), inputDto.getUserName(), inputDto.getId());
        List<UserListOutputPage> pageList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(listBizBo.getRows())){
            UserListOutputPage user = null;
            for (ExpUserBizBo bo : listBizBo.getRows()) {
                user = new UserListOutputPage();
                user.setId(String.valueOf(bo.getId()));
                user.setUserName(bo.getUserName());
                user.setUserpwd(bo.getUserpwd());
                user.setUserType(String.valueOf(bo.getUserType()));
                user.setUserLevel(String.valueOf(bo.getUserLevel()));
                user.setPwdLevel(String.valueOf(bo.getPwdLevel()));
                user.setSmsVerify(String.valueOf(bo.getSmsVerify()));
                user.setEmailVerify(String.valueOf(bo.getEmailVerify()));
                user.setGoogleVerify(String.valueOf(bo.getGoogleVerify()));
                user.setAreaCode(bo.getAreaCode());
                user.setStatus(String.valueOf(bo.getStatus()));
                user.setCtime(String.valueOf(bo.getCtime()));
                user.setMtime(String.valueOf(bo.getMtime()));
                user.setEmail(bo.getEmail());
                user.setPhone(bo.getPhone());
                user.setReferrerId(bo.getReferrerId());
                user.setReferrerCode(bo.getReferrerCode());
                user.setNationality(bo.getNationality());
                user.setRealName(bo.getRealName());
                user.setIdNum(bo.getIdNum());
                user.setFundPwd(bo.getFundPwd());
                user.setSecretKey(bo.getSecretKey());
                user.setFeeLevel(String.valueOf(bo.getFeeLevel()));
                user.setBbFeeLevel(String.valueOf(bo.getBbFeeLevel()));
                user.setIdType(String.valueOf(bo.getIdType()));
                user.setSurname(bo.getSurname());
                user.setRealName(String.valueOf(bo.getId()));
                user.setLoginTime(String.valueOf(bo.getLoginTime()));
                user.setLoginIp(bo.getLoginIp());
                pageList.add(user);
            }
        }
        UserListOutputDto outputDto = new UserListOutputDto();
        outputDto.setRows(pageList);
        outputDto.setTotal(listBizBo.getTotal());
        outputDto.setSizePerPage(inputDto.getPageSize());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
