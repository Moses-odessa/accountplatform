package ua.moses.microservices.warehouses.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.moses.microservices.warehouses.model.Warehouses;
import ua.moses.microservices.warehouses.service.WarehousesService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/warehouses", produces = "application/json;charset=UTF-8")
public class WarehosesController {
    @Inject
    private WarehousesService warehousesService;

    @GetMapping
    public ResponseEntity<List<Warehouses>> getAllWarehouses() {
        List<Warehouses> result = warehousesService.getAllWarehouses();
        return ResponseEntity.ok(result);
    }

    @PutMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Warehouses> insertWarehous(@Valid @RequestBody Warehouses warehous) {
        Warehouses result = warehousesService.insertWarehous(warehous);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Warehouses> deleteWarehous(@Valid @RequestBody Warehouses warehous) {
        Warehouses result = warehousesService.deleteWarehous(warehous);
        return ResponseEntity.ok(result);
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Warehouses> updateWarehous(@Valid @RequestBody Warehouses warehous) {
        Warehouses result = warehousesService.updateWarehous(warehous);
        return ResponseEntity.ok(result);
    }

}
