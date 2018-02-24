package ua.moses.microservices.warehouses.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.moses.microservices.warehouses.model.Warehouses;
import ua.moses.microservices.warehouses.repository.WarehousesRepository;
import ua.moses.microservices.warehouses.service.impl.WarehousesServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
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
        List<Warehouses> expectedList = Arrays.asList(new Warehouses("stock1"), new Warehouses("stock2"));
        when(warehousesRepository.findAll()).thenReturn(expectedList);
        List<Warehouses> result = warehousesService.getAllWarehouses();
        assertEquals(result, expectedList);
    }

}
