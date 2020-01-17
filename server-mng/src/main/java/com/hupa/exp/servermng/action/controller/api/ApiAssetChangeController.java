package com.hupa.exp.servermng.action.controller.api;


import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.assetchange.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiAssetChangeControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="apiAssetChangeController")
@RestController
@RequestMapping(path = "/v1/http/asset",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiAssetChangeController {
    @Autowired
    private IApiAssetChangeControllerService service;
    @Autowired
    private SessionHelper sessionHelper;

    private Logger logger = LoggerFactory.getLogger(ApiAssetChangeController.class);
    @ApiOperation(value = "获取资金账户")
    @GetMapping("/query_fund_asset")
    public BaseResultViaApiDto<FundAssetChangeInputDto,FundAssetChangeOutputDto> getFundAssetChange(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="symbol",value = "symbol",required = true)
            @RequestParam(name = "symbol") String symbol
    ){

        FundAssetChangeOutputDto outputDto=new FundAssetChangeOutputDto();
        FundAssetChangeInputDto inputDto=new FundAssetChangeInputDto();
        inputDto.setId(id);
        inputDto.setSymbol(symbol);
        try{
            outputDto = service.getFundAssetChange(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    /**
     * 查询资金账户列表
     * @param id
     * @param accountId
     * @param asset
     * @param pageStatus
     * @param pageSize
     * @param currentPage
     * @return
     */
    @ApiOperation(value = "获取资金账户列表")
    @GetMapping("/query_fund_asset_list")
    public BaseResultViaApiDto<FundAssetChangeListInputDto,FundAssetChangeListOutputDto> getFundAssetChangeList(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name="account_id",value = "account_id",required = true)
            @RequestParam(name = "account_id") Long accountId,
            @ApiParam(name="asset",value = "asset",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="page_status",value = "条数",required = true)
            @RequestParam(name = "page_status") Integer pageStatus,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    ){

        FundAssetChangeListOutputDto outputDto=new FundAssetChangeListOutputDto();
        FundAssetChangeListInputDto inputDto=new FundAssetChangeListInputDto();
        inputDto.setId(id);
        inputDto.setAccountId(accountId);
        inputDto.setAsset(asset);
        inputDto.setPageStatus(pageStatus);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto = service.getFundAssetChangeList(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取合约账户")
    @GetMapping("/query_pc_asset")
    public BaseResultViaApiDto<PcAssetChangeInputDto,PcAssetChangeOutputDto> getPcAssetChange(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="asset",value = "asset",required = true)
            @RequestParam(name = "asset") String asset
    ){

        PcAssetChangeOutputDto outputDto=new PcAssetChangeOutputDto();
        PcAssetChangeInputDto inputDto=new PcAssetChangeInputDto();
        inputDto.setId(id);
        inputDto.setAsset(asset);
        try{
            outputDto = service.getPcAssetChange(inputDto);
        }catch(BizException e){

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    /**
     * 查询合约账户列表
     * @param id
     * @param accountId
     * @param asset
     * @param pageStatus
     * @param pageSize
     * @param currentPage
     * @return
     */
    @ApiOperation(value = "获取合约账户列表")
    @GetMapping("/query_pc_asset_list")
    public BaseResultViaApiDto<PcAssetChangeListInputDto,PcAssetChangeListOutputDto> getPcAssetChangeList(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name="account_id",value = "account_id",required = true)
            @RequestParam(name = "account_id") Long accountId,
            @ApiParam(name="asset",value = "asset",required = true)
            @RequestParam(name = "asset") String asset,
            @ApiParam(name="page_status",value = "条数",required = true)
            @RequestParam(name = "page_status") Integer pageStatus,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    ){

        PcAssetChangeListOutputDto outputDto=new PcAssetChangeListOutputDto();
        PcAssetChangeListInputDto inputDto=new PcAssetChangeListInputDto();
        inputDto.setId(id);
        inputDto.setAccountId(accountId);
        inputDto.setAsset(asset);
        inputDto.setPageStatus(pageStatus);
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        try{
            outputDto = service.getPcAssetChangeList(inputDto);
        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
