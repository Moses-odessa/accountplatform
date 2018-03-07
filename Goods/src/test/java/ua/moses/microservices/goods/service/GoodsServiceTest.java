package ua.moses.microservices.goods.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.moses.microservices.goods.model.Good;
import ua.moses.microservices.goods.repository.GoodsRepository;
import ua.moses.microservices.goods.service.impl.GoodsServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class GoodsServiceTest {

    private GoodsService goodsService;

    @MockBean
    private GoodsRepository goodsRepository;

    @Before
    public void init() {
        goodsService = new GoodsServiceImpl(goodsRepository);
    }

    @Test
    public void getAllGoodsTest() {
        String ownerId = "owner";
        List<Good> expectedList = Arrays.asList(new Good(ownerId,"good1"), new Good(ownerId, "good2"));
        when(goodsRepository.getAllGoods(ownerId)).thenReturn(expectedList);
        List<Good> result = goodsService.getAllGoods(ownerId);
        assertEquals(expectedList, result);
    }

    @Test
    public void getGoodByIdTest() {
        String goodId = "5a981eaba3e33c120c2c67bf";
        String ownerId = "owner";
        Good expected = new Good(goodId, ownerId, "good1", false, "1", 100);
        when(goodsRepository.findByOwnerIdAndId(ownerId, goodId)).thenReturn(expected);
        Good result = goodsService.getGoodById(ownerId, goodId);
        assertEquals(expected, result);
    }

    @Test
    public void insertGoodTest() {
        Good expected = new Good("owner","good1");
        when(goodsRepository.insert(expected)).thenReturn(expected);
        Good result = goodsService.insertGood(expected);
        assertEquals(expected, result);
    }


    @Test
    public void deleteGoodTest() {
        Good given = new Good("owner","good1");
        Good expected = new Good("owner","good1");
        expected.setDeleted(true);
        when(goodsRepository.exists(given.getId())).thenReturn(true);
        Good result = goodsService.deleteGood(given);
        assertEquals(expected, result);
    }

    @Test
    public void updateGoodTest() {
        Good expected = new Good("owner","good1");
        expected.setDeleted(true);
        when(goodsRepository.exists(expected.getId())).thenReturn(true);
        when(goodsRepository.save(expected)).thenReturn(expected);
        Good result = goodsService.updateGood(expected);
        assertEquals(expected, result);
    }

}
