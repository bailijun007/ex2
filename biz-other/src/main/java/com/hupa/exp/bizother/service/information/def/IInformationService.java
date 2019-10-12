package com.hupa.exp.bizother.service.information.def;

import com.hupa.exp.bizother.entity.information.ExpInformationBizBo;
import com.hupa.exp.bizother.entity.information.ExpInformationListBizBo;
import com.hupa.exp.bizother.entity.information.ExpInformationPageDataBizBo;

public interface IInformationService {
    ExpInformationPageDataBizBo getInformationPageData(Integer type,String title, int currentPage, int pageSize);
    ExpInformationBizBo getInformationById(long id);
    long createInformation(ExpInformationBizBo bizBo);
    int editInformation(ExpInformationBizBo bizBo);
    int deleteInformation(long id);
}
