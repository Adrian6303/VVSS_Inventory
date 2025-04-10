package inventory;

import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;
import inventory.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Step2 {
    private InventoryService service;
    private InventoryRepository realRepository;
    private Product mockProduct;
    private Part mockPart;

    @BeforeEach
    void setUp() {
        realRepository = new InventoryRepository();
        service = new InventoryService(realRepository);

        // Setup mocks for entities
        mockProduct = mock(Product.class);
        when(mockProduct.getName()).thenReturn("Mock Product");
        when(mockProduct.getProductId()).thenReturn(999);

        mockPart = mock(Part.class);
        when(mockPart.getName()).thenReturn("Mock Part");
        when(mockPart.getPartId()).thenReturn(999);
    }

    @Test
    void testAddProductIntegration() {
        // Arrange
        ObservableList<Part> parts = FXCollections.observableArrayList();

        // Act
        service.addProduct("Test Product", 100.0, 5, 1, 10, parts);

        // Assert
        Product found = service.lookupProduct("Test Product");
        assertNotNull(found);
        assertEquals("Test Product", found.getName());
        assertEquals(0, found.getAssociatedParts().size()); // No parts added
    }

    @Test
    void testPartLookupIntegration() {
        // Arrange
        realRepository.addPart(new InhousePart(1, "Test Part", 10.0, 5, 1, 10, 123));

        // Act
        Part found = service.lookupPart("Test Part");

        // Assert
        assertNotNull(found);
        assertEquals("Test Part", found.getName());
        assertEquals(10.0, found.getPrice());
    }
}