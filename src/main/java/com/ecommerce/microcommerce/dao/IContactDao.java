package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface IContactDao extends JpaRepository<Contact, Long> {

    @Query("select c from Contact c where c.nom like :x")
    public Page<Contact> chercherContact (@Param("x") String nom, Pageable pageable);

    @Query("select c from Contact c where c.nom like :x")
    public List<Contact> chercherContactParNom (@Param("x") String nom);

}
