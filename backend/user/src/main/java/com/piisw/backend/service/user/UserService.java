package com.piisw.backend.service.user;


import com.piisw.backend.controller.dto.UserResponse;
import com.piisw.backend.entity.user.User;
import com.piisw.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse getById(Long id) {
        User user = userRepository.getById(id);
        return new UserResponse(user.getGlobalId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getAccountStatus(), user.getRole());
    }
}