package com.tconecta.loyalty.api.service;

import com.tconecta.loyalty.api.model.UserRequest;
import com.tconecta.loyalty.api.model.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse userCreate (UserRequest userRequest);

    UserResponse getUserById (Long userId);

    UserResponse getUserByName (String userName);

    List<UserResponse> getAllUser ();
}
