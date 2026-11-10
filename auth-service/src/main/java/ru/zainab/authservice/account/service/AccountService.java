package ru.zainab.authservice.account.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zainab.authservice.account.dto.RegisterRequest;
import ru.zainab.authservice.account.dto.RegisterResponse;
import ru.zainab.authservice.account.entity.AuthAccount;
import ru.zainab.authservice.account.mapper.AuthAccountMapper;
import ru.zainab.authservice.account.repository.AuthAccountRepository;
import ru.zainab.authservice.exception.EmailAlreadyExistsException;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AuthAccountRepository authAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthAccountMapper authAccountMapper;

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        String normalizedEmail = request.email().trim().toLowerCase(Locale.ROOT);
        if (authAccountRepository.existsByEmail(normalizedEmail)) {
            throw new EmailAlreadyExistsException("Email already exists! ");
        }
        AuthAccount account = new AuthAccount(normalizedEmail,
                passwordEncoder.encode(request.password()));
        AuthAccount savedAccount = authAccountRepository.save(account);
        return authAccountMapper.toResponse(savedAccount);

    }


}
