package co.uk.ecpisegroup.pricingkata.dto;

import co.uk.ecpisegroup.pricingkata.model.Product;

public class ProductDTOBuilder {
    private String name;
    private double price;
    private double quantity;
    private String category;
    private String color;

    public ProductDTOBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductDTOBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public ProductDTOBuilder setQuantity(double quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductDTOBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public ProductDTOBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public ProductDTO createProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setCategory(category);
        productDTO.setColor(color);
        productDTO.setPrice(price);
        productDTO.setQuantity(quantity);
        return productDTO;

    }
}