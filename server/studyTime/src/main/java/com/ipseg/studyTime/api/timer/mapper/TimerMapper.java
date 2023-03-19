package com.ipseg.studyTime.api.timer.mapper;

import com.ipseg.studyTime.api.timer.model.Timer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface TimerMapper {

    /** 타이머 추가 */
    int timerAdd(HashMap<String, Object> param);

    /** 타이머 수정 */
    int modifyTimer(HashMap<String, Object> param);

    /** 사용자 타이머 조회 */
    List<HashMap<String, Object>> getUserTimer(HashMap<String, Object> param);

}
