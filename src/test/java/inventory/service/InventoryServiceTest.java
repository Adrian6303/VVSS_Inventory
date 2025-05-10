package inventory.service;

import inventory.model.Part;
import inventory.model.Product;
import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryServiceTest {
    private InventoryService service;
    private InventoryRepository mockRepo;

    @BeforeEach
    void setUp() {
        mockRepo = Mockito.mock(InventoryRepository.class);
        service = new InventoryService(mockRepo);
    }
    
    @Test
    void testService(){
        when(mockRepo.getAutoPartId()).thenReturn(1);

        service.addInhousePart("Gear", 10.0, 5, 1, 10, 123);

        verify(mockRepo, times(1)).addPart(any());
        verify(mockRepo, times(1)).getAutoPartId();

        ObservableList<Part> parts = FXCollections.observableArrayList();
        Product expectedProduct = new Product(1, "Bicycle", 100.0, 5, 1, 10, parts);
        when(mockRepo.lookupProduct("Bicycle")).thenReturn(expectedProduct);

        Product found = service.lookupProduct("Bicycle");
        assertNotNull(found);
        assertEquals("Bicycle", found.getName());

        ObservableList<Part> parts2 = FXCollections.observableArrayList();
        service.updateProduct(0, 1, "Updated", 150.0, 5, 1, 10, parts2);

        verify(mockRepo, times(1)).updateProduct(eq(0), any(Product.class));


    }
}