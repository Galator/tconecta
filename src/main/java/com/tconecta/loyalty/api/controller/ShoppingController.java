package com.tconecta.loyalty.api.controller;

import com.tconecta.loyalty.api.model.BuyRequest;
import com.tconecta.loyalty.api.model.BuyResponse;
import com.tconecta.loyalty.api.service.BuysBussinesManager;
import com.tconecta.loyalty.api.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/shopping")
public class ShoppingController {

    @Autowired
    private BuysBussinesManager buysBussinesManager;

    @Autowired
    private ShoppingService shoppingService;

    private static final Integer TRANSACTION_TYPE_SHOP = 1;
    private static final Integer TRANSACTION_TYPE_REWARD = 2;

    @PostMapping
    public ResponseEntity<Object> buysUser (@RequestBody @Valid BuyRequest buyRequest) {
        BuyResponse buyResponse = buysBussinesManager.buysProducts(buyRequest);
        return new ResponseEntity<>(buyResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAll () {
        HttpStatus httpStatus = HttpStatus.OK;
        List<BuyResponse> buyResponseList = shoppingService.getShoppingAll(TRANSACTION_TYPE_SHOP);
        if (buyResponseList.isEmpty()) {
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(buyResponseList, httpStatus);
    }

    @GetMapping(value = "/user/{user}")
    public ResponseEntity<Object> getBuyByUserId (@PathVariable Long user) {
        HttpStatus httpStatus = HttpStatus.OK;
        List<BuyResponse> buyResponseList = shoppingService.getShoppingByUserId(user);
        if (buyResponseList.isEmpty()) {
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(buyResponseList, httpStatus);
    }

    @PostMapping(value = "/reward")
    public ResponseEntity<Object> rewardRedeem (@RequestBody @Valid BuyRequest buyRequest) {
        BuyResponse buyResponse = buysBussinesManager.redeemReward(buyRequest);
        return new ResponseEntity<>(buyResponse, HttpStatus.CREATED);
    }

    @GetMapping (value = "/reward")
    public ResponseEntity<Object> getRewardAll () {
        HttpStatus httpStatus = HttpStatus.OK;
        List<BuyResponse> buyResponseList = shoppingService.getShoppingAll(TRANSACTION_TYPE_REWARD);
        if (buyResponseList.isEmpty()) {
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(buyResponseList, httpStatus);
    }

}
