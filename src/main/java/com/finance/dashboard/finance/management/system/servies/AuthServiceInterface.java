package com.finance.dashboard.finance.management.system.servies;

import com.finance.dashboard.finance.management.system.dtos.LoginRequest;
import com.finance.dashboard.finance.management.system.dtos.LoginResponse;
import com.finance.dashboard.finance.management.system.dtos.RegisteRequestDto;

public interface AuthServiceInterface {
    String register(RegisteRequestDto request);

    LoginResponse login(LoginRequest request);
}
