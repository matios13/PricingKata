package co.uk.ecpisegroup.pricingkata.model;

public class ProductBuilder {
    private String name;
    private double price;
    private double quantity=0d;
    private String category;
    private String color;

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public ProductBuilder setQuantity(double quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public ProductBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public Product createProduct() {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategory(category);
        product.setColor(color);
        return product;
    }
}