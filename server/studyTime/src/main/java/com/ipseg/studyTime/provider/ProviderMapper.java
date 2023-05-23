package com.ipseg.studyTime.provider;

import com.ipseg.studyTime.common.client.Client;
import com.ipseg.studyTime.security.User;

import java.util.HashMap;

public interface ProviderMapper {
    Client selectClient(String param);
    User selectUser(String param);
}
