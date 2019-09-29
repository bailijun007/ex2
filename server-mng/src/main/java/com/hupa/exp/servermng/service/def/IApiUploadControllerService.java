package com.hupa.exp.servermng.service.def;

import com.hupa.exp.servermng.exception.ImgException;
import org.springframework.web.multipart.MultipartFile;

public interface IApiUploadControllerService {
    String updateHead(MultipartFile file)throws ImgException;
    String deleteFile(String file);
}
