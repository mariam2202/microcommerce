package com.ecommerce.microcommerce.DTO;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Product Model")
public class ProductDTO implements Comparable<ProductDTO>{
    @ApiModelProperty(value = "Product id")
    private Integer id;

    @ApiModelProperty(value = "Nom Product")
    private String nom;

    @ApiModelProperty(value = "Prix Product")
    private int prix;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public ProductDTO() {

    }


    public ProductDTO(Integer id, String nom, int prix) {
        super();
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }


    @Override
    public int compareTo(ProductDTO o) {
        return nom.compareToIgnoreCase(o.nom);
    }
}
