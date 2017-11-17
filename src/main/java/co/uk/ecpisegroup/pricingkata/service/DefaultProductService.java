package co.uk.ecpisegroup.pricingkata.service;

import co.uk.ecpisegroup.pricingkata.dto.ProductDTO;
import co.uk.ecpisegroup.pricingkata.model.Product;
import co.uk.ecpisegroup.pricingkata.model.ProductBuilder;
import co.uk.ecpisegroup.pricingkata.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductService implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product newProduct = new ProductBuilder()
                .setName(productDTO.getName())
                .setCategory(productDTO.getCategory())
                .setColor(productDTO.getColor())
                .setPrice(productDTO.getPrice())
                .setQuantity(productDTO.getQuantity())
                .createProduct();
        return productRepository.save(newProduct);
    }

    @Override
    public List<Product> findAllProductsByNameStartWith(String name){
        return productRepository.findAllByNameStartingWith(name);
    }

    @Override
    public List<Product> findAllProductBasedUponCategory(String category) {
        return productRepository.findAllByCategory(category);
    }
}
