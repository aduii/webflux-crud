package com.ajuep.webfluxcrud.mapper;

import com.ajuep.webfluxcrud.dto.RoleDto;
import com.ajuep.webfluxcrud.entity.Role;
import org.springframework.beans.BeanUtils;

public class RoleMapper {
    public static RoleDto entityToDto(Role role){
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(role, roleDto);
        return roleDto;
    }

    public static Role dtoToEntity(RoleDto roleDto){
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        return role;
    }
}
