package com.tconecta.loyalty.api.service;

import com.tconecta.loyalty.api.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuysBussinesManagerImpl implements BuysBussinesManager {

    @Autowired
    private PointService pointService;

    @Autowired
    private ShoppingService shoppingService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private RewardService rewardService;

    private static final Integer TRANSACTION_TYPE_SHOP = 1;
    private static final Integer TRANSACTION_TYPE_REWARD = 2;

    @Override
    @Transactional
    public BuyResponse buysProducts(BuyRequest buyRequest) {

        ProductResponse productResponse = productService.getProductById(buyRequest.getProductId());
        BuyResponse buyResponse = shoppingService.shopping(buyRequest, TRANSACTION_TYPE_SHOP);
        pointService.saveUpdateUserPoint(buyRequest.getUserId(), productResponse.getPoints());
        return buyResponse;
    }

    @Override
    public BuyResponse redeemReward(BuyRequest buyRequest) {
        BuyResponse buyResponse = null;
        RewardResponse rewardResponse = rewardService.getByRewardId(buyRequest.getProductId());
        PointModel pointModel = pointService.getPointByUser(buyRequest.getUserId());

        if (pointModel.getAmount() >= rewardResponse.getPoints()) {
            buyResponse = shoppingService.shopping(buyRequest, TRANSACTION_TYPE_REWARD);
            pointModel.setAmount(pointModel.getAmount() - rewardResponse.getPoints());
            pointService.updatePoint(pointModel, buyRequest.getUserId());
        }

        return buyResponse;
    }

}
