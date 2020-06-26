package com.ecommerce.microcommerce.service;

import com.ecommerce.microcommerce.model.Product;

import java.util.List;

public interface IProductService {

    public List<Product> findAllProduct();
    public Product findUnProduct(int id);
    public Product addNewProduct(Product product);
}
