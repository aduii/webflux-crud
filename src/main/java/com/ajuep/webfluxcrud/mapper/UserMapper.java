package com.ajuep.webfluxcrud.mapper;

import com.ajuep.webfluxcrud.dto.UserDto;
import com.ajuep.webfluxcrud.entity.User;
import org.springframework.beans.BeanUtils;

public class UserMapper {
    public static UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public static User dtoToEntity(UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

}
