package com.ajuep.webfluxcrud.dto;

public class ProductDto {
    private String id;
    private String name;
    private Double price;
    private Integer stock;

    private String idUser;

    public ProductDto() {
    }

    public ProductDto(String id, String name, Double price, Integer stock, String idUser) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
