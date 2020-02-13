package com.insurance_system.service;

import com.insurance_system.bean.NullAwareBeanUtilsBean;
import com.insurance_system.model.Product;
import com.insurance_system.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product){
        productRepository.save(product);
        return product;
    }


    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Product product, Long id) {
        Optional<Product> old_product = productRepository.findById(id);
        old_product.ifPresent(
                p -> {
                    NullAwareBeanUtilsBean bean = new NullAwareBeanUtilsBean();
                    try {
                        bean.copyProperties(p, product);
                        if (product.getName() != null) p.setName(product.getName());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    productRepository.save(p);
                });
        return getProductById(id).get();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
