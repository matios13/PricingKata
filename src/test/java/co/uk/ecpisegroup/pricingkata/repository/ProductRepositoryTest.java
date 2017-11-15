package co.uk.ecpisegroup.pricingkata.repository;

import co.uk.ecpisegroup.pricingkata.model.Product;
import co.uk.ecpisegroup.pricingkata.model.ProductBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Before
    public void setup() {
        Product product = new ProductBuilder().setName("test").createProduct();
        product.setId(1L);
        productRepository.save(product);
    }

    @Test
    public void shouldFindProductInDatabse() {
        assertNotNull(productRepository.findOne(1L));
    }

    @Test
    public void shouldFindOnlyOneProductInDatabse() {
        assertThat(productRepository.count(), is(1L));
    }

    @Test
    public void shouldNotFindProductInDatabse() {
        assertNull(productRepository.findOne(2L));
    }

}