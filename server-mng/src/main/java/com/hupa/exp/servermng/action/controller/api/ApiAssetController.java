package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.asset.*;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiAssetControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Api(tags = "apiAssetController")
@RestController
@RequestMapping(path = "/v1/http/asset",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiAssetController {

    private Logger logger = LoggerFactory.getLogger(ApiAssetController.class);

    @Autowired
    private IApiAssetControllerService iApiAssetControllerService;


    /**
     * 新增或修改币种
     * @param id
     * @param icon
     * @param iconImg
     * @param chainAppointId
     * @param realName
     * @param displayName
     * @param chainName
     * @param precision
     * @param privilege
     * @param status
     * @param sort
     * @param dwType
     * @param minDepositVolume
     * @param minWithdrawVolume
     * @param withdrawFee
     * @param chainTransactionUrl
     * @return
     */
    @ApiOperation(value = "新增或修改币种")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<AssetInputDto,AssetOutputDto> createOrEditAsset(
            @ApiParam(name="id",value = "币Id",required = true)
            @RequestParam(name = "id") long id,
//            @ApiParam(name="symbol",value = "币的符号",required = true)
//            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="icon",value = "图标",required = true)
            @RequestParam(name = "icon") String icon,
            @ApiParam(name="icon_img",value = "图标图片",required = true)
            @RequestParam(name = "icon_img") String iconImg,
            @ApiParam(name="chain_appoint_id",value = "链上服务的Id",required = true)
            @RequestParam(name = "chain_appoint_id") Integer chainAppointId,
            @ApiParam(name="real_name",value = "币的名称",required = true)
            @RequestParam(name = "real_name") String realName,
            @ApiParam(name="display_name",value = "币的展示名",required = true)
            @RequestParam(name = "display_name") String displayName,
            @ApiParam(name="chain_name",value = "币所属的链",required = true)
            @RequestParam(name = "chain_name") String chainName,
            @ApiParam(name="pre",value = "精度",required = true)
            @RequestParam(name = "pre") BigDecimal precision,
            @ApiParam(name="privilege",value = "币的权限",required = true)
            @RequestParam(name = "privilege") Integer privilege,
            @ApiParam(name="stat",value = "是否生效",required = true)
            @RequestParam(name = "stat") Integer status,
            @ApiParam(name="sort",value = "排序",required = true)
            @RequestParam(name = "sort") Integer sort,
            @ApiParam(name="dw_type",value = "是否生效",required = true)
            @RequestParam(name = "dw_type") Integer dwType,
            @ApiParam(name="min_deposit_volume",value = "最小充币额",required = true)
            @RequestParam(name = "min_deposit_volume") BigDecimal minDepositVolume,
            @ApiParam(name="min_withdraw_volume",value = "最小提币额",required = true)
            @RequestParam(name = "min_withdraw_volume") BigDecimal minWithdrawVolume,
            @ApiParam(name="withdraw_fee",value = "提币手续费",required = true)
            @RequestParam(name = "withdraw_fee") BigDecimal withdrawFee,
            @ApiParam(name="c2c_fee",value = "c2c手续费",required = true)
            @RequestParam(name = "c2c_fee") BigDecimal c2cFee,
             @ApiParam(name="chain_transaction_url",value = "以太坊地址",required = true)
            @RequestParam(name = "chain_transaction_url") String chainTransactionUrl,
            @ApiParam(name="enable_flag_pc",value = "永续合约中是否启用",required = true)
            @RequestParam(name = "enable_flag_pc") Integer enableFlagPc,
            @ApiParam(name="enable_flag_bb",value = "BB中是否启用",required = true)
            @RequestParam(name = "enable_flag_bb") Integer enableFlagBb
    ) {
        AssetInputDto inputDto=new AssetInputDto();
        inputDto.setId(id);
        //inputDto.setSymbol(symbol);
        inputDto.setIconImg(iconImg);
        inputDto.setIcon(icon);
        inputDto.setChainAppointId(chainAppointId);
        inputDto.setRealName(realName);
        inputDto.setDisplaynName(displayName);
        inputDto.setChainNname(chainName);
        inputDto.setPrecision(precision);
        inputDto.setPrivilege(privilege);
        inputDto.setStatus(status);
        inputDto.setSort(sort);
        inputDto.setMinDepositVolume(minDepositVolume);
        inputDto.setMinWithdrawVolume(minWithdrawVolume);
        inputDto.setWithdrawFee(withdrawFee);
        inputDto.setChainTransactionUrl(chainTransactionUrl);
        inputDto.setDwType(dwType);
        inputDto.setEnableFlagPc(enableFlagPc);
        inputDto.setEnableFlagBb(enableFlagBb);
        inputDto.setC2cFee(c2cFee);
        AssetOutputDto outputDto=new AssetOutputDto();
        try {
            if(inputDto.getId()>0)
                outputDto= iApiAssetControllerService.editAsset(inputDto);
            else
                outputDto= iApiAssetControllerService.createAsset(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }


    @ApiOperation(value = "获取币")
    @GetMapping(path = "/query")
    public BaseResultViaApiDto<GetAssetInputDto,GetAssetOutputDto> getAsset(
            @ApiParam(name="id",value = "币id",required = true)
            @RequestParam(name = "id") long id
    )
    {
        GetAssetInputDto inputDto=new GetAssetInputDto();
        inputDto.setId(id);
        GetAssetOutputDto outputDto=new GetAssetOutputDto();
        try {
            outputDto= iApiAssetControllerService.getAssetById(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
    @ApiOperation(value = "检测币种是否已经存在了")
    @PostMapping(path = "/check_has_asset")
    public BaseResultViaApiDto<CheckHasAssetInputDto,CheckHasAssetOutputDto> checkHasAsset(
            @ApiParam(name="real_name",value = "币id",required = true)
            @RequestParam(name = "real_name") String realName
    )
    {
        CheckHasAssetInputDto inputDto=new CheckHasAssetInputDto();
        inputDto.setRealName(realName);
        CheckHasAssetOutputDto outputDto=new CheckHasAssetOutputDto();
        try {
            outputDto= iApiAssetControllerService.checkHasAsset(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取币列表")
    @GetMapping(path = "/query_list")
    public BaseResultViaApiDto<AssetListInputDto,AssetListOutputDto> getAssetList(
     @ApiParam(name="real_name",value = "币的真实名称",required = true)
     @RequestParam(name = "real_name") String realName,
     @ApiParam(name="page_size",value = "条数",required = true)
     @RequestParam(name = "page_size") Integer pageSize,
     @ApiParam(name="current_page",value = "页码",required = true)
     @RequestParam(name = "current_page") Integer currentPage
    )
    {
        AssetListInputDto inputDto=new AssetListInputDto();
        inputDto.setRealName(realName);
        inputDto.setPageSize(pageSize);
        inputDto.setCurrentPage(currentPage);
        AssetListOutputDto outputDto=new AssetListOutputDto();
        try {
            outputDto= iApiAssetControllerService.getAssetList(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取交易对列表")
    @GetMapping(path = "/query_asset_list")
    public BaseResultViaApiDto<RealNameListInputDto,RealNameListOutPutDto> getSymbolList()
    {
        RealNameListInputDto inputDto=new RealNameListInputDto();
        RealNameListOutPutDto outputDto=new RealNameListOutPutDto();
        try {
            outputDto= iApiAssetControllerService.getRealNameList(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public BaseResultViaApiDto<DeleteInputDto,DeleteOutputDto> deleteAsset(
            @ApiParam(name="ids",value = "ids",required = true)
            @RequestParam(name = "ids") String ids
    ){
        //logger.info("打印日志--------------------->");
        DeleteInputDto inputDto=new DeleteInputDto();
        DeleteOutputDto outputDto=new DeleteOutputDto();
        inputDto.setIds(ids);

        try{
            outputDto = iApiAssetControllerService.deleteAsset(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
