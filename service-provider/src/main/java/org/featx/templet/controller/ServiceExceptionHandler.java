package org.featx.templet.controller;

import lombok.extern.slf4j.Slf4j;
import org.featx.spec.enums.BusinessError;
import org.featx.spec.error.BusinessException;
import org.featx.spec.feature.BaseExceptionHandler;
import org.featx.spec.model.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Excepts
 * @since 2020/1/2 16:11
 */
@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler extends BaseExceptionHandler {

    @Override
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public <R> BaseResponse<R> handleException(Exception e) {
        log.error("Controller common exception", e);
        return BaseResponse.occur(BusinessError.SERVER_ERROR);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = BusinessException.class)
    public BaseResponse<Object> handleBusinessException(BusinessException e) {
        log.error("Business error {}, {}", e.getBusinessError(), e.getExtra());
        return BaseResponse.occur(e.getBusinessError(), e.getExtra());
    }
}
