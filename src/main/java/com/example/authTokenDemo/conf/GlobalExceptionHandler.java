package com.example.authTokenDemo.conf;

import com.example.authTokenDemo.framework.constant.CommonErrorEnum;
import com.example.authTokenDemo.framework.model.Result;
import com.example.authTokenDemo.framework.model.StaticInfo;
import com.example.authTokenDemo.util.exception.AssertException;
import com.example.authTokenDemo.util.exception.ServiceException;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 统一异常处理，返回JSON
 *
 * @author luwl
 * @version [1.0.0, 2020-4-20 下午 09:43]
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * method 不支持异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Result<Object> handleHttpRequestMethodNotSupportedException(HttpServletRequest request, Exception e) {
        Result<Object> resultInfo = Result.error();
        //org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'GET' not supported
        resultInfo.setMessage(e.getMessage());
        StaticInfo.setExceptionResult(resultInfo);
        return resultInfo;
    }

    /**
     * 请求参数转换错误 (RequestBody 接收的字符串转换不了Bean)
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result<Object> handleHttpMessageNotReadableException(HttpServletRequest request, Exception e) {
        Result<Object> resultInfo = Result.error();
        //org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Unexpected character ('"' (code 34)): ...
        resultInfo.setMessage("请求参数格式错误，请检查！");
        printErrorStackTraceInResultData(e, resultInfo);
        StaticInfo.setExceptionResult(resultInfo);
        return resultInfo;
    }

    /**
     * 断言异常
     */
    @ExceptionHandler(AssertException.class)
    @ResponseBody
    public Result<Object> handleAssertException(HttpServletRequest request, Exception e) {
        Result<Object> resultInfo = Result.error();
        resultInfo.setMessage(e.getMessage());
        StaticInfo.setExceptionResult(resultInfo);
        return resultInfo;
    }


    /**
     * Controller 缺少`@RequestParam`参数：
     * 默认不能为空，如果想为空可以使用：`@RequestParam(required = false)`
     * org.springframework.web.bind.MissingServletRequestParameterException: Required String parameter 'thirdAccountType' is not present
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Result<Object> handleMissingServletRequestParameterException(HttpServletRequest request, Exception e) {
        Result<Object> resultInfo = Result.error(CommonErrorEnum.E_PARAM_ERROR);
        e.printStackTrace();
        printErrorStackTraceInResultData(e, resultInfo);
        StaticInfo.setExceptionResult(resultInfo);
        return resultInfo;
    }

    /**
     * 处理自定义服务异常
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result<Object> handleServiceException(HttpServletRequest request, Exception e) {
        Result<Object> resultInfo = Result.error();
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            if (serviceException.getResult() != null) {
                return serviceException.getResult();
            } else {
                resultInfo.setMessage(e.getMessage());
                printErrorStackTraceInResultData(e, resultInfo);
            }
        }
        StaticInfo.setExceptionResult(resultInfo);
        return resultInfo;
    }

    /**
     * 上面只能捕获RootCause是自身的，对RootCause不是自身的在这里判断。
     **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Object> handleException(HttpServletRequest request, Exception e) {
        Result<Object> resultInfo = Result.error();
        log.error("统一未知异常处理 => 请求路径：" + request.getRequestURI() + "，异常信息：" + e.getMessage());
        e.printStackTrace();
        Throwable rootCause = Throwables.getRootCause(e);
        resultInfo = Result.error(CommonErrorEnum.E_UN_KNOW.getCode(), rootCause.getMessage());
        printErrorStackTraceInResultData(e, resultInfo);
        StaticInfo.setExceptionResult(resultInfo);
        return resultInfo;
    }

    /**
     * 打印错误堆栈跟踪结果数据
     *
     * @param e          e
     * @param resultInfo 返回信息
     */
    private void printErrorStackTraceInResultData(Exception e, Result<Object> resultInfo) {
        //DEBUG级别才：未知异常打印堆栈信息到data中。
        if (log.isDebugEnabled()) {
            try {
                ByteArrayOutputStream buf = new ByteArrayOutputStream();
                e.printStackTrace(new PrintWriter(buf, true));
                buf.close();
                resultInfo.setData(buf.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
