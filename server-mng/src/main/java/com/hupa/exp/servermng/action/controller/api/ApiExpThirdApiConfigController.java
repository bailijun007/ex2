package com.hupa.exp.servermng.action.controller.api;


import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.dic.*;
import com.hupa.exp.servermng.entity.expthirdapiconfig.*;
import com.hupa.exp.servermng.service.def.IApiExpThirdApiConfigControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags="apiExpThirdApiConfigController")
@RestController
@RequestMapping(path = "/v1/http/expthirdapiconfig",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiExpThirdApiConfigController {

    @Autowired
    private IApiExpThirdApiConfigControllerService service;

    /**
     * 第三方Api配置
     * @param currentPage
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "获取字典信息")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<ExpThirdApiConfigListInputDto,ExpThirdApiConfigListOutputDto> getDicList(
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize
    ){
        //logger.info("打印日志--------------------->");
        ExpThirdApiConfigListInputDto inputDto=new ExpThirdApiConfigListInputDto();
        ExpThirdApiConfigListOutputDto outputDto=new ExpThirdApiConfigListOutputDto();
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto = service.getExpThirdApiConfigPageData(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "新增或修改字段")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<ExpThirdApiConfigInputDto,ExpThirdApiConfigOutputDto> createOrEditDic(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name="third_api_id",value = "第三方api_id,对于程序的枚举值,唯一",required = true)
            @RequestParam(name = "third_api_id") Integer thirdApiId,
            @ApiParam(name="third_api_name",value = "api名称",required = true)
            @RequestParam(name = "third_api_name") String thirdApiName,
            @ApiParam(name="api_module",value = "1.pc永续合约",required = true)
            @RequestParam(name = "api_module") Integer apiModule,
            @ApiParam(name="time_unit",value = "时间单位,second",required = true)
            @RequestParam(name = "time_unit") String timeUnit,
            @ApiParam(name="limit_time",value = "1表示1秒,秒为time_unit",required = true)
            @RequestParam(name = "limit_time") Integer limitTime,
            @ApiParam(name="limit_count",value = "每个时间单位限制的次数",required = true)
            @RequestParam(name = "limit_count") Integer limitCount,
            @ApiParam(name="enable_flag",value = "0.关闭,1.启动",required = true)
            @RequestParam(name = "enable_flag") Integer enableFlag

    ){
        //logger.info("打印日志--------------------->");
        ExpThirdApiConfigInputDto inputDto=new ExpThirdApiConfigInputDto();
        ExpThirdApiConfigOutputDto outputDto=new ExpThirdApiConfigOutputDto();
        inputDto.setId(id);
        inputDto.setThirdApiId(thirdApiId);
        inputDto.setThirdApiName(thirdApiName);
        inputDto.setApiModule(apiModule);
        inputDto.setTimeUnit(timeUnit);
        inputDto.setLimitTime(limitTime);
        inputDto.setLimitCount(limitCount);
        inputDto.setEnableFlag(enableFlag);
        try{
            if(id>0)
                outputDto = service.editExpThirdApiConfig(inputDto);
            else
                outputDto = service.createExpThirdApiConfig(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/query")
    public BaseResultViaApiDto<ExpThirdApiConfigInfoInputDto,ExpThirdApiConfigInfoOutputDto> createOrEditDic(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id
    ){
        //logger.info("打印日志--------------------->");
        ExpThirdApiConfigInfoInputDto inputDto=new ExpThirdApiConfigInfoInputDto();
        ExpThirdApiConfigInfoOutputDto outputDto=new ExpThirdApiConfigInfoOutputDto();
        inputDto.setId(id);

        try{
            outputDto = service.getExpThirdApiConfigById(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public BaseResultViaApiDto<DeleteInputDto,DeleteOutputDto> deleteDic(
            @ApiParam(name="ids",value = "ids",required = true)
            @RequestParam(name = "ids") String ids
    ){
        //logger.info("打印日志--------------------->");
        DeleteInputDto inputDto=new DeleteInputDto();
        DeleteOutputDto outputDto=new DeleteOutputDto();
        inputDto.setIds(ids);

        try{
            outputDto = service.deleteExpThirdApiConfig(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
