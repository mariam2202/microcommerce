package com.ecommerce.microcommerce.service;

import com.ecommerce.microcommerce.dao.IContactDao;
import com.ecommerce.microcommerce.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("contactService")
@Transactional
public class ContactServiceImpl implements IContactService {

    @Autowired
    private IContactDao contactDao;

    @Override
    public List<Contact> findAllContact() {
        return contactDao.findAll();
    }

    @Override
    public Contact findOneContact(Long id) {

        for (Contact contact : contactDao.findAll()) {
            if(contact.getId() == id) {
                return contact;
            }
        }
        return null;
        //return contactDao.getOne(id);
    }

    @Override
    public Contact addNewContact(Contact contact) {
        return contactDao.save(contact);
    }

    @Override
    public Contact updateContact(Long id, Contact contact) {
        contact.setId(id);
        return contactDao.save(contact);

    }

    @Override
    public void deleteContact(Long id) {
        for (Contact contact : contactDao.findAll()) {
            if (contactDao.existsById(id) && contact.getId() == id) {
                contactDao.deleteById(id);
            }
        }
    }

    @Override
    public Page<Contact> findContact(String nom, PageRequest pageRequest) {
        return contactDao.chercherContact(nom, pageRequest);
    }

    @Override
    public List<Contact> findOneContactParNom(String nom) {
        return contactDao.chercherContactParNom(nom);
    }


}
