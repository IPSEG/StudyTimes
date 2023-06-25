package com.ipseg.studyTime.common;


public enum ResultCode {

    SUCCESS("000", "정상 처리 되었습니다."),
    ERROR_001("001", "요청 처리에 실패했습니다."),
    ERROR_002("002", "유저 정보가 존재하지 않습니다."),
    ERROR_003("003", "토큰이 유효하지 않습니다."),
    ERROR_004("004", "비밀번호가 일치하지 않습니다."),
    ERROR_005("005", "타이머 정보가 존재하지 않습니다."),
    ERROR_006("006", "파라미터 정보가 존재하지 않습니다."),
    ERROR_007("007", "비밀번호와 재확인 비밀번호가 동일하지 않습니다."),
    ERROR_008("008", "아이디는 10자 이하여야 합니다."),
    ERROR_009("009","타이머 메모 생성 실패하였습니다");

    private final String resultCode;
    private final String resultMsg;

    ResultCode(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public String code() {return this.resultCode;}
    public String msg() {return this.resultMsg;}

}
