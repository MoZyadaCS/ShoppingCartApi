package com.eg.swa.ntier.shopping.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDto {
    @NotNull(message = "product name cannot be null")
    @NotBlank(message =  "product name cannot be blank")
    private String name;
    @Min(value = 0,message = "quantity cannot be less than 0")
    private Integer quantity;
    @Min(value = 0,message = "price cannot be less than 0")
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
