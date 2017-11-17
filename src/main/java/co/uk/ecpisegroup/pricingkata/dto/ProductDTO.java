package co.uk.ecpisegroup.pricingkata.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

public class ProductDTO {


    @NotEmpty
    private String name;
    @Min(value = 0L)
    private double price;
    @Min(value = 0L)
    private double quantity;
    private String category;
    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
