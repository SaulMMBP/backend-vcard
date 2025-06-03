package io.github.saulmmbp.services.impl;

import org.springframework.stereotype.Service;

import io.github.saulmmbp.dtos.UserRequestDto;
import io.github.saulmmbp.entities.User;
import io.github.saulmmbp.exceptions.ResourceAlreadyExistsException;
import io.github.saulmmbp.repositories.IUserRepository;
import io.github.saulmmbp.services.IUserService;
import io.github.saulmmbp.utils.mappers.IUserMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private IUserMapper userMapper;

    @Override
    public void createUser(UserRequestDto userRequest) {
        User user = userMapper.toEntity(userRequest);
        if (userRepository.existsById(userRequest.id())) throw new ResourceAlreadyExistsException("User", userRequest.id());
        userRepository.save(user);
    }

}
