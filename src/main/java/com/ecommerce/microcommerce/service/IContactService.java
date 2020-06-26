package com.ecommerce.microcommerce.service;

import com.ecommerce.microcommerce.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IContactService {

    public List<Contact> findAllContact();
    public Contact findOneContact(Long id);
    public Contact addNewContact(Contact contact);
    public Contact updateContact(Long id, Contact contact);
    public void deleteContact(Long id);
    public Page<Contact> findContact(String nom, PageRequest pageRequest);
    public List<Contact> findOneContactParNom(String nom);

}
