module inventory {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;



    opens inventory.model;
    exports inventory.model;

    opens inventory.service;
    exports inventory.service;

    opens inventory.repository;
    exports inventory.repository;



    opens inventory;
    exports inventory;

    opens inventory.controller;
    exports inventory.controller;
}