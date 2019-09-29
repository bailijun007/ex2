package com.hupa.exp.servermng.action.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiUploadControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = {"apiStoringLevelController"})
@RestController
@RequestMapping(path = "/v1/http/upload",produces = "text/html;charset=UTF-8")
public class ApiUploadController {

    @Autowired
    private IApiUploadControllerService service;

    @Autowired
    private SessionHelper sessionHelper;

    private Logger logger = LoggerFactory.getLogger(ApiUploadController.class);

    @ApiOperation(value = "上传图片")
    @PostMapping("/img")
    public String headImgUpload(HttpServletRequest request,
                                  MultipartFile file) {
        Map<String, Object> value = new HashMap<String, Object>();
        try {
            String url = service.updateHead(file);
            value.put("data", url);
            value.put("code", 0);
            value.put("msg", "图片上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            value.put("code", 2000);
            value.put("msg", "图片上传失败");
        }
        return JSONObject.toJSONString(value);
    }

    @ApiOperation(value = "上传图片")
    @PostMapping("/delete_img")
    public String headImgUpload(
            @ApiParam(name="file_name",value = "交易对",required = true)
            @RequestParam(name = "file_name") String fileName
    ) {
        Map<String, Object> value = new HashMap<String, Object>();
        try {
            String[] fileStr=fileName.split("/");
            fileName=fileStr[fileStr.length-1];
            String url = service.deleteFile(fileName);

            value.put("data", url);
            value.put("code", 0);
            value.put("msg", "图片上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            value.put("code", 2000);
            value.put("msg", "图片上传失败");
        }
        return JSONObject.toJSONString(value);
    }


}
