package com.vaadin.tutorial.crm.ui.view.list;


import com.vaadin.tutorial.crm.backend.entity.Company;
import com.vaadin.tutorial.crm.backend.entity.Contact;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ContactFormTest {
    private List<Company> companies;
    private Contact sanderJ;
    private Company company1;
    private Company company2;

    @Before
    public void setupData() {
        companies = new ArrayList<>();
        company1 = new Company("Vaadin LTD");
        company2 = new Company("IT Mill");
        companies.add(company1);
        companies.add(company2);

        sanderJ = new Contact();
        sanderJ.setFirstName("Sander");
        sanderJ.setLastName("J");
        sanderJ.setEmail("sander@j.com");
        sanderJ.setStatus(Contact.Status.Contacted);
        sanderJ.setCompany(company1);

    }

    @Test
    public void formFieldsPopulated() {
        ContactForm form = new ContactForm(companies);
        form.setContact(sanderJ);
        assertEquals("Sander", form.firstName.getValue());
        assertEquals("J", form.lastName.getValue());
        assertEquals("sander@j.com", form.email.getValue());
        assertEquals(company1, form.company.getValue());
        assertEquals(Contact.Status.Contacted, form.status.getValue());
    }

    @Test
    public void saveEventHasCorrectValues(){
        ContactForm form = new ContactForm(companies);
        Contact contact = new Contact();
        form.setContact(contact);

        form.firstName.setValue("John");
        form.lastName.setValue("Doe");
        form.company.setValue(company1);
        form.email.setValue("john@doe.com");
        form.status.setValue(Contact.Status.Customer);

        AtomicReference<Contact> savedContactRef = new AtomicReference<>(null);
        form.addListener(ContactForm.SaveEvent.class, e -> {
            savedContactRef.set(e.getContact());
        });
        form.save.click();
        Contact savedContact = savedContactRef.get();
        assertEquals("John", savedContact.getFirstName());
        assertEquals("Doe", savedContact.getLastName());
        assertEquals("john@doe.com", savedContact.getEmail());
        assertEquals(company1, savedContact.getCompany());
        assertEquals(Contact.Status.Customer, savedContact.getStatus());
    }


}
