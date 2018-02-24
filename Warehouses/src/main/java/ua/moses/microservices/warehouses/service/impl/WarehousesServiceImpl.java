package ua.moses.microservices.warehouses.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.moses.microservices.warehouses.model.Warehouses;
import ua.moses.microservices.warehouses.repository.WarehousesRepository;
import ua.moses.microservices.warehouses.service.WarehousesService;

import javax.inject.Inject;
import java.util.List;

@Service
@AllArgsConstructor
public class WarehousesServiceImpl implements WarehousesService {
    @Inject
    private WarehousesRepository warehousesRepository;

    public List<Warehouses> getAllWarehouses() {
        return warehousesRepository.findAll();

    }

    @Override
    public Warehouses insertWarehous(Warehouses warehous) {
        return warehousesRepository.insert(warehous);
    }

    @Override
    public Warehouses deleteWarehous(Warehouses warehous) {
        if (warehousesRepository.exists(warehous.getId())) {
            warehousesRepository.delete(warehous);
            return warehous;
        } else {
            return null;
        }
    }

    @Override
    public Warehouses updateWarehous(Warehouses warehous) {
        if (warehousesRepository.exists(warehous.getId())) {
            warehousesRepository.save(warehous);
            return warehous;
        } else {
            return null;
        }
    }
}
