package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.coin.*;
import com.hupa.exp.servermng.help.SessionHelper;
import com.hupa.exp.servermng.service.def.IApiCoinControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Api(tags = "apiCoinController")
@RestController
@RequestMapping(path = "/v1/http/coin",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiCoinController {

    @Autowired
    private IApiCoinControllerService iApiCoinControllerService;
    @Autowired
    private SessionHelper sessionHelper;

    private Logger logger = LoggerFactory.getLogger(ApiCoinController.class);

    @ApiOperation(value = "新增或修改币种")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<CoinInputDto,CoinOutputDto> createOrEditCoin(
            @ApiParam(name="id",value = "币Id",required = true)
            @RequestParam(name = "id") long id,
            @ApiParam(name="symbol",value = "币的符号",required = true)
            @RequestParam(name = "symbol") String symbol,
            @ApiParam(name="chain_symbol_id",value = "链上服务的symbolId",required = true)
            @RequestParam(name = "chain_symbol_id") Integer chainSymbolId,
            @ApiParam(name="coin_name",value = "币的名称",required = true)
            @RequestParam(name = "coin_name") String coinName,
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
            @ApiParam(name="min_withdraw_volume",value = "最小提币额",required = true)
            @RequestParam(name = "min_withdraw_volume") BigDecimal minWithdrawVolume,
            @ApiParam(name="withdraw_fee",value = "提币手续费",required = true)
            @RequestParam(name = "withdraw_fee") BigDecimal withdrawFee,
             @ApiParam(name="chain_transaction_url",value = "以太坊地址",required = true)
            @RequestParam(name = "chain_transaction_url") String chainTransactionUrl
    )
    {
        CoinInputDto inputDto=new CoinInputDto();
        inputDto.setId(id);
        //inputDto.setSymbol(symbol);
        inputDto.setChainSymbolId(chainSymbolId);
        inputDto.setCoinName(coinName);
        inputDto.setDisplaynName(displayName);
        inputDto.setChainNname(chainName);
        inputDto.setPrecision(precision);
        inputDto.setPrivilege(privilege);
        inputDto.setStatus(status);
        inputDto.setMinWithdrawVolume(minWithdrawVolume);
        inputDto.setWithdrawFee(withdrawFee);
        inputDto.setChainTransactionUrl(chainTransactionUrl);
        CoinOutputDto outputDto=new CoinOutputDto();
        try {
            if(inputDto.getId()>0)
                outputDto= iApiCoinControllerService.editCoin(inputDto);
            else
                outputDto= iApiCoinControllerService.createCoin(inputDto);
        } catch (BizException e) {

            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
    @ApiOperation(value = "获取币")
    @GetMapping(path = "/query")
    public BaseResultViaApiDto<GetCoinInputDto,GetCoinOutputDto> getCoin(
            @ApiParam(name="id",value = "币id",required = true)
            @RequestParam(name = "id") long id
    )
    {
        GetCoinInputDto inputDto=new GetCoinInputDto();
        inputDto.setId(id);
        GetCoinOutputDto outputDto=new GetCoinOutputDto();
        try {
            outputDto=iApiCoinControllerService.getCoinById(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
    @ApiOperation(value = "检测币种是否已经存在了")
    @PostMapping(path = "/check_has_coin")
    public BaseResultViaApiDto<CheckHasCoinInputDto,CheckHasCoinOutputDto> checkHasCoin(
            @ApiParam(name="symbol",value = "币id",required = true)
            @RequestParam(name = "symbol") String symbol
    )
    {
        CheckHasCoinInputDto inputDto=new CheckHasCoinInputDto();
        inputDto.setSymbol(symbol);
        CheckHasCoinOutputDto outputDto=new CheckHasCoinOutputDto();
        try {
            outputDto=iApiCoinControllerService.checkHasCoin(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取交易对列表")
    @GetMapping(path = "/query_list")
    public BaseResultViaApiDto<CoinListInputDto,CoinListOutputDto> getCoinList(//
     @ApiParam(name="page_size",value = "条数",required = true)
     @RequestParam(name = "page_size") Integer pageSize,
     @ApiParam(name="current_page",value = "页码",required = true)
     @RequestParam(name = "current_page") Integer currentPage
    )
    {
        CoinListInputDto inputDto=new CoinListInputDto();
        inputDto.setPageSize(pageSize);
        inputDto.setCurrentPage(currentPage);
        CoinListOutputDto outputDto=new CoinListOutputDto();
        try {
            outputDto=iApiCoinControllerService.getCoinList(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "获取交易对列表")
    @GetMapping(path = "/query_symbol_list")
    public BaseResultViaApiDto<SymbolListInputDto,SymbolListOutPutDto> getSymbolList()
    {
        SymbolListInputDto inputDto=new SymbolListInputDto();
        SymbolListOutPutDto outputDto=new SymbolListOutPutDto();
        try {
            outputDto=iApiCoinControllerService.getSymbolList(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return  BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
