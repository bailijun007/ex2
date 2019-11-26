package com.hupa.exp.servermng.action.controller.api;//package com.hupa.exp.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.role.*;
import com.hupa.exp.servermng.exception.RoleException;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiRoleControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"apiRoleController"})
@RestController
@RequestMapping(path = "/v1/http/role",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiRoleController {
    private Logger logger = LoggerFactory.getLogger(ApiRoleController.class);
    @Autowired
    private IApiRoleControllerService iApiRoleControllerService;

    @Autowired
    private SessionHelper sessionHelper;
    /**
     * 演示效果接口
     */
    @ApiOperation(value = "插入角色")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<RoleMenuInputDto,RoleMenuOutputDto> createOrEditRole(
            @RequestBody RoleMenuInputDto inputDto){
        boolean result=true;
        RoleMenuOutputDto outputDto=new RoleMenuOutputDto();
        try{
            iApiRoleControllerService.createOrEditRole(inputDto);
        }catch (BizException e)
        {
            logger.info("新增或修改角色信息报错 错误信息：" +e.getMessage());
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
        //return JSON.toJSONString(result);
    }
    @ApiOperation(value = "获取角色列表")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<RoleListInputDto,RoleListOutputDto> selectRoleList(
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    )
    {
        RoleListOutputDto outputDto=new RoleListOutputDto();
        RoleListInputDto inputDto=new RoleListInputDto();
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try {
            outputDto = iApiRoleControllerService.queryRolePageList(inputDto);
        } catch (BizException e) {
          return   BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取角色列表")
    @GetMapping("/query_all_list")
    public BaseResultViaApiDto<RoleAllInputDto,RoleAllOutputDto> selectAllRoleList(
    )
    {
        RoleAllOutputDto outputDto=new RoleAllOutputDto();
        RoleAllInputDto inputDto=new RoleAllInputDto();
        try {
            outputDto = iApiRoleControllerService.queryRoleList(inputDto);
        } catch (BizException e) {
            return   BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取角色详情")
    @GetMapping("/query")
    public BaseResultViaApiDto<RoleInputDto,RoleOutputDto> selectRoleById(
            @ApiParam(name="id",value = "角色id",required = true)
            @RequestParam(name = "id") long id)
    {
        RoleInputDto inputDto=new RoleInputDto();
        inputDto.setId(id);
        RoleOutputDto outputDto=new RoleOutputDto();
        try {
             outputDto=iApiRoleControllerService.queryRoleById(inputDto.getId());
        } catch (RoleException e) {
            return  BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }

        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto) ;
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public BaseResultViaApiDto<DeleteInputDto,DeleteOutputDto> deleteArea(
            @ApiParam(name="ids",value = "ids",required = true)
            @RequestParam(name = "ids") String ids
    ){
        //logger.info("打印日志--------------------->");
        DeleteInputDto inputDto=new DeleteInputDto();
        DeleteOutputDto outputDto=new DeleteOutputDto();
        inputDto.setIds(ids);

        try{
            outputDto = iApiRoleControllerService.deleteRole(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
