package com.ecommerce.microcommerce.web.controller;


import com.ecommerce.microcommerce.model.Contact;
import com.ecommerce.microcommerce.service.IContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@Api(description = "Gestion de contact")
@RestController
@CrossOrigin("*")
public class ContactController {
    @Autowired
    private IContactService contactService;

    @RequestMapping(value="/Contacts", method= RequestMethod.GET)
    @ApiOperation(value = "liste des contacts")
    public List<Contact> listContacts() {
        return contactService.findAllContact();
    }

    @RequestMapping(value="/Contacts/{id}", method= RequestMethod.GET)
    @ApiOperation(value = "lire un contact")
    public Contact afficheContact(@PathVariable Long id) {
        Contact contact = contactService.findOneContact(id);
        if (contact == null)  {
            throw new IllegalArgumentException("existe pas le contact");
        }
        return contact;
    }

    @PostMapping("/AddContact")
    public Contact ajoutContact(@RequestBody Contact contact) {
        return contactService.addNewContact(contact);
    }

    @RequestMapping(value="/DeleteContacts/{id}", method= RequestMethod.DELETE)
    @ApiOperation(value = "Supprimer un contact")
    public void supprimerContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }

   @RequestMapping(value = "/UpdateContact/{id}", method = RequestMethod.PUT)
   @ApiOperation(value = "Modifier un Contact")
    public Contact modifierContact(@PathVariable Long id, @RequestBody Contact contact) {
        return contactService.updateContact(id, contact);
    }

    @RequestMapping(value="/ChercherContacts", method= RequestMethod.GET)
    @ApiOperation(value = "lire un contact")
    public Page<Contact> afficheContact(
            @RequestParam(name="monContact", defaultValue = "") String monContact,
            @RequestParam(name="page", defaultValue = "0") int page,
            @RequestParam(name="size", defaultValue = "5") int size) {

        return contactService.findContact("%" + monContact + "%", PageRequest.of(page, size));

    }
    @RequestMapping(value="/ChercherParNomContacts", method= RequestMethod.GET)
    @ApiOperation(value = "Chercher par nom un contact")
    public List<Contact> afficheContact(
            @RequestParam(name="monContact", defaultValue = "") String monContact) {

        List<Contact> contact = contactService.findOneContactParNom("%" + monContact + "%");
        if (contact == null)  {
            throw new IllegalArgumentException("existe pas le contact");
        }
        return contact;
    }

}
