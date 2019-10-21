package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.servermng.help.OSSClientUtil;
import com.hupa.exp.servermng.enums.ImgExceptionCode;
import com.hupa.exp.servermng.exception.ImgException;
import com.hupa.exp.servermng.service.def.IApiUploadControllerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ApiUploadControllerServiceImpl implements IApiUploadControllerService {
    @Autowired
    private OSSClientUtil ossClient;

    @Override
    public String updateHead(MultipartFile file) throws ImgException {
        if (file == null || file.getSize() <= 0) {
            throw new ImgException(ImgExceptionCode.VALIDATE_HAS_CODE_ERROR);
        }
        ossClient.init();
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);

        String[] split = imgUrl.split("\\?");
        return split[0];

    }

    @Override
    public String deleteFile(String file)  {
//        if (file == null || file.getSize() <= 0) {
//            throw new ImgException(ImgExceptionCode.VALIDATE_HAS_CODE_ERROR);
//        }
        if(!StringUtils.isEmpty(file))
        {
            String[] strArr=file.split("/");
            ossClient.deleteFile(strArr[strArr.length-1]);
        }

        return "";

    }
}
