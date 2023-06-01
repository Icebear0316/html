package cn.tedu.baking.exception;




import cn.tedu.baking.response.ResultVO;
import cn.tedu.baking.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * RestControllerAdvice 描述的类型为一个全局异常处理对象类型,
 * 当某个Controller方法中出现了异常，此时在Controller类内部，又没有定义对应的
 * 异常处理方法(使用ExcetpionHandler注解描述的方法)，系统底层就会查找有没有定义全局异常处理对象。
 * 这个全局异常处理对象中有没有定义对应的异常处理方法，假如有就调用此方法处理异常。
 */

@Slf4j //lombok提供的日志注解，在代码层面会为我们提供一个org.slf4j.Logger对象
@RestControllerAdvice //=@ControllerAdvice+@ResponseBody
public class GlobalExceptionHandler {
    //private static final Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     *  @ExceptionHandler 描述的方法为一个异常处理方法，在此注解内部可以定义具体的异常处理
     *  类型(例如RuntimeException.class),此注解描述的方法需要定义一个异常类型的形式参数，
     *  通过这个参数接收具体的异常对象(也可以接收其异常类型对应的子类类型的异常)。
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResultVO doHandleRuntimeException(RuntimeException ex){
        System.out.println(ex.getClass().getName());
        log.error("error is {}",ex.getMessage());//日志级别trace<debug<info<warn<error
        return new ResultVO(0,ex.getMessage());
    }

    /**
     * 对参数校验异常进行处理
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResultVO doBindException(BindException ex){
        ObjectError objectError = ex.getBindingResult().getAllErrors().get(0);
        return new ResultVO(StatusCode.VALIDATE_ERROR,
                objectError.getDefaultMessage());
    }
    /**
     * 假如用全局异常处理对象处理Controller类中出现的异常，全局异常处理对象会优先查找与Controller
     * 中相匹配的异常处理方法，假如没有，会查找对应异常的父类异常处理方法。
     * @param ex
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVO doHandleRuntimeException(IllegalArgumentException ex){
        log.error("IllegalArgumentException is {}",ex.getMessage());//日志级别trace<debug<info<warn<error
        return new ResultVO(0,ex.getMessage());
    }
    /**
     * 用户名或密码错误抛出的异常
     */
    @ExceptionHandler({InternalAuthenticationServiceException.class,
            BadCredentialsException.class})
    public ResultVO handleAuthenticationException(AuthenticationException e){
        //判断当前异常是否属于用户名不存在异常
        if (e instanceof InternalAuthenticationServiceException){
            log.warn("用户名不存在!");
            return new ResultVO(StatusCode.USERNAME_ERROR);
        }
        log.warn("密码错误!");
        return new ResultVO(StatusCode.PASSWORD_ERROR);
    }

    /**
     * 无访问时抛出的异常
     * */
    @ExceptionHandler(AccessDeniedException.class)
    public ResultVO handleAccessDeniedException(AccessDeniedException e){
        log.warn("无权访问!");
        return new ResultVO(StatusCode.FORBIDDEN_ERROR);
    }

}
