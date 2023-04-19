package com.ipseg.studyTime.common.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResult<T>{
    String resultCode;
    String resultMsg;
    T data;

    public ApiResult(String resultCode) {
        this.resultCode = resultCode;
    }

    public ApiResult(String resultCode, T data) {
        this.resultCode = resultCode;
        this.data = data;
    }

    public ApiResult(String resultCode, String resultMsg, T data) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }

    public ApiResult(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
