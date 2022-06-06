package com.piisw.backend.controller.user;

import com.piisw.backend.controller.BaseController;
import com.piisw.backend.controller.dto.UserResponse;
import com.piisw.backend.security.AuthenticationContextService;
import com.piisw.backend.security.AuthorizationConstants;
import com.piisw.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class UserController extends BaseController {
    private final UserService userService;
    private final AuthenticationContextService authenticationContextService;

    @PreAuthorize(AuthorizationConstants.HAS_ANY_USER_ROLE)
    @GetMapping("/@me")
    public UserResponse getCurrent() {
        Long userId = authenticationContextService.getCurrentUserId();
        return userService.getById(userId);
    }
}