package ru.zainab.authservice.account.mapper;

import org.mapstruct.Mapper;
import ru.zainab.authservice.account.dto.RegisterRequest;
import ru.zainab.authservice.account.dto.RegisterResponse;
import ru.zainab.authservice.account.entity.AuthAccount;

@Mapper(componentModel = "spring")
public interface AuthAccountMapper {
    RegisterResponse toResponse(AuthAccount authAccount);


}
