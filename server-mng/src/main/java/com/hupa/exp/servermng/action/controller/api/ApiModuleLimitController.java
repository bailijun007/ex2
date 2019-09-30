package com.hupa.exp.servermng.action.controller.api;


import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.modulelimit.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiModuleLimitControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Api(tags="apiModuleLimitController")
@RestController
@RequestMapping(path = "/v1/http/modulelimit",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiModuleLimitController {
    private Logger logger = LoggerFactory.getLogger(ApiModuleLimitController.class);

    @Autowired
    private IApiModuleLimitControllerService iApiModuleLimitControllerService;

    @Autowired
    private SessionHelper sessionHelper;

    /**
     * 插入区号信息
     */
    @ApiOperation(value = "插入区号信息")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<ModuleLimitInputDto,ModuleLimitOutputDto> createArea(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="module",value = "模块",required = true)
            @RequestParam(name = "module") String module,
            @ApiParam(name="module_url",value = "模块地址",required = true)
            @RequestParam(name = "module_url") String moduleUrl,
            @ApiParam(name="tries_limit",value = "区名",required = true)
            @RequestParam(name = "tries_limit") long triesLimit,
            @ApiParam(name="enable",value = "是否启用",required = true)
            @RequestParam(name = "enable") Integer enable
    ){
        //logger.info("打印日志--------------------->");
        ModuleLimitOutputDto outputDto=new ModuleLimitOutputDto();
        ModuleLimitInputDto inputDto=new ModuleLimitInputDto();
        inputDto.setId(id);
        inputDto.setModule(module);
        inputDto.setModuleUrl(moduleUrl);
        inputDto.setTriesLimit(triesLimit);
        inputDto.setEnable(enable);
        inputDto.setId(id);
        try{
                outputDto = iApiModuleLimitControllerService.createOrEditModuleLimit(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取模块限制信息")
    @GetMapping("/query")
    public BaseResultViaApiDto<GetModuleLimitInputDto,GetModuleLimitOutputDto> getArea(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") long id
    ){
        //logger.info("打印日志--------------------->");
        GetModuleLimitOutputDto outputDto=new GetModuleLimitOutputDto();
        GetModuleLimitInputDto inputDto=new GetModuleLimitInputDto();
        inputDto.setId(id);
        try{
            outputDto = iApiModuleLimitControllerService.getModuleLimitById(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取模块限制列表")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<ModuleLimitListInputDto,ModuleLimitListOutputDto> getAreaList(
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    ){
        //logger.info("打印日志--------------------->");
        ModuleLimitListOutputDto outputDto=new ModuleLimitListOutputDto();
        ModuleLimitListInputDto inputDto=new ModuleLimitListInputDto();
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto = iApiModuleLimitControllerService.getModuleLimitList(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
