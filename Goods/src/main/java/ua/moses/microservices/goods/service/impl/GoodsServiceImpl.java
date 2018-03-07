package ua.moses.microservices.goods.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ua.moses.microservices.goods.model.Good;
import ua.moses.microservices.goods.repository.GoodsRepository;
import ua.moses.microservices.goods.service.GoodsService;

import javax.inject.Inject;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class GoodsServiceImpl implements GoodsService {
    @Inject
    private GoodsRepository goodsRepository;

    @Override
    public List<Good> getAllGoods(String ownerId) {
        return goodsRepository.getAllGoods(ownerId);
    }

    @Override
    public Good insertGood(Good good) {
        return goodsRepository.insert(good);
    }

    @Override
    public Good deleteGood(Good good) {
        if (goodsRepository.exists(good.getId())) {
            good.setDeleted(true);
            goodsRepository.save(good);
            return good;
        } else {
            return null;
        }
    }

    @Override
    public Good updateGood(Good good) {
        if (goodsRepository.exists(good.getId())) {
            return goodsRepository.save(good);
        } else {
            return null;
        }
    }

    @Override
    public Good getGoodById(String ownerId, String goodId) {
        return goodsRepository.findByOwnerIdAndId(ownerId, goodId);
    }
}
