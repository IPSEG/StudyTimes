package com.ipseg.studyTime.provider;

import com.ipseg.studyTime.common.client.Client;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {
    private ProviderMapper providerMapper;

    public Client selectClient(String clientId) {
        return providerMapper.selectClient(clientId);
    }
}
