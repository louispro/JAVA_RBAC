package com.louis.rabc.exception;

import com.louis.rabc.code.ResultCode;

public class BusinessException extends BaseException {
    public BusinessException(ResultCode resultCode) {
        super(resultCode);
    }
}
