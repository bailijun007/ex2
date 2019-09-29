package com.hupa.exp.servermng.validate;

public interface ILoginValidate<T> {

    void  validate(T obj,String verifyCode) throws Exception;
}
