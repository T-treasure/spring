package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysResult {

    private Integer status; //状态码信息 200成功  201失败
    private String msg;     //服务器提示信息
    private Object data;    //服务器的返回值

    public static SysResult fail() {
        return new SysResult(201, "服务器运行失败", null);
    }

    public static SysResult success() {
        return new SysResult(200, "服务器运行成功", null);
    }

    public static SysResult success(Object data) {
        return new SysResult(200, "服务器运行成功", data);
    }

    public static SysResult success(String msg, Object data) {
        return new SysResult(200, msg, data);
    }

}
