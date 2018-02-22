package ua.moses.microservices.warehouses.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.javacrumbs.jsonunit.core.Option;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.moses.microservices.warehouses.model.Warehouses;
import ua.moses.microservices.warehouses.service.WarehousesService;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(WarehosesController.class)
public class WarehousesControllerTest {
    @Value("${warehouses.endpoint.url}")
    private String warehousesEndpointUrl;

    @MockBean
    private WarehousesService warehousesService;

    @Inject
    private MockMvc mockMvc;

    public static String convertToJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }


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

    @Test
    public void insertWarehousTest() throws Exception {
        Warehouses expected = new Warehouses("1", "stock1");
        when(warehousesService.insertWarehous(any(Warehouses.class))).thenReturn(expected);

        mockMvc.perform(post(warehousesEndpointUrl)
                .content(convertToJsonString(expected))
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));

        verify(warehousesService).insertWarehous(any(Warehouses.class));
        verifyNoMoreInteractions(warehousesService);
    }

    @Test
    public void deleteWarehousTest() throws Exception {
        Warehouses expected = new Warehouses("1", "stock1");
        when(warehousesService.deleteWarehous(any(Warehouses.class))).thenReturn(expected);

        mockMvc.perform(delete(warehousesEndpointUrl)
                .contentType(APPLICATION_JSON_UTF8)
                .content(convertToJsonString(expected)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));

        verify(warehousesService).deleteWarehous(any(Warehouses.class));
        verifyNoMoreInteractions(warehousesService);
    }

    @Test
    public void updateWarehousTest() throws Exception {
        Warehouses expected = new Warehouses("1", "stock1");
        when(warehousesService.updateWarehous(any(Warehouses.class))).thenReturn(expected);

        mockMvc.perform(put(warehousesEndpointUrl)
                .contentType(APPLICATION_JSON_UTF8)
                .content(convertToJsonString(expected)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));

        verify(warehousesService).updateWarehous(any(Warehouses.class));
        verifyNoMoreInteractions(warehousesService);
    }

}
