package com.ipseg.studyTime.common.handler;

import com.ipseg.studyTime.common.ResultCode;
import com.ipseg.studyTime.common.response.ApiResult;
import com.ipseg.studyTime.common.response.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResult<Object>> handleBusinessException(final BusinessException exception) {
        ResultCode code = exception.getResultCode();
        ApiResult<Object> body = new ApiResult<>(code.code(), code.msg());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
