package com.example.handle;

import com.example.domain.Result;
import com.example.exception.GirlException;
import com.example.exception.PayException;
import com.example.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Aidon on 17/7/14.
 */
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        //可定义多个exception作为判断条件
        if (e instanceof GirlException) {
            GirlException girlException = (GirlException) e;
            return ResultUtil.error(girlException.getCode(), girlException.getMessage());
        } else if (e instanceof PayException) {
            PayException payException = (PayException) e;
            return ResultUtil.error(payException.getCode(), payException.getMessage());
        } else {
            logger.error("系统异常 {}", e.getMessage());
            return ResultUtil.error(-1, e.getMessage());
        }
    }
}
