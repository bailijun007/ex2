package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultDto;
import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.wsconfig.WsExpConfigInputDto;
import com.hupa.exp.servermng.entity.wsconfig.WsExpConfigListOutputDto;
import com.hupa.exp.servermng.entity.wsconfig.WsExpConfigOutputDto;
import com.hupa.exp.servermng.service.def.IApiWsExpConfigControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"apiWsExpConfigController"})
@RestController
@RequestMapping(path = "/v1/http/wsexpconfig",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiWsExpConfigController {

    @Autowired
    private IApiWsExpConfigControllerService iApiWsExpConfigControllerService;

    @ApiOperation(value = "分页查询列表")
    @GetMapping("/get_page_list")
    public BaseResultDto<WsExpConfigInputDto,WsExpConfigListOutputDto> getPageList(
            @ApiParam(name="current_page",value ="currentPage" ,required = true)
            @RequestParam(name = "current_page") Integer currentPage,
            @ApiParam(name="page_size",value ="pageSize" ,required = true)
            @RequestParam(name = "page_size") Integer pageSize) {
        WsExpConfigInputDto inputDto=new WsExpConfigInputDto();
        WsExpConfigListOutputDto outputDto = null;
        try {
            inputDto.setCurrentPage(currentPage);
            inputDto.setPageSize(pageSize);
            outputDto= iApiWsExpConfigControllerService.getWsExpConfigPageData(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    @ApiOperation(value = "创建或修改WS配置")
    @PostMapping("/create_or_edit")
    public BaseResultDto<WsExpConfigInputDto, WsExpConfigOutputDto> createOrEditWsExpConfig (
            @RequestBody WsExpConfigInputDto inputDto) {
        WsExpConfigOutputDto outputDto = null;
        try {
            outputDto= iApiWsExpConfigControllerService.createOrEdit(inputDto);
        } catch (BizException e) {
            BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "根据ID查询WS配置")
    @GetMapping("/getWsExpConfig")
    public BaseResultDto<WsExpConfigInputDto,WsExpConfigOutputDto> getWsExpConfigById(
            @ApiParam(name="id",value ="id" ,required = true)
            @RequestParam(name = "id") long id) {
        WsExpConfigInputDto inputDto = new WsExpConfigInputDto();
        WsExpConfigOutputDto outputDto = null;
        try {
            inputDto.setId(id);
            outputDto= iApiWsExpConfigControllerService.getWsExpConfig(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    @ApiOperation(value = "删除WS配置")
    @PostMapping("/delete")
    public BaseResultViaApiDto<DeleteInputDto,DeleteOutputDto> deleteWsExpConfig(
            @ApiParam(name="ids",value = "ids",required = true)
            @RequestParam(name = "ids") String ids){
        DeleteInputDto inputDto = new DeleteInputDto();
        DeleteOutputDto outputDto = null;
        try{
            inputDto.setIds(ids);
            outputDto = iApiWsExpConfigControllerService.delete(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }



}
