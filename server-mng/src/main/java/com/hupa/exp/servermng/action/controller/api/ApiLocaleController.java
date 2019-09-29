package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.locale.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiLocaleControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"apiLocaleController"})
@RestController
@RequestMapping(path = "/v1/http/locale", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiLocaleController {
    @Autowired
    private IApiLocaleControllerService service;

    @Autowired
    private SessionHelper sessionHelper;

    private Logger logger = LoggerFactory.getLogger(ApiLocaleController.class);

    @ApiOperation(value = "插入或修改")
    @PostMapping("/create_or_edit")
    public BaseResultDto<LocaleInputDto, LocaleOutputDto> createOrEditLocale(
            @ApiParam(name = "id", value = "主键")
            @RequestParam(value = "id") Long id,
            @ApiParam(name = "type", value = "类型")
            @RequestParam(value = "type") Integer type,
            @ApiParam(name = "code", value = "错误码")
            @RequestParam(value = "code") Integer code,
//            @ApiParam(name = "locale_language", value = "语言")
//            @RequestParam(value = "locale_language") String localeLanguage,
            @ApiParam(name = "locale_constant", value = "国际化常量")
            @RequestParam(value = "locale_constant") String localeConstant,
            @ApiParam(name = "locale_content", value = "国际化说明内容")
            @RequestParam(value = "locale_content") String localeContent,
            @ApiParam(name = "remark", value = "备注")
            @RequestParam(value = "remark") String remark,
            @ApiParam(name = "module", value = "备注")
            @RequestParam(value = "module") String module
    ) {
        LocaleInputDto inputDto = new LocaleInputDto();
        inputDto.setId(id);
        inputDto.setCode(code);
        inputDto.setLocaleConstant(localeConstant);
        inputDto.setLocaleContent(localeContent);
        inputDto.setRemark(remark);
        inputDto.setModule(module);
        inputDto.setType(type);
        //inputDto.setLocaleLanguage(localeLanguage);
        LocaleOutputDto outputDto = new LocaleOutputDto();
        try {
            if (id > 0)
                service.editLocale(inputDto);
            else {
                service.createLocale(inputDto);
            }
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto, outputDto, e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto, outputDto);
    }


    @ApiOperation(value = "检查是否存在")
    @PostMapping("/check_has_locale")
    public BaseResultDto<CheckHasLocaleInputDto, CheckHasLocaleOutputDto> checkHasLocale(

            @ApiParam(name = "code", value = "错误码")
            @RequestParam(value = "code") Integer code
    ) {
        CheckHasLocaleInputDto inputDto = new CheckHasLocaleInputDto();
        inputDto.setErrorCode(code);


        CheckHasLocaleOutputDto outputDto = new CheckHasLocaleOutputDto();
        try {
            service.checkHasLocale(inputDto);

        } catch (BizException e) {
           return BaseResultViaApiUtil.buildExceptionResult(inputDto, outputDto, e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto, outputDto);
    }

    @ApiOperation(value = "查询单个")
    @GetMapping(path = "query")
    public BaseResultDto<LocaleInfoInputDto, LocaleInfoOutputDto> getLocaleById(
            @ApiParam(name = "id", value = "id")
            @RequestParam(value = "id") long id
    ) {
        LocaleInfoInputDto inputDto = new LocaleInfoInputDto();
        inputDto.setId(id);
        LocaleInfoOutputDto outputDto = new LocaleInfoOutputDto();
        try {
            outputDto = service.getLocaleById(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto, outputDto, e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto, outputDto);
    }

    @ApiOperation(value = "删除")
    @PostMapping(path = "delete")
    public BaseResultDto<LocaleDeleteInputDto, LocaleDeleteOutputDto> deleteLocaleById(
            @ApiParam(name = "ids", value = "ids")
            @RequestParam(value = "ids") String ids
    ) {
        LocaleDeleteInputDto inputDto = new LocaleDeleteInputDto();
        inputDto.setIds(ids);
        LocaleDeleteOutputDto outputDto = new LocaleDeleteOutputDto();
        try {
            outputDto = service.deleteLocale(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto, outputDto, e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto, outputDto);
    }

    @ApiOperation(value = "查询集合")
    @GetMapping(path = "query_list")
    public BaseResultDto<LocaleListInputDto, LocaleListOutputDto> getLocaleList(
            @ApiParam(name = "module", value = "module")
            @RequestParam(value = "module") String module,
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code") Integer code,
            @ApiParam(name = "current_page", value = "current_page")
            @RequestParam(value = "current_page") Integer currentPage,
            @ApiParam(name = "page_size", value = "language")
            @RequestParam(value = "page_size") Integer pageSize
    ) {
        LocaleListInputDto inputDto = new LocaleListInputDto();
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        inputDto.setErrorCode(code);
        inputDto.setModule(module);
        LocaleListOutputDto outputDto = new LocaleListOutputDto();
        try {
            outputDto = service.getLocaleList(inputDto);
        } catch (BizException e) {
            return  BaseResultViaApiUtil.buildExceptionResult(inputDto, outputDto, e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto, outputDto);


    }


}
