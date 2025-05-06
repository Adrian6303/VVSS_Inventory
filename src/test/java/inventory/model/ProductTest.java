package inventory.model;

import org.junit.jupiter.api.Test;

import inventory.repository.InventoryRepository;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private InventoryRepository repo;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        repo = new InventoryRepository();
        inventory = new Inventory();
    }

    @Test
    void testAddPartAndGetAllParts() {
        int initialSize = repo.getAllParts().size();

        Part newPart = new InhousePart(
                repo.getAutoPartId(), "Gear", 5.99, 10, 1, 50, 1234);
        repo.addPart(newPart);

        ObservableList<Part> parts = repo.getAllParts();
        assertEquals(initialSize + 1, parts.size());
        assertTrue(parts.contains(newPart));
    }

    @Test
    void testLookupPartByName() {
        Part newPart = new OutsourcedPart(
                repo.getAutoPartId(), "Sensor", 9.99, 5, 1, 20, "ACME Corp");
        repo.addPart(newPart);

        Part found = repo.lookupPart("Sensor");
        assertNotNull(found);
        assertEquals("Sensor", found.getName());
    }

    @Test
    void testLookupProduct(){
        Product newProduct = new Product(1, "Clock", 7.45, 7, 2, 20, null);

        inventory.addProduct(newProduct);

        Product found = inventory.lookupProduct("Clock");
        assertNotNull(found);
        assertEquals("Clock", found.getName());

    }

    @Test
    void testLookupProduct2(){
        Product newProduct = new Product(1, "Clock", 7.45, 7, 2, 20, null);

        inventory.addProduct(newProduct);

        Product found2 = inventory.lookupProduct("product1");
        assertNull(found2.getName());
    }

}
