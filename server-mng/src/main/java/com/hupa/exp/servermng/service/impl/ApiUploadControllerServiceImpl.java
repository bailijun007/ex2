package com.hupa.exp.servermng.service.impl;

import com.hupa.exp.servermng.config.aliyunoss.OSSClientUtil;
import com.hupa.exp.servermng.enums.ImgExceptionCode;
import com.hupa.exp.servermng.exception.ImgException;
import com.hupa.exp.servermng.service.def.IApiUploadControllerService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ApiUploadControllerServiceImpl implements IApiUploadControllerService {
    @Override
    public String updateHead(MultipartFile file) throws ImgException {
        if (file == null || file.getSize() <= 0) {
            throw new ImgException(ImgExceptionCode.VALIDATE_HAS_CODE_ERROR);
        }
        OSSClientUtil ossClient=new OSSClientUtil();
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
        OSSClientUtil ossClient=new OSSClientUtil();
        ossClient.deleteFile(file);
        return "";

    }
}
