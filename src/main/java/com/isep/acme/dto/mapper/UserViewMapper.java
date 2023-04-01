package com.isep.acme.dto.mapper;

import org.mapstruct.Mapper;

import com.isep.acme.domain.model.User;
import com.isep.acme.dto.UserView;

@Mapper(componentModel = "spring")
public abstract class UserViewMapper {

    public abstract UserView toUserView(User user);
}
