package com.hupa.exp.servermng.action.controller.api;


import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.sms.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiSmsTempControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"apiSmsTempController"})
@RestController
@RequestMapping(path = "/v1/http/smstemp",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiSmsTempController {

    private Logger logger = LoggerFactory.getLogger(ApiSmsTempController.class);

    @Autowired
    IApiSmsTempControllerService service;

    @Autowired
    private SessionHelper sessionHelper;
    /**
     * 插入菜单数据
     */
    @ApiOperation(value = "插入通知")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<SmsTempInputDto,SmsTempOutputDto> createOrEditSmsTemp(
            @ApiParam(name="id",value = "通知内容",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="content",value = "通知内容",required = true)
            @RequestParam(name = "content") String content,
            @ApiParam(name="type",value = "通知类型",required = true)
            @RequestParam(name = "type") Integer type,
            @ApiParam(name="remark",value = "备注",required = true)
            @RequestParam(name = "remark") String remark,
             @ApiParam(name="code",value = "具体通知类型",required = true)
            @RequestParam(name = "code") String code
){
        //logger.info("打印日志--------------------->");
        SmsTempOutputDto outputDto=new SmsTempOutputDto();
        SmsTempInputDto inputDto=new SmsTempInputDto();
        inputDto.setId(id);
        inputDto.setContent(content);
        inputDto.setRemark(remark);
        inputDto.setType(type);
        inputDto.setCode(code);
        try{
            if(id>0)
                outputDto= service.editSmsTemp(inputDto);
            else
                outputDto= service.createSmsTemp(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取短信模板列表")
    @GetMapping(path = "/query_list")
    public BaseResultViaApiDto<SmsTempListInputDto,SmsTempListOutputDto> getSmsTempList(//
          @ApiParam(name="page_size",value = "条数",required = true)
          @RequestParam(name = "page_size") Integer pageSize,
          @ApiParam(name="current_page",value = "页码",required = true)
          @RequestParam(name = "current_page") Integer currentPage
    )
    {
        SmsTempListInputDto inputDto=new SmsTempListInputDto();
        inputDto.setPageSize(pageSize);
        inputDto.setCurrentPage(currentPage);
        SmsTempListOutputDto outputDto=new SmsTempListOutputDto();
        try {
            outputDto=service.querySmsTempList(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取短信模板详情")
    @GetMapping(path = "/query")
    public BaseResultViaApiDto<SmsTempInfoInputDto,SmsTempInfoOutputDto> querySmsTempById(//
     @ApiParam(name="id",value = "id",required = true)
     @RequestParam(name = "id") long id
    )
    {
        SmsTempInfoInputDto inputDto=new SmsTempInfoInputDto();
        inputDto.setId(id);
        SmsTempInfoOutputDto outputDto=new SmsTempInfoOutputDto();
        try {
            outputDto=service.querySmsTempById(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
