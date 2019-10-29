package com.hupa.exp.servermng.service.def;

import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.servermng.entity.user.*;
import com.hupa.exp.servermng.exception.MngException;

public interface IApiUserControllerService {
    UserOutputDto createUser(UserInputDto expUserInputDto) throws  BizException;

    UserListOutputDto queryList(UserListInputDto inputDto) throws BizException;

    UserOutputDto queryUserInfoById(UserInputDto inputDto) throws BizException;

    FundAccountListOutputDto queryFundAccountList(FundAccountListInputDto inputDto) throws BizException;

    FundAccountInfoOutputDto queryFundAccountById(FundAccountInfoInputDto inputDto) throws BizException;

    EditFundAccountOutputDto editFundAccount(EditFundAccountInputDto inputDto) throws BizException;

    EditFundAccountOutputDto editFundAccountOneAsset(EditFundAccountInputDto inputDto) throws BizException;

    UserListOutputDto queryListByUserType(UserListInputDto inputDto) throws BizException;

    FundAccountListOutputDto queryFundAccountListByParam(FundAccountListInputDto inputDto) throws BizException;

    EnableUserOutputDto enableUser(EnableUserInputDto inputDto) throws BizException;

    CreateAccountOutputDto createAccount(CreateAccountInputDto inputDto) throws MngException;

    GenFeeOutputDto genFee(GenFeeInputDto inputDto) throws BizException;

    CheckExistUserOutputDto checkExistUser(CheckExistUserInputDto inputDto) throws BizException;

}
