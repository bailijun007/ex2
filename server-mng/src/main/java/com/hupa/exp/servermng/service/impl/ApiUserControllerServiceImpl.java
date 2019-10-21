package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.account.def.Account4ServerDef;
import com.hupa.exp.account.def.fund.FundAccount4MngDef;
import com.hupa.exp.account.def.fund.FundAccount4ServerDef;
import com.hupa.exp.account.service.account.def.FundAccountDef;
import com.hupa.exp.account.util.token.Account4ServerTokenUtil;
import com.hupa.exp.account.util.token.fund.FundAccount4MngTokenUtil;
import com.hupa.exp.account.util.token.fund.FundAccount4ServerTokenUtil;
import com.hupa.exp.base.config.redis.Db0RedisBean;
import com.hupa.exp.base.enums.OperationModule;
import com.hupa.exp.base.enums.OperationType;
import com.hupa.exp.base.enums.ValidateExceptionCode;
import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.account.PcFeeBizBo;
import com.hupa.exp.bizother.entity.dic.ExpDicBizBo;
import com.hupa.exp.bizother.entity.fundaccount.FundAccountMngBizBo;
import com.hupa.exp.bizother.entity.fundaccount.FundAccountMngListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserListBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserRoleBizBo;
import com.hupa.exp.bizother.service.account.def.IPcFeeBiz;
import com.hupa.exp.bizother.service.dic.def.IDicService;
import com.hupa.exp.bizother.service.operationlog.def.IExpOperationLogService;
import com.hupa.exp.bizother.service.user.def.IUserBiz;
import com.hupa.exp.bizother.service.user.def.IUserRoleService;
import com.hupa.exp.common.component.redis.RedisUtil;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.format.JsonUtil;
import com.hupa.exp.daomysql.dao.expv2.def.IExpUserDao;
import com.hupa.exp.id.def.account.AccountIdDef;
import com.hupa.exp.servermng.entity.user.*;
import com.hupa.exp.servermng.enums.LoginExceptionCode;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.exception.LoginException;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiUserControllerService;
import com.hupa.exp.servermng.validate.UserValidateImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApiUserControllerServiceImpl implements IApiUserControllerService {
    @Autowired
    private IUserBiz iUserBiz;
    @Autowired
    private IUserRoleService iUserRoleService;
    @Autowired
    private UserValidateImpl userValidate;

    @Reference
    private AccountIdDef accountIdDef;

    @Reference
    private Account4ServerDef account4ServerDef;

    @Reference
    private FundAccount4MngDef fundAccount4MngDef;

    @Reference
    private FundAccount4ServerDef fundAccount4ServerDef;

    @Autowired
    private FundAccountDef fundAccountDef;

    @Autowired
    @Qualifier(Db0RedisBean.beanName)
    private RedisUtil redisUtilDb0;


    @Autowired
    private IDicService dicService;

    @Autowired
    private IPcFeeBiz iPcFeeBiz;

    @Autowired
    private IExpUserDao iExpUserDao;


//
//    @Autowired
//    @Qualifier(Db1RedisBean.beanName)
//    private RedisUtil redisUtilDb1;


    @Autowired
    private IExpOperationLogService logService;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public UserOutputDto createUser(UserInputDto inputDto) throws BizException {

        ExpUserBizBo expUserBo = new ExpUserBizBo();
        ExpUserBizBo beforeBo = null;
        if (inputDto.getId() != null && inputDto.getId() > 0) {
            beforeBo = iUserBiz.queryById(inputDto.getId());
            if (beforeBo != null)
                expUserBo = beforeBo;
        }
        expUserBo.setAreaCode(inputDto.getAreaCode());
        expUserBo.setEmail(inputDto.getEmail());
        expUserBo.setPhone(inputDto.getPhone());
        expUserBo.setRoleList(inputDto.getRoleList());
        expUserBo.setPwdLevel(1);
        expUserBo.setUserType(inputDto.getUserType());
        expUserBo.setStatus(inputDto.getStatus());

        expUserBo.setUserpwd(inputDto.getPassword());
        expUserBo.setFundPwd(inputDto.getFundPwd());
        UserOutputDto outputDto = new UserOutputDto();
        //userValidate.validate(inputDto);//验证参数
        long id = accountIdDef.newAccountId();
        ExpUserBizBo user = sessionHelper.getUserInfoBySession();
        if (inputDto.getId() > 0) {
            iUserBiz.editById(expUserBo);
            //记日志
            logService.createOperationLog(user.getId(), user.getUserName(), OperationModule.User.toString(),
                    OperationType.Update.toString(), JsonUtil.toJsonString(beforeBo),
                    JsonUtil.toJsonString(expUserBo));
            id = expUserBo.getId();
        } else {
            //用户名不让改  只有创建的时候可以有一个
            expUserBo.setUserName(inputDto.getUserName());
            expUserBo.setId(id);
            iUserBiz.createUser(expUserBo);
            //记日志
            logService.createOperationLog(user.getId(), user.getUserName(), OperationModule.User.toString(),
                    OperationType.Insert.toString(), JsonUtil.toJsonString(expUserBo),
                    "");
            if (inputDto.getUserType() != 0) {
                try {
                    account4ServerDef.createAccount(id, Account4ServerTokenUtil.genToken4CreateAccount(id));
                    //fundAccount4ServerDef.createFundAccount(id, "BTC", FundAccount4ServerTokenUtil.genToken4CreateFundAccount(id, "BTC"));
                } catch (Exception e) {
                    throw new MngException(MngExceptionCode.DUBBO_SERVER_ERROR);
                }
            }
        }
        outputDto.setId(String.valueOf(id));
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
        for (ExpUserBizBo bo : listBizBo.getRows()) {
            UserListOutputPage user = new UserListOutputPage();
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
        outputDto.setRows(pageList);
        outputDto.setTotal(listBizBo.getTotal());
        outputDto.setSizePerPage(inputDto.getPageSize());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }

    @Override
    public UserOutputDto queryUserInfoById(UserInputDto inputDto) throws BizException {

        ExpUserBizBo expUserBo = iUserBiz.queryById(inputDto.getId());
        List<ExpUserRoleBizBo> expUserRoleList = iUserRoleService.queryPosByUserId(inputDto.getId());
        UserOutputDto outputDto = new UserOutputDto();
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
        outputDto.setIdType(String.valueOf(expUserBo.getIdType()));
        outputDto.setSurname(expUserBo.getSurname());
        outputDto.setRealName(String.valueOf(expUserBo.getId()));
        outputDto.setLoginTime(String.valueOf(expUserBo.getLoginTime()));
        outputDto.setLoginIp(expUserBo.getLoginIp());
        List<String> roleList = new ArrayList<>();
        for (int i = 0; i < expUserRoleList.size(); i++) {
            roleList.add(String.valueOf(expUserRoleList.get(i).getRoleid()));
        }
        outputDto.setRoleList(roleList);

        return outputDto;
    }

    @Override
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
    }

    @Override
    public FundAccountListOutputDto queryFundAccountListByParam(FundAccountListInputDto inputDto) throws BizException {
        if (inputDto.getPageSize() > 100)
            inputDto.setPageSize(100);
        FundAccountListOutputDto outputDto = new FundAccountListOutputDto();
        FundAccountMngListBizBo listBizBo = iUserBiz.queryFundAccountListByParam(inputDto.getCurrentPage(), inputDto.getPageSize(), inputDto.getUserType(), inputDto.getUserName(), inputDto.getId());
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
    }

    @Override
    public EnableUserOutputDto enableUser(EnableUserInputDto inputDto) throws BizException {
        String[] idsArr = inputDto.getIds().split(",");
        long[] ids = Arrays.asList(idsArr).stream().mapToLong(Long::parseLong).toArray();
        for (long id : ids) {
            ExpUserBizBo bizBo = iUserBiz.queryById(id);
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
        for (ExpUserBizBo bo : bizBos) {
            if (bo.getUserType() != 0) {
                try {
                    account4ServerDef.createAccount(bo.getId(), Account4ServerTokenUtil.genToken4CreateAccount(bo.getId()));
                } catch (Exception e) {
                    throw new MngException(MngExceptionCode.DUBBO_SERVER_ERROR);
                }

                //fundAccount4ServerDef.createFundAccount(id, "BTC", FundAccount4ServerTokenUtil.genToken4CreateFundAccount(id, "BTC"));
            }
        }
        return null;
    }

    @Override
    public GenFeeOutputDto genFee(GenFeeInputDto inputDto) throws BizException {
        List<ExpUserBizBo> bizBos = iUserBiz.queryList();
        ExpDicBizBo dicBizBo = dicService.queryDicByKey("PcFeeRedisKey");
        String redisKey = dicBizBo.getValue();
        List<PcFeeBizBo> pcFeeBizBoList = iPcFeeBiz.getAllPcFee();
        Map<Integer, PcFeeBizBo> feeMap = pcFeeBizBoList.stream().collect(Collectors.toMap(PcFeeBizBo::getTier, a -> a, (k1, k2) -> k1));
        BigDecimal redistakerFee = new BigDecimal("0");
        BigDecimal redismakerFee = new BigDecimal("0");
        BigDecimal dbtakerFee = new BigDecimal("0");
        BigDecimal dbmakerFee = new BigDecimal("0");
        for (ExpUserBizBo userBizBo : bizBos) {

            if (userBizBo.getUserType() != 0) {

                //默认一级
                if (userBizBo.getFeeLevel() == null) {
                    redistakerFee = feeMap.get(1).getTakerFee().divide(new BigDecimal("100"));
                    redismakerFee = feeMap.get(1).getMakerFee().divide(new BigDecimal("100"));
                    dbtakerFee = feeMap.get(1).getTakerFee();
                    dbmakerFee = feeMap.get(1).getMakerFee();
                    userBizBo.setFeeLevel(feeMap.get(1).getTier());
                } else {
                    redistakerFee = feeMap.get(userBizBo.getFeeLevel()).getTakerFee().divide(new BigDecimal("100"));
                    redismakerFee = feeMap.get(userBizBo.getFeeLevel()).getMakerFee().divide(new BigDecimal("100"));
                    dbtakerFee = feeMap.get(userBizBo.getFeeLevel()).getTakerFee();
                    dbmakerFee = feeMap.get(userBizBo.getFeeLevel()).getMakerFee();
                    userBizBo.setFeeLevel(feeMap.get(userBizBo.getFeeLevel()).getTier());
                }
                if (!StringUtils.isEmpty(userBizBo.getReferrerId())) {
                    userBizBo.setTakerFee(dbtakerFee.multiply(new BigDecimal("0.95")));
                    userBizBo.setMakerFee(dbmakerFee.multiply(new BigDecimal("0.95")));
                    redisUtilDb0.hset(redisKey, "t_" + userBizBo.getId(),
                            redistakerFee.multiply(new BigDecimal("0.95")));
                    redisUtilDb0.hset(redisKey, "m_" + userBizBo.getId(),
                            redismakerFee.multiply(new BigDecimal("0.95")));
                } else {
                    userBizBo.setTakerFee(dbtakerFee);
                    userBizBo.setMakerFee(dbmakerFee);
                    redisUtilDb0.hset(redisKey, "t_" + userBizBo.getId(),
                            redistakerFee);
                    redisUtilDb0.hset(redisKey, "m_" + userBizBo.getId(),
                            redismakerFee);
                }
                iUserBiz.editById(userBizBo);
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
//        String fundStr = "";
//        try {
//            fundStr = URLDecoder.decode(inputDto.getFunds(), "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        if (!StringUtils.isEmpty(fundStr)) {
//
//            String[] fundArr = fundStr.split(",");
//            for (String fund : fundArr) {
//                String[] pair = fund.split("[|]");
//                if (StringUtils.isEmpty(redisUtilDb1.get("fundAccount:" + pair[0] + ":" + inputDto.getId()))) {
//                    fundAccount4ServerDef.createFundAccount(inputDto.getId(), pair[0],
//                            FundAccount4ServerTokenUtil.genToken4CreateFundAccount(inputDto.getId(), pair[0]));
//                }
//                BigDecimal delta = new BigDecimal(pair[1]);
//                boolean bol = fundAccount4MngDef.addAvailableByManager(inputDto.getId(), pair[0], delta,
//                        FundAccount4MngTokenUtil.genToken4AddAvailableByManager(inputDto.getId(), pair[0], delta
//                        ));
//                System.out.println(bol);
//            }
//        }
        return null;
    }

    @Override
    public EditFundAccountOutputDto editFundAccountOnePair(EditFundAccountInputDto inputDto) throws BizException {
        String fundStr = "";
        try {
            fundStr = URLDecoder.decode(inputDto.getFunds(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ValidateException(ValidateExceptionCode.VALIDATE_PARAM_VALUE_ERROR);
        }
        String[] fundArr = fundStr.split("[|]");
        if (fundArr.length > 0) {
            try {
                String symbol = fundArr[0];
                if (fundAccountDef.getFundAccount(inputDto.getId(), symbol, true) == null) {
                    fundAccount4ServerDef.createFundAccount(inputDto.getId(), symbol,
                            FundAccount4ServerTokenUtil.genToken4CreateFundAccount(inputDto.getId(), symbol));
                }

                BigDecimal delta = new BigDecimal(fundArr[1]);
                FundAccountMngBizBo beforeBo = iUserBiz.queryFundAccountById(inputDto.getId());
                //加钱
                boolean bol = fundAccount4MngDef.addAvailableByManager(inputDto.getId(), symbol, delta,
                        FundAccount4MngTokenUtil.genToken4AddAvailableByManager(inputDto.getId(), symbol, delta
                        ));
                FundAccountMngBizBo afterBo = iUserBiz.queryFundAccountById(inputDto.getId());
                ExpUserBizBo user = sessionHelper.getUserInfoBySession();
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
        for (ExpUserBizBo bo : listBizBo.getRows()) {
            UserListOutputPage user = new UserListOutputPage();
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
            user.setIdType(String.valueOf(bo.getIdType()));
            user.setSurname(bo.getSurname());
            user.setRealName(String.valueOf(bo.getId()));
            user.setLoginTime(String.valueOf(bo.getLoginTime()));
            user.setLoginIp(bo.getLoginIp());
            pageList.add(user);
//            if(bo.getRoleList()!=null)
//            {
//                 List<String> roleList=new ArrayList<>();
//                 for(Integer roleId: bo.getRoleList())
//                 {
//                     roleList.add(String.valueOf(roleId));
//                 }
//                 user.setRoleList(roleList);
//            }
        }
        UserListOutputDto outputDto = new UserListOutputDto();
        outputDto.setRows(pageList);
        outputDto.setTotal(listBizBo.getTotal());
        outputDto.setSizePerPage(inputDto.getPageSize());
        outputDto.setTime(String.valueOf(System.currentTimeMillis()));
        return outputDto;
    }
}
