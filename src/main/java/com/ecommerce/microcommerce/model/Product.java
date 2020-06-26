package com.ecommerce.microcommerce.model;

import com.fasterxml.jackson.annotation.JsonFilter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
//@JsonFilter("monFiltreDynamique")
public class Product {
    private int id;
    private String nom;
    private int prix;

    //constructeur par défaut
    public Product() {
    }

    //constructeur pour nos tests

    public Product(int id, String nom, int prix) {
        this.id=id;
        this.nom=nom;
        this.prix=prix;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Product_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    @Column(name = "NOM", nullable = false)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom=nom;
    }

    @Column(name = "Prix", nullable = false)
    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix=prix;
    }

    @Override
    public String toString(){
        return "Product{"+
                "id=" + id +
                ", nom='"+ nom + '\'' +
                ", prix=" + prix+ '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (prix != product.prix) return false;
        return nom != null ? nom.equals(product.nom) : product.nom == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + prix;
        return result;
    }
}


