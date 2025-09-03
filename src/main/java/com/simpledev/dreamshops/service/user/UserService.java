package com.simpledev.dreamshops.service.user;

import com.simpledev.dreamshops.dto.*;
import com.simpledev.dreamshops.exceptions.AlreadyExistsException;
import com.simpledev.dreamshops.exceptions.ResourceNotFoundException;
import com.simpledev.dreamshops.model.User;
import com.simpledev.dreamshops.repository.UserRepository;
import com.simpledev.dreamshops.request.CreateUserRequest;
import com.simpledev.dreamshops.request.UserUpdateRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public UserDto createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException("User with email " + request.getEmail() + " already exists");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // ⚠️ bu yerda password encode qilinishi kerak!
        return convertToDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(UserUpdateRequest request, Long userId) {
        return userRepository.findById(userId).map(existingUser -> {
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());
            return userRepository.save(existingUser);
        }).map(this::convertToDto).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new ResourceNotFoundException("User not found");
                });
    }

    @Override
    public UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);

        if (user.getOrder() != null) {
            userDto.setOrders(user.getOrder().stream().map(item -> {
                return modelMapper.map(item, OrderDto.class);
            }).collect(Collectors.toList()));
        }

        if (user.getCart() != null) {
            userDto.setCart(modelMapper.map(user.getCart(), CartDto.class));

            if (user.getCart().getItems() != null) {
                userDto.getCart().setItems(
                        user.getCart().getItems().stream().map(item -> {
                            CartItemDto itemDto = modelMapper.map(item, CartItemDto.class);
                            if (item.getProduct() != null) {
                                itemDto.setProductDto(modelMapper.map(item.getProduct(), ProductDto.class));
                            }
                            return itemDto;
                        }).collect(Collectors.toSet()));
            }
        }

        return userDto;
    }
}
