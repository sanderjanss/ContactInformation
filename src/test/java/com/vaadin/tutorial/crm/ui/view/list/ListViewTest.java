package com.vaadin.tutorial.crm.ui.view.list;

import static org.junit.Assert.*;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.tutorial.crm.backend.entity.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ListViewTest {

    @Autowired
    private ListView listView;

    @Test
    public void formShownWhenContactSelected() {
        Grid<Contact> grid = listView.grid;
        Contact firstContact = getFirstItem(grid);
        ContactForm form = listView.form;
        assertFalse(form.isVisible());
        grid.asSingleSelect().setValue(firstContact);
        assertTrue(form.isVisible());
        assertEquals(firstContact, form.binder.getBean());
    }
    private Contact getFirstItem(Grid<Contact> grid) {
        return( (ListDataProvider<Contact>) grid.getDataProvider()).getItems()
                .iterator().next();
    }

}
