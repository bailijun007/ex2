package com.hupa.exp.bizother.service.locale.def;

import com.hupa.exp.base.exception.ValidateException;
import com.hupa.exp.bizother.entity.locale.ExpLocaleBizBo;
import com.hupa.exp.bizother.entity.locale.ExpLocaleListBizBo;

public interface ILocaleService {
    long createLocale(ExpLocaleBizBo bizBo) throws ValidateException;

    int editLocale(ExpLocaleBizBo bizBo) throws ValidateException;

    int deleteLocale(long id);

    ExpLocaleBizBo getOneLocale(Integer code);

    ExpLocaleBizBo getLocaleById(long id);

    ExpLocaleListBizBo getLocalePageData(String module,Integer errorCode,long currentPage,long pageSize);

}
