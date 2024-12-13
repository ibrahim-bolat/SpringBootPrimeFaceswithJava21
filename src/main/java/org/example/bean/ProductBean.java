package org.example.bean;

import jakarta.annotation.PostConstruct;
import org.example.entity.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;


@Component
@Scope("session")
public class ProductBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private ProductService productService;

    private List<Product> products;
    private Product product;


    @PostConstruct
    public void init() {
        onlLoad();
    }

    public void onlLoad() {
        products = productService.getAllProducts();
    }

    public void saveProduct() {
        if(product != null) {
            productService.saveProduct(product);
            product = new Product();
            products = productService.getAllProducts();
        }
    }

    public void editProduct(Product product) {
        this.product = product;
    }

    public void deleteProduct(Long productId) {;
        if(productId != null) {
            productService.deleteProduct(productId);
            product = new Product();
            products = productService.getAllProducts();
        }
    }
    public void refresh() {
        product = new Product();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}