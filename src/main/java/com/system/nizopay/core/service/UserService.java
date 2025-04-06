package com.system.nizopay.core.service;

import com.system.nizopay.core.exception.AlreadyExistsException;
import com.system.nizopay.core.model.Account;
import com.system.nizopay.core.model.User;
import com.system.nizopay.core.repository.AccountRepository;
import com.system.nizopay.core.repository.UserRepository;
import com.system.nizopay.http.rest.dto.UserDto;
import com.system.nizopay.persistence.orm.mapper.AccountMapper;
import com.system.nizopay.persistence.orm.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public void createUser(UserDto userDto) {
        var userExists = this.userRepository.existsByEmail(userDto.email());
        if (userExists) {
            throw new AlreadyExistsException("Email already registered");
        }
        var userCreated = this.userRepository.save(UserMapper.toEntity(User.create(userDto.fullName(),userDto.email())));
        this.accountRepository.save(AccountMapper.toEntity(Account.create(userCreated.getUserId())));
    }
}
