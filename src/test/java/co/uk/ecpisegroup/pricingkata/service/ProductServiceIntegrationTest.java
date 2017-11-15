package co.uk.ecpisegroup.pricingkata.service;

import co.uk.ecpisegroup.pricingkata.dto.ProductDTO;
import co.uk.ecpisegroup.pricingkata.dto.ProductDTOBuilder;
import co.uk.ecpisegroup.pricingkata.model.Product;
import co.uk.ecpisegroup.pricingkata.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProductServiceIntegrationTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void shouldSaveProductInDatabase() {
        String name = "testProduct";
        ProductDTO productDTO = new ProductDTOBuilder().setName(name).createProductDTO();
        Product newProduct = productService.createProduct(productDTO);
        assertThat(newProduct.getName(), is(name));
        assertThat(productRepository.findAllByName(name), is(not(empty())));
    }

    @Test
    public void shouldCompleteProduct() {
        String name = "testProduct";
        double price = 0d;
        double quantity = 1d;
        String color = "orange";
        String category = "1";
        ProductDTO productDTO = new ProductDTOBuilder()
                .setName(name)
                .setPrice(price)
                .setQuantity(quantity)
                .setColor(color)
                .setCategory(category)
                .createProductDTO();
        Product newProduct = productService.createProduct(productDTO);
        assertThat(newProduct.getName(), is(name));
        assertThat(newProduct.getPrice(), is(price));
        assertThat(newProduct.getQuantity(), is(quantity));
        assertThat(newProduct.getColor(), is(color));
        assertThat(newProduct.getCategory(), is(category));
        assertThat(productRepository.findAllByNameAndPriceAndQuantityAndColorAndCategory(name, price, quantity, color, category), is(not(empty())));
    }
}