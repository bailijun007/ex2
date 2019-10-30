package com.hupa.exp.servermng.action.controller.api;//package com.hupa.exp.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.menu.*;
import com.hupa.exp.servermng.exception.MenuException;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiMenuControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"apiMenuController"})
@RestController
@RequestMapping(path = "/v1/http/menu",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiMenuController {
    private Logger logger = LoggerFactory.getLogger(ApiMenuController.class);
    @Autowired
    private  IApiMenuControllerService iApiMenuControllerService;

    @Autowired
    private SessionHelper sessionHelper;



    /**
     * 插入菜单数据
     */
    @ApiOperation(value = "插入菜单")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<MenuInputDto,MenuOutputDto> createOrEditMenu(
            @ApiParam(name="id",value = "菜单id",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="menuname",value = "菜单名称",required = true)
            @RequestParam(name = "menuname") String menuname,
            @ApiParam(name="parentmenuid",value = "上级id",required = true)
            @RequestParam(name = "parentmenuid") Integer parentmenuid,
            @ApiParam(name="menuurl",value = "菜单地址",required = true)
            @RequestParam(name = "menuurl") String menuurl,
            @ApiParam(name="sort",value = "排序",required = true)
            @RequestParam(name = "sort") Integer sort,
            @ApiParam(name="enable",value = "是否启用",required = true)
            @RequestParam(name = "enable") boolean enable,
            @ApiParam(name="icon",value = "图标",required = false)
            @RequestParam(name = "icon") String icon){
        //logger.info("打印日志--------------------->");
        MenuInputDto inputDto=new MenuInputDto();
        inputDto.setId(id);
        inputDto.setMenuname(menuname);
        inputDto.setMenuurl(menuurl);
        inputDto.setParentmenuid(parentmenuid);
        inputDto.setEnable(enable);
        inputDto.setSort(sort);
        inputDto.setIcon(icon);
        MenuOutputDto outputDto= null;
        try {
            outputDto = iApiMenuControllerService.createMenu(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }


        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
    /**
    * 获取菜单列表
    */
    @ApiOperation(value = "获取菜单列表")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<MenuListInputDto, MenuListOutputDto> getMenuList(
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    )
    {
        MenuListInputDto inputDto=new MenuListInputDto();
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        MenuListOutputDto outputDto=new MenuListOutputDto();
        try {
              outputDto=iApiMenuControllerService.queryMenuList(inputDto);
        } catch (MenuException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    /**
     * 获取菜单列表
     */
    @ApiOperation(value = "获取角色详情树形菜单")
    @GetMapping("/query_tree_list")
    public BaseResultViaApiDto<MenuTreeListInputDto,MenuTreeListOutputDto> getMenuTreeList()
    {
        MenuTreeListOutputDto outputDto=new MenuTreeListOutputDto();
        MenuTreeListInputDto inputDto=new MenuTreeListInputDto();
        try {
            outputDto=iApiMenuControllerService.queryMenuTreeList(inputDto);
        } catch (MenuException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
    @ApiOperation(value = "获取菜单详情")
    @GetMapping("/query")
    public BaseResultViaApiDto<GetMenuInputDto,GetMenuOutputDto> getMenuById(
            @ApiParam(name="id",value = "菜单id",required = true)
            @RequestParam(name = "id") long id)
    {
        GetMenuInputDto inputDto=new GetMenuInputDto();
        inputDto.setId(id);
        GetMenuOutputDto outputDto= new GetMenuOutputDto();
        try {
            outputDto = iApiMenuControllerService.getMenuById(inputDto);
        } catch (MenuException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取用户菜单")
    @GetMapping("/query_html_by_user_id")
    //@CheckLogin(param = "ApiMenuController")
    public BaseResultViaApiDto<MenuHtmlInputDto,MenuHtmlOutputDto> selectMenuHtmlByUserId()
    {
        MenuHtmlInputDto inputDto=new MenuHtmlInputDto();
        MenuHtmlOutputDto outputDto= null;
        try {
            outputDto = iApiMenuControllerService.getMenuHtmlByUserId();
        } catch (BizException e) {
           return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

}
