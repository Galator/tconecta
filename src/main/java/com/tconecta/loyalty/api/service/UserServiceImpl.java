package com.tconecta.loyalty.api.service;

import com.tconecta.loyalty.api.entity.UserEntity;
import com.tconecta.loyalty.api.model.UserRequest;
import com.tconecta.loyalty.api.model.UserResponse;
import com.tconecta.loyalty.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse userCreate(UserRequest userRequest) {

        UserEntity userEntitySave = userRepository.save(converterRequestToEntity (userRequest));

        return converterEntityToResponse(userEntitySave);
    }

    @Override
    public UserResponse getUserById(Long userId) {

        return userRepository.getByUserId(userId).map(userEntity -> {
            return UserResponse.builder()
                    .name(userEntity.getUserName())
                    .id(userEntity.getUserId())
                    .build();
        }).orElse(null);
    }

    @Override
    public UserResponse getUserByName(String userName) {
        return userRepository.getByUserName(userName).map(userEntity -> {
            return UserResponse.builder()
                    .name(userEntity.getUserName())
                    .id(userEntity.getUserId())
                    .build();
        }).orElse(null);
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<UserResponse> userResponseList = new ArrayList<>();

        userRepository.findAll().forEach(userEntity -> userResponseList.add(converterEntityToResponse(userEntity)));

        return userResponseList;
    }

    private UserEntity converterRequestToEntity (UserRequest userRequest) {
        return UserEntity.builder()
                .userName(userRequest.getUserName().trim().toUpperCase())
                .userPsw(userRequest.getUserPassword())
                .build();
    }

    private UserResponse converterEntityToResponse (UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getUserId())
                .name(userEntity.getUserName())
                .build();
    }
}
