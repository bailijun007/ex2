package com.hupa.exp.servermng.validate;

import com.hupa.exp.bizregister.service.def.IRegisterUserBiz;
import com.hupa.exp.servermng.entity.user.UserInputDto;
import com.hupa.exp.servermng.enums.UserExceptionCode;
import com.hupa.exp.servermng.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userValidateImpl")
public class UserValidateImpl implements IValidate<UserInputDto> {
    @Autowired
    IRegisterUserBiz iRegisterUserBiz;
    @Override
    public void validate(UserInputDto obj) throws UserException {
//        if(new SessionHelper().getToken()==null)
//            throw new UserException(UserExceptionCode.);
//        if(obj.getUserName()==null||obj.getUserName().isEmpty())
//            throw new UserException(UserExceptionCode.USERNAME_NULL_ERROR);
        if(obj.getPassword()==null||obj.getPassword().isEmpty())
            throw new UserException(UserExceptionCode.PWD_NULL_ERROR);
        if(iRegisterUserBiz.checkPhone(obj.getPhone()))
            throw new UserException(UserExceptionCode.PWD_NULL_ERROR);
        if(iRegisterUserBiz.checkEmail(obj.getEmail()))
            throw new UserException(UserExceptionCode.PWD_NULL_ERROR);


    }
}
