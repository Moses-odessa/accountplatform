package ua.moses.microservices.warehouses.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.moses.microservices.warehouses.model.Warehouse;
import ua.moses.microservices.warehouses.service.WarehousesService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "${warehouses.endpoint.url}", produces = "application/json;charset=UTF-8")
public class WarehosesController {
    @Inject
    private WarehousesService warehousesService;

    @GetMapping(value = "{ownerId}")
    public ResponseEntity<List<Warehouse>> getAllWarehouses(@PathVariable String ownerId) {
        List<Warehouse> result = warehousesService.getAllWarehouses(ownerId);
        if (result == null) {
            result = new ArrayList<>(0);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Warehouse> insertWarehous(@Valid @RequestBody Warehouse warehouse) {
        Warehouse result = warehousesService.insertWarehous(warehouse);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Warehouse> deleteWarehous(@Valid @RequestBody Warehouse warehouse) {
        Warehouse result = warehousesService.deleteWarehous(warehouse);
        return ResponseEntity.ok(result);
    }

    @PutMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Warehouse> updateWarehous(@Valid @RequestBody Warehouse warehouse) {
        Warehouse result = warehousesService.updateWarehous(warehouse);
        return ResponseEntity.ok(result);
    }

}
