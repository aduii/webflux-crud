package com.ajuep.webfluxcrud.dto;

public class RoleDto {
    private String id;
    private String name;
    private String idUser;

    public RoleDto() {
    }

    public RoleDto(String id, String name, String idUser) {
        this.id = id;
        this.name = name;
        this.idUser = idUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
