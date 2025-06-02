package io.github.saulmmbp.services;

import io.github.saulmmbp.dtos.UserRequestDto;

public interface IUserService {

    void createUser(UserRequestDto userRequest);

}
