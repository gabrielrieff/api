package com.application.usecases;

import com.domain.user.dto.RegisterUserDTO;
import com.domain.user.dto.UserDetailDTO;

public interface IUserUseCases {
    UserDetailDTO create(RegisterUserDTO data);
}
