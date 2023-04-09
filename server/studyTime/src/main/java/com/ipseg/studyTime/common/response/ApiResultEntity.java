package com.ipseg.studyTime.common.response;

import com.ipseg.studyTime.common.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResultEntity {

    public static <T> ResponseEntity<Object> success() {
        final ApiResult<T> body = new ApiResult<>(ResultCode.SUCCESS.code(), ResultCode.SUCCESS.msg());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public static <T> ResponseEntity<Object> success(T data) {
        final ApiResult<T> body = new ApiResult<>(ResultCode.SUCCESS.code(), ResultCode.SUCCESS.msg(), data);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public static <T> ResponseEntity<Object> fail(T data) {
        ApiResult<T> body = new ApiResult<>(ResultCode.ERROR_001.code(), ResultCode.ERROR_001.msg(), data);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public static <T> ResponseEntity<Object> fail(ResultCode resultCode) {
        ApiResult<T> body = new ApiResult<>(resultCode.code(), resultCode.msg());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public static <T> ResponseEntity<Object> fail() {
        ApiResult<T> body = new ApiResult<>(ResultCode.ERROR_001.code(), ResultCode.ERROR_001.msg());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
