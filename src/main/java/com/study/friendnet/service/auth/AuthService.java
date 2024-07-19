package com.study.friendnet.service.auth;

import com.study.friendnet.dto.auth.LoginDTO;
import com.study.friendnet.dto.auth.RegisterDTO;

public interface AuthService {
    void register(RegisterDTO data);
    String login(LoginDTO data);
}
