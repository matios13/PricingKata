package co.uk.ecpisegroup.pricingkata.service;

import co.uk.ecpisegroup.pricingkata.dto.ProductDTO;
import co.uk.ecpisegroup.pricingkata.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);

    List<Product> findAllProductsByNameStartWith(String name);

    List<Product> findAllProductBasedUponCategory(String category);
}
