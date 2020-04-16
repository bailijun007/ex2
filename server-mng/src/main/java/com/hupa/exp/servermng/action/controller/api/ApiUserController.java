package com.hupa.exp.servermng.action.controller.api;

import com.hupa.exp.common.entity.dto.BaseResultViaApiDto;
import com.hupa.exp.common.exception.BizException;
import com.hupa.exp.common.tool.converter.BaseResultViaApiUtil;
import com.hupa.exp.servermng.entity.base.DeleteInputDto;
import com.hupa.exp.servermng.entity.base.DeleteOutputDto;
import com.hupa.exp.servermng.entity.user.*;
import com.hupa.exp.servermng.service.def.IApiUserControllerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags = {"apiUserController"})
@RestController
@RequestMapping(path = "/v1/http/user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiUserController {

    //private Logger logger = LoggerFactory.getLogger(ApiUserController.class);

    @Autowired
    private IApiUserControllerService iApiUserControllerService;


    /**
     * 插入菜单数据
     */
    @ApiOperation(value = "插入用户")
    @PostMapping("/create_or_edit")
    public BaseResultViaApiDto<UserInputDto,UserOutputDto> createOrEditUser(@RequestBody UserInputDto inputDto){
        //logger.info("打印日志--------------------->");
        UserOutputDto outputDto=new UserOutputDto();
        inputDto.setUpdatetime(new Date().getTime());
        try{
            outputDto= iApiUserControllerService.createUser(inputDto);
        }catch(BizException e){
           return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }

    @ApiOperation(value = "查询用户列表")
    @GetMapping("/query_list")
    public BaseResultViaApiDto<UserListInputDto,UserListOutputDto>  selectUserList(
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage
    )
    {
        UserListInputDto inputDto =new UserListInputDto();
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        UserListOutputDto outputDto =new UserListOutputDto();
        try {
            outputDto=iApiUserControllerService.queryList(inputDto);
        } catch (BizException e) {
            return  BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
        //return JSON.toJSONString(list);
    }

    @ApiOperation(value = "按类型查询用户列表")
    @GetMapping("/query_list_by_type")
    public BaseResultViaApiDto<UserListInputDto,UserListOutputDto>  selectUserListByUserType(
            @ApiParam(name="id",value = "用户Id",required = true)
            @RequestParam(name = "id") Long id,
            @ApiParam(name="user_name",value = "用户名",required = true)
            @RequestParam(name = "user_name") String userName,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage,
            @ApiParam(name="user_type",value = "用户类型",required = true)
            @RequestParam(name = "user_type") Integer userType
    )
    {
        UserListInputDto inputDto =new UserListInputDto();
        inputDto.setCurrentPage(currentPage);
        inputDto.setPageSize(pageSize);
        inputDto.setUserType(userType);
        inputDto.setUserName(userName);
        inputDto.setId(id);
        UserListOutputDto outputDto =new UserListOutputDto();
        try {
            outputDto=iApiUserControllerService.queryListByUserType(inputDto);
        } catch (BizException e) {
            return  BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }

        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
        //return JSON.toJSONString(list);
    }

    @ApiOperation(value = "获取用户详情")
    @GetMapping("/query")
    public BaseResultViaApiDto<UserInputDto,UserOutputDto> getUserInfoById(
            @ApiParam(name="id",value = "用户id",required = true)
            @RequestParam(name = "id") long id)
    {
        UserOutputDto outputDto=new UserOutputDto();
        UserInputDto inputDto=new UserInputDto();
        inputDto.setId(id);
        try {
            outputDto= iApiUserControllerService.queryUserInfoById(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e) ;
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto) ;
    }


    /**
     * 获取资金账户列表
     * @param id
     * @param userName
     * @param userType
     * @param pageSize
     * @param currentPage
     * @return
     */
    @ApiOperation(value = "获取账户资金列表")
    @GetMapping("/query_fund_account_list")
    public BaseResultViaApiDto<FundAccountListInputDto,FundAccountListOutputDto> getFundAccountList(
            @ApiParam(name="id",value = "账号",required = true)
            @RequestParam(name = "id") Long id ,
            @ApiParam(name="user_name",value = "账号",required = true)
            @RequestParam(name = "user_name") String userName ,
            @ApiParam(name="asset",value = "币种",required = true)
            @RequestParam(name = "asset") String asset ,
            @ApiParam(name="user_type",value = "用户类型",required = true)
            @RequestParam(name = "user_type") Integer userType,
            @ApiParam(name="page_size",value = "条数",required = true)
            @RequestParam(name = "page_size") Integer pageSize,
            @ApiParam(name="current_page",value = "页码",required = true)
            @RequestParam(name = "current_page") Integer currentPage)

    {
        FundAccountListOutputDto outputDto=new FundAccountListOutputDto();
        FundAccountListInputDto inputDto=new FundAccountListInputDto();
        inputDto.setPageSize(pageSize);
        inputDto.setCurrentPage(currentPage);
        inputDto.setUserType(userType);
        inputDto.setUserName(userName);
        inputDto.setAsset(asset);
        inputDto.setId(id);
        try {
        outputDto= iApiUserControllerService.queryFundAccountListByParam(inputDto);
    } catch (BizException e) {
        return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e) ;
    }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto) ;
}

    @ApiOperation(value = "获取账户资金详细信息")
    @GetMapping("/query_fund_account")
    public BaseResultViaApiDto<FundAccountInfoInputDto,FundAccountInfoOutputDto> queryFundAccountById(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") String id)
    {
        FundAccountInfoOutputDto outputDto=new FundAccountInfoOutputDto();
        FundAccountInfoInputDto inputDto=new FundAccountInfoInputDto();
        inputDto.setId(Long.parseLong(id));
        try {
            outputDto= iApiUserControllerService.queryFundAccountById(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e) ;
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto) ;
    }

    @ApiOperation(value = "给资金账户加钱")
    @PostMapping("/edit_fund_account")
    public BaseResultViaApiDto<EditFundAccountInputDto,EditFundAccountOutputDto> editFundAccount(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") String id,
            @ApiParam(name="funds",value = "funds",required = true)
            @RequestParam(name = "funds") String funds)
    {
        EditFundAccountOutputDto outputDto=new EditFundAccountOutputDto();
        EditFundAccountInputDto inputDto=new EditFundAccountInputDto();
        inputDto.setId(Long.parseLong(id));
        inputDto.setFunds(funds);
        try {
            outputDto= iApiUserControllerService.editFundAccount(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e) ;
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto) ;
    }


    /**
     * 给账户加钱和扣钱操作
     * @param id
     * @param funds
     * @return
     */
    @ApiOperation(value = "给某一个交易对资金账户加钱")
    @PostMapping("/edit_fund_account_asset")
    public BaseResultViaApiDto<EditFundAccountInputDto,EditFundAccountOutputDto> editFundAccountAsset(
            @ApiParam(name="id",value = "id",required = true)
            @RequestParam(name = "id") String id,
            @ApiParam(name="type",value = "type",required = true)
            @RequestParam(name = "type") Integer type,
            @ApiParam(name="funds",value = "funds",required = true)
            @RequestParam(name = "funds") String funds)
        {
            EditFundAccountOutputDto outputDto=new EditFundAccountOutputDto();
            EditFundAccountInputDto inputDto=new EditFundAccountInputDto();
        try {
            inputDto.setId(Long.parseLong(id));
            inputDto.setType(type);
            inputDto.setFunds(funds);
            outputDto= iApiUserControllerService.editFundAccountOneAsset(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e) ;
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto) ;
    }

    @ApiOperation(value = "修改用户状态")
    @PostMapping("/enable")
    public BaseResultViaApiDto<EnableUserInputDto,EnableUserOutputDto> enableUser(
            @ApiParam(name="ids",value = "ids",required = true)
            @RequestParam(name = "ids") String ids,
            @ApiParam(name="status",value = "status",required = true)
            @RequestParam(name = "status") Integer status)
    {
        EnableUserOutputDto outputDto=new EnableUserOutputDto();
        EnableUserInputDto inputDto=new EnableUserInputDto();
        inputDto.setIds(ids);
        inputDto.setStatus(status);
        try {
            outputDto= iApiUserControllerService.enableUser(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e) ;
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto) ;
    }


    @ApiOperation(value = "创建账户")
    @GetMapping("/create_account")
    public BaseResultViaApiDto<CreateAccountInputDto,CreateAccountOutputDto> createAccount() {
        CreateAccountOutputDto outputDto=new CreateAccountOutputDto();
        CreateAccountInputDto inputDto=new CreateAccountInputDto();
        try {
            outputDto= iApiUserControllerService.createAccount(inputDto);
        } catch (Exception e) {
            return null;
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto) ;
    }


    @ApiOperation(value = "批量创建账户")
    @GetMapping("/batch_create_account")
    public BaseResultViaApiDto<CreateAccountInputDto,CreateAccountOutputDto> batchCreateAccount(
            @ApiParam(name="number",value = "number",required = true)
            @RequestParam(name = "number") Integer number) {
        CreateAccountOutputDto outputDto = null;
        CreateAccountInputDto inputDto=new CreateAccountInputDto();
        try {
            inputDto.setNumber(number);
            outputDto = iApiUserControllerService.batchCreateAccount(inputDto);
        } catch (BizException e) {
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e) ;
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto) ;
    }

    @ApiOperation(value = "生成手续费率")
    @GetMapping("/gen_fee")
    public BaseResultViaApiDto<GenFeeInputDto,GenFeeOutputDto> genFee()
    {
        GenFeeOutputDto outputDto=new GenFeeOutputDto();
        GenFeeInputDto inputDto=new GenFeeInputDto();

        try {
            outputDto= iApiUserControllerService.genFee(inputDto);
        } catch (Exception e) {
            return null;
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto) ;
    }

    @ApiOperation(value = "检查用户是否已存在")
    @PostMapping("/check_has_user")
    public BaseResultViaApiDto<CheckExistUserInputDto,CheckExistUserOutputDto> cehckHasUser(
            @ApiParam(name="account",value = "account",required = true)
            @RequestParam(name = "account") String account)
    {
        CheckExistUserOutputDto outputDto=new CheckExistUserOutputDto();
        CheckExistUserInputDto inputDto=new CheckExistUserInputDto();
        inputDto.setAccount(account);
        try {
            outputDto= iApiUserControllerService.checkExistUser(inputDto);
        } catch (Exception e) {
            return null;
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
            outputDto = iApiUserControllerService.deleteUser(inputDto);

        }catch(BizException e){
            return BaseResultViaApiUtil.buildExceptionResult(inputDto,outputDto,e);
        }
        return BaseResultViaApiUtil.buildSucceedResult(inputDto,outputDto);
    }
}
