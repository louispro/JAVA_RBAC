package com.louis.rabc.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ResponseVo<T> implements Serializable {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 请求成功
     *
     * @param t t
     * @return {@link ResponseVo}<{@link T}>
     */
    public static <T> ResponseVo<T> success(T t) {
        ResponseVo<T> responseVo = new ResponseVo<>();
        return responseVo.setCode(2000).setMessage("请求成功").setData(t);
    }

    /**
     * 请求失败
     *
     * @return {@link ResponseVo}
     */
    public static ResponseVo failure() {
        ResponseVo responseVo = new ResponseVo();
        return responseVo.setCode(4000).setMessage("响应失败");
    }

}
