package ua.moses.microservices.warehouses.service;

import ua.moses.microservices.warehouses.model.Warehouses;

import java.util.List;

public interface WarehousesService {
    List<Warehouses> getAllWarehouses();

    Warehouses insertWarehous(Warehouses warehous);

    Warehouses deleteWarehous(Warehouses warehous);

    Warehouses updateWarehous(Warehouses warehous);
}
