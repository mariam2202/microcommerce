package com.ecommerce.microcommerce.service;

import com.ecommerce.microcommerce.dao.IProductDao;
import com.ecommerce.microcommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAllProduct() {
        return productDao.findAll();
    }

    public Product findUnProduct(int id) {

        for (Product product : findAllProduct()) {
            if(product.getId() ==id){
                return product;
            }
        }
        return null;
    }

    public Product addNewProduct(Product product){
//        List<Product> products = findAllProduct();
//        products.add(product);
//        return product;
        return productDao.save(product);

    }
}
