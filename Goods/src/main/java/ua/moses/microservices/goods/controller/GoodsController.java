package ua.moses.microservices.goods.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.moses.microservices.goods.model.Good;
import ua.moses.microservices.goods.service.GoodsService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "${goods.endpoint.url}", produces = "application/json;charset=UTF-8")
public class GoodsController {
    @Inject
    private GoodsService goodsService;

    @GetMapping(value = "{ownerId}")
    public ResponseEntity<List<Good>> getAllGoods(@PathVariable String ownerId) {
        List<Good> result = goodsService.getAllGoods(ownerId);
        if (result == null) {
            result = new ArrayList<>(0);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "{ownerId}/{goodId}")
    public ResponseEntity<Good> getGoodById(@PathVariable String ownerId, @PathVariable String goodId) {
        Good result = goodsService.getGoodById(ownerId, goodId);
        return ResponseEntity.ok(result);
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Good> insertGood(@Valid @RequestBody Good good) {
        Good result = goodsService.insertGood(good);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Good> deleteGood(@Valid @RequestBody Good good) {
        Good result = goodsService.deleteGood(good);
        return ResponseEntity.ok(result);
    }

    @PutMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Good> updateGood(@Valid @RequestBody Good good) {
        Good result = goodsService.updateGood(good);
        return ResponseEntity.ok(result);
    }

}
