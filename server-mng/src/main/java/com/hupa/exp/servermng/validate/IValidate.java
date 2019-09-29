package com.hupa.exp.servermng.validate;

public interface IValidate<T> {

    void  validate(T obj) throws Exception;
}
