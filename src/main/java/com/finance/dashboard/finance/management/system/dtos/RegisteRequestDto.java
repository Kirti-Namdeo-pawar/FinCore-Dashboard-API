package com.finance.dashboard.finance.management.system.dtos;

import com.finance.dashboard.finance.management.system.entities.RoleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisteRequestDto {
    private String username;
    private String email;
    private String password;
    private RoleType role;
}
