package com.fengyu.modelservice.expection;

import com.fengyu.modelservice.constant.CommonConstants;
import com.fengyu.modelservice.vo.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 日子记录.
     */
    public static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 系统未知异常.
     * @param e exception
     * @return ResponseResult
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult systemExceptionHandle(Exception e) {
        LOG.error("system exception ---", e);
        return ResponseResult.error("S001", "system error");
    }

    /**
     * 自定义异常.
     * @param e BussinessException
     * @return ResponseResult
     */
    @ExceptionHandler(BussinessException.class)
    @ResponseBody
    public ResponseResult businessExceptionHandle(BussinessException e) {
        LOG.error("system exception ---", e);
        return ResponseResult.error(e.getErrorCode(), e.getMessage());
    }

    /**
     * 参数异常.
     * @param e BindException
     * @return ResponseResult
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseResult bindExceptionHandle(BindException e) {
        LOG.error("system exception ---", e);
        return ResponseResult.error("S0090", "参数错误");
    }

    /**
     * 解析异常
     * @param e
     * @return
     */
    private String getExceptionMsg(Exception e) {
        StringBuilder msg = new StringBuilder();
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement f : stackTrace) {
            if (f.getClassName().contains(CommonConstants.PACKAGE_NAME) && f.getLineNumber() > 0) {
                msg.append(f.getFileName() + " at line " + f.getLineNumber());
            }
        }
        msg.append(e.getMessage());
        return msg.toString();
    }



}
