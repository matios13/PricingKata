package co.uk.ecpisegroup.pricingkata.controller;

import co.uk.ecpisegroup.pricingkata.dto.ProductDTO;
import co.uk.ecpisegroup.pricingkata.model.Product;
import co.uk.ecpisegroup.pricingkata.model.ProductBuilder;
import co.uk.ecpisegroup.pricingkata.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @RequestMapping(value = "/sample-product",method = RequestMethod.GET)
    public Product getSampleTestProductWithName(@RequestParam(defaultValue = "defaultName") String name){
        return new ProductBuilder().setName(name).createProduct();
    }

    @RequestMapping(value = "/create-product",method = RequestMethod.POST)
    public Product createProduct(@RequestBody @Valid ProductDTO productDTO){
        return productService.createProduct(productDTO);
    }

    @RequestMapping(value = "/search-product/name", method = RequestMethod.GET)
    public List<Product> findAllProductsWithName(@RequestParam String name){
        return productService.findAllProductsByNameStartWith(name);
    }

    @RequestMapping(value = "/search-product/category", method = RequestMethod.GET)
    public List<Product> findAllProductBasedUponCategory(@RequestParam String category){
        return productService.findAllProductBasedUponCategory(category);
    }

    @RequestMapping(value = "/search-product/category/{category}", method = RequestMethod.GET)
    public List<Product> findAllProductBasedUponCategory2(@PathVariable String category){
        return productService.findAllProductBasedUponCategory(category);
    }


}


