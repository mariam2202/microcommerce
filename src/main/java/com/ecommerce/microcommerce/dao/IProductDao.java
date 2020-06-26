package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface IProductDao extends JpaRepository<Product, Integer> {

    public List<Product> findAll();

//    @Override
//    public Product findById(int id) {
//        for (Product product : findAll()) {
//            if(product.getId() ==id){
//                return product;
//            }
//        }
//        return null;
//    }


//    public Product findOneProduct(int id);


//    public Product save(Product product);
}
