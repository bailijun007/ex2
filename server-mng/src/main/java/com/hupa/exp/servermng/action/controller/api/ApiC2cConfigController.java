package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.c2config.C2cConfigInputDto;
import com.hupa.exp.servermng.entity.c2config.C2cConfigListInputDto;
import com.hupa.exp.servermng.entity.c2config.C2cConfigListOutputDto;
import com.hupa.exp.servermng.entity.c2config.C2cConfigOutputDto;
import com.hupa.exp.servermng.service.def.IApiC2cConfigControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2020/1/10.
 */

@Api(tags = "apiC2cConfigController")
@RestController
@RequestMapping(path = "/v1/http/c2cConfig",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiC2cConfigController {

    @Autowired
    private IApiC2cConfigControllerService apiC2cConfigControllerService;

    /**
     * 获取C2C资产列表
     * @param asset
     * @param pageSize
     * @param currentPage
     * @return
     */

   @ApiOperation(value = "获取c2c列表")
    @GetMapping(path = "/query_list")
    public BaseResultViaApiDto<C2cConfigListInputDto,C2cConfigListOutputDto> getC2cConfigList(
            @ApiParam(name="asset",value = "币种",required = true)
            @RequestParam(name = "asset") String asset,
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    )
    {
        C2cConfigListInputDto inputDto=new C2cConfigListInputDto();
        inputDto.setAsset(asset);
        inputDto.setPageSize(pageSize);
        inputDto.setCurrentPage(currentPage);
        C2cConfigListOutputDto outputDto=new C2cConfigListOutputDto();
        try {
            outputDto= apiC2cConfigControllerService.findC2cConfigList(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    @ApiOperation(value = "新增或修改")
    @PostMapping("/createOrEdit")
    public BaseResultViaApiDto<C2cConfigInputDto,C2cConfigOutputDto> createOrEditC2cConfig(
            @ApiParam(name="id",value = "主键id",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="asset",value = "币种",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="min_in_money",value = "币种",required = true)
            @RequestParam(name = "min_in_money") BigDecimal minInMoney,
            @ApiParam(name="min_out_money",value = "币种",required = true)
            @RequestParam(name = "min_out_money") BigDecimal minOutMoney
    ) {
        C2cConfigInputDto inputDto=new C2cConfigInputDto();
        inputDto.setId(id);
        inputDto.setAsset(asset);
        inputDto.setMinInMoney(minInMoney);
        inputDto.setMinOutMoney(minOutMoney);
        C2cConfigOutputDto outputDto=new C2cConfigOutputDto();
        try {
            if(inputDto.getId()>0)
                outputDto= apiC2cConfigControllerService.editC2cConfig(inputDto);
            else
                outputDto= apiC2cConfigControllerService.createC2cConfig(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    @ApiOperation(value = "获取c2c")
    @GetMapping(path = "/queryC2cConfig")
    public BaseResultViaApiDto<C2cConfigInputDto,C2cConfigOutputDto> queryC2cConfig(
            @ApiParam(name="id",value = "主键id",required = true)
            @RequestParam(name = "id") String id) {

        C2cConfigInputDto inputDto=new C2cConfigInputDto();
        inputDto.setId(Long.parseLong(id));
        C2cConfigOutputDto outputDto=new C2cConfigOutputDto();
        try {
            outputDto= apiC2cConfigControllerService.getC2cConfigById(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/deleteC2cConfig")
    public BaseResultViaApiDto<DeleteInputDto,DeleteOutputDto> deleteC2cConfig(
            @ApiParam(name="ids",value = "ids",required = true)
            @RequestParam(name = "ids") String ids
    ){
        DeleteInputDto inputDto=new DeleteInputDto();
        DeleteOutputDto outputDto= new DeleteOutputDto();
        try{
            inputDto.setIds(ids);
            outputDto = apiC2cConfigControllerService.deletesConfigList(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


}
