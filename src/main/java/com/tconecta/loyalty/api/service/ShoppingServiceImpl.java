package com.tconecta.loyalty.api.service;

import com.tconecta.loyalty.api.entity.ShoppingEntity;
import com.tconecta.loyalty.api.entity.UserEntity;
import com.tconecta.loyalty.api.model.BuyRequest;
import com.tconecta.loyalty.api.model.BuyResponse;
import com.tconecta.loyalty.api.repository.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private ShoppingRepository shoppingRepository;

    @Autowired
    private PointService pointService;

    @Override
    public BuyResponse shopping(BuyRequest buyRequest, Integer transactionType) {

        ShoppingEntity shoppingEntity = shoppingRepository.save(converterRequestToEntity(buyRequest, transactionType));

        return converterEntityToResponse(shoppingEntity);
    }

    @Override
    public List<BuyResponse> getShoppingByUserId(Long userId) {
        List<BuyResponse> buyResponseList = new ArrayList<>();
        shoppingRepository.getByUserId(UserEntity.builder().userId(userId).build()).forEach(shoppingEntity ->
                buyResponseList.add(converterEntityToResponse(shoppingEntity)));
        return buyResponseList;
    }

    @Override
    public List<BuyResponse> getShoppingAll(Integer transactionType) {
        List<BuyResponse> buyResponseList = new ArrayList<>();
        shoppingRepository.findAll().forEach(shoppingEntity -> {
            if (transactionType.equals(shoppingEntity.getTransactionType())){
                buyResponseList.add(converterEntityToResponse(shoppingEntity));
            }
        });
        return buyResponseList;
    }

    private BuyResponse converterEntityToResponse (ShoppingEntity shoppingEntity) {
        return BuyResponse.builder()
                .buyId(shoppingEntity.getShoppingId())
                .product(shoppingEntity.getProductId().toString())
                .user(shoppingEntity.getUserId().getUserName() == null ? shoppingEntity.getUserId().getUserId().toString() : shoppingEntity.getUserId().getUserName().trim())
                . build();
    }

    private ShoppingEntity converterRequestToEntity (BuyRequest buyRequest, Integer trnsactionType) {
        return ShoppingEntity.builder()
                .userId(UserEntity.builder().userId(buyRequest.getUserId()).build())
                .productId(buyRequest.getProductId())
                .transactionType(trnsactionType)
                .build();
    }
}
