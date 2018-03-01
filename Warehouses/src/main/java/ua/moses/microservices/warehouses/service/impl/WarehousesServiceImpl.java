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
        return warehousesRepository.findByOwnerId(ownerId);
    }

    @Override
    public Warehouse insertWarehous(Warehouse warehous) {
        return warehousesRepository.insert(warehous);
    }

    @Override
    public Warehouse deleteWarehous(Warehouse warehous) {
        if (warehousesRepository.exists(warehous.getId())) {
            warehousesRepository.delete(warehous);
            return warehous;
        } else {
            return null;
        }
    }

    @Override
    public Warehouse updateWarehous(Warehouse warehous) {
        if (warehousesRepository.exists(warehous.getId())) {
            warehousesRepository.save(warehous);
            return warehous;
        } else {
            return null;
        }
    }
}
