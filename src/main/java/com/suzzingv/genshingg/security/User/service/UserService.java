package com.suzzingv.genshingg.security.User.service;

import com.suzzingv.genshingg.security.User.User;
import com.suzzingv.genshingg.security.User.UserRepository;
import com.suzzingv.genshingg.security.User.dto.AddUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest req) {
        return userRepository.save(User.builder()
                .email(req.email())
                .password(bCryptPasswordEncoder.encode(req.password()))
                .build()).getId();
    }
}
