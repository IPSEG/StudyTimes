package com.ipseg.studyTime.provider;

import com.ipseg.studyTime.common.client.Client;

import java.util.HashMap;

public interface ProviderMapper {
    Client selectClient(String param);
}
