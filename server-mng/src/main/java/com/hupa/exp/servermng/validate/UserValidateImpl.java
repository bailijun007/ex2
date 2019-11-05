package com.hupa.exp.servermng.validate;

import com.hupa.exp.daomysql.dao.expv2.def.IExpUserDao;
import com.hupa.exp.servermng.entity.user.UserInputDto;
import com.hupa.exp.servermng.enums.MngExceptionCode;
import com.hupa.exp.servermng.enums.UserExceptionCode;
import com.hupa.exp.servermng.exception.MngException;
import com.hupa.exp.servermng.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userValidateImpl")
public class UserValidateImpl implements IValidate<UserInputDto> {
    @Autowired
    private IExpUserDao iExpUserDao;
    @Override
    public void validate(UserInputDto obj) throws  MngException {
//        if(new SessionHelper().getToken()==null)
//            throw new UserException(UserExceptionCode.);
//        if(obj.getUserName()==null||obj.getUserName().isEmpty())
//            throw new UserException(UserExceptionCode.USERNAME_NULL_ERROR);
        if(obj.getPassword()==null||obj.getPassword().isEmpty())
            throw new MngException(MngExceptionCode.PWD_NULL_ERROR);
        if(iExpUserDao.getUserInfoByPhone(obj.getPhone())!=null)
            throw new MngException(MngExceptionCode.PHONE_EXIST_ERROR_MNG);
        if(iExpUserDao.getUserInfoByEmail(obj.getEmail())!=null)
            throw new MngException(MngExceptionCode.EMAIL_EXIST_ERROR_MNG);
    }
}
