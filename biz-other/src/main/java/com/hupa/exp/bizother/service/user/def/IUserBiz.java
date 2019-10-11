package com.hupa.exp.bizother.service.user.def;

import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.user.ExpUserBizBo;
import com.hupa.exp.bizother.entity.user.ExpUserListBizBo;
import com.hupa.exp.bizother.entity.fundaccount.FundAccountBizBo;
import com.hupa.exp.bizother.entity.fundaccount.FundAccountListBizBo;
import com.hupa.exp.bizother.exception.BizUserException;
import com.hupa.exp.common.exception.BizException;

import java.util.List;

public interface IUserBiz {
    long createUser(ExpUserBizBo expUserBo) throws BizUserException, ValidateException;


    List<ExpUserBizBo> queryList() ;

    ExpUserListBizBo queryListByUserType(long currentPage, long pageSize,Integer userType,String userName,Long id) ;

    long editById(ExpUserBizBo expUserBo) throws BizUserException, ValidateException;

    ExpUserBizBo queryById(long id);


    ExpUserBizBo queryUserInfoByPhone(String phone) throws BizException;

    ExpUserBizBo queryUserInfoByEmail(String email) throws BizException;



    FundAccountListBizBo queryFundAccountList(long currentPage, long pageSize,Integer userType,String userName,Long id)throws BizException;

    FundAccountListBizBo queryFundAccountListByParam(long currentPage, long pageSize,Integer userType,String userName,Long id)throws BizException;


    FundAccountBizBo queryFundAccountById(long id) throws BizException;

    long enableById(ExpUserBizBo expUserBo) throws BizUserException, ValidateException;

}
