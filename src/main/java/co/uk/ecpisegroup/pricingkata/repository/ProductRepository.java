package co.uk.ecpisegroup.pricingkata.repository;

import co.uk.ecpisegroup.pricingkata.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByName(String name);
    List<Product> findAllByNameStartingWith(String name);
    List<Product> findAllByNameAndPriceAndQuantityAndColorAndCategory(String name, double price, double quantity, String color, String category);
    List<Product> findAllByCategory(String category);
}
