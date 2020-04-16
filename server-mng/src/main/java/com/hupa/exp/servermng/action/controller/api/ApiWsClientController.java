package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultDto;
import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.wsclient.WsClientInputDto;
import com.hupa.exp.servermng.entity.wsclient.WsClientListOutputDto;
import com.hupa.exp.servermng.entity.wsclient.WsClientOutputDto;
import com.hupa.exp.servermng.service.def.IApiWsClientControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"apiWsClientController"})
@RestController
@RequestMapping(path = "/v1/http/wsclient",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiWsClientController {

   @Autowired
   private IApiWsClientControllerService iApiWsClientControllerService;

    @ApiOperation(value = "分页查询列表")
    @GetMapping("/get_page_list")
    public BaseResultDto<WsClientInputDto,WsClientListOutputDto> getPageList(
        @ApiParam(name="current_page",value ="currentPage" ,required = true)
        @RequestParam(name = "current_page") Integer currentPage,
        @ApiParam(name="page_size",value ="pageSize" ,required = true)
        @RequestParam(name = "page_size") Integer pageSize) {
        WsClientInputDto inputDto = new WsClientInputDto();
        WsClientListOutputDto outputDto = null;
        try {
            inputDto.setCurrentPage(currentPage);
            inputDto.setPageSize(pageSize);
            outputDto = iApiWsClientControllerService.getWsClientConfigPageData(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "创建或修改")
    @PostMapping("/create_or_edit_wsclient")
    public BaseResultDto<WsClientInputDto, WsClientOutputDto> createOrEditWsClient (
        @ApiParam(name="id",value ="id" ,required = true)
        @RequestParam(name = "id") long id,
        @ApiParam(name="wsip",value ="wsip" ,required = true)
        @RequestParam(name = "wsip") String wsip,
        @ApiParam(name="wsport",value ="wsport" ,required = true)
        @RequestParam(name = "wsport") String wsport) {
        WsClientInputDto inputDto = new WsClientInputDto();
        WsClientOutputDto outputDto = null;
        try {
            inputDto.setId(id);
            inputDto.setWsIp(wsip);
            inputDto.setWsPort(wsport);
            outputDto= iApiWsClientControllerService.createOrEdit(inputDto);
        } catch (BizException e) {
            BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "根据ID查询")
    @GetMapping("/getWsClient")
    public BaseResultDto<WsClientInputDto,WsClientOutputDto> getWsClient(
            @ApiParam(name="id",value ="id" ,required = true)
            @RequestParam(name = "id") long id) {
        WsClientInputDto inputDto = new WsClientInputDto();
        WsClientOutputDto outputDto = null;
        try {
            inputDto.setId(id);
            outputDto= iApiWsClientControllerService.getWsClientConfig(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    @ApiOperation(value = "删除")
    @PostMapping("/deleteWsClient")
    public BaseResultViaApiDto<DeleteInputDto,DeleteOutputDto> deleteWsClient(
            @ApiParam(name="ids",value = "ids",required = true)
            @RequestParam(name = "ids") String ids){
        DeleteInputDto inputDto = new DeleteInputDto();
        DeleteOutputDto outputDto = null;
        try{
            inputDto.setIds(ids);
            outputDto = iApiWsClientControllerService.delete(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }





}
