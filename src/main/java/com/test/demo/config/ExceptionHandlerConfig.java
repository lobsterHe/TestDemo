package com.test.demo.config;

import com.test.demo.exception.BusinessException;
import com.test.demo.exception.ExceptionEnum;
import com.test.demo.response.ApiResponse;
import com.test.demo.util.ErrorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerConfig {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /**
     * 业务异常处理
     *
     * @param e 业务异常
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ApiResponse exceptionHandler(BusinessException e) {
        logger.error(ErrorUtil.errorInfoToString(e));
        return ApiResponse.error(e.getCode(), e.getErrorMsg());
    }

    /**
     * 未知异常处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResponse exceptionHandler(Exception e) {
        // 把错误信息输入到日志中
        logger.error(ErrorUtil.errorInfoToString(e));
        return ApiResponse.error(ExceptionEnum.UNKNOWN.getCode(),
                ExceptionEnum.UNKNOWN.getMsg());
    }

}
