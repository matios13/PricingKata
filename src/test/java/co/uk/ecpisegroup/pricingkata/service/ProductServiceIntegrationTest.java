package co.uk.ecpisegroup.pricingkata.service;

import co.uk.ecpisegroup.pricingkata.dto.ProductDTO;
import co.uk.ecpisegroup.pricingkata.dto.ProductDTOBuilder;
import co.uk.ecpisegroup.pricingkata.model.Product;
import co.uk.ecpisegroup.pricingkata.model.ProductBuilder;
import co.uk.ecpisegroup.pricingkata.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProductServiceIntegrationTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    private static final String TEST_PRODUCT_NAME = "testProduct";
    private static final String TEST_PRODUCT_COLOR = "orange";
    private static final String TEST_PRODUCT_CATEGORY = "testCategory";
    private static final double TEST_PRODUCT_QUANTITY = 1d;
    private static final double TEST_PRODUCT_PRICE = 0d;

    @Test
    public void shouldSaveProductWithOnlyNameInDatabase() {
        //given
        String name = TEST_PRODUCT_NAME;
        //when
        ProductDTO productDTO = new ProductDTOBuilder().setName(name).createProductDTO();
        //then
        Product newProduct = productService.createProduct(productDTO);
        assertThat(newProduct.getName(), is(name));
        assertThat(productRepository.findAllByName(name), is(not(empty())));
    }

    @Test
    public void shouldSaveCompleteProduct() {
        //given
        ProductDTO productDTO = prepareTestProductDTO();
        //when
        Product newProduct = productService.createProduct(productDTO);
        //then
        checkProduct(newProduct);
        assertThat(productRepository.findAllByNameAndPriceAndQuantityAndColorAndCategory(TEST_PRODUCT_NAME, TEST_PRODUCT_PRICE, TEST_PRODUCT_QUANTITY, TEST_PRODUCT_COLOR, TEST_PRODUCT_CATEGORY), is(not(empty())));
    }

    @Test
    public void shouldFindProductStartingWithFullName(){
        //given
        prepareProductInDatabase();
        //when
        List<Product> productList = productService.findAllProductsByNameStartWith(TEST_PRODUCT_NAME);
        //then
        assertTrue(productList.stream().anyMatch(p->p.getName().equals(TEST_PRODUCT_NAME)));
    }

    @Test
    public void shouldFindProductStartingWithPartialName(){
        //given
        prepareProductInDatabase();
        //when
        List<Product> productList = productService.findAllProductsByNameStartWith("test");
        //then
        assertTrue(productList.stream().anyMatch(p->p.getName().equals(TEST_PRODUCT_NAME)));
    }

    @Test
    public void shouldFindProductBasedUponCategory(){
        //given
        prepareProductInDatabase();
        //when
        List<Product> productList = productService.findAllProductBasedUponCategory(TEST_PRODUCT_CATEGORY);
        //then
        assertTrue(productList.stream().anyMatch(p->p.getCategory().equals(TEST_PRODUCT_CATEGORY)));
    }

    private void prepareProductInDatabase() {
        Product testProduct = new ProductBuilder()
                .setName(TEST_PRODUCT_NAME)
                .setCategory(TEST_PRODUCT_CATEGORY)
                .createProduct();
        productRepository.save(testProduct);
    }

    private void checkProduct(Product newProduct) {
        assertThat(newProduct.getName(), is(TEST_PRODUCT_NAME));
        assertThat(newProduct.getPrice(), is(TEST_PRODUCT_PRICE));
        assertThat(newProduct.getQuantity(), is(TEST_PRODUCT_QUANTITY));
        assertThat(newProduct.getColor(), is(TEST_PRODUCT_COLOR));
        assertThat(newProduct.getCategory(), is(TEST_PRODUCT_CATEGORY));
    }

    private ProductDTO prepareTestProductDTO() {
        return new ProductDTOBuilder()
                    .setName(TEST_PRODUCT_NAME)
                    .setPrice(TEST_PRODUCT_PRICE)
                    .setQuantity(TEST_PRODUCT_QUANTITY)
                    .setColor(TEST_PRODUCT_COLOR)
                    .setCategory(TEST_PRODUCT_CATEGORY)
                    .createProductDTO();
    }
}