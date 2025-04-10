package inventory.model;

import inventory.model.Part;
import inventory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class InventoryTest {
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    @Test
    void testAddAndLookupPart() {
        Part part = new Part(1, "Test Part", 10.0, 5, 1, 10) {};
        inventory.addPart(part);

        Part found = inventory.lookupPart("Test Part");
        assertNotNull(found);
        assertEquals(part, found);

        found = inventory.lookupPart("1");
        assertNotNull(found);
        assertEquals(part, found);
    }

    @Test
    void testAddAndLookupProduct() {
        ObservableList<Part> parts = FXCollections.observableArrayList();
        Product product = new Product(1, "Test Product", 30.0, 5, 1, 10, parts);
        inventory.addProduct(product);

        Product found = inventory.lookupProduct("Test Product");
        assertNotNull(found);
        assertEquals(product, found);

        found = inventory.lookupProduct("1");
        assertNotNull(found);
        assertEquals(product, found);
    }

    @Test
    void testAutoIdGeneration() {
        assertEquals(1, inventory.getAutoPartId());
        assertEquals(2, inventory.getAutoPartId());

        assertEquals(1, inventory.getAutoProductId());
        assertEquals(2, inventory.getAutoProductId());
    }
}