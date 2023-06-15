package com.ipseg.studyTime.repository.timer;

import java.util.HashMap;
import java.util.List;

public interface TimerRepository {
    int addTimer(HashMap<String, Object> param);

    int modifyTimer(HashMap<String, Object> param);

    List<HashMap<String, Object>> getUserTimer(HashMap<String, Object> param);

    int deleteTimer(HashMap<String, Object> param);
}
