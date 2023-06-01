package cn.tedu.baking.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 统一响应结果的封装类型
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO {

    /**响应状态码(业务状态码)*/
    private int code;
    /**服务端要返回给客户端的消息(例如，密码不正确)*/
    private String msg;
    /**服务端要返回给客户端的具体数据(业务数据，比方说从数据库查询到的数据)*/
    private Object data;

    /**通过构造方法对属性初始化*/
    public ResultVO(Object data){
        this.code=StatusCode.SUCCESS.getCode();
        this.msg=StatusCode.SUCCESS.getMsg();
        this.data=data;
    }
    public ResultVO(int code,String msg){
        this.code=code;
        this.msg=msg;
    }
    /**通过构造方法对属性初始化*/
    public ResultVO(StatusCode statusCode){
        this.code=statusCode.getCode();
        this.msg=statusCode.getMsg();
    }
    /**通过构造方法对象属性初始化*/
    public ResultVO(StatusCode statusCode,Object data){
        this(statusCode);
        this.data=data;
    }

    public static ResultVO ok(Object data){
        ResultVO resultVO = new ResultVO(StatusCode.SUCCESS,data);
        return resultVO;
    }

    public static ResultVO ok(){
        return ok(null);
    }

}
