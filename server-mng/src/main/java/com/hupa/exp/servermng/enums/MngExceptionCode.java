package com.hupa.exp.servermng.enums;

import com.hupa.exp.common.exception.ExceptionDescribe;

public enum MngExceptionCode implements ExceptionDescribe {
    Success(0, "SUCCEED", ""),//成功
    DUBBO_SERVER_ERROR(-90001, "DUBBO_SERVER_ERROR", ""),//连接Dubbo服务错误
    COLLECT_FEE_NUM_ERROR(-90002,"COLLECT_FEE_NUM_ERROR",""),
    TOKEN_NULL_ERROR(-10004,"TOKEN_NULL_ERROR",""),
    ASSET_EXIST_ERROR(-90003,"ASSET_EXIST_ERROR",""),
    CONTRACT_EXIST_ERROR(-90004,"CONTRACT_EXIST_ERROR",""),
    PWD_NULL_ERROR(-90005,"PWD_NULL_ERROR",""),
    PHONE_EXIST_ERROR_MNG(-90007,"PHONE_EXIST_ERROR_MNG",""),
    EMAIL_EXIST_ERROR_MNG(-90008,"EMAIL_EXIST_ERROR_MNG",""),
    TABLE_NOT_EXIST_ERROR(-90010,"TABLE_NOT_EXIST_ERROR",""),
    CONSTANT_EXIST_ERROR(-90011,"CONSTANT_EXIST_ERROR",""),
    OPERATION_DATABASE_ERROR(-90012,"CONSTANT_OPE_DATABASE_ERROR","操作数据库错误"),
    DATA_EXIST_ERROR(-90013,"DATA_EXIST_ERROR",""),
    DATA_NOT_EXIST_ERROR(-90014,"DATA_NOT_EXIST_ERROR",""),
    THE_PARAMETER_CANNOT_BE_NULL(-90015,"THE_PARAMETER_CANNOT_BE_NULL",""),
    MIN_SPACE_NOT_GREATER_THAN_MAX_SPACE(-90016,"最小价格区间不能大于最大价格区间",""),
    MIN_ORDER_NUMBER_NOT_GREATER_THAN_MAX_ORDER_NUMBER(-90017,"最小下单量不能大于最大下单量",""),
    MIN_SWING_NOT_GREATER_THAN_MAX_SWING(-90018,"最小价格摆动不能大于最大价格摆动",""),
    MIN_BACK_NOT_BE_ZERO_OR_NEGATIVE(-90019,"关闭控制时,回到默认最小行情的幅度,不能是负和零",""),
    MAX_BACK_NOT_BE_ZERO_OR_NEGATIVE(-90020,"关闭控制时,回到默认最大行情的幅度,不能是负和零",""),
    THE_PARAM_MUST_BE_A_NUMBER(-90021,"参数必须为数字,请检查输入参数类型",""),
    MIN_FREQUENCY_NOT_GREATER_THAN_MAX_FREQUENCY(-90022,"行情控制最小频率不能大于最大频率",""),
    MIN_BACK_NOT_GREATER_THAN_MAX_BACK(-90023,"最小默认行情的幅度不能大于最大默认行情的幅度",""),
    ;
    private int code;
    private String constant;
    private String params;

    @Override
    public String getParams() {
        return this.params;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getConstant() {
        return this.constant;
    }

    MngExceptionCode(int code, String constant, String params) {
        this.code = code;
        this.constant = constant;
        this.params = params;
    }

}