package ua.moses.microservices.warehouses.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.moses.microservices.warehouses.model.Warehouse;
import ua.moses.microservices.warehouses.service.WarehousesService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "${warehouses.endpoint.url}")
public class WarehosesController {
    @Inject
    private WarehousesService warehousesService;

    @GetMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> result = warehousesService.getAllWarehouses();
        return ResponseEntity.ok(result);
    }

    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Warehouse> insertWarehous(@Valid @RequestBody Warehouse warehous) {
        Warehouse result = warehousesService.insertWarehous(warehous);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Warehouse> deleteWarehous(@Valid @RequestBody Warehouse warehous) {
        Warehouse result = warehousesService.deleteWarehous(warehous);
        return ResponseEntity.ok(result);
    }

    @PutMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Warehouse> updateWarehous(@Valid @RequestBody Warehouse warehous) {
        Warehouse result = warehousesService.updateWarehous(warehous);
        return ResponseEntity.ok(result);
    }

}
