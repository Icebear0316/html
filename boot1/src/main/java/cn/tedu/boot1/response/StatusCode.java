package cn.tedu.boot1.response;

import lombok.Getter;

/**
 * 通过此枚举定义一些响应状态码信息,
 * 实际项目中会基于不同业务定义不同状态码对象
 */
@Getter
public enum StatusCode {
    SUCCESS(1,"OK"),
    NOT_LOGIN(1000,"未登录"), //所有实例必须是在最上面的
    LOGIN_SUCCESS(1001,"登录成功"),
    PASSWORD_ERROR(1002,"密码错误"),//对象(枚举实例)
    USERNAME_ERROR(1003,"用户名错误"),
    USERNAME_ALREADY_EXISTS(1004,"用户名已存在"),
    FORBIDDEN_ERROR(1005,"无权访问"),

    OPERATION_SUCCESS(2001,"操作成功"),
    OPERATION_FAILED(2002,"操作失败"),
    VALIDATE_ERROR(3002, "参数校验失败");
    /**表示状态码*/
    private int code;
    /**状态码信息*/
    private String msg;
    //所有构造方法默认使用private访问修饰符修饰
    StatusCode(){}
    /**构造方法*/
    StatusCode(int code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
