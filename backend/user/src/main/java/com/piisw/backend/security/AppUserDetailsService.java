package com.piisw.backend.security;

import com.piisw.backend.entity.account_activation.AccountStatus;
import com.piisw.backend.entity.user.User;
import com.piisw.backend.repository.UserRepository;
import com.piisw.backend.service.authentication.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        loginAttemptService.checkIfUsernameLocked(username);

        User user = userRepository.findByEmailAndAccountStatus(username, AccountStatus.ACTIVE).orElseThrow(() -> new UsernameNotFoundException(username));

        return new AppUserDetails(user.getId(), user.getEmail(), user.getPassword(), user.getRole(),
                true, true, true, true, List.of(mapRoleToAuthority(user)));

    }

    private SimpleGrantedAuthority mapRoleToAuthority(User user) {
        return new SimpleGrantedAuthority(user.getRole().getAuthorityRoleName());
    }
}