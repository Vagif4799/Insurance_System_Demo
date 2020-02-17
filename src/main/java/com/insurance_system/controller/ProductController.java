package com.insurance_system.controller;


import com.insurance_system.model.Company;
import com.insurance_system.model.Product;
import com.insurance_system.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Iterable<Product> getAllProducts() {
        LOGGER.info("Inside of getAllProducts() method");
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        LOGGER.info("getProductById() is invoked by id: " + id);
        return productService.getProductById(id);
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        LOGGER.info("createProduct() is invoked by product: " + product);
        productService.createProduct(product);
    }

    @PutMapping("{id}")
    public Product updateProduct(@RequestBody Product product,@PathVariable("id") Long id) {
        LOGGER.info("updateProduct() is invoked by product: {} and id: {}", product, id);
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        LOGGER.info("The product with id: {} deleted by deleteProduct() method", id);
    }

}
