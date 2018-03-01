package ua.moses.microservices.warehouses.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.moses.microservices.warehouses.model.Warehouse;
import ua.moses.microservices.warehouses.repository.WarehousesRepository;
import ua.moses.microservices.warehouses.service.impl.WarehousesServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class WarehousesServiceTest {

    private WarehousesService warehousesService;

    @MockBean
    private WarehousesRepository warehousesRepository;

    @Before
    public void init() {
        warehousesService = new WarehousesServiceImpl(warehousesRepository);
    }

    @Test
    public void getAllWarehousesTest() {
        String ownerId = "owner";
        List<Warehouse> expectedList = Arrays.asList(new Warehouse(ownerId,"stock1"), new Warehouse(ownerId, "stock2"));
        when(warehousesRepository.findByOwnerId(ownerId)).thenReturn(expectedList);
        List<Warehouse> result = warehousesService.getAllWarehouses(ownerId);
        assertEquals(expectedList, result);
    }

    @Test
    public void insertWarehousTest() {
        Warehouse expected = new Warehouse("owner","stock1");
        when(warehousesRepository.insert(expected)).thenReturn(expected);
        Warehouse result = warehousesService.insertWarehous(expected);
        assertEquals(expected, result);
    }

}
