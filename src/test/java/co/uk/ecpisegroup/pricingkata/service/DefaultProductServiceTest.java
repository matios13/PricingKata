package co.uk.ecpisegroup.pricingkata.service;

import co.uk.ecpisegroup.pricingkata.dto.ProductDTO;
import co.uk.ecpisegroup.pricingkata.dto.ProductDTOBuilder;
import co.uk.ecpisegroup.pricingkata.model.Product;
import co.uk.ecpisegroup.pricingkata.model.ProductBuilder;
import co.uk.ecpisegroup.pricingkata.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultProductServiceTest {

    private static final String PROPERTY_CATEGORY = "category";
    @InjectMocks
    private ProductService productService = new DefaultProductService();

    @Mock
    private ProductRepository productRepository;

    private static final String PROPERTY_NAME = "name";
    private static final String TEST_PRODUCT_NAME = "testProduct";
    private static final String TEST_PRODUCT_COLOR = "orange";
    private static final String TEST_PRODUCT_CATEGORY = "testCategory";
    private static final double TEST_PRODUCT_QUANTITY = 1d;
    private static final double TEST_PRODUCT_PRICE = 0d;

    @Test
    public void shouldCreateProductWithName() {
        //given
        ProductDTO productDTO = prepareTestProductDTO();
        when(productRepository.save(any(Product.class))).then(m-> m.getArguments()[0]);
        //when
        Product result = productService.createProduct(productDTO);
        //then
        checkProduct(result);
    }


    @Test
    public void shouldFindProductStartingWithFullName(){
        //given
        List<Product> something = Collections.singletonList(new ProductBuilder().setName(TEST_PRODUCT_NAME).createProduct());
        when(productRepository.findAllByNameStartingWith(TEST_PRODUCT_NAME)).thenReturn(something);
        //when
        List<Product> productList = productService.findAllProductsByNameStartWith(TEST_PRODUCT_NAME);
        //then
        assertThat(productList,contains(hasProperty(PROPERTY_NAME,is(TEST_PRODUCT_NAME))));
    }

    @Test
    public void shouldFindProductStartingWithPartialName(){
        //given
        List<Product> something = Collections.singletonList(new ProductBuilder().setName(TEST_PRODUCT_NAME).createProduct());
        when(productRepository.findAllByNameStartingWith(eq("test"))).thenReturn(something);
        //when
        List<Product> productList = productService.findAllProductsByNameStartWith("test");
        //then
        assertThat(productList,contains(hasProperty(PROPERTY_NAME,is(TEST_PRODUCT_NAME))));
    }

    @Test
    public void shouldFindProductBasedUponCategory(){
        //given
        List<Product> something = Collections.singletonList(new ProductBuilder().setCategory(TEST_PRODUCT_CATEGORY).createProduct());
        when(productRepository.findAllByCategory(eq(TEST_PRODUCT_CATEGORY))).thenReturn(something);
        //when
        List<Product> productList = productService.findAllProductBasedUponCategory(TEST_PRODUCT_CATEGORY);
        //then
        assertThat(productList,contains(hasProperty(PROPERTY_CATEGORY,is(TEST_PRODUCT_CATEGORY))));
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