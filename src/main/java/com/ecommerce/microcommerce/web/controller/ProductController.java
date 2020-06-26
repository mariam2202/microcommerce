package com.ecommerce.microcommerce.web.controller;
import com.ecommerce.microcommerce.DTO.ProductDTO;
import com.ecommerce.microcommerce.dao.IProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.service.ProductServiceImpl;
import com.ecommerce.microcommerce.web.exception.ProduitIntrouvableException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;

@Api(description = "Gestion de produit")
@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private IProductDao productDao;

    @RequestMapping(value="/Produits", method= RequestMethod.GET)
    @ApiOperation(value = "liste des produits")
    public List<Product> listeProduits() {
        return productService.findAllProduct();
    }

    //Récupérer la liste des produits
//    @RequestMapping(value = "/Produitses", method = RequestMethod.GET)
//    public MappingJacksonValue listeProduits2() {
//
//        List<Product> produits = productService.findAllProduct();
//
//        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prix");
//
////        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);
//
//        MappingJacksonValue produitsFiltres = new MappingJacksonValue(produits);
//
//        produitsFiltres.setFilters(listDeNosFiltres);
//
//        return produitsFiltres;
//    }

    //@RequestMapping(value = "/Produits/{id}", method = RequestMethod.GET)  --meme chose
    @GetMapping(value="/Produits/{id}")
    @ApiOperation(value = "récupere le produit à partir de son id")
    public Product afficherUnProduit(@PathVariable int id) {
//       Product product =new Product(id, new String("Aspirateur"), 100 );
        //return productService.findUnProduct(id);

        Product produit = productService.findUnProduct(id);

        if(produit==null) throw new ProduitIntrouvableException("Le produit avec l'id " + id + " est INTROUVABLE. Écran Bleu si je pouvais.");

        return produit;
    }

    @PostMapping("/addProduct")
    @ApiOperation(value = "Add a new Product", response = ProductDTO.class)
//    @ApiResponses(value = { @ApiResponse(code = 409, message = "Conflict: the loan already exist"),
//            @ApiResponse(code = 201, message = "Created: the product is successfully inserted"),
//            @ApiResponse(code = 304, message = "Not Modified: the product is unsuccessfully inserted") })
    public Product createNewProduct(@RequestBody Product product) {
//        productDao.save(product);
        return productService.addNewProduct(product);

    }
    @PostMapping("/New")
    public ResponseEntity<Void> ajouterProduit(@RequestBody Product product) {

        Product productAdded =  productService.addNewProduct(product);

        if (productAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}

