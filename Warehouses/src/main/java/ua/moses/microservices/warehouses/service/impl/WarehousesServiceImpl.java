package ua.moses.microservices.warehouses.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ua.moses.microservices.warehouses.model.Warehouse;
import ua.moses.microservices.warehouses.repository.WarehousesRepository;
import ua.moses.microservices.warehouses.service.WarehousesService;

import javax.inject.Inject;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class WarehousesServiceImpl implements WarehousesService {
    @Inject
    private WarehousesRepository warehousesRepository;

    @Override
    public List<Warehouse> getAllWarehouses(String ownerId) {
        return warehousesRepository.getAllWarehouses(ownerId);
    }

    @Override
    public Warehouse insertWarehouse(Warehouse warehouse) {
        return warehousesRepository.insert(warehouse);
    }

    @Override
    public Warehouse deleteWarehouse(Warehouse warehouse) {
        if (warehousesRepository.exists(warehouse.getId())) {
            warehouse.setDeleted(true);
            warehousesRepository.save(warehouse);
            return warehouse;
        } else {
            return null;
        }
    }

    @Override
    public Warehouse updateWarehouse(Warehouse warehouse) {
        if (warehousesRepository.exists(warehouse.getId())) {
            return warehousesRepository.save(warehouse);
        } else {
            return null;
        }
    }

    @Override
    public Warehouse getWarehouseById(String ownerId, String warehouseId) {
        return warehousesRepository.findByOwnerIdAndId(ownerId, warehouseId);
    }
}
