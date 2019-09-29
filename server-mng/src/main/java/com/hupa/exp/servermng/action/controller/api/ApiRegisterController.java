package com.hupa.exp.servermng.action.controller.api;


import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"apiRegisterController"})
@RestController
@RequestMapping(path = "/v1/http/register",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiRegisterController {
//    private Logger logger = LoggerFactory.getLogger(ApiRegisterController.class);
//    @Autowired
//    private IApiRegisterController iApiRegisterController;
//    /**
//     * 演示效果接口
//     */
//    @ApiOperation(value = "邮箱注册演示demo")
//    @ApiImplicitParam(name = "email",value = "邮箱名称1",paramType = "query",dataType = "String")
//    @GetMapping("/email_register_test")
//    public Object emailRegisterTest(@RequestParam(name = "email") String email) throws Exception {
//        logger.info("打印日志--------------------->");
//        EmailRegisterTestInputDto inputDto = new EmailRegisterTestInputDto();//初始化查询条件对象
//        inputDto.setEmail(email);//
//        EmailRegisterTestOutputDto outputDto = iApiRegisterController.emailRegisterTest(inputDto);
//        return outputDto;
//    }
//
//    @ApiOperation(value="通过用户Id查询用户信息")
//    @ApiImplicitParam(name="id",value = "用户id",paramType = "failed",dataType = "int")
//    @PostMapping("/get_user")
//    public Object getUser(@RequestParam(name="id")Integer id)
//    {
//
//
//        return  "";
//
//    }


}
