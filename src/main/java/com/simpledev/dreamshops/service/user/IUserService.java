package com.simpledev.dreamshops.service.user;

import com.simpledev.dreamshops.dto.UserDto;
import com.simpledev.dreamshops.model.User;
import com.simpledev.dreamshops.request.CreateUserRequest;
import com.simpledev.dreamshops.request.UserUpdateRequest;

public interface IUserService {

    User getUserById(Long userId);
    UserDto createUser(CreateUserRequest request);
    UserDto updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertToDto(User user);

    User getAuthenticatedUser();
}
