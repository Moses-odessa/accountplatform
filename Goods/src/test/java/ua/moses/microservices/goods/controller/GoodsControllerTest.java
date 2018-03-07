package ua.moses.microservices.goods.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.javacrumbs.jsonunit.core.Option;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.moses.microservices.goods.model.Good;
import ua.moses.microservices.goods.service.GoodsService;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(GoodsController.class)
public class GoodsControllerTest {
    @Value("${goods.endpoint.url}")
    private String goodsEndpointUrl;

    @MockBean
    private GoodsService goodsService;

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

    @Test
    public void getAllGoodsTest() throws Exception {
        String ownerId = "owner";
        List<Good> expectedList = Arrays.asList(new Good(ownerId, "stock1"), new Good(ownerId, "stock2"));
        when(goodsService.getAllGoods(ownerId)).thenReturn(expectedList);

        String result = mockMvc.perform(get(goodsEndpointUrl + "/" + ownerId)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThatJson(result).when(Option.IGNORING_ARRAY_ORDER).isEqualTo(expectedList);
        verify(goodsService).getAllGoods(ownerId);
        verifyNoMoreInteractions(goodsService);

    }

    @Test
    public void getGoodByIdTest() throws Exception {
        String goodId = "5a981eaba3e33c120c2c67bf";
        String ownerId = "owner";
        Good expected = new Good(goodId, ownerId, "good1", false, "", 100);
        when(goodsService.getGoodById(ownerId, goodId)).thenReturn(expected);

        mockMvc.perform(get(goodsEndpointUrl + "/" + ownerId + "/" + goodId)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));
        verify(goodsService).getGoodById(ownerId, goodId);
        verifyNoMoreInteractions(goodsService);

    }

    @Test
    public void insertGoodTest() throws Exception {
        Good expected = new Good("owner", "good1");
        when(goodsService.insertGood(any(Good.class))).thenReturn(expected);

        mockMvc.perform(post(goodsEndpointUrl)
                .content(convertToJsonString(expected))
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));

        verify(goodsService).insertGood(any(Good.class));
        verifyNoMoreInteractions(goodsService);
    }

    @Test
    public void deleteGoodTest() throws Exception {
        Good expected = new Good("owner", "good1");
        when(goodsService.deleteGood(any(Good.class))).thenReturn(expected);

        mockMvc.perform(delete(goodsEndpointUrl)
                .contentType(APPLICATION_JSON_UTF8)
                .content(convertToJsonString(expected)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));

        verify(goodsService).deleteGood(any(Good.class));
        verifyNoMoreInteractions(goodsService);
    }

    @Test
    public void updateGoodTest() throws Exception {
        Good expected = new Good("owner", "good1");
        when(goodsService.updateGood(any(Good.class))).thenReturn(expected);

        mockMvc.perform(put(goodsEndpointUrl)
                .contentType(APPLICATION_JSON_UTF8)
                .content(convertToJsonString(expected)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));

        verify(goodsService).updateGood(any(Good.class));
        verifyNoMoreInteractions(goodsService);
    }

}
