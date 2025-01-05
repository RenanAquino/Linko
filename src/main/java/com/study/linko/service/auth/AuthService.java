package com.study.linko.service.auth;

import com.study.linko.dto.auth.LoginDTO;
import com.study.linko.dto.auth.RegisterDTO;

public interface AuthService {
    void register(RegisterDTO data);
    String login(LoginDTO data);
}
