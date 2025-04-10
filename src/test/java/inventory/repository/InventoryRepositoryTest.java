package inventory.repository;

import inventory.model.Inventory;
import inventory.model.Part;
import inventory.model.Product;
import inventory.repository.InventoryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryRepositoryTest {
    private InventoryRepository repository;
    private Inventory mockInventory;

    @BeforeEach
    void setUp() {
        mockInventory = Mockito.mock(Inventory.class);
        repository = new InventoryRepository();
        repository.setInventory(mockInventory);
    }


    @Test
    void testLookupPart() {
        when(mockInventory.lookupPart("Test Part")).thenReturn(
                new Part(1, "Test Part", 10.0, 5, 1, 10) {}
        );

        Part found = repository.lookupPart("Test Part");
        assertNotNull(found);
        assertEquals("Test Part", found.getName());
    }

    @Test
    void testGetAllParts() {
        ObservableList<Part> expectedParts = FXCollections.observableArrayList();
        when(mockInventory.getAllParts()).thenReturn(expectedParts);

        ObservableList<Part> actualParts = repository.getAllParts();
        assertSame(expectedParts, actualParts);
    }
}