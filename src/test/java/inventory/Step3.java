package inventory;

import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;
import inventory.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Step3 {


    private InventoryService service;
    private InventoryRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InventoryRepository(false);
        service = new InventoryService(repository);
    }

    @Test
    void testCompleteProductLifecycle() {
        // Create parts
        service.addInhousePart("Wheel", 25.0, 10, 1, 100, 101);
        service.addInhousePart("Frame", 50.0, 5, 1, 50, 102);

        // Get created parts
        Part wheel = service.lookupPart("Wheel");
        Part frame = service.lookupPart("Frame");

        // Create product
        ObservableList<Part> bikeParts = FXCollections.observableArrayList();
        bikeParts.add(wheel);
        bikeParts.add(frame);
        service.addProduct("Bicycle", 200.0, 5, 1, 10, bikeParts);

        // Verify
        Product bike = service.lookupProduct("Bicycle");
        assertNotNull(bike);
        assertEquals(2, bike.getAssociatedParts().size());
        assertEquals(200.0, bike.getPrice());
    }

    @Test
    void testInventoryAutoIncrement() {
        // First product
        service.addProduct("Product 1", 10.0, 5, 1, 10, FXCollections.observableArrayList());
        Product p1 = service.lookupProduct("Product 1");
        assertEquals(1, p1.getProductId());

        // Second product
        service.addProduct("Product 2", 20.0, 5, 1, 10, FXCollections.observableArrayList());
        Product p2 = service.lookupProduct("Product 2");
        assertEquals(2, p2.getProductId());

        // Verify part IDs increment separately
        service.addInhousePart("Part 1", 5.0, 5, 1, 10, 101);
        Part part = service.lookupPart("Part 1");
        assertEquals(1, part.getPartId());
    }
}