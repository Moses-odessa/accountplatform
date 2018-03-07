package ua.moses.microservices.goods.service;

import ua.moses.microservices.goods.model.Good;

import java.util.List;

public interface GoodsService {

    List<Good> getAllGoods(String ownerId);

    Good insertGood(Good good);

    Good deleteGood(Good good);

    Good updateGood(Good good);

    Good getGoodById(String ownerId, String goodId);
}
