package com.ipseg.studyTime.security.service;

import com.ipseg.studyTime.common.client.Client;
import com.ipseg.studyTime.provider.ProviderMapper;
import com.ipseg.studyTime.security.User;
import org.springframework.stereotype.Service;

@Service
public class StudyPlatformService {
    private ProviderMapper providerMapper;

    public Client selectClient(String clientId) {
        return providerMapper.selectClient(clientId);
    }

    public User selectUser(String userId) {return providerMapper.selectUser(userId);}
}
