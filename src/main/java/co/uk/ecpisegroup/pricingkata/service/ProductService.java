package co.uk.ecpisegroup.pricingkata.service;

import co.uk.ecpisegroup.pricingkata.dto.ProductDTO;
import co.uk.ecpisegroup.pricingkata.model.Product;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
}
