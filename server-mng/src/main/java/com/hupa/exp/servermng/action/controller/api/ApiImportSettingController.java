package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.importsetting.ImportSettingInputDto;
import com.hupa.exp.servermng.entity.importsetting.ImportSettingOutputDto;
import com.hupa.exp.servermng.service.def.IApiImportSettingControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="apiImportSettingController")
@RestController
@RequestMapping(path="/v1/http/import",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiImportSettingController {
    @Autowired
    private IApiImportSettingControllerService service;

    @ApiOperation(value = "获取资讯列表")
    @PostMapping("/setting")
    public BaseResultViaApiDto<ImportSettingInputDto,ImportSettingOutputDto> ImportSetting()  {
        ImportSettingInputDto inputDto=new ImportSettingInputDto();
        ImportSettingOutputDto outputDto=new ImportSettingOutputDto();
        try {
            outputDto=service.importSetting(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
