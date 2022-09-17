package com.louis.rabc.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.louis.rabc.code.ResultCode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ResponseExceptionVo implements Serializable {

    private Integer code;
    private String message;
    private boolean success = false;
    @JsonIgnore
    private ResultCode resultCode;

    public static ResponseExceptionVo error() {
        ResponseExceptionVo result = new ResponseExceptionVo();
        result.setResultCode(ResultCode.SERVER_ERROR);
        return result;
    }

    public static ResponseExceptionVo error(String message) {
        ResponseExceptionVo result = new ResponseExceptionVo();
        result.setCode(ResultCode.SERVER_ERROR.code());
        result.setMessage(message);
        return result;
    }

    public static ResponseExceptionVo error(Integer code, String message) {
        ResponseExceptionVo result = new ResponseExceptionVo();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static ResponseExceptionVo error(ResultCode resultCode, String message) {
        ResponseExceptionVo result = new ResponseExceptionVo();
        result.setResultCode(resultCode);
        result.setMessage(message);
        return result;
    }
}
