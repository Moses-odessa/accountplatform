package ua.moses.microservices.warehouses.controller;

import net.javacrumbs.jsonunit.core.Option;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.moses.microservices.warehouses.model.Warehouses;
import ua.moses.microservices.warehouses.service.WarehousesService;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WarehousesControllerTest {
    @Value("${warehouses.endpoint.url}")
    private String warehousesEndpointUrl;

    @MockBean
    private WarehousesService warehousesService;

    @Inject
    private MockMvc mockMvc;


    @Test
    public void getAllWarehousesTest() throws Exception {
        List<Warehouses> expectedList = Arrays.asList(new Warehouses("stock1"), new Warehouses("stock2"));
        when(warehousesService.getAllWarehouses()).thenReturn(expectedList);

        String result = mockMvc.perform(get(warehousesEndpointUrl)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThatJson(result).when(Option.IGNORING_ARRAY_ORDER).isEqualTo(expectedList);
        verify(warehousesService).getAllWarehouses();
        verifyNoMoreInteractions(warehousesService);

    }

}
